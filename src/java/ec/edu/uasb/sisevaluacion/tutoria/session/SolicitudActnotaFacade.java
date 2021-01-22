/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.session;

import ec.edu.uasb.sisevaluacion.entities.SolicitudActnota;
import ec.edu.uasb.sisevaluacion.session.SolicitudActnotaFacadeLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class SolicitudActnotaFacade extends AbstractFacade<SolicitudActnota> implements SolicitudActnotaFacadeLocal {
    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudActnotaFacade() {
        super(SolicitudActnota.class);
    }
    
}
