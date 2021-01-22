/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "DATOS_VIAJERO", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosViajero.findAll", query = "SELECT d FROM DatosViajero d"),
    @NamedQuery(name = "DatosViajero.findByCdoCodigo", query = "SELECT d FROM DatosViajero d WHERE d.datosViajeroPK.cdoCodigo = :cdoCodigo"),
    @NamedQuery(name = "DatosViajero.findByDviTipoRegistro", query = "SELECT d FROM DatosViajero d WHERE d.datosViajeroPK.dviTipoRegistro = :dviTipoRegistro"),
    @NamedQuery(name = "DatosViajero.findByDviTipoPasaje", query = "SELECT d FROM DatosViajero d WHERE d.dviTipoPasaje = :dviTipoPasaje"),
    @NamedQuery(name = "DatosViajero.findByDviRutaPasaje", query = "SELECT d FROM DatosViajero d WHERE d.dviRutaPasaje = :dviRutaPasaje"),
    @NamedQuery(name = "DatosViajero.findByDviValorDiario", query = "SELECT d FROM DatosViajero d WHERE d.dviValorDiario = :dviValorDiario"),
    @NamedQuery(name = "DatosViajero.findByDviFechaDesde", query = "SELECT d FROM DatosViajero d WHERE d.dviFechaDesde = :dviFechaDesde"),
    @NamedQuery(name = "DatosViajero.findByDviFechaHasta", query = "SELECT d FROM DatosViajero d WHERE d.dviFechaHasta = :dviFechaHasta")})
public class DatosViajero implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatosViajeroPK datosViajeroPK;
    @Size(max = 1)
    @Column(name = "DVI_TIPO_PASAJE")
    private String dviTipoPasaje;
    @Size(max = 255)
    @Column(name = "DVI_RUTA_PASAJE")
    private String dviRutaPasaje;
    @Column(name = "DVI_CANTIDAD")
    private Short dviCantidad;    
// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DVI_VALOR_DIARIO")
    private BigDecimal dviValorDiario;
    @Column(name = "DVI_FECHA_DESDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dviFechaDesde;
    @Column(name = "DVI_FECHA_HASTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dviFechaHasta;
    @JoinColumn(name = "RUB_CODIGO", referencedColumnName = "RUB_CODIGO")
    @ManyToOne
    private Rubros rubros;
    @JoinColumn(name = "CDO_CODIGO", referencedColumnName = "CDO_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContratoDocente contratoDocente;

    public DatosViajero() {
    }

    public DatosViajero(DatosViajeroPK datosViajeroPK) {
        this.datosViajeroPK = datosViajeroPK;
    }

    public DatosViajero(long cdoCodigo, String dviTipoRegistro) {
        this.datosViajeroPK = new DatosViajeroPK(cdoCodigo, dviTipoRegistro);
    }

    public DatosViajeroPK getDatosViajeroPK() {
        return datosViajeroPK;
    }

    public void setDatosViajeroPK(DatosViajeroPK datosViajeroPK) {
        this.datosViajeroPK = datosViajeroPK;
    }

    public String getDviTipoPasaje() {
        return dviTipoPasaje;
    }

    public void setDviTipoPasaje(String dviTipoPasaje) {
        this.dviTipoPasaje = dviTipoPasaje;
    }

    public String getDviRutaPasaje() {
        return dviRutaPasaje;
    }

    public void setDviRutaPasaje(String dviRutaPasaje) {
        this.dviRutaPasaje = dviRutaPasaje;
    }

    public Short getDviCantidad() {
        return dviCantidad;
    }

    public void setDviCantidad(Short dviCantidad) {
        this.dviCantidad = dviCantidad;
    }

    public BigDecimal getDviValorDiario() {
        return dviValorDiario;
    }

    public void setDviValorDiario(BigDecimal dviValorDiario) {
        this.dviValorDiario = dviValorDiario;
    }

    public Date getDviFechaDesde() {
        return dviFechaDesde;
    }

    public void setDviFechaDesde(Date dviFechaDesde) {
        this.dviFechaDesde = dviFechaDesde;
    }

    public Date getDviFechaHasta() {
        return dviFechaHasta;
    }

    public void setDviFechaHasta(Date dviFechaHasta) {
        this.dviFechaHasta = dviFechaHasta;
    }

    public Rubros getRubros() {
        return rubros;
    }

    public void setRubros(Rubros rubros) {
        this.rubros = rubros;
    }

    public ContratoDocente getContratoDocente() {
        return contratoDocente;
    }

    public void setContratoDocente(ContratoDocente contratoDocente) {
        this.contratoDocente = contratoDocente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datosViajeroPK != null ? datosViajeroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosViajero)) {
            return false;
        }
        DatosViajero other = (DatosViajero) object;
        if ((this.datosViajeroPK == null && other.datosViajeroPK != null) || (this.datosViajeroPK != null && !this.datosViajeroPK.equals(other.datosViajeroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatosViajero{" + "datosViajeroPK=" + datosViajeroPK + ", dviTipoPasaje=" + dviTipoPasaje + ", dviRutaPasaje=" + dviRutaPasaje+ ", dviCantidad=" + dviCantidad + ", dviValorDiario=" + dviValorDiario + ", dviFechaDesde=" + dviFechaDesde + ", dviFechaHasta=" + dviFechaHasta + ", rubros=" + rubros + '}';
    }

  
    
}
