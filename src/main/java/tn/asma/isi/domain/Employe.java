package tn.asma.isi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Employe.
 */
@Entity
@Table(name = "employe")
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @OneToMany(mappedBy = "employe")
    private Set<Poste> postes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "employe_formation",
               joinColumns = @JoinColumn(name = "employe_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "formation_id", referencedColumnName = "id"))
    private Set<Formation> formations = new HashSet<>();

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

    public Employe nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Employe prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Employe adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<Poste> getPostes() {
        return postes;
    }

    public Employe postes(Set<Poste> postes) {
        this.postes = postes;
        return this;
    }

    public Employe addPoste(Poste poste) {
        this.postes.add(poste);
        poste.setEmploye(this);
        return this;
    }

    public Employe removePoste(Poste poste) {
        this.postes.remove(poste);
        poste.setEmploye(null);
        return this;
    }

    public void setPostes(Set<Poste> postes) {
        this.postes = postes;
    }

    public Set<Formation> getFormations() {
        return formations;
    }

    public Employe formations(Set<Formation> formations) {
        this.formations = formations;
        return this;
    }

    public Employe addFormation(Formation formation) {
        this.formations.add(formation);
        formation.getEmployes().add(this);
        return this;
    }

    public Employe removeFormation(Formation formation) {
        this.formations.remove(formation);
        formation.getEmployes().remove(this);
        return this;
    }

    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employe)) {
            return false;
        }
        return id != null && id.equals(((Employe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employe{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
}
