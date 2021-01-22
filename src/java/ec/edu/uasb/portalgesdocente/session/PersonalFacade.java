/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Personal;
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
public class PersonalFacade extends AbstractFacade<Personal> implements PersonalFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalFacade() {
        super(Personal.class);
    }

    @Override
    public List<Personal> getListaPersonal() {
        Query q = em.createNativeQuery("select CODNUM,NOMBRES, APELLIDOS,DNIPRS "
                + "from OPENQUERY(OCU,'select CODNUM,NOMPRS NOMBRES,LL1PRS || '' '' || LL2PRS APELLIDOS,DNIPRS "
                + " from VUIB_DATOSPERSONALES DP  WHERE DP.FLGPAS = ''S'' ORDER BY APELLIDOS, NOMBRES') ",Personal.class);
        return q.getResultList();
    }

    @Override
    public Personal find(Object id) {
         Query q = em.createNativeQuery("select CODNUM,NOMBRES, APELLIDOS,DNIPRS "
                + "from OPENQUERY(OCU,'select CODNUM,NOMPRS NOMBRES,LL1PRS || '' '' || LL2PRS APELLIDOS,DNIPRS "
                + " from VUIB_DATOSPERSONALES DP  WHERE DP.FLGPAS = ''S'' "
                + " and DP.CODNUM = "+(Long)id +"') ",Personal.class);
        return (Personal) q.getSingleResult();
    }
    
    
}
