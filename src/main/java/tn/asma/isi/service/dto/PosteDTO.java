package tn.asma.isi.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link tn.asma.isi.domain.Poste} entity.
 */
public class PosteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String departement;

    @NotNull
    private Integer salaire;


    private Long employeId;
    
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

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public void setSalaire(Integer salaire) {
        this.salaire = salaire;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PosteDTO)) {
            return false;
        }

        return id != null && id.equals(((PosteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PosteDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", departement='" + getDepartement() + "'" +
            ", salaire=" + getSalaire() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
