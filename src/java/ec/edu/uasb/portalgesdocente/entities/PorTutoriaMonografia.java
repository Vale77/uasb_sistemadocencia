/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "POR_TUTORIA_MONOGRAFIA", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PorTutoriaMonografia.findAll", query = "SELECT p FROM PorTutoriaMonografia p"),
    @NamedQuery(name = "PorTutoriaMonografia.findByContrato", query = "SELECT p FROM PorTutoriaMonografia p where p.contratoDocente.cdoCodigo = :cdoCodigo"),
    @NamedQuery(name = "PorTutoriaMonografia.findByAluCodigo", query = "SELECT p FROM PorTutoriaMonografia p WHERE p.aluCodigo = :aluCodigo")})

public class PorTutoriaMonografia implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TTM_CODIGO")
    private Long ttmCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALU_CODIGO")
    private Long aluCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "TTM_TITULO")
    private String ttmTitulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TTM_FECHA_FINPROG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttmFechaFinprog;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TTM_FECHA_INIPROG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ttmFechaIniprog;
    @JoinColumn(name = "CDO_CODIGO", referencedColumnName = "CDO_CODIGO")
    @ManyToOne(optional = false)
    private ContratoDocente contratoDocente;
    @Transient
    private String nombres;

    public PorTutoriaMonografia() {
    }

    public PorTutoriaMonografia(Long ttmCodigo) {
        this.ttmCodigo = ttmCodigo;
    }

    public PorTutoriaMonografia(Long ttmCodigo, Long aluCodigo, String ttmTitulo, Date ttmFechaFinprog) {
        this.ttmCodigo = ttmCodigo;
        this.aluCodigo = aluCodigo;
        this.ttmTitulo = ttmTitulo;
        this.ttmFechaFinprog = ttmFechaFinprog;
    }

    public Long getTtmCodigo() {
        return ttmCodigo;
    }

    public void setTtmCodigo(Long ttmCodigo) {
        this.ttmCodigo = ttmCodigo;
    }

    public Long getAluCodigo() {
        return aluCodigo;
    }

    public void setAluCodigo(Long aluCodigo) {
        this.aluCodigo = aluCodigo;
    }

    public String getTtmTitulo() {
        return ttmTitulo;
    }

    public void setTtmTitulo(String ttmTitulo) {
        this.ttmTitulo = ttmTitulo;
    }

    public Date getTtmFechaFinprog() {
        return ttmFechaFinprog;
    }

    public void setTtmFechaFinprog(Date ttmFechaFinprog) {
        this.ttmFechaFinprog = ttmFechaFinprog;
    }

    public Date getTtmFechaIniprog() {
        return ttmFechaIniprog;
    }

    public void setTtmFechaIniprog(Date ttmFechaIniprog) {
        this.ttmFechaIniprog = ttmFechaIniprog;
    }

    public ContratoDocente getContratoDocente() {
        return contratoDocente;
    }

    public void setContratoDocente(ContratoDocente contratoDocente) {
        this.contratoDocente = contratoDocente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ttmCodigo != null ? ttmCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorTutoriaMonografia)) {
            return false;
        }
        PorTutoriaMonografia other = (PorTutoriaMonografia) object;
        if ((this.ttmCodigo == null && other.ttmCodigo != null) || (this.ttmCodigo != null && !this.ttmCodigo.equals(other.ttmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public PorTutoriaMonografia clone() throws CloneNotSupportedException {
        return (PorTutoriaMonografia) super.clone();
    }

    @Override
    public String toString() {
        return "PorTutoriaMonografia{" + "ttmCodigo=" + ttmCodigo + ", aluCodigo=" + aluCodigo + ", ttmTitulo=" + ttmTitulo + ", ttmFechaFinprog=" + ttmFechaFinprog + ", ttmFechaIniprog=" + ttmFechaIniprog + ", nombres=" + nombres + '}';
    }



}
