/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author victor.barba
 */
@Entity
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CODNUM")
    private Long codNum;
    @Column(name = "DNIPRS")
    private String dniPrs;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;

    public Long getCodNum() {
        return codNum;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Personal() {
    }

    public Personal(Long codNum, String dniPrs, String nombres, String apellidos) {
        this.codNum = codNum;
        this.dniPrs = dniPrs;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.codNum != null ? this.codNum.hashCode() : 0);
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
        final Personal other = (Personal) obj;
        if (this.codNum != other.codNum && (this.codNum == null || !this.codNum.equals(other.codNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Personal{" + "codNum=" + codNum + ", dniPrs=" + dniPrs + ", nombres=" + nombres + ", apellidos=" + apellidos + '}';
    }

}
