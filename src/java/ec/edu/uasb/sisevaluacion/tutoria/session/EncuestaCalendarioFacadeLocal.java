/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.session;

import ec.edu.uasb.sisevaluacion.tutoria.entities.EncuestaCalendario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface EncuestaCalendarioFacadeLocal {

    void create(EncuestaCalendario encuestaCalendario);

    void edit(EncuestaCalendario encuestaCalendario);

    void remove(EncuestaCalendario encuestaCalendario);

    EncuestaCalendario find(Object id);

    List<EncuestaCalendario> findAll();

    List<EncuestaCalendario> findRange(int[] range);

    int count();        

    public void reaperturaEncuesta(String[] asignatura);

    public void cierreEncuesta(String[] asignatura);

    public void reaperturaEvalCoordADocente(String[] asignatura);

    public void pagEstampillaEvalEstADocente(String[] asignatura);
    
}
