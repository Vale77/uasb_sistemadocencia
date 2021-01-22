/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.session;

import ec.edu.uasb.entities.Area;
import ec.edu.uasb.entities.Programa;
import ec.edu.uasb.portalgesdocente.entities.CandidatoDocente;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.DatosViajero;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContratoPK;
import ec.edu.uasb.portalgesdocente.entities.Honorarios;
import ec.edu.uasb.portalgesdocente.entities.PorTutoriaMonografia;
import ec.edu.uasb.portalgesdocente.entities.TipoEstado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class ContratoDocenteFacade extends AbstractFacade<ContratoDocente> implements ContratoDocenteFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoDocenteFacade() {
        super(ContratoDocente.class);
    }

    @Override
    public void enviaCorreo(String profile_name, String recipients, String copy_recipients, String blind_copy_recipients, String psubject, String pmensaje, String body_format) throws java.sql.SQLException {
        Query query = em.createNativeQuery("{call msdb.dbo.sp_send_dbmail(?,?,?,?,?,?,?)}");
        query.setParameter(1, profile_name);
        query.setParameter(2, recipients);
        query.setParameter(3, copy_recipients);
        query.setParameter(4, blind_copy_recipients);
        query.setParameter(5, psubject);
        query.setParameter(6, pmensaje);
        query.setParameter(7, body_format);
        try {
            query.executeUpdate();
        } catch (Exception e) {
            throw new java.sql.SQLException(e.getCause());
        }

    }

    @Override
    public boolean getOkNroContrato(String nroContrato) {
        ContratoDocente reg = null;
        Query q = em.createNamedQuery("ContratoDocente.findByCdoNumero").setParameter("cdoNumero", nroContrato);
        try {
            reg = (ContratoDocente) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return (reg != null);
    }

    @Override
    public List<ContratoDocente> findByAreaAndProg(Long area, Long prog, Long anio) {
        List<ContratoDocente> contratos = new ArrayList<ContratoDocente>();
        Query q = em.createNamedQuery("findAllContratosByArea");
        q.setParameter(1, area).setParameter(2, prog).setParameter(3, anio);
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            ContratoDocente ctr = (ContratoDocente) reg[0];
            TipoEstado tEstado = (TipoEstado) reg[1];
            Programa programa = (Programa) reg[3];
            CandidatoDocente cand = (CandidatoDocente) reg[4];
            EstadoSolicContrato esc = (EstadoSolicContrato) reg[5];
            ctr.setEstadoContrato(tEstado.getStaCodigo());
            ctr.setSiguienteProceso(tEstado.getStaSiguienteProceso());
            ctr.setContratado(cand);
            ctr.setEstadoSolicContrato(esc);
            ctr.setProgramaContrato(programa.getNombrePrograma());
            contratos.add(ctr);
        }
        return contratos;
    }

    @Override
    public List<ContratoDocente> findBecasInvestByArea(Long area, Long anio) {
        List<ContratoDocente> contratos = new ArrayList<ContratoDocente>();
        Query q = em.createNamedQuery("findAllContratosBecaIncest");
        q.setParameter(1, area);
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            ContratoDocente ctr = (ContratoDocente) reg[0];
            TipoEstado tEstado = (TipoEstado) reg[1];
            Programa programa = (Programa) reg[3];
            CandidatoDocente cand = (CandidatoDocente) reg[4];
            EstadoSolicContrato esc = (EstadoSolicContrato) reg[5];
            ctr.setEstadoContrato(tEstado.getStaCodigo());
            ctr.setSiguienteProceso(tEstado.getStaSiguienteProceso());
            ctr.setContratado(cand);
            ctr.setEstadoSolicContrato(esc);
            ctr.setProgramaContrato(programa.getNombrePrograma());
            contratos.add(ctr);
        }
        return contratos;
    }

    @Override
    public List<ContratoDocente> findByEstado(String est, String estPerfil, String estAprob, Date desde, Date hasta, Long nroSolic, String tipoListado) {
        Query q = null;
        StringBuilder sb = new StringBuilder();
        List<ContratoDocente> contratos = new ArrayList<ContratoDocente>();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (tipoListado.equals("O")) {
            sb.append(" DECLARE @temp_table table(CODIGO_PROFESOR bigint,CED_PAS_PROFESOR varchar(15),NOMBRE_PROFESOR varchar(100),APELLIDO_PROFESOR varchar(100),DOMICILIO varchar(512)) ")
                    .append(" DECLARE @temp_programas table (ARE_CODIGO bigint, PRG_CODIGO bigint,ANIO int,NOMBRE_PROGRAMA varchar(1024)) ")
                    .append(" insert into @temp_table select CODIGO_PROFESOR,CED_PAS_PROFESOR,NOMBRE_PROFESOR,APELLIDO_PROFESOR,DOMICILIO FROM PROFESOR ")
                    .append(" insert into @temp_programas select DISTINCT ARE_CODIGO, PRG_CODIGO,CAST(SUBSTRING(ANIO, 1, 4) AS INT) AS ANIO,NOMBRE_PROGRAMA FROM V_GRUPO_ASIGNATURA_PROGRAMA ")
                    .append(" SELECT C.*, AREA.NOMBRE_AREA,TIPO_ESTADO.*,E.*,R.*, ")
                    .append("(SELECT P.NOMBRE_PROGRAMA FROM @temp_programas P WHERE P.ARE_CODIGO = C.ARE_CODIGO AND P.ANIO = C.CDO_ANIO_ACAD AND P.PRG_CODIGO = C.PRG_CODIGO) NOMBRE_PROGRAMA,")
                    .append(" PROFESOR.CED_PAS_PROFESOR DNIPRS,PROFESOR.APELLIDO_PROFESOR +' '+ PROFESOR.NOMBRE_PROFESOR  NOMBRES_APELLIDOS,PROFESOR.DOMICILIO ")
                    .append(" FROM GESTIONDOCENTE.CONTRATO_DOCENTE C ")
                    .append(" LEFT OUTER JOIN GESTIONDOCENTE.DATOS_VIAJERO R ON (R.CDO_CODIGO = C.CDO_CODIGO and R.DVI_TIPO_REGISTRO = 'R') ")
                    .append(" INNER JOIN GESTIONDOCENTE.TIPO_ESTADO ON (TIPO_ESTADO.STA_CODIGO = C.STA_CODIGO) ")
                    .append(" INNER JOIN (SELECT ARE_CODIGO,NOMBRE_AREA FROM AREA ")
                    .append(" UNION SELECT DEP_CODIGO, DEP_NOMBRE  FROM DEPARTAMENTO_ADMI where DEP_CODIGO = 17) AREA ON (AREA.ARE_CODIGO = C.ARE_CODIGO) ")
                    .append(" INNER JOIN @temp_table PROFESOR  ON (C.PRF_CODIGO = PROFESOR.CODIGO_PROFESOR) ")
                    .append(" INNER JOIN GESTIONDOCENTE.getEstados() E ON (E.CDO_CODIGO = C.CDO_CODIGO)")
                    .append(" WHERE IIF(isnull(").append(nroSolic).append(",0) = 0,0,C.CDO_CODIGO) = IIF(isnull(").append(nroSolic).append(",0) = 0,0,").append(nroSolic).append(")");
        } else {
            sb.append("SELECT C.*,TIPO_ESTADO.*,DEPARTAMENTO_ADMI.DEP_NOMBRE NOMBRE_AREA,E.*,'' NOMBRE_PROGRAMA, ")
                    .append(" PER_ID_DOC DNIPRS, PER_PRIMER_APELLIDO+ ISNULL(' '+PER_SEGUNDO_APELLIDO,'')+' '+PER_NOMBRES NOMBRES_APELLIDOS,PER_DIREC_DOM DOMICILIO, ")
                    .append(" INVESTIGACION.TITULO,INVESTIGACION.CAI_DESCRIPCION,INVESTIGACION.FECHA_APROB_INFO ")
                    .append(" FROM GESTIONDOCENTE.CONTRATO_DOCENTE C ")
                    .append(" INNER JOIN interfaseOcu.dbo.PRIN_PERSONA ON (C.PRF_CODIGO = PRIN_PERSONA.PER_CODIGO ) ")
                    .append(" INNER JOIN GESTIONDOCENTE.TIPO_ESTADO ON (TIPO_ESTADO.STA_CODIGO = C.STA_CODIGO) ")
                    .append(" INNER JOIN interfaseOcu.dbo.DEPARTAMENTO_ADMI ON (C.ARE_CODIGO = DEPARTAMENTO_ADMI.DEP_CODIGO ) ")
                    .append(" INNER JOIN GESTIONDOCENTE.getEstados() E ON (E.CDO_CODIGO = C.CDO_CODIGO) ")
                    .append(" INNER JOIN (SELECT PRY_NUMERO_SOLIC_CONTRATO,isnull(INVE_PROYECTO.PRY_TITULO_FINAL,INVE_PROYECTO.PRY_TITULO) TITULO, ")
                    .append("   PRIN_CATEGORIA_INVEST.CAI_DESCRIPCION,INFO_APROB.FECHA_APROB_INFO ")
                    .append("   FROM interfaseOcu.dbo.INVE_PROYECTO ")
                    .append("   INNER JOIN interfaseOcu.dbo.PRIN_CATEGORIA_INVEST ON (INVE_PROYECTO.CAI_CODIGO = PRIN_CATEGORIA_INVEST.CAI_CODIGO) ")
                    .append("   INNER JOIN ( SELECT PRY_CODIGO,SEG_FECHA_COMUNIC FECHA_APROB_INFO, ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                    .append("                             FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 3 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A' ) INFO_APROB ")
                    .append("                   ON INFO_APROB.NUM_FILA = 1 and INFO_APROB.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO ) INVESTIGACION ")
                    .append("  ON (INVESTIGACION.PRY_NUMERO_SOLIC_CONTRATO = C.CDO_CODIGO) ")
                    .append(" WHERE IIF(isnull(").append(nroSolic).append(",0) = 0,0,C.CDO_CODIGO) = IIF(isnull(").append(nroSolic).append(",0) = 0,0,").append(nroSolic).append(")");
        }
        if (est.equals("T")) {
            sb = sb.append(" AND CONVERT(DATETIME,CONVERT(VARCHAR,C.CDO_FECHA_CREA, 103)) BETWEEN CONVERT(DATETIME,'").append(df.format(desde)).append("') and CONVERT(DATETIME,'").append(df.format(hasta)).append("')");
            sb.append("  ORDER BY C.CDO_CODIGO ");
            q = em.createNativeQuery(sb.toString(), tipoListado.equals("O") ? "ContratosResultsEstado" : "ContratosBecasInvestResultsEstado");
            q.setParameter(1, estPerfil).setParameter(2, estAprob);
        } else {
            sb = sb.append(" AND C.STA_CODIGO in (?) and CONVERT(DATETIME,CONVERT(VARCHAR,C.CDO_FECHA_CREA, 103)) BETWEEN CONVERT(DATETIME,'").append(df.format(desde)).append("') and CONVERT(DATETIME,'").append(df.format(hasta)).append("')");
            sb.append("  ORDER BY C.CDO_CODIGO ");
            q = em.createNativeQuery(sb.toString(), tipoListado.equals("O") ? "ContratosResultsEstado" : "ContratosBecasInvestResultsEstado");
            q.setParameter(1, est);
        }

//        System.out.println(sb.toString());
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            ContratoDocente ctr = (ContratoDocente) reg[0];
            TipoEstado tEstado = (TipoEstado) reg[1];
            Area area = (Area) reg[2];
            Programa programa = (Programa) reg[3];
            CandidatoDocente cand = (CandidatoDocente) reg[4];
            EstadoSolicContrato esc = (EstadoSolicContrato) reg[5];
            if (tipoListado.equals("I")) {
                ctr.setTituloInvest(reg[6] == null ? null : reg[6].toString());
                ctr.setCategInvest(reg[7] == null ? null : reg[7].toString());
                try {
                    ctr.setFechaAprobInvest(reg[8] == null ? null : dateFormat.parse(reg[8].toString()));
                } catch (ParseException ex) {
                    Logger.getLogger(ContratoDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ctr.setEstadoContrato(tEstado.getStaCodigo());
            ctr.setProgramaContrato(programa.getNombrePrograma());
            ctr.setAreaContrato(area.getNombreArea());
            ctr.setContratado(cand);
            ctr.setEstadoSolicContrato(esc);
            ctr.setSiguienteProceso(tEstado.getStaSiguienteProceso());
            contratos.add(ctr);
        }

        return contratos;
    }

    @Override
    public List<ContratoDocente> findAprobadosByEstado(String est, Date desde) {
        Query q = null;
        StringBuilder sb = new StringBuilder();
        List<ContratoDocente> contratos = new ArrayList<ContratoDocente>();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        sb.append(" DECLARE @temp_table table(CODIGO_PROFESOR bigint,CED_PAS_PROFESOR varchar(15),NOMBRE_PROFESOR varchar(100),APELLIDO_PROFESOR varchar(100),DOMICILIO varchar(512)) ");
        sb.append(" DECLARE @temp_programas table (ARE_CODIGO bigint, PRG_CODIGO bigint,ANIO int,NOMBRE_PROGRAMA varchar(1024)) ");
        sb.append(" insert into @temp_table select CODIGO_PROFESOR,CED_PAS_PROFESOR,NOMBRE_PROFESOR,APELLIDO_PROFESOR,DOMICILIO FROM PROFESOR ");
        sb.append(" insert into @temp_programas select DISTINCT ARE_CODIGO, PRG_CODIGO,CAST(SUBSTRING(ANIO, 1, 4) AS INT) AS ANIO,NOMBRE_PROGRAMA FROM V_GRUPO_ASIGNATURA_PROGRAMA ");
        sb.append(" SELECT C.*, AREA.NOMBRE_AREA,TIPO_ESTADO.*,E.*,R.*, ");
        sb.append("(SELECT P.NOMBRE_PROGRAMA FROM @temp_programas P WHERE P.ARE_CODIGO = C.ARE_CODIGO AND P.ANIO = C.CDO_ANIO_ACAD AND P.PRG_CODIGO = C.PRG_CODIGO) NOMBRE_PROGRAMA,");
        sb.append(" PROFESOR.CED_PAS_PROFESOR DNIPRS, PROFESOR.NOMBRE_PROFESOR +' '+ PROFESOR.APELLIDO_PROFESOR NOMBRES_APELLIDOS,PROFESOR.DOMICILIO ");
        sb.append(" FROM GESTIONDOCENTE.CONTRATO_DOCENTE C ");
        sb.append(" INNER JOIN GESTIONDOCENTE.DATOS_VIAJERO R ON (R.CDO_CODIGO = C.CDO_CODIGO and R.DVI_TIPO_REGISTRO = 'R') ");
        sb.append(" INNER JOIN GESTIONDOCENTE.TIPO_ESTADO ON (TIPO_ESTADO.STA_CODIGO = C.STA_CODIGO) ");
        sb.append(" INNER JOIN AREA ON (AREA.ARE_CODIGO = C.ARE_CODIGO) ");
        sb.append(" INNER JOIN @temp_table PROFESOR  ON (C.PRF_CODIGO = PROFESOR.CODIGO_PROFESOR) ");
        sb.append(" INNER JOIN GESTIONDOCENTE.getEstados() E ON (E.CDO_CODIGO = C.CDO_CODIGO)");
        sb = sb.append(" WHERE C.STA_CODIGO in (?) and E.ESC_FECHA >= '").append(df.format(desde)).append("'");
        sb.append("  ORDER BY PROFESOR.APELLIDO_PROFESOR,PROFESOR.NOMBRE_PROFESOR");
        q = em.createNativeQuery(sb.toString(), "ContratosResultsEstado");
        q.setParameter(1, est);

        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            ContratoDocente ctr = (ContratoDocente) reg[0];
            TipoEstado tEstado = (TipoEstado) reg[1];
            Area area = (Area) reg[2];
            Programa programa = (Programa) reg[3];
            CandidatoDocente cand = (CandidatoDocente) reg[4];
            EstadoSolicContrato esc = (EstadoSolicContrato) reg[5];
//            DatosViajero resid = (DatosViajero) reg[6];
            ctr.setEstadoContrato(tEstado.getStaCodigo());
            ctr.setProgramaContrato(programa.getNombrePrograma());
            ctr.setAreaContrato(area.getNombreArea());
            ctr.setContratado(cand);
            ctr.setEstadoSolicContrato(esc);
//            ctr.setResidencia(resid);
            ctr.setSiguienteProceso(tEstado.getStaSiguienteProceso());
            contratos.add(ctr);
        }

        return contratos;
    }

    //<editor-fold defaultstate="collapsed" desc="CRUD">
    @Override
    public void remove(ContratoDocente entity) {
        Query q = null;
        q = em.createNativeQuery("DELETE FROM GESTIONDOCENTE.HONORARIOS WHERE CDO_CODIGO = ?").setParameter(1, entity.getCdoCodigo());
        q.executeUpdate();
        q = em.createNativeQuery("DELETE FROM GESTIONDOCENTE.ESTADO_SOLIC_CONTRATO WHERE CDO_CODIGO = ?").setParameter(1, entity.getCdoCodigo());
        q.executeUpdate();
        q = em.createNativeQuery("DELETE FROM GESTIONDOCENTE.DATOS_VIAJERO WHERE CDO_CODIGO = ?").setParameter(1, entity.getCdoCodigo());
        q.executeUpdate();
        q = em.createNativeQuery("DELETE FROM GESTIONDOCENTE.POR_TUTORIA_MONOGRAFIA WHERE CDO_CODIGO = ?").setParameter(1, entity.getCdoCodigo());
        q.executeUpdate();
        super.remove(entity);
    }

    @Override
    public void createContrato(ContratoDocente entity, EstadoSolicContrato esc) {
        // Asignar numero de contrato
        em.merge(entity);
        esc.setContratoDocente(entity);
        esc.setEscCodigoUsr(entity.getCdoUsuModif());
        esc.setEscFecha(entity.getCdoFechaModif());
        em.persist(esc);
    }

    @Override
    public void create(ContratoDocente entity, List<Honorarios> viaticos, List<PorTutoriaMonografia> tutorias,
            DatosViajero datViajero, DatosViajero residencia) {
        entity.setStaCodigo("I");
        em.persist(entity);
        em.flush();
        for (Honorarios viatico : viaticos) {
            viatico.getHonorariosPK().setCdoCodigo(entity.getCdoCodigo());
            em.persist(viatico);
        }
        for (PorTutoriaMonografia tut : tutorias) {
            tut.getContratoDocente().setCdoCodigo(entity.getCdoCodigo());
            em.persist(tut);
        }
        if (datViajero.getDviTipoPasaje() != null) {
            datViajero.setContratoDocente(entity);
            datViajero.getDatosViajeroPK().setCdoCodigo(entity.getCdoCodigo());
            em.persist(datViajero);
        }
        if (residencia.getRubros() != null && residencia.getRubros().getRubCodigo() != null) {
            residencia.setContratoDocente(entity);
            residencia.getDatosViajeroPK().setCdoCodigo(entity.getCdoCodigo());
            em.persist(residencia);
        }
        EstadoSolicContrato esc = new EstadoSolicContrato();
        esc.setContratoDocente(entity);
        esc.setEscCodigoUsr(entity.getCdoUsuCrea());
        esc.setEscFecha(entity.getCdoFechaCrea());
        esc.setEstadoSolicContratoPK(new EstadoSolicContratoPK(entity.getCdoCodigo(), new Short("1"), "I"));
        em.persist(esc);
    }

    @Override
    public void edit(ContratoDocente entity, List<Honorarios> viaticos, List<PorTutoriaMonografia> tutorias,
            DatosViajero datViajeroEditado, DatosViajero residencia) {
        em.merge(entity);
        em.flush();
        for (Honorarios viatico : viaticos) {
            if (viatico.getHonorariosPK().getCdoCodigo() == null) {
                viatico.getHonorariosPK().setCdoCodigo(entity.getCdoCodigo());
                em.persist(viatico);
            } else {
                em.merge(viatico);
            }
        }
        for (PorTutoriaMonografia tut : tutorias) {
            if (tut.getContratoDocente().getCdoCodigo() == null) {
                tut.getContratoDocente().setCdoCodigo(entity.getCdoCodigo());
                em.persist(tut);
            } else {
                em.merge(tut);
            }
        }
        if (datViajeroEditado.getDviTipoPasaje() != null) {
            em.merge(datViajeroEditado);
        } else {
            DatosViajero d = em.find(DatosViajero.class, datViajeroEditado.getDatosViajeroPK());
            if (d != null) {
                em.remove(d);
            }
        }
        if (residencia.getRubros() != null && residencia.getRubros().getRubCodigo() != null) {
            em.merge(residencia);
        } else {
            DatosViajero r = em.find(DatosViajero.class, residencia.getDatosViajeroPK());
            if (r != null) {
                em.remove(r);
            }
        }

    }
    //</editor-fold>

    @Override
    public String getMensaje(ContratoDocente contratoDocente) {
        StringBuilder sb = new StringBuilder();

        if (contratoDocente.getRolDocente().getRdoCodigo().equals("I")) {
            sb.append("SELECT ").append("'<span style=\"font-weight:bold;padding-right:5px;").append("\">Solicitud  N°: ").append(" </span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_CODIGO)+").append("'</br></br>'+")
                    .append("'<span style=\"font-weight:bold;").append("\">Area: ").append("</span>'").append("+DEPARTAMENTO_ADMI.DEP_NOMBRE+'</br>'+")
                    .append("'<span style=\"font-weight:bold;").append("\">Título de investigación: ").append("</span>'").append("+INVESTIGACION.TITULO+'</br>'+")
                    .append("'<span style=\"font-weight:bold;").append("\">Investigador: ").append("</span>'").append("+PER_PRIMER_APELLIDO+ ISNULL(' '+PER_SEGUNDO_APELLIDO,'')+' '+PER_NOMBRES+'</br>'+")
                    .append("'<span style=\"font-weight:bold;").append("\">Tipo de Fondo: ").append("</span>'").append("+INVESTIGACION.CAI_DESCRIPCION+").append("'</br>'+")
                    .append("'<span style=\"font-weight:bold;").append("\">Inicio: ").append("</span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_FECINI,106)+").append("'</br>'+")
                    .append("'<span style=\"font-weight:bold;").append("\">Fin: ").append("</span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_FECFIN,106)+").append("'</br>'+")
                    .append("'<span style=\"font-weight:bold;").append("\">Plazo: ").append("</span>'").append("+'Investigación aprobada por el Comité de Investigaciones el '+convert(varchar,INVESTIGACION.FECHA_APROB_INFO,106)+").append("'</br>'")
                    .append(" FROM GESTIONDOCENTE.CONTRATO_DOCENTE ")
                    .append(" INNER JOIN interfaseOcu.dbo.PRIN_PERSONA ON (CONTRATO_DOCENTE.PRF_CODIGO = PRIN_PERSONA.PER_CODIGO )  ")
                    .append(" INNER JOIN GESTIONDOCENTE.TIPOS_FORMA_PAGO on GESTIONDOCENTE.TIPOS_FORMA_PAGO.TFP_CODIGO = CONTRATO_DOCENTE.TFP_CODIGO ")
                    .append(" INNER JOIN interfaseOcu.dbo.DEPARTAMENTO_ADMI ON (CONTRATO_DOCENTE.ARE_CODIGO = DEPARTAMENTO_ADMI.DEP_CODIGO )  ")
                    .append(" INNER JOIN (SELECT PRY_NUMERO_SOLIC_CONTRATO,isnull(INVE_PROYECTO.PRY_TITULO_FINAL,INVE_PROYECTO.PRY_TITULO) TITULO,PRIN_CATEGORIA_INVEST.CAI_DESCRIPCION,INFO_APROB.FECHA_APROB_INFO ")
                    .append("               FROM interfaseOcu.dbo.INVE_PROYECTO ")
                    .append("                INNER JOIN interfaseOcu.dbo.PRIN_CATEGORIA_INVEST ON (INVE_PROYECTO.CAI_CODIGO = PRIN_CATEGORIA_INVEST.CAI_CODIGO) ")
                    .append("               INNER JOIN ( SELECT PRY_CODIGO,SEG_FECHA_COMUNIC FECHA_APROB_INFO, ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                    .append("                               FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 3 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A' ) INFO_APROB")
                    .append("                   ON INFO_APROB.NUM_FILA = 1 and INFO_APROB.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO ) INVESTIGACION ")
                    .append("       ON (INVESTIGACION.PRY_NUMERO_SOLIC_CONTRATO = CONTRATO_DOCENTE.CDO_CODIGO) ")
                    .append("  WHERE CONTRATO_DOCENTE.CDO_CODIGO = ? ");
        } else {
            if (contratoDocente.getRolDocente().getRdoCodigo().equals("D")) {
                sb.append("SELECT ").append("'<span style=\"font-weight:bold;padding-right:5px;").append("\">Solicitud N°: ").append(" </span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_CODIGO)+'</br>'+V_GRUPO_ASIGNATURA_PROGRAMA.ARE_NOMBRE+'</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Programa: ").append("</span>'").append("+V_GRUPO_ASIGNATURA_PROGRAMA.NOMBRE_PROGRAMA+'</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Asignatura: ").append("</span>'").append("+V_GRUPO_ASIGNATURA_PROGRAMA.ID_ASSIGNATURA+' - '+V_GRUPO_ASIGNATURA_PROGRAMA.ASS_NOMBRE+' - '+V_GRUPO_ASIGNATURA_PROGRAMA.GRP_NOMBRE+'</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Año académico: ").append("</span>'").append("+CICLO_ACADEMICO.NOMBRE_CICLO+'</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Por: ").append("</span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_CANT_UNIDAD)").append("+' '+RUBROS.RUB_UNIMEDIDA+'s</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Desde: ").append("</span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_FECINI,106)+' Hasta: '+convert(varchar,CONTRATO_DOCENTE.CDO_FECFIN,106)+'</br>'")
                        .append(" FROM GESTIONDOCENTE.CONTRATO_DOCENTE CONTRATO_DOCENTE")
                        .append(" INNER JOIN V_GRUPO_ASIGNATURA_PROGRAMA V_GRUPO_ASIGNATURA_PROGRAMA")
                        .append(" ON (CONTRATO_DOCENTE.ARE_CODIGO = V_GRUPO_ASIGNATURA_PROGRAMA.ARE_CODIGO)")
                        .append(" AND (CONTRATO_DOCENTE.PRG_CODIGO = V_GRUPO_ASIGNATURA_PROGRAMA.PRG_CODIGO)")
                        .append(" AND (CONTRATO_DOCENTE.ASG_CODIGO = V_GRUPO_ASIGNATURA_PROGRAMA.ASS_CODIGO)")
                        .append(" AND (CONTRATO_DOCENTE.PRL_CODIGO = V_GRUPO_ASIGNATURA_PROGRAMA.GRP_CODIGO)")
                        .append(" AND (CONTRATO_DOCENTE.ASP_CODIGO = V_GRUPO_ASIGNATURA_PROGRAMA.ACT_CODIGO)")
                        .append(" INNER JOIN CICLO_ACADEMICO CICLO_ACADEMICO ON (CONTRATO_DOCENTE.CDO_ANIO_ACAD = CICLO_ACADEMICO.ANIO)")
                        .append(" INNER JOIN GESTIONDOCENTE.ROL_DOCENTE ROL_DOCENTE ON (ROL_DOCENTE.RDO_CODIGO = CONTRATO_DOCENTE.RDO_CODIGO)")
                        .append(" INNER JOIN GESTIONDOCENTE.RUBROS RUBROS ON (ROL_DOCENTE.RUB_CODIGO = RUBROS.RUB_CODIGO)")
                        .append("  WHERE CONTRATO_DOCENTE.CDO_CODIGO = ?");
            } else {
                sb.append("SELECT ").append("'<span style=\"font-weight:bold;padding-right:5px;").append("\">Solicitud  N°: ").append(" </span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_CODIGO)+'</br>'+ AREA.NOMBRE_AREA+'</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Programa: ").append("</span>'").append("+PROGRAMA.NOMBRE_PROGRAMA+'</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Año académico: ").append("</span>'").append("+CICLO_ACADEMICO.NOMBRE_CICLO+'</br>'+")
                        .append("'<span style=\"font-weight:bold;").append("\">Por: ").append("</span>'").append("+convert(varchar,CONTRATO_DOCENTE.CDO_CANT_UNIDAD)").append("+' '+RUBROS.RUB_UNIMEDIDA+'</br>'")
                        .append("  FROM ((((interfaseOcu.GESTIONDOCENTE.CONTRATO_DOCENTE CONTRATO_DOCENTE ")
                        .append(" INNER JOIN interfaseOcu.dbo.PROGRAMA PROGRAMA  ON (PROGRAMA.ARE_CODIGO = CONTRATO_DOCENTE.ARE_CODIGO) ")
                        .append(" AND (PROGRAMA.PRG_CODIGO = CONTRATO_DOCENTE.PRG_CODIGO) and (PROGRAMA.ANIO =  CONTRATO_DOCENTE.CDO_ANIO_ACAD)) ")
                        .append(" INNER JOIN interfaseOcu.dbo.AREA AREA ON (AREA.ARE_CODIGO = PROGRAMA.ARE_CODIGO)) ")
                        .append(" INNER JOIN interfaseOcu.dbo.CICLO_ACADEMICO CICLO_ACADEMICO  ON (CONTRATO_DOCENTE.CDO_ANIO_ACAD = CICLO_ACADEMICO.ANIO)) ")
                        .append(" INNER JOIN interfaseOcu.GESTIONDOCENTE.ROL_DOCENTE ROL_DOCENTE ON (CONTRATO_DOCENTE.RDO_CODIGO = ROL_DOCENTE.RDO_CODIGO)) ")
                        .append(" INNER JOIN interfaseOcu.GESTIONDOCENTE.RUBROS RUBROS  ON (ROL_DOCENTE.RUB_CODIGO = RUBROS.RUB_CODIGO) ")
                        .append("  WHERE CONTRATO_DOCENTE.CDO_CODIGO = ? ");

            }
        }
        Query q = em.createNativeQuery(sb.toString()).setParameter(1, contratoDocente.getCdoCodigo());
        return (String) q.getSingleResult();

    }

}
