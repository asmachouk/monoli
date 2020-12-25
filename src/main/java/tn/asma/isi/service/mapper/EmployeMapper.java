package tn.asma.isi.service.mapper;


import tn.asma.isi.domain.*;
import tn.asma.isi.service.dto.EmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employe} and its DTO {@link EmployeDTO}.
 */
@Mapper(componentModel = "spring", uses = {FormationMapper.class})
public interface EmployeMapper extends EntityMapper<EmployeDTO, Employe> {


    @Mapping(target = "postes", ignore = true)
    @Mapping(target = "removePoste", ignore = true)
    @Mapping(target = "removeFormation", ignore = true)
    Employe toEntity(EmployeDTO employeDTO);

    default Employe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employe employe = new Employe();
        employe.setId(id);
        return employe;
    }
}
