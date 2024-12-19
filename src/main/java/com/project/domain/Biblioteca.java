package com.project.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "biblioteques")
public class Biblioteca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bibliotecaId",unique = true,nullable = false)
    private long bibliotecaId;

    @Column(name = "nom",nullable = false,length = 50)
    private String nom;

    @Column(name = "ciutat",nullable = false,length = 50)
    private String ciutat;

    @Column(name = "adreca",nullable = false,length = 255)
    private String adreca;

    @Column(name = "telefon",nullable = false,length = 50)
    private String telefon;

    @Column(name = "email",nullable = false,length = 50)
    private String email;

    @OneToMany(mappedBy = "biblioteca",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Exemplar> exemplars = new HashSet<>();

    public Biblioteca() {
    }

    public Biblioteca(String nom, String ciutat, String adreca, String telefon, String email) {
        this.nom = nom;
        this.ciutat = ciutat;
        this.adreca = adreca;
        this.telefon = telefon;
        this.email = email;
        this.exemplars = new HashSet<>();
    }

    public Biblioteca(String nom, Set<Exemplar> exemplars, String email, String telefon, String adreca, String ciutat) {
        this.nom = nom;
        this.exemplars = exemplars;
        this.email = email;
        this.telefon = telefon;
        this.adreca = adreca;
        this.ciutat = ciutat;
    }

    public Set<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(Set<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }

    public long getBibliotecaId() {
        return bibliotecaId;
    }

    public void setBibliotecaId(long bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Biblioteca[id=%d, nom='%s', ciutat='%s'", 
            bibliotecaId, nom, ciutat));
        
        if (adreca != null) {
            sb.append(String.format(", adreca='%s'", adreca));
        }
        if (telefon != null) {
            sb.append(String.format(", tel='%s'", telefon));
        }
        if (email != null) {
            sb.append(String.format(", email='%s'", email));
        }
        
        if (!exemplars.isEmpty()) {
            sb.append(", exemplars={");
            boolean first = true;
            for (Exemplar e : exemplars) {
                if (!first) sb.append(", ");
                sb.append(e.getCodiBarres());
                first = false;
            }
            sb.append("}");
        }
        
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biblioteca that = (Biblioteca) o;
        return bibliotecaId == that.bibliotecaId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(bibliotecaId);
    }

}