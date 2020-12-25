package tn.asma.isi.service.mapper;


import tn.asma.isi.domain.*;
import tn.asma.isi.service.dto.PosteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Poste} and its DTO {@link PosteDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface PosteMapper extends EntityMapper<PosteDTO, Poste> {

    @Mapping(source = "employe.id", target = "employeId")
    PosteDTO toDto(Poste poste);

    @Mapping(source = "employeId", target = "employe")
    Poste toEntity(PosteDTO posteDTO);

    default Poste fromId(Long id) {
        if (id == null) {
            return null;
        }
        Poste poste = new Poste();
        poste.setId(id);
        return poste;
    }
}
