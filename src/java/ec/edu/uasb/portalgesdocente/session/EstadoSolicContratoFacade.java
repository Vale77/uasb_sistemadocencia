/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.entities.Honorarios;
import ec.edu.uasb.portalgesdocente.entities.TipoEstado;
import ec.edu.uasb.seg.entities.Usuario;
import java.util.ArrayList;
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
public class EstadoSolicContratoFacade extends AbstractFacade<EstadoSolicContrato> implements EstadoSolicContratoFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoSolicContratoFacade() {
        super(EstadoSolicContrato.class);
    }

    @Override
    public void create(EstadoSolicContrato entity) {
        Query q = em.createNativeQuery("select MAX(ESC_NRO_ESTADO) from GESTIONDOCENTE.ESTADO_SOLIC_CONTRATO WHERE CDO_CODIGO = ? and STA_CODIGO = ?");
        q.setParameter(1, entity.getContratoDocente().getCdoCodigo());
        q.setParameter(2, entity.getEstadoSolicContratoPK().getStaCodigo());
        Integer sec = (Integer) q.getSingleResult();
        sec = (sec == null ? 0 : sec) + 1;
        entity.getEstadoSolicContratoPK().setCdoCodigo(entity.getContratoDocente().getCdoCodigo());
        entity.getEstadoSolicContratoPK().setEscNroEstado(sec.shortValue());
        em.persist(entity);
        entity.getContratoDocente().setStaCodigo(entity.getEstadoSolicContratoPK().getStaCodigo());
        em.merge(entity.getContratoDocente());
    }

    @Override
    public void create(EstadoSolicContrato entity, List<Honorarios> viaticos) {
        this.create(entity);
        for (Honorarios viatico : viaticos) {
            viatico.getHonorariosPK().setCdoCodigo(entity.getContratoDocente().getCdoCodigo());
            em.merge(viatico);
        }
    }

    @Override
    public EstadoSolicContrato getUltimoEstadoContrato(Long nroSolic) {
        Query q = em.createNativeQuery("select * from GESTIONDOCENTE.getEstados() WHERE CDO_CODIGO = ? ", EstadoSolicContrato.class);
        q.setParameter(1, nroSolic);
        return (EstadoSolicContrato) q.getSingleResult();
    }

    @Override
    public List<EstadoSolicContrato> getHistoricoContrato(Long nroSolic) {
        Query q = null;
        List<EstadoSolicContrato> estados = new ArrayList<EstadoSolicContrato>();
        StringBuilder sb = new StringBuilder();
        sb.append(" DECLARE @temp_table table(CDO_CODIGO bigint,ESC_NRO_ESTADO smallint,STA_CODIGO varchar(1),ESC_CODIGO_USR bigint,ESC_FECHA datetime, ");
        sb.append(" ESC_OBSERVACION varchar(255),CODIGO bigint,NOMBRES varchar(120),APELLIDOS varchar(120),STA_DESCRIPCION varchar(50),STA_SIGUIENTE_PROCESO varchar(20) ) ");
        sb.append(" INSERT INTO @temp_table SELECT DISTINCT ESTADO_SOLIC_CONTRATO.*,V_USUARIO.CODIGO, V_USUARIO.NOMBRES ,V_USUARIO.APELLIDOS,TIPO_ESTADO.STA_DESCRIPCION,TIPO_ESTADO.STA_SIGUIENTE_PROCESO ");
        sb.append(" FROM (interfaseOcu.GESTIONDOCENTE.ESTADO_SOLIC_CONTRATO  ");
        sb.append(" INNER JOIN interfaseOcu.GESTIONDOCENTE.TIPO_ESTADO ON (ESTADO_SOLIC_CONTRATO.STA_CODIGO = TIPO_ESTADO.STA_CODIGO)) ");
        sb.append(" INNER JOIN interfaseOcu.dbo.V_USUARIO ON (ESTADO_SOLIC_CONTRATO.ESC_CODIGO_USR = V_USUARIO.CODIGO ) ");
        sb.append(" SELECT * FROM @temp_table WHERE CDO_CODIGO = ?  ORDER BY ESC_FECHA DESC ");
        q = em.createNativeQuery(sb.toString(), "EstadosResults");
        q.setParameter(1, nroSolic);
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            EstadoSolicContrato esc = (EstadoSolicContrato) reg[0];
            Usuario usuario = (Usuario) reg[1];
            TipoEstado tEstado = (TipoEstado) reg[2];
            esc.setTipoEstado(tEstado);
            esc.setUsuario(usuario);
            estados.add(esc);
        }
        return estados;
    }
}
