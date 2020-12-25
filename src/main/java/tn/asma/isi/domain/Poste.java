package tn.asma.isi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Poste.
 */
@Entity
@Table(name = "poste")
public class Poste implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "departement", nullable = false)
    private String departement;

    @NotNull
    @Column(name = "salaire", nullable = false)
    private Integer salaire;

    @ManyToOne
    @JsonIgnoreProperties(value = "postes", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Poste nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartement() {
        return departement;
    }

    public Poste departement(String departement) {
        this.departement = departement;
        return this;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public Poste salaire(Integer salaire) {
        this.salaire = salaire;
        return this;
    }

    public void setSalaire(Integer salaire) {
        this.salaire = salaire;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Poste employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Poste)) {
            return false;
        }
        return id != null && id.equals(((Poste) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Poste{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", departement='" + getDepartement() + "'" +
            ", salaire=" + getSalaire() +
            "}";
    }
}
