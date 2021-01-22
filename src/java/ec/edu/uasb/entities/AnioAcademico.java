/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "CICLO_ACADEMICO")
public class AnioAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private Long anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE_CICLO")
    private String nombreCiclo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_CICLO")
    private String estadoCiclo;
    @Column(name = "F_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fInicio;
    @Column(name = "F_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fFinal;
    @Basic(optional = false)
    @Column(name = "COD_EJERCICIO")
    private Long codEjercicio;

    public AnioAcademico() {
    }

    public AnioAcademico(Long anio, String nombreCiclo, String estadoCiclo, Date fInicio, Date fFinal, Long codEjercicio) {
        this.anio = anio;
        this.nombreCiclo = nombreCiclo;
        this.estadoCiclo = estadoCiclo;
        this.fInicio = fInicio;
        this.fFinal = fFinal;
        this.codEjercicio = codEjercicio;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public String getNombreCiclo() {
        return nombreCiclo;
    }

    public void setNombreCiclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }

    public String getEstadoCiclo() {
        return estadoCiclo;
    }

    public void setEstadoCiclo(String estadoCiclo) {
        this.estadoCiclo = estadoCiclo;
    }

    public Date getfInicio() {
        return fInicio;
    }

    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }

    public Date getfFinal() {
        return fFinal;
    }

    public void setfFinal(Date fFinal) {
        this.fFinal = fFinal;
    }

    public Long getCodEjercicio() {
        return codEjercicio;
    }

    public void setCodEjercicio(Long codEjercicio) {
        this.codEjercicio = codEjercicio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.anio != null ? this.anio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnioAcademico other = (AnioAcademico) obj;
        if (this.anio != other.anio && (this.anio == null || !this.anio.equals(other.anio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnioAcademico{" + "anio=" + anio + ", nombreCiclo=" + nombreCiclo + ", estadoCiclo=" + estadoCiclo + ", fInicio=" + fInicio + ", fFinal=" + fFinal + ", codEjercicio=" + codEjercicio + '}';
    }

}
