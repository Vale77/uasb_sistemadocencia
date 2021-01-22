/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.session;

import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class ConsultaFacade implements ConsultaFacadeLocal {

    @PersistenceContext(unitName = "interfaseOCU_PU")
    private EntityManager em;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String separadorPath;
    private static String carpetaCompartida = "opt";

    JasperPrint jasperPrint;
    private String reportPath = null;
    private String destReportPath = null;

    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isMac() {
        return (OS.contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }

    private static boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    private static String separadorOS() {
        if (isWindows()) {
            return "\\";
        } else if (isUnix() || isSolaris() || isMac()) {
            return "//";
        } else {
            return "";
        }
    }

    private String getPathDoc() {
        separadorPath = separadorOS();
        return (isWindows() ? "\\\\VICTORBARBA" : "") + separadorPath + carpetaCompartida + separadorPath + "DATA" + separadorPath + "imagenes" + separadorPath
                + "CONTRATOS_DOCENTES";
    }

    private String getPath() {
        separadorPath = separadorOS();
        return (isWindows() ? "\\\\VICTORBARBA" : "") + separadorPath + carpetaCompartida + separadorPath + "DATA" + separadorPath;
    }

    private String getSeparadorPath() {
        return separadorOS();
    }

    private void getPathReporte() {
        separadorPath = separadorOS();
        reportPath = (isWindows() ? "\\\\VICTORBARBA" : "") + separadorPath + carpetaCompartida + separadorPath + "Contratos";
        destReportPath = (isWindows() ? "\\\\VICTORBARBA" : "") + separadorPath + carpetaCompartida + separadorPath + "DATA" + separadorPath + "contratos";
    }

    @Override
    public Vector ejecutaSqlList(String sql) {
        return (Vector) em.createNativeQuery(sql).setParameter(1, sql).getResultList();
    }

    @Override
    public List<String[]> trimestreAnio(Long anio) {
        ArrayList<String[]> lresult = new ArrayList<String[]>();
        Vector v = new Vector();
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct codigo_nivel,  (case codigo_nivel when 1 then 'PRIMERO' when 2 then 'SEGUNDO' when 3 then 'TERCERO' when 4 then 'CUARTO' else '' end) trimestre"
                + " from NIVEL_ESPECIALIZACION"
                + " where anio = ").append(anio);
        v = (Vector) ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[2];
                asign[0] = object[0].toString();
                asign[1] = object[1].toString();
                lresult.add(asign);
            }
        }
        return lresult;
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

    public void GenRep(FacesContext fc,String subsist, String reporte, ContratoDocente contrato) {
        Connection conn = null;
        Map<String, Object> params = new HashMap<String, Object>();
        String rep;
        getPathReporte();
        try {
            params.put(JRParameter.REPORT_LOCALE, new Locale("es_EC"));
            params.put("REPORT_PATH_IMAGES", reportPath + separadorPath);
            params.put("nroSolic", contrato.getCdoCodigo().toString());

            rep = fc.getExternalContext().getRealPath("/reportes"+ separadorPath  + reporte + ".jasper");
            System.out.println("GenRep "+rep);
            conn = em.unwrap(java.sql.Connection.class);
            jasperPrint = JasperFillManager.fillReport(rep, params, conn);
            System.out.println("GenRep "+destReportPath + separadorPath + contrato.getCdoCodigo()+"_"+contrato.getCdoNumero() + "_temp.pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint, destReportPath + separadorPath + contrato.getCdoCodigo()+"_"+contrato.getCdoNumero() + "_temp.pdf");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException jrex) {
            Logger.getLogger(ConsultaFacade.class.getName()).log(Level.SEVERE, null, jrex);
        } finally {
            closeConnection(conn);
            params = null;
        }

    }

    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            //  System.out.println("Developer Msg : Exception in printReport1Servlet.closeConnection()");  
        }
    }

}
