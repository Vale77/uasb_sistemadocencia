/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.entities.Asignatura;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
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
public class AsignaturaFacade extends AbstractFacade<Asignatura> implements AsignaturaFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(Asignatura.class);
    }

    @Override
    public List<Asignatura> getAsignaturasByAreaProg(Long codArea, Long codProg, Long anio) {

        Query q = em.createNativeQuery("SELECT  DISTINCT VAC_CODNUM ACT_CODIGO , COD_PARALELO GRP_CODIGO ,CODIGO_MATERIA ASS_CODIGO, NOMLARGO_PARALELO GRP_NOMBRE , IDENTIF_MATERIA ID_ASSIGNATURA, "
                //+ "NOMBRE_MATERIA ASS_NOMBRE, NUMCLASES NCLASES_PLANIFICADAS,NUMCLASE_PRACTICA FROM interfaseOcu.dbo.VISTA_COORDINADOR_PROGRAMA  where  SUBSTRING(IDENTIF_MATERIA,1,2) = SIGLA_AREA "
                + "NOMBRE_MATERIA ASS_NOMBRE, NUMCLASES NCLASES_PLANIFICADAS,NUMCLASE_PRACTICA FROM interfaseOcu.dbo.VISTA_COORDINADOR_PROGRAMA  where  TIPO_ASIGNATURA NOT IN ('L') "
                + "AND NUMCLASES is not null AND CODIGO_FACULTAD = ? and  CODIGO_ESP = ? and ANIO = ? ORDER BY 6,4", Asignatura.class);
        q.setParameter(1, codArea);
        q.setParameter(2, codProg);
        q.setParameter(3, anio);
        return q.getResultList();
    }

    @Override
    public Asignatura getAsignatura(ContratoDocente contrato) {

        Query q = em.createNativeQuery("SELECT DISTINCT VAC_CODNUM ACT_CODIGO, COD_PARALELO GRP_CODIGO,CODIGO_MATERIA ASS_CODIGO,NOMLARGO_PARALELO GRP_NOMBRE, IDENTIF_MATERIA ID_ASSIGNATURA,"
                + " NOMBRE_MATERIA ASS_NOMBRE, NUMCLASES NCLASES_PLANIFICADAS,NUMCLASE_PRACTICA FROM interfaseOcu.dbo.VISTA_COORDINADOR_PROGRAMA where CODIGO_FACULTAD = ?"
                + " AND CODIGO_ESP = ? AND ANIO = ? AND CODIGO_MATERIA = ? AND COD_PARALELO = ? AND VAC_CODNUM = ? ", Asignatura.class);
        q.setParameter(1, contrato.getAreCodigo());
        q.setParameter(2, contrato.getPrgCodigo());
        q.setParameter(3, contrato.getCdoAnioAcad());
        q.setParameter(4, contrato.getAsgCodigo());
        q.setParameter(5, contrato.getPrlCodigo());
        q.setParameter(6, contrato.getAspCodigo());

        return (Asignatura) q.getSingleResult();

    }

}
