/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.session;

import ec.edu.uasb.sisevaluacion.tutoria.entities.EncuestaCalendario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class EncuestaCalendarioFacade extends AbstractFacade<EncuestaCalendario> implements EncuestaCalendarioFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaCalendarioFacade() {
        super(EncuestaCalendario.class);
    }

    @Override
    public void reaperturaEncuesta(String[] asignatura) {
        Query query = em.createNativeQuery("{call EVALUACION.sp_reapertura_encuesta(?,?,?,?,?,?,?,?,?,?,?)}");
        query.setParameter(1, asignatura[1]); // anio
        query.setParameter(2, asignatura[2]);//ciclo
        query.setParameter(3, asignatura[3]);//codmateria
        query.setParameter(4, asignatura[4]);//codprofesor
        query.setParameter(5, asignatura[5]);//codencuesta
        query.setParameter(6, asignatura[6]);//codestudiante
        query.setParameter(7, asignatura[7]);//codigoesp
        query.setParameter(8, asignatura[8]);//codigonivel
        query.setParameter(9, asignatura[9]); //fecininotas
        query.setParameter(10, asignatura[15]); //tipoencuesta
        query.setParameter(11, asignatura[16]); //codigoparalelo
        query.executeUpdate();
    }

    @Override
    public void cierreEncuesta(String[] asignatura) {
        Query query = em.createNativeQuery("{call EVALUACION.sp_cierre_encuesta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        query.setParameter(1, asignatura[1]);//ANIO
        query.setParameter(2, asignatura[2]);//ciclo
        query.setParameter(3, asignatura[3]);//codmateria
        query.setParameter(4, asignatura[4]);//codprofesor
        query.setParameter(5, asignatura[5]);//codencuesta
        query.setParameter(6, asignatura[6]);//codestudiante
        query.setParameter(7, asignatura[7]);//codigoesp
        query.setParameter(8, asignatura[8]);//codigonivel
        query.setParameter(9, asignatura[9]); //TIPO
        query.setParameter(10, asignatura[10]); //PROGRAMA
        query.setParameter(11, asignatura[11]); //MATERIA
        query.setParameter(12, asignatura[12]);//TRIMESTRE 
        query.setParameter(13, asignatura[13]); //EMAIL
        query.setParameter(14, asignatura[14]); //CODIGO COORDINADOR
        query.setParameter(15, asignatura[15]); //CODIGO PARALELO
        query.setParameter(16, asignatura[16]); //NOMBRE PARALELO
        query.executeUpdate();
    }

    @Override
    public void reaperturaEvalCoordADocente(String[] asignatura) {
        Query query = em.createNativeQuery("{call EVALUACION.sp_calendarizacion_encuesta_coordinador(?,?,?,?,?,?)}");
        query.setParameter(1, asignatura[4]);// Codigo Coordinador
        query.setParameter(2, asignatura[7]); //Anio
        query.setParameter(3, asignatura[0]);//Codigo Esp
        query.setParameter(4, asignatura[8]);//Codigo nivel       
        query.setParameter(5, asignatura[11]);//Fecha Inicio
        query.setParameter(6, asignatura[10]);//Codigo_encuesta        
        query.executeUpdate();
    }

    @Override
    public void pagEstampillaEvalEstADocente(String[] asignatura) {
        Query query = em.createNativeQuery("{call EVALUACION.sp_pago_estampilla(?,?,?,?,?)}");
        query.setParameter(1, asignatura[8]);//Anio
        query.setParameter(2, asignatura[4]); //Codigo estudiante
        query.setParameter(3, asignatura[2]);//Codigo Esp
        query.setParameter(4, asignatura[7]);//Codigo nivel       
        query.setParameter(5, asignatura[10]);//Tipo Encuesta

        query.executeUpdate();
    }
}
