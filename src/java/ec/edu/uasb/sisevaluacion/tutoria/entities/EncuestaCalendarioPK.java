/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author johana.orozco
 */
@Embeddable
public class EncuestaCalendarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private Long anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CICLO")
    private Long ciclo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MATERIA")
    private Long codigoMateria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENCUESTA")
    private Long codigoEncuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROFESOR")
    private Long codigoProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESP")
    private Long codigoEsp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ESTUDIANTE")
    private Long codEstudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_NIVEL")
    private Long codigoNivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PARALELO")
    private Long codigoParalelo;

    public EncuestaCalendarioPK() {
    }

    public EncuestaCalendarioPK(Long anio, Long ciclo, Long codigoMateria, Long codigoEncuesta, Long codigoProfesor,
            Long codigoEsp, Long codEstudiante, Long codigoNivel, Long codigoParalelo) {
        this.anio = anio;
        this.ciclo = ciclo;
        this.codigoMateria = codigoMateria;
        this.codigoEncuesta = codigoEncuesta;
        this.codigoProfesor = codigoProfesor;
        this.codigoEsp = codigoEsp;
        this.codEstudiante = codEstudiante;
        this.codigoNivel = codigoNivel;
        this.codigoParalelo = codigoParalelo;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public Long getCiclo() {
        return ciclo;
    }

    public void setCiclo(Long ciclo) {
        this.ciclo = ciclo;
    }

    public Long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Long codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public Long getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(Long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public Long getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(Long codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public Long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(Long codigoEsp) {
        this.codigoEsp = codigoEsp;
    }

    public Long getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(Long codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public Long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public Long getCodigoParalelo() {
        return codigoParalelo;
    }

    public void setCodigoParalelo(Long codigoParalelo) {
        this.codigoParalelo = codigoParalelo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.anio != null ? this.anio.hashCode() : 0);
        hash = 47 * hash + (this.ciclo != null ? this.ciclo.hashCode() : 0);
        hash = 47 * hash + (this.codigoMateria != null ? this.codigoMateria.hashCode() : 0);
        hash = 47 * hash + (this.codigoEncuesta != null ? this.codigoEncuesta.hashCode() : 0);
        hash = 47 * hash + (this.codigoProfesor != null ? this.codigoProfesor.hashCode() : 0);
        hash = 47 * hash + (this.codigoEsp != null ? this.codigoEsp.hashCode() : 0);
        hash = 47 * hash + (this.codEstudiante != null ? this.codEstudiante.hashCode() : 0);
        hash = 47 * hash + (this.codigoNivel != null ? this.codigoNivel.hashCode() : 0);
        hash = 47 * hash + (this.codigoParalelo != null ? this.codigoParalelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EncuestaCalendarioPK other = (EncuestaCalendarioPK) obj;
        if (this.anio != other.anio && (this.anio == null || !this.anio.equals(other.anio))) {
            return false;
        }
        if (this.ciclo != other.ciclo && (this.ciclo == null || !this.ciclo.equals(other.ciclo))) {
            return false;
        }
        if (this.codigoMateria != other.codigoMateria && (this.codigoMateria == null || !this.codigoMateria.equals(other.codigoMateria))) {
            return false;
        }
        if (this.codigoEncuesta != other.codigoEncuesta && (this.codigoEncuesta == null || !this.codigoEncuesta.equals(other.codigoEncuesta))) {
            return false;
        }
        if (this.codigoProfesor != other.codigoProfesor && (this.codigoProfesor == null || !this.codigoProfesor.equals(other.codigoProfesor))) {
            return false;
        }
        if (this.codigoEsp != other.codigoEsp && (this.codigoEsp == null || !this.codigoEsp.equals(other.codigoEsp))) {
            return false;
        }
        if (this.codEstudiante != other.codEstudiante && (this.codEstudiante == null || !this.codEstudiante.equals(other.codEstudiante))) {
            return false;
        }
        if (this.codigoNivel != other.codigoNivel && (this.codigoNivel == null || !this.codigoNivel.equals(other.codigoNivel))) {
            return false;
        }
        if (this.codigoParalelo != other.codigoParalelo && (this.codigoParalelo == null || !this.codigoParalelo.equals(other.codigoParalelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaCalendarioPK{" + "anio=" + anio + ", ciclo=" + ciclo + ", codigoMateria=" + codigoMateria + ", codigoEncuesta=" + codigoEncuesta + ", codigoProfesor=" + codigoProfesor + ", codigoEsp=" + codigoEsp + ", codEstudiante=" + codEstudiante + ", codigoNivel=" + codigoNivel + ", codigoParalelo=" + codigoParalelo + '}';
    }

}
