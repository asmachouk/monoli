package tn.asma.isi.service.impl;

import tn.asma.isi.service.PosteService;
import tn.asma.isi.domain.Poste;
import tn.asma.isi.repository.PosteRepository;
import tn.asma.isi.service.dto.PosteDTO;
import tn.asma.isi.service.mapper.PosteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Poste}.
 */
@Service
@Transactional
public class PosteServiceImpl implements PosteService {

    private final Logger log = LoggerFactory.getLogger(PosteServiceImpl.class);

    private final PosteRepository posteRepository;

    private final PosteMapper posteMapper;

    public PosteServiceImpl(PosteRepository posteRepository, PosteMapper posteMapper) {
        this.posteRepository = posteRepository;
        this.posteMapper = posteMapper;
    }

    @Override
    public PosteDTO save(PosteDTO posteDTO) {
        log.debug("Request to save Poste : {}", posteDTO);
        Poste poste = posteMapper.toEntity(posteDTO);
        poste = posteRepository.save(poste);
        return posteMapper.toDto(poste);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PosteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Postes");
        return posteRepository.findAll(pageable)
            .map(posteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PosteDTO> findOne(Long id) {
        log.debug("Request to get Poste : {}", id);
        return posteRepository.findById(id)
            .map(posteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Poste : {}", id);
        posteRepository.deleteById(id);
    }
}
