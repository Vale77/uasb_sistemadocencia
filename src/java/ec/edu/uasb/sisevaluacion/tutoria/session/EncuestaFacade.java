/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.session;

import ec.edu.uasb.sisevaluacion.tutoria.entities.Encuesta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class EncuestaFacade extends AbstractFacade<Encuesta> implements EncuestaFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaFacade() {
        super(Encuesta.class);
    }

    @Override
    public List<Encuesta> findAllActivo(char tipEncuesta) {
        return em.createNativeQuery("Select * "
                + " from  EVALUACION.encuesta p "
                + " where p.ESTADO = 'A'"
                + " AND p.TIPO =  '" + tipEncuesta + "'", Encuesta.class).getResultList();
    }

    
}
