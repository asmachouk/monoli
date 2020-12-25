package tn.asma.isi.service;

import tn.asma.isi.service.dto.FormationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link tn.asma.isi.domain.Formation}.
 */
public interface FormationService {

    /**
     * Save a formation.
     *
     * @param formationDTO the entity to save.
     * @return the persisted entity.
     */
    FormationDTO save(FormationDTO formationDTO);

    /**
     * Get all the formations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FormationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" formation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormationDTO> findOne(Long id);

    /**
     * Delete the "id" formation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
