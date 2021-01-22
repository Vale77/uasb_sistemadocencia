/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class AsignaturaSyllabusPK implements Serializable {

    @Column(name = "CODIGO_PROFESOR")
    private Long codigoProfesor;
    @Column(name = "CODIGO_MATERIA")
    private Long codigoMateria;
    @Column(name = "CODIGO_NIVEL")
    private Long codigoNivel;
    @Column(name = "VAC_CODNUM")
    private Long vacCodnum;
    @Column(name = "COD_PARALELO")
    private Long codParalelo;
    @Column(name = "CODIGO_ESP")
    private Long codPrograma;

    public AsignaturaSyllabusPK() {
    }

    public Long getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(Long codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public Long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Long codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public Long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public Long getCodParalelo() {
        return codParalelo;
    }

    public void setCodParalelo(Long codParalelo) {
        this.codParalelo = codParalelo;
    }

    public Long getVacCodnum() {
        return vacCodnum;
    }

    public void setVacCodnum(Long vacCodnum) {
        this.vacCodnum = vacCodnum;
    }

    public Long getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(Long codPrograma) {
        this.codPrograma = codPrograma;
    }

    public AsignaturaSyllabusPK(Long codigoProfesor, Long codigoMateria, Long codigoNivel, Long vacCodnum, Long codParalelo, Long codPrograma) {
        this.codigoProfesor = codigoProfesor;
        this.codigoMateria = codigoMateria;
        this.codigoNivel = codigoNivel;
        this.vacCodnum = vacCodnum;
        this.codParalelo = codParalelo;
        this.codPrograma = codPrograma;
    }

    @Override
    public String toString() {
        return codigoProfesor + "-" + codigoMateria + ":" + codigoNivel + ";" + vacCodnum + "=" + codParalelo + "." + codPrograma;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.codigoProfesor != null ? this.codigoProfesor.hashCode() : 0);
        hash = 19 * hash + (this.codigoMateria != null ? this.codigoMateria.hashCode() : 0);
        hash = 19 * hash + (this.codigoNivel != null ? this.codigoNivel.hashCode() : 0);
        hash = 19 * hash + (this.vacCodnum != null ? this.vacCodnum.hashCode() : 0);
        hash = 19 * hash + (this.codParalelo != null ? this.codParalelo.hashCode() : 0);
        hash = 19 * hash + (this.codPrograma != null ? this.codPrograma.hashCode() : 0);
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
        final AsignaturaSyllabusPK other = (AsignaturaSyllabusPK) obj;
        if (this.codigoProfesor != other.codigoProfesor && (this.codigoProfesor == null || !this.codigoProfesor.equals(other.codigoProfesor))) {
            return false;
        }
        if (this.codigoMateria != other.codigoMateria && (this.codigoMateria == null || !this.codigoMateria.equals(other.codigoMateria))) {
            return false;
        }
        if (this.codigoNivel != other.codigoNivel && (this.codigoNivel == null || !this.codigoNivel.equals(other.codigoNivel))) {
            return false;
        }
        if (this.vacCodnum != other.vacCodnum && (this.vacCodnum == null || !this.vacCodnum.equals(other.vacCodnum))) {
            return false;
        }
        if (this.codParalelo != other.codParalelo && (this.codParalelo == null || !this.codParalelo.equals(other.codParalelo))) {
            return false;
        }
        if (this.codPrograma != other.codPrograma && (this.codPrograma == null || !this.codPrograma.equals(other.codPrograma))) {
            return false;
        }
        return true;
    }

}
