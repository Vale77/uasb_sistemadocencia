/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.TipoEstado;
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
public class TipoEstadoFacade extends AbstractFacade<TipoEstado> implements TipoEstadoFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstadoFacade() {
        super(TipoEstado.class);
    }

    @Override
    public String getEmailSigProcesoByEstado(String estado) {
        TipoEstado e = this.find(estado);
        Query q = em.createNativeQuery("SELECT STUFF((select ';'+u.EMAIL from V_USUARIO u where ID_PERFIL = ?  FOR XML PATH('')),1,1, '') ");
        q.setParameter(1, e.getStaSiguientePerfil());
        return (String) q.getSingleResult();
    }
   
    
}
