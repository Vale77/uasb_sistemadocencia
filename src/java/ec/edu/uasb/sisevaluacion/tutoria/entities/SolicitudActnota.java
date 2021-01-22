/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "SOLICITUD_ACTNOTA", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudActnota.findAll", query = "SELECT s FROM SolicitudActnota s"),
    @NamedQuery(name = "SolicitudActnota.findBySolCodigo", query = "SELECT s FROM SolicitudActnota s WHERE s.solCodigo = :solCodigo"),
    @NamedQuery(name = "SolicitudActnota.findByCodigoEstudiante", query = "SELECT s FROM SolicitudActnota s WHERE s.codigoEstudiante = :codigoEstudiante"),
    @NamedQuery(name = "SolicitudActnota.findBySolEstado", query = "SELECT s FROM SolicitudActnota s WHERE s.solEstado = :solEstado"),
    @NamedQuery(name = "SolicitudActnota.findByCodigoMateria", query = "SELECT s FROM SolicitudActnota s WHERE s.codigoMateria = :codigoMateria"),
    @NamedQuery(name = "SolicitudActnota.findByAnio", query = "SELECT s FROM SolicitudActnota s WHERE s.anio = :anio"),
    @NamedQuery(name = "SolicitudActnota.findByCiclo", query = "SELECT s FROM SolicitudActnota s WHERE s.ciclo = :ciclo"),
    @NamedQuery(name = "SolicitudActnota.findByCodigoNivel", query = "SELECT s FROM SolicitudActnota s WHERE s.codigoNivel = :codigoNivel"),
    @NamedQuery(name = "SolicitudActnota.findByFechaCrea", query = "SELECT s FROM SolicitudActnota s WHERE s.fechaCrea = :fechaCrea"),
    @NamedQuery(name = "SolicitudActnota.findByUsuarioCrea", query = "SELECT s FROM SolicitudActnota s WHERE s.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "SolicitudActnota.findByFechaModifica", query = "SELECT s FROM SolicitudActnota s WHERE s.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "SolicitudActnota.findByUsuarioModifica", query = "SELECT s FROM SolicitudActnota s WHERE s.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "SolicitudActnota.findByCodigoEsp", query = "SELECT s FROM SolicitudActnota s WHERE s.codigoEsp = :codigoEsp"),
    @NamedQuery(name = "SolicitudActnota.findByTipoEncuesta", query = "SELECT s FROM SolicitudActnota s WHERE s.tipoEncuesta = :tipoEncuesta")})
public class SolicitudActnota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SOL_CODIGO")
    private Long solCodigo;
    @Column(name = "CODIGO_ESTUDIANTE")
    private Long codigoEstudiante;
    @Column(name = "SOL_ESTADO")
    private Character solEstado;
    @Column(name = "CODIGO_MATERIA")
    private Long codigoMateria;
    @Column(name = "ANIO")
    private Long anio;
    @Column(name = "CICLO")
    private Long ciclo;
    @Column(name = "CODIGO_NIVEL")
    private Long codigoNivel;
    @Column(name = "FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;
    @Size(max = 60)
    @Column(name = "USUARIO_CREA")
    private String usuarioCrea;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
    @Size(max = 60)
    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;
    @Column(name = "CODIGO_ESP")
    private Long codigoEsp;
    @Column(name = "TIPO_ENCUESTA")
    private Character tipoEncuesta;

    public SolicitudActnota() {
    }

    public SolicitudActnota(Long solCodigo) {
        this.solCodigo = solCodigo;
    }

    public Long getSolCodigo() {
        return solCodigo;
    }

    public void setSolCodigo(Long solCodigo) {
        this.solCodigo = solCodigo;
    }

    public Long getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Long codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public Character getSolEstado() {
        return solEstado;
    }

    public void setSolEstado(Character solEstado) {
        this.solEstado = solEstado;
    }

    public Long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Long codigoMateria) {
        this.codigoMateria = codigoMateria;
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

    public Long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(Long codigoEsp) {
        this.codigoEsp = codigoEsp;
    }

    public Character getTipoEncuesta() {
        return tipoEncuesta;
    }

    public void setTipoEncuesta(Character tipoEncuesta) {
        this.tipoEncuesta = tipoEncuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solCodigo != null ? solCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudActnota)) {
            return false;
        }
        SolicitudActnota other = (SolicitudActnota) object;
        if ((this.solCodigo == null && other.solCodigo != null) || (this.solCodigo != null && !this.solCodigo.equals(other.solCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.SolicitudActnota[ solCodigo=" + solCodigo + " ]";
    }
    
}
