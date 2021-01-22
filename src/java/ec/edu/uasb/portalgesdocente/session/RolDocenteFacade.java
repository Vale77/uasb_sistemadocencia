/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.RolDocente;
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
public class RolDocenteFacade extends AbstractFacade<RolDocente> implements RolDocenteFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolDocenteFacade() {
        super(RolDocente.class);
    }

    @Override
    public List<RolDocente> getRolDocenteByEstadoAndNivel(String estado,Short codNivelAcad) {
        Query q = em.createNamedQuery("RolDocente.findByRdoEstado").setParameter("rdoEstado", estado).setParameter("codNivelAcad",codNivelAcad);
        return q.setHint("eclipselink.refresh", true).getResultList();

    }

}
