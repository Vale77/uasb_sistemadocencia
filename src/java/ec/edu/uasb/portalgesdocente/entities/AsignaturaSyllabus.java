/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author victor.barba
 */
@Entity
public class AsignaturaSyllabus implements Serializable {

    private static final long serialVersionUID = 106L;
    @EmbeddedId
    protected AsignaturaSyllabusPK asignaturaSyllabusPK;
    @Column(name = "IDENTIF_MATERIA")
    private String identifMateria;
    @Column(name = "NOMBRE_MATERIA")
    private String nombreMateria;
    @Column(name = "NOMBRE_NIVEL")
    private String nombreNivel;
    @Column(name = "ANIO")
    private Long anio;
    @Column(name = "CICLO")
    private Long ciclo;
    @Column(name = "RESPONSABLE_MATERIA")
    private String responsableSyllabus;
    @Column(name = "OBSERVACION_COORDINADOR")
    private String observacionCoordinador;
    @Column(name = "ESTADO_SYLABUS")
    private String estadoSyllabus;
    @Column(name = "ESTADO_ENVIO")
    private String estadoEnvio;
    @Column(name = "APROBADO_POR")
    private Long aprobadoPor;
    @Column(name = "HORAS_MATERIA_DICTAR")
    private Long horasMateriaDictar;
    @Column(name = "NCREDITOS")
    private BigDecimal nCreditos;
    @Column(name = "APELLIDO_PROFESOR")
    private String apellidoProfesor;
    @Column(name = "NOMBRE_PROFESOR")
    private String nombreProfesor;
    @Column(name = "EMAIL_AREA")
    private String emailArea;
    @Column(name = "AREA")
    private String area;
    @Column(name = "PROGRAMA")
    private String programa;
    @Column(name = "NOMBRE_PARALELO")
    private String nombreParalelo;
    @Column(name = "CODIGO_COORDINADOR")
    private Long codigoCoordinador;
    @Column(name = "EXISTE")
    private Long existe;

    public AsignaturaSyllabus() {
    }

    public Long getExiste() {
        return existe;
    }

    public void setExiste(Long existe) {
        this.existe = existe;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public String getEstadoSyllabus() {
        return estadoSyllabus;
    }

    public void setEstadoSyllabus(String estadoSyllabus) {
        this.estadoSyllabus = estadoSyllabus;
    }

    public String getIdentifMateria() {
        return identifMateria;
    }

    public void setIdentifMateria(String identifMateria) {
        this.identifMateria = identifMateria;
    }

    public Long getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Long anio) {
        this.anio = anio;
    }

    /**
     * @return the ciclo
     */
    public Long getCiclo() {
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(Long ciclo) {
        this.ciclo = ciclo;
    }

    /**
     * @return the responsableSyllabus
     */
    public String getResponsableSyllabus() {
        return responsableSyllabus;
    }

    /**
     * @param responsableSyllabus the responsableSyllabus to set
     */
    public void setResponsableSyllabus(String responsableSyllabus) {
        this.responsableSyllabus = responsableSyllabus;
    }

    /**
     * @return the observacionCoordinador
     */
    public String getObservacionCoordinador() {
        return observacionCoordinador;
    }

    /**
     * @param observacionCoordinador the observacionCoordinador to set
     */
    public void setObservacionCoordinador(String observacionCoordinador) {
        this.observacionCoordinador = observacionCoordinador;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Long getHorasMateriaDictar() {
        return horasMateriaDictar;
    }

    public void setHorasMateriaDictar(Long horasMateriaDictar) {
        this.horasMateriaDictar = horasMateriaDictar;
    }

    public BigDecimal getnCreditos() {
        return nCreditos;
    }

    public void setnCreditos(BigDecimal nCreditos) {
        this.nCreditos = nCreditos;
    }

    public Long getAprobadoPor() {
        return aprobadoPor;
    }

    public void setAprobadoPor(Long aprobadoPor) {
        this.aprobadoPor = aprobadoPor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getEmailArea() {
        return emailArea;
    }

    public void setEmailArea(String emailArea) {
        this.emailArea = emailArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getNombreParalelo() {
        return nombreParalelo;
    }

    public void setNombreParalelo(String nombreParalelo) {
        this.nombreParalelo = nombreParalelo;
    }

    public Long getCodigoCoordinador() {
        return codigoCoordinador;
    }

    public void setCodigoCoordinador(Long codigoCoordinador) {
        this.codigoCoordinador = codigoCoordinador;
    }

    public AsignaturaSyllabusPK getAsignaturaSyllabusPK() {
        return asignaturaSyllabusPK;
    }

    public void setAsignaturaSyllabusPK(AsignaturaSyllabusPK asignaturaSyllabusPK) {
        this.asignaturaSyllabusPK = asignaturaSyllabusPK;
    }

    public AsignaturaSyllabus(AsignaturaSyllabusPK asignaturaSyllabusPK, String identifMateria, String nombreMateria, Long codigoNivel, String nombreNivel,
            String estadoSyllabus, String estadoEnvio, Long anio, Long ciclo, String responsableSyllabus,
            String observacionCoordinador, Long horasMateriaDictar, BigDecimal nCreditos, Long aprobadoPor,
            String nombreProfesor, String apellidoProfesor, String emailArea, String area, String programa, String nombreParalelo) {
        this.asignaturaSyllabusPK = asignaturaSyllabusPK;
        this.identifMateria = identifMateria;
        this.nombreMateria = nombreMateria;
        this.nombreNivel = nombreNivel;
        this.estadoSyllabus = estadoSyllabus;
        this.estadoEnvio = estadoEnvio;
        this.anio = anio;
        this.ciclo = ciclo;
        this.responsableSyllabus = responsableSyllabus;
        this.observacionCoordinador = observacionCoordinador;
        this.horasMateriaDictar = horasMateriaDictar;
        this.nCreditos = nCreditos;
        this.aprobadoPor = aprobadoPor;
        this.nombreProfesor = nombreProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.emailArea = emailArea;
        this.area = area;
        this.programa = programa;
        this.nombreParalelo = nombreParalelo;
    }

    @Override
    public String toString() {
        return "AsignaturaSyllabus{" + "asignaturaSyllabusPK=" + asignaturaSyllabusPK + ", identifMateria=" + identifMateria + ", nombreMateria=" + nombreMateria + ", nombreNivel=" + nombreNivel + ", anio=" + anio + ", ciclo=" + ciclo + ", responsableSyllabus=" + responsableSyllabus + ", observacionCoordinador=" + observacionCoordinador + ", estadoSyllabus=" + estadoSyllabus + ", estadoEnvio=" + estadoEnvio + ", aprobadoPor=" + aprobadoPor + ", horasMateriaDictar=" + horasMateriaDictar + ", nCreditos=" + nCreditos + ", apellidoProfesor=" + apellidoProfesor + ", nombreProfesor=" + nombreProfesor + ", emailArea=" + emailArea + ", area=" + area + ", programa=" + programa + ", paralelo=" + nombreParalelo + ", codigoCoordinador=" + codigoCoordinador + '}';
    }

}
