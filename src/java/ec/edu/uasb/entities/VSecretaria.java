/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "V_SECRETARIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VSecretaria.findAll", query = "SELECT v FROM VSecretaria v"),
    @NamedQuery(name = "VSecretaria.findByCodigo", query = "SELECT v FROM VSecretaria v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "VSecretaria.findByCedPasUsuario", query = "SELECT v FROM VSecretaria v WHERE v.cedPasUsuario = :cedPasUsuario"),
    @NamedQuery(name = "VSecretaria.findByNombreCompleto", query = "SELECT v FROM VSecretaria v WHERE v.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "VSecretaria.findByCodPrograma", query = "SELECT v FROM VSecretaria v WHERE v.codPrograma = :codPrograma"),
    @NamedQuery(name = "VSecretaria.findByPrograma", query = "SELECT v FROM VSecretaria v WHERE v.programa = :programa"),
    @NamedQuery(name = "VSecretaria.findByCodArea", query = "SELECT v FROM VSecretaria v WHERE v.codArea = :codArea"),
    @NamedQuery(name = "VSecretaria.findByArea", query = "SELECT v FROM VSecretaria v WHERE v.area = :area"),
    @NamedQuery(name = "VSecretaria.findByEmail", query = "SELECT v FROM VSecretaria v WHERE v.email = :email")})
public class VSecretaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    @Id
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CED_PAS_USUARIO")
    private String cedPasUsuario;
    @Size(max = 98)
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "COD_PROGRAMA")
    private String codPrograma;
    @Size(max = 75)
    @Column(name = "PROGRAMA")
    private String programa;
    @Column(name = "ARE_CODIGO")
    private Long codArea;
    @Size(max = 75)
    @Column(name = "NOMBRE_AREA")
    private String area;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;

    public VSecretaria() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCedPasUsuario() {
        return cedPasUsuario;
    }

    public void setCedPasUsuario(String cedPasUsuario) {
        this.cedPasUsuario = cedPasUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(String codPrograma) {
        this.codPrograma = codPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Long getCodArea() {
        return codArea;
    }

    public void setCodArea(Long codArea) {
        this.codArea = codArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
