/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "ENCUESTA", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e"),
    @NamedQuery(name = "Encuesta.findByCodigoEncuesta", query = "SELECT e FROM Encuesta e WHERE e.codigoEncuesta = :codigoEncuesta"),
    @NamedQuery(name = "Encuesta.findByTitulo", query = "SELECT e FROM Encuesta e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "Encuesta.findByReferencia", query = "SELECT e FROM Encuesta e WHERE e.referencia = :referencia"),
    @NamedQuery(name = "Encuesta.findByDescripcion", query = "SELECT e FROM Encuesta e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Encuesta.findByUso", query = "SELECT e FROM Encuesta e WHERE e.uso = :uso"),
    @NamedQuery(name = "Encuesta.findByEstado", query = "SELECT e FROM Encuesta e WHERE e.estado = :estado"),
    @NamedQuery(name = "Encuesta.findByTipo", query = "SELECT e FROM Encuesta e WHERE e.tipo = :tipo")})

public class Encuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_ENCUESTA")
    private Long codigoEncuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "USO")
    private Character uso;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "TIPO")
    private Character tipo;


    public String getnomuso() {
        String variable = "";
        if (this.uso != null) {
            switch (this.uso) {
                case 'S':
                    variable = "Académico";
                    break;
                case 'D':
                    variable = "Seguimiento";
                    break;
                default:

                    break;
            }
        } else {
            variable = "S/V";
        }

        return variable;
    }

    public Encuesta() {
    }

    public Encuesta(Long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public Encuesta(Long codigoEncuesta, String titulo, String referencia) {
        this.codigoEncuesta = codigoEncuesta;
        this.titulo = titulo;
        this.referencia = referencia;
    }

    public Long getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(Long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getUso() {
        return uso;
    }

    public void setUso(Character uso) {
        this.uso = uso;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEncuesta != null ? codigoEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.codigoEncuesta == null && other.codigoEncuesta != null) || (this.codigoEncuesta != null && !this.codigoEncuesta.equals(other.codigoEncuesta))) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "ec.edu.uasb.sisevaluacion.entities.Encuesta[ codigoEncuesta=" + codigoEncuesta + " ]";
    }
}
