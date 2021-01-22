/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.CandidatoDocente;
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
public class CandidatoDocenteFacade extends AbstractFacade<CandidatoDocente> implements CandidatoDocenteFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;
    private StringBuilder sbBase = new StringBuilder();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatoDocenteFacade() {
        super(CandidatoDocente.class);

        sbBase.append(" DECLARE @temp_table table(CODIGO_PROFESOR bigint,CED_PAS_PROFESOR varchar(15),NOMBRE_PROFESOR varchar(100),APELLIDO_PROFESOR varchar(100),DOMICILIO varchar(512),ASISTENTE_ACAD varchar(1)) ")
                .append(" DECLARE @temp_titulos table (PRS_CODNUM bigint,TITULO varchar(512)) ")
                .append(" insert into @temp_table select CODIGO_PROFESOR,CED_PAS_PROFESOR,NOMBRE_PROFESOR,APELLIDO_PROFESOR,DOMICILIO,ASISTENTE_ACAD FROM PROFESOR ")
                .append(" insert into @temp_titulos select PRS_CODNUM,TITULO FROM interfaseOcu.GESTIONDOCENTE.V_TITULOS_DOCENTE ")
                .append(" SELECT CODIGO_PROFESOR  PRF_CODIGO,PROFESOR.CED_PAS_PROFESOR DNIPRS,TITULO, ASISTENTE_ACAD, ")
                .append(" PROFESOR.APELLIDO_PROFESOR +' '+ PROFESOR.NOMBRE_PROFESOR NOMBRES_APELLIDOS ")
                .append(" FROM @temp_table PROFESOR LEFT OUTER JOIN @temp_titulos TITULOS ON (TITULOS.PRS_CODNUM = PROFESOR.CODIGO_PROFESOR)");
    }

    @Override
    public List<CandidatoDocente> getCandidatosDocentes() {
        StringBuilder sb = new StringBuilder(sbBase);
        Query q = em.createNativeQuery(sb.append(" ORDER BY 4").toString(), CandidatoDocente.class);
        return q.getResultList();

    }

    @Override
    public CandidatoDocente getCandidato(Long prfCodigo) {
        StringBuilder sb  = new StringBuilder(sbBase);
        Query q = em.createNativeQuery(sb.append(" WHERE CODIGO_PROFESOR = ? ORDER BY 4").toString(), CandidatoDocente.class);
        q.setParameter(1, prfCodigo);
        return (CandidatoDocente) q.getSingleResult();
    }

}
