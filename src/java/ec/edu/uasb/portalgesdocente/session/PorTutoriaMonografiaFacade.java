/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.PorTutoriaMonografia;
import ec.edu.uasb.portalgesdocente.entities.Tesis;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class PorTutoriaMonografiaFacade extends AbstractFacade<PorTutoriaMonografia> implements PorTutoriaMonografiaFacadeLocal {

    @EJB
    private TesisFacadeLocal tesisFacade;

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PorTutoriaMonografiaFacade() {
        super(PorTutoriaMonografia.class);
    }

    @Override
    public List<PorTutoriaMonografia> getTutoriasContrato(ContratoDocente contrato) {
        List<PorTutoriaMonografia> temp = new ArrayList<PorTutoriaMonografia>();
        if (contrato.getCdoCodigo() != null) {
            List<Tesis> tesis = tesisFacade.findByDocente(contrato.getPrfCodigo(),contrato.getRolDocente().getCodNivelAcad());
            Query q = em.createNamedQuery("PorTutoriaMonografia.findByContrato").setParameter("cdoCodigo", contrato.getCdoCodigo());
            temp = q.getResultList();
            for (PorTutoriaMonografia tut : temp) {
                for (Tesis tes : tesis) {
                    if (tes.getCodNum().compareTo(tut.getAluCodigo()) == 0) {
                        tut.setNombres(tes.getNombres());
                        break;
                    }
                }
            }
        }
        return temp;
    }

}
