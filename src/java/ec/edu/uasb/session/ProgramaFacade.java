/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.entities.Programa;
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
public class ProgramaFacade extends AbstractFacade<Programa> implements ProgramaFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramaFacade() {
        super(Programa.class);
    }

//    @Override
//    public List<Programa> getProgramasByProgArea(Long codSecre, Long codArea, Long anio) {
//        Query q = em.createNativeQuery("SELECT  ARE_CODIGO,PRG_CODIGO,NOMBRE_PROGRAMA,NIVEL_ACADEMICO,COD_NIVEL_ACAD FROM V_SECRETARIA where  ARE_CODIGO = ? and CODIGO = ? and ANIO_ACAD = ? ORDER BY 3", Programa.class);
//        q.setParameter(1, codArea);
//        q.setParameter(2, codSecre);
//        q.setParameter(3, anio);
//        return q.getResultList();
//    }

    @Override
    public List<Programa> getProgramasByArea(Long codArea, Long anio) {
        Query q = em.createNativeQuery("SELECT DISTINCT ARE_CODIGO,PRG_CODIGO,NOMBRE_PROGRAMA,TIPO_PROG NIVEL_ACADEMICO,COD_NIVEL_ACAD FROM PROGRAMA where  ARE_CODIGO = ? and ANIO = ? ORDER BY 3", Programa.class);
        q.setParameter(1, codArea);
        q.setParameter(2, anio);
        return q.getResultList();
    }

    @Override
    public Programa findByPrg(Object id) {
        Query q = em.createNativeQuery("SELECT DISTINCT ARE_CODIGO,PRG_CODIGO,NOMBRE_PROGRAMA,TIPO_PROG NIVEL_ACADEMICO,COD_NIVEL_ACAD FROM PROGRAMA where PRG_CODIGO = ?  ", Programa.class);
        q.setParameter(1, id);
        return (Programa) q.getSingleResult();
    }

}
