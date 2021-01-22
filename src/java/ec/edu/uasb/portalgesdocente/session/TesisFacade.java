/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Tesis;
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
public class TesisFacade extends AbstractFacade<Tesis> implements TesisFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TesisFacade() {
        super(Tesis.class);
    }

    @Override
    public List<Tesis> findByDocente(Long codDocente,Short codNivelAcad) {
        Query q = em.createQuery("select t from Tesis t where t.docente = :docente and t.codNivelAcad = :codNivelAcad order by t.nombres");
        q.setParameter("docente", codDocente).setParameter("codNivelAcad", codNivelAcad);
        return q.setHint("eclipselink.refresh", true).getResultList();

    }
}
