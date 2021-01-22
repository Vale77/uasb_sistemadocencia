/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "HONORARIOS", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Honorarios.findAll", query = "SELECT h FROM Honorarios h"),
    @NamedQuery(name = "Honorarios.findByCdoCodigo", query = "SELECT h FROM Honorarios h WHERE h.honorariosPK.cdoCodigo = :cdoCodigo"),
    @NamedQuery(name = "Honorarios.findByRubCodigo", query = "SELECT h FROM Honorarios h WHERE h.honorariosPK.rubCodigo = :rubCodigo"),
    @NamedQuery(name = "Honorarios.findByHonCantidad", query = "SELECT h FROM Honorarios h WHERE h.honCantidad = :honCantidad"),
    @NamedQuery(name = "Honorarios.findByHonValor", query = "SELECT h FROM Honorarios h WHERE h.honValor = :honValor")})
public class Honorarios implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HonorariosPK honorariosPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "HON_CANTIDAD")
    private BigDecimal honCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HON_VALOR")
    private BigDecimal honValor;
    @JoinColumn(name = "RUB_CODIGO", referencedColumnName = "RUB_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rubros rubros;
    @JoinColumn(name = "CDO_CODIGO", referencedColumnName = "CDO_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContratoDocente contratoDocente;
    @Transient
    private String descripRubro;

    public Honorarios() {
    }

    public Honorarios(HonorariosPK honorariosPK) {
        this.honorariosPK = honorariosPK;
    }

    public Honorarios(HonorariosPK honorariosPK, BigDecimal honCantidad, BigDecimal honValor) {
        this.honorariosPK = honorariosPK;
        this.honCantidad = honCantidad;
        this.honValor = honValor;
    }

    public Honorarios(Long cdoCodigo, Long rubCodigo) {
        this.honorariosPK = new HonorariosPK(cdoCodigo, rubCodigo);
    }

    public HonorariosPK getHonorariosPK() {
        return honorariosPK;
    }

    public void setHonorariosPK(HonorariosPK honorariosPK) {
        this.honorariosPK = honorariosPK;
    }

    public BigDecimal getHonCantidad() {
        return honCantidad;
    }

    public void setHonCantidad(BigDecimal honCantidad) {
        this.honCantidad = honCantidad;
    }

    public BigDecimal getHonValor() {
        return honValor;
    }

    public void setHonValor(BigDecimal honValor) {
        this.honValor = honValor;
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

    public String getDescripRubro() {
        return descripRubro;
    }

    public void setDescripRubro(String descripRubro) {
        this.descripRubro = descripRubro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (honorariosPK != null ? honorariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Honorarios)) {
            return false;
        }
        Honorarios other = (Honorarios) object;
        if ((this.honorariosPK == null && other.honorariosPK != null) || (this.honorariosPK != null && !this.honorariosPK.equals(other.honorariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public Honorarios clone() throws CloneNotSupportedException {
        return (Honorarios) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Honorarios{" + "honorariosPK=" + honorariosPK + ", honCantidad=" + honCantidad + ", honValor=" + honValor + ", descripRubro=" + descripRubro + '}';
    }



}
