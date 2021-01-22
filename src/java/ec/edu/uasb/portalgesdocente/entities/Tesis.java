/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "VISTA_TESIS")
public class Tesis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODNUM")
    private Long codNum;
    @Column(name = "EXP_NUMORD")
    private Long expNumord;
    @Column(name = "PLA_CODALF")
    private Long plaCodalf;
    @Column(name = "COD_NIVELACAD")
    private Short codNivelAcad;
    @Column(name = "ALU_DNIALU")
    private String aluDnialu;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "TEMA")
    private String tema;
    @Column(name = "DOCENTE")
    private Long docente;
    @Column(name = "FECHA_INI")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIni;
    @Column(name = "FECHA_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;

    public Tesis() {
    }

    public Tesis(Long codNum) {
        this.codNum = codNum;
    }

    public Tesis(Long codNum, Long expNumord, Long plaCodalf, Short codNivelAcad, String aluDnialu, String nombres, String tema, Long docente, Date fechaIni, Date fechaFin) {
        this.codNum = codNum;
        this.expNumord = expNumord;
        this.plaCodalf = plaCodalf;
        this.codNivelAcad = codNivelAcad;
        this.aluDnialu = aluDnialu;
        this.nombres = nombres;
        this.tema = tema;
        this.docente = docente;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public Long getCodNum() {
        return codNum;
    }

    public void setCodNum(Long codNum) {
        this.codNum = codNum;
    }

    public Long getExpNumord() {
        return expNumord;
    }

    public void setExpNumord(Long expNumord) {
        this.expNumord = expNumord;
    }

    public Long getPlaCodalf() {
        return plaCodalf;
    }

    public void setPlaCodalf(Long plaCodalf) {
        this.plaCodalf = plaCodalf;
    }

    public Short getCodNivelAcad() {
        return codNivelAcad;
    }

    public void setCodNivelAcad(Short codNivelAcad) {
        this.codNivelAcad = codNivelAcad;
    }

    public String getAluDnialu() {
        return aluDnialu;
    }

    public void setAluDnialu(String aluDnialu) {
        this.aluDnialu = aluDnialu;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Long getDocente() {
        return docente;
    }

    public void setDocente(Long docente) {
        this.docente = docente;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.codNum != null ? this.codNum.hashCode() : 0);
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
        final Tesis other = (Tesis) obj;
        return !(this.codNum != other.codNum && (this.codNum == null || !this.codNum.equals(other.codNum)));
    }

    @Override
    public String toString() {
        return "Tesis{" + "codNum=" + codNum + ", expNumord=" + expNumord + ", plaCodalf=" + plaCodalf + ", codNivelAcad=" + codNivelAcad + ", aluDnialu=" + aluDnialu + ", nombres=" + nombres + ", tema=" + tema + ", docente=" + docente + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + '}';
    }

}
