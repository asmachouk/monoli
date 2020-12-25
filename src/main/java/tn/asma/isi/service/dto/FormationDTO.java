package tn.asma.isi.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link tn.asma.isi.domain.Formation} entity.
 */
public class FormationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer nombre_participant;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNombre_participant() {
        return nombre_participant;
    }

    public void setNombre_participant(Integer nombre_participant) {
        this.nombre_participant = nombre_participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormationDTO)) {
            return false;
        }

        return id != null && id.equals(((FormationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormationDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", date='" + getDate() + "'" +
            ", nombre_participant=" + getNombre_participant() +
            "}";
    }
}
