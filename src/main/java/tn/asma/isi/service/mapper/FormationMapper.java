package tn.asma.isi.service.mapper;


import tn.asma.isi.domain.*;
import tn.asma.isi.service.dto.FormationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Formation} and its DTO {@link FormationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FormationMapper extends EntityMapper<FormationDTO, Formation> {


    @Mapping(target = "employes", ignore = true)
    @Mapping(target = "removeEmploye", ignore = true)
    Formation toEntity(FormationDTO formationDTO);

    default Formation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Formation formation = new Formation();
        formation.setId(id);
        return formation;
    }
}
