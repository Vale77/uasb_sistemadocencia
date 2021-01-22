/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.session;

import ec.edu.uasb.sisevaluacion.entities.SolicitudActnota;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johana.orozco
 */
@Local
public interface SolicitudActnotaFacadeLocal {

    void create(SolicitudActnota solicitudActnota);

    void edit(SolicitudActnota solicitudActnota);

    void remove(SolicitudActnota solicitudActnota);

    SolicitudActnota find(Object id);

    List<SolicitudActnota> findAll();

    List<SolicitudActnota> findRange(int[] range);

    int count();
    
}
