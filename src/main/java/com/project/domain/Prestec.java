package com.project.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "prestecs")
public class Prestec implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prestecId", unique=true, nullable=false)
    private Long prestecId;

    @ManyToOne
    @JoinColumn(name = "exemplarId")
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "personaId")
    private Persona persona;

    @Column(name = "dataPrestec", nullable = false, length = 50)
    private LocalDate dataPrestec;

    @Column(name = "dataRetornPrevista", nullable = false, length = 50)
    private LocalDate dataRetornPrevista;

    @Column(name = "dataRetornReal",nullable = true, length = 50)
    private LocalDate dataRetornReal;

    @Column(name = "actiu", nullable = false, length = 10)
    private boolean actiu;

    public Prestec() {
    }

    public Prestec(Exemplar exemplar, Persona persona, LocalDate dataPrestec, LocalDate dataRetornPrevista) {
        this.exemplar = exemplar;
        this.persona = persona;
        this.dataPrestec = dataPrestec;
        this.dataRetornPrevista = dataRetornPrevista;
        this.actiu = true;
    }

    public Prestec(Exemplar exemplar, Persona persona, LocalDate dataPrestec, LocalDate dataRetornPrevista, LocalDate dataRetornReal) {
        this.exemplar = exemplar;
        this.persona = persona;
        this.dataPrestec = dataPrestec;
        this.dataRetornPrevista = dataRetornPrevista;
        this.dataRetornReal = dataRetornReal;
    }

    public Long getPrestecId() {
        return prestecId;
    }

    public void setPrestecId(Long prestecId) {
        this.prestecId = prestecId;
    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LocalDate getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(LocalDate dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public LocalDate getDataRetornPrevista() {
        return dataRetornPrevista;
    }

    public void setDataRetornPrevista(LocalDate dataRetornPrevista) {
        this.dataRetornPrevista = dataRetornPrevista;
    }

    public LocalDate getDataRetornReal() {
        return dataRetornReal;
    }

    public void setDataRetornReal(LocalDate dataRetornReal) {
        this.dataRetornReal = dataRetornReal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Prestec[id=%d", prestecId));
        
        if (exemplar != null) {
            sb.append(String.format(", exemplar='%s'", exemplar.getCodiBarres()));
        }
        
        if (persona != null) {
            sb.append(String.format(", persona='%s'", persona.getNom()));
        }
        
        sb.append(String.format(", dataPrestec='%s'", dataPrestec));
        sb.append(String.format(", dataRetornPrevista='%s'", dataRetornPrevista));
        
        if (dataRetornReal != null) {
            sb.append(String.format(", dataRetornReal='%s'", dataRetornReal));
        }
        
        sb.append(String.format(", actiu=%s", actiu));
        
        if (estaRetardat()) {
            sb.append(String.format(", diesRetard=%d", getDiesRetard()));
        }
        
        sb.append("]");
        return sb.toString();
    }

    private long getDiesRetard() {
        if (dataRetornReal == null){
            return DAYS.between(dataRetornPrevista,LocalDate.now());
        }else {
            return DAYS.between(dataRetornPrevista,dataRetornReal);
        }

    }

    boolean estaRetardat() {
        if (dataRetornReal == null){
            return true;
        } else if (dataRetornPrevista.isBefore(dataRetornReal)){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestec prestec = (Prestec) o;
        return prestecId == prestec.prestecId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(prestecId);
    }

}