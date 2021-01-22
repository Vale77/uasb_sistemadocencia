/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "RUBROS", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubros.findAll", query = "SELECT r FROM Rubros r"),
    @NamedQuery(name = "Rubros.findByRubCategoria", query = "SELECT r FROM Rubros r WHERE r.rubCategoria = :rubCategoria and r.rubEstado = 'A'")})
public class Rubros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUB_CODIGO")
    private Long rubCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RUB_DESCRIPCION")
    private String rubDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUB_VALOR_MAES")
    private BigDecimal rubValorMaes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUB_VALOR_ESPE")
    private BigDecimal rubValorEspe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUB_VALOR_DOCTO")
    private BigDecimal rubValorDocto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "RUB_CATEGORIA")
    private String rubCategoria;
    @Size(max = 20)
    @Column(name = "RUB_UNIMEDIDA")
    private String rubUnimedida;
    @Size(min = 1, max = 1)
    @Column(name = "RUB_ESTADO")
    private String rubEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rubros")
    private Collection<Honorarios> honorariosCollection;
    @OneToMany(mappedBy = "rubros")
    private Collection<DatosViajero> datosViajeroCollection;
    @OneToMany(mappedBy = "rubros")
    private Collection<RolDocente> rolDocenteCollection;

    public Rubros() {
    }

    public Rubros(Long rubCodigo) {
        this.rubCodigo = rubCodigo;
    }

    public Rubros(Long rubCodigo, String rubDescripcion, BigDecimal rubValorMaes, BigDecimal rubValorDocto,
            BigDecimal rubValorEspe, String rubCategoria, String rubEstado) {
        this.rubCodigo = rubCodigo;
        this.rubDescripcion = rubDescripcion;
        this.rubValorEspe = rubValorEspe;
        this.rubValorMaes = rubValorMaes;
        this.rubValorDocto = rubValorDocto;
        this.rubCategoria = rubCategoria;
        this.rubEstado = rubEstado;
    }

    public Long getRubCodigo() {
        return rubCodigo;
    }

    public void setRubCodigo(Long rubCodigo) {
        this.rubCodigo = rubCodigo;
    }

    public String getRubDescripcion() {
        return rubDescripcion;
    }

    public void setRubDescripcion(String rubDescripcion) {
        this.rubDescripcion = rubDescripcion;
    }

    public BigDecimal getRubValorEspe() {
        return rubValorEspe;
    }

    public void setRubValorEspe(BigDecimal rubValorEspe) {
        this.rubValorEspe = rubValorEspe;
    }

    public BigDecimal getRubValorDocto() {
        return rubValorDocto;
    }

    public void setRubValorDocto(BigDecimal rubValorDocto) {
        this.rubValorDocto = rubValorDocto;
    }

    public BigDecimal getRubValorMaes() {
        return rubValorMaes;
    }

    public void setRubValorMaes(BigDecimal rubValor) {
        this.rubValorMaes = rubValor;
    }

    public String getRubCategoria() {
        return rubCategoria;
    }

    public void setRubCategoria(String rubCategoria) {
        this.rubCategoria = rubCategoria;
    }

    public String getRubUnimedida() {
        return rubUnimedida;
    }

    public void setRubUnimedida(String rubUnimedida) {
        this.rubUnimedida = rubUnimedida;
    }

    public String getRubEstado() {
        return rubEstado;
    }

    public void setRubEstado(String rubEstado) {
        this.rubEstado = rubEstado;
    }

    @XmlTransient
    public Collection<Honorarios> getHonorariosCollection() {
        return honorariosCollection;
    }

    public void setHonorariosCollection(Collection<Honorarios> honorariosCollection) {
        this.honorariosCollection = honorariosCollection;
    }

    @XmlTransient
    public Collection<DatosViajero> getDatosViajeroCollection() {
        return datosViajeroCollection;
    }

    public void setDatosViajeroCollection(Collection<DatosViajero> datosViajeroCollection) {
        this.datosViajeroCollection = datosViajeroCollection;
    }

    @XmlTransient
    public Collection<RolDocente> getRolDocenteCollection() {
        return rolDocenteCollection;
    }

    public void setRolDocenteCollection(Collection<RolDocente> rolDocenteCollection) {
        this.rolDocenteCollection = rolDocenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubCodigo != null ? rubCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubros)) {
            return false;
        }
        Rubros other = (Rubros) object;
        if ((this.rubCodigo == null && other.rubCodigo != null) || (this.rubCodigo != null && !this.rubCodigo.equals(other.rubCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.portalgesdocente.entities.Rubros[ rubCodigo=" + rubCodigo + " ]";
    }

}
