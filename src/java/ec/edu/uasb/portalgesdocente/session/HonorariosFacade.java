/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.Honorarios;
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
public class HonorariosFacade extends AbstractFacade<Honorarios> implements HonorariosFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HonorariosFacade() {
        super(Honorarios.class);
    }

    @Override
    public List<Honorarios> findViaticosByContrato(Long contrato, String categoria) {
        Query q = em.createQuery("SELECT h FROM Honorarios h,Rubros r WHERE h.honorariosPK.rubCodigo = r.rubCodigo and r.rubCategoria = :rubCategoria "
                + "and h.honorariosPK.cdoCodigo = :cdoCodigo");
        q.setParameter("rubCategoria", categoria);
        q.setParameter("cdoCodigo", contrato);
        List<Honorarios> temp = q.getResultList();
        for (Honorarios hon : temp) {
            hon.setDescripRubro(hon.getRubros().getRubDescripcion());
        }
        return temp;
    }

    @Override
    public boolean hayModificaciones(Long contrato, List<Honorarios> viaticos) {
        boolean existen = false;
        int i = 0;
        List<Honorarios> temp = this.findViaticosByContrato(contrato, "V");
        for (Honorarios hon : temp) {
            i = viaticos.indexOf(hon);
            if (i >= 0) {
                existen = (hon.getHonCantidad().compareTo(viaticos.get(i).getHonCantidad()) != 0);
                break;
            }
        }
        return existen;
    }
}
