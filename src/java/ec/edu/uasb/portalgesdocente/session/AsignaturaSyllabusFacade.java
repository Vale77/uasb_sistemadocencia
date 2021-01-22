/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;


import ec.edu.uasb.portalgesdocente.entities.AsignaturaSyllabus;
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
public class AsignaturaSyllabusFacade extends AbstractFacade<AsignaturaSyllabus> implements AsignaturaSyllabusFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaSyllabusFacade() {
        super(AsignaturaSyllabus.class);
    }

    @Override
    public List<AsignaturaSyllabus> getAsignatSyllabus(Long anio, Long codArea, Long codProg) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCT VCP.ANIO, VCP.CICLO, VCP.CODIGO_MATERIA, VCP.CODIGO_NIVEL, VCP.VAC_CODNUM,VCP.COD_PARALELO, VCP.CODIGO_PROFESOR,");
        sb.append(" VCP.NOMBRE_MATERIA, VCP.IDENTIF_MATERIA,VCP.NOMBRE_PARALELO, VCP.TRIMESTRE NOMBRE_NIVEL, VCP.RESPONSABLE_MATERIA, VCP.CODIGO_ESP, ");
        sb.append(" SY.ESTADO_SYLABUS, SY.APROBADO_POR, SY.ESTADO_ENVIO, SY.OBSERVACION_COORDINADOR, VCP.AREA, VCP.PROGRAMA,VCP.CODIGO_COORDINADOR, ");
        sb.append(" NCREDITOS*5 HORAS_MATERIA_DICTAR, NCREDITOS,VCP.APELLIDO_PROFESOR,VCP.NOMBRE_PROFESOR,");
        sb.append(" (SELECT distinct CONCAT(EMAIL,';')  AS [data()] FROM V_SECRETARIA WHERE ARE_CODIGO = VCP.CODIGO_FACULTAD and PRG_CODIGO = VCP.CODIGO_ESP For XML PATH (''))  EMAIL_AREA ");
        sb.append(" FROM VISTA_COORDINADOR_PROGRAMA VCP LEFT OUTER JOIN SYLABUS_DOCENTE SY ON VCP.CODIGO_PROFESOR = SY.CODIGO_PROFESOR AND ");
        sb.append(" VCP.CODIGO_NIVEL = SY.CODIGO_NIVEL AND VCP.CODIGO_MATERIA = SY.CODIGO_MATERIA AND VCP.CODIGO_ESP = SY.COD_PROGRAMA AND ");
        sb.append(" VCP.ANIO = SY.ANIO AND VCP.CICLO = SY.CICLO AND VCP.VAC_CODNUM = SY.VAC_CODNUM AND VCP.COD_PARALELO = SY.COD_PARALELO ");
        sb.append(" WHERE  VCP.ANIO = ? AND VCP.CICLO = 1 AND VCP.RESPONSABLE_MATERIA = 'S'  AND VCP.CODIGO_FACULTAD = ? AND VCP.CODIGO_ESP =   ? ");
        sb.append(" ORDER BY  VCP.PROGRAMA,VCP.NOMBRE_MATERIA, VCP.CODIGO_NIVEL,VCP.COD_PARALELO ");

        Query q = em.createNativeQuery(sb.toString(), AsignaturaSyllabus.class);
        q.setParameter(1, anio).setParameter(2, codArea).setParameter(3, codProg);
        List<AsignaturaSyllabus> tmp = q.getResultList();
        return tmp;
    }


}
