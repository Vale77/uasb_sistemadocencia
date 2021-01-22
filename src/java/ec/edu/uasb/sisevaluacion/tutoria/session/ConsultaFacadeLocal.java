/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.session;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;
import javax.faces.context.FacesContext;

/**
 *
 * @author johana.orozco
 */
@Local
public interface ConsultaFacadeLocal {

    public java.util.Vector ejecutaSqlList(java.lang.String sql);

    public java.util.List<java.lang.String[]> trimestreAnio(java.lang.Long anio);

    public void enviaCorreo(String profile_name, String recipients, String copy_recipients, String blind_copy_recipients, String psubject, String pmensaje, String body_format) throws SQLException;

    
    public void GenRep(FacesContext fc, String subsist, String reporte, ContratoDocente contrato);


}
