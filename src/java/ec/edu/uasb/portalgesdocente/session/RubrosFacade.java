/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Rubros;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class RubrosFacade extends AbstractFacade<Rubros> implements RubrosFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubrosFacade() {
        super(Rubros.class);
    }

    @Override
    public List<Rubros> getRubrosByCateg(String categ) {
        Query q = em.createNamedQuery("Rubros.findByRubCategoria").setParameter("rubCategoria", categ);
        return q.setHint("eclipselink.refresh", true).getResultList();
    }

}
