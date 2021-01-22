/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.seg.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Entity
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PERFIL")
    private Long codPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERFIL")
    private String idPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DES_PERFIL")
    private String desPerfil;

    public Perfil() {
    }

    public Perfil(Long codPerfil, String idPerfil, String desPerfil) {
        this.codPerfil = codPerfil;
        this.idPerfil = idPerfil;
        this.desPerfil = desPerfil;
    }

    public Long getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(Long codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDesPerfil() {
        return desPerfil;
    }

    public void setDesPerfil(String desPerfil) {
        this.desPerfil = desPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.codPerfil != null ? this.codPerfil.hashCode() : 0);
        hash = 23 * hash + (this.idPerfil != null ? this.idPerfil.hashCode() : 0);
        hash = 23 * hash + (this.desPerfil != null ? this.desPerfil.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("obj == null");
            return false;
        }
        if (getClass() != obj.getClass()) {
                System.out.println("getClass() != obj.getClass()");
            return false;
        }
        final Perfil other = (Perfil) obj;
        if (this.codPerfil != other.codPerfil && (this.codPerfil == null || !this.codPerfil.equals(other.codPerfil))) {
                     System.out.println("this.codPerfil "+this.codPerfil);
            return false;
        }
        if ((this.idPerfil == null) ? (other.idPerfil != null) : !this.idPerfil.equals(other.idPerfil)) {
            System.out.println("this.idPerfil "+this.idPerfil);
            return false;
        }
        if ((this.desPerfil == null) ? (other.desPerfil != null) : !this.desPerfil.equals(other.desPerfil)) {
                System.out.println("this.desPerfil "+this.desPerfil);
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Perfil{" + "codPerfil=" + codPerfil + ", idPerfil=" + idPerfil + ", desPerfil=" + desPerfil + '}';
    }

}
