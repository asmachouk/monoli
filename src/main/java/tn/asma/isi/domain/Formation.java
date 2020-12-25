package tn.asma.isi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Formation.
 */
@Entity
@Table(name = "formation")
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "nombre_participant", nullable = false)
    private Integer nombre_participant;

    @ManyToMany(mappedBy = "formations")
    @JsonIgnore
    private Set<Employe> employes = new HashSet<>();

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

    public Formation nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public Formation date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNombre_participant() {
        return nombre_participant;
    }

    public Formation nombre_participant(Integer nombre_participant) {
        this.nombre_participant = nombre_participant;
        return this;
    }

    public void setNombre_participant(Integer nombre_participant) {
        this.nombre_participant = nombre_participant;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public Formation employes(Set<Employe> employes) {
        this.employes = employes;
        return this;
    }

    public Formation addEmploye(Employe employe) {
        this.employes.add(employe);
        employe.getFormations().add(this);
        return this;
    }

    public Formation removeEmploye(Employe employe) {
        this.employes.remove(employe);
        employe.getFormations().remove(this);
        return this;
    }

    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Formation)) {
            return false;
        }
        return id != null && id.equals(((Formation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Formation{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", date='" + getDate() + "'" +
            ", nombre_participant=" + getNombre_participant() +
            "}";
    }
}
