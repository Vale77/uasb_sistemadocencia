/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.bean;

import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.seg.session.UsuarioFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import ec.edu.uasb.utils.LDAPAuthentication;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "loginMgmtBean")
@RequestScoped
public class LoginJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120423L;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private String userName;
    private String password;

    //<editor-fold defaultstate="collapsed" desc="Propiedades ">
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>

    /**
     * Creates a new instance of LoginJSFManagedBean
     */
    public LoginJSFManagedBean() {
    }

    public String login(String subsist) {
        String prfs = null;
        java.util.List<String> msgs = new ArrayList<String>();
        boolean loggedIn = false;
        FacesContext fc = FacesContext.getCurrentInstance();
        boolean ok;
        try {
            LDAPAuthentication lDAPAuthentication = new LDAPAuthentication();
            ok = lDAPAuthentication.authenticateLDAPUser(getUserName(), getPassword(), "uasb.edu.ec", "172.16.21.43:389", "dc=uasb,dc=edu,dc=ec");
            if (ok) {
                Usuario user = usuarioFacade.getPerson(getUserName());
//                if (user.getLoginStatus().equals(LoginStatus.SUCCESSFUL)) {
                try {
                    prfs = JsfUtil.getPerfilPermitido();
//                    user.setUsuCodigo(new Long("16262"));
//                    System.out.println("user.getUsuCodigo() "+user.getUsuCodigo());
                    List<Perfil> perfiles = usuarioFacade.getPerfil(user.getUsuCodigo(), prfs);
                    System.out.println(perfiles);
                    if (!perfiles.isEmpty()) {
                        String filtrarAreas = parametrizarFiltro(perfiles);
                        loggedIn = true;
                        fc.getExternalContext().getSessionMap().put("logined", loggedIn);
                        fc.getExternalContext().getSessionMap().put("user", user);
                        fc.getExternalContext().getSessionMap().put("subsist", subsist);
                        fc.getExternalContext().getSessionMap().put("perfiles", perfiles);
                        fc.getExternalContext().getSessionMap().put("filtrarAreas", filtrarAreas);
                        fc.getExternalContext().getFlash().setKeepMessages(true);
                        ResourceBundle rb = ResourceBundle.getBundle("i18n_" + FacesContext.getCurrentInstance().getELContext().getLocale());
                        JsfUtil.addSuccessMessage(null, rb.getObject("app.label.topNav.welcome").toString());
                        return "/principal?faces-redirect=true";
                    } else {
                        msgs.add("No tiene un perfil asignado. Por favor, contacte a su administrador.");
                    }
                } catch (Exception e) {
                    msgs.add("No puedo obtener un perfil. " + e.getMessage() + " Por favor, informe a su administrador.");
                }
//                } else {
//                    msgs.add("Cuenta Desabilitada. Por favor, contacte a su administrador.");
//                }
            }
        } catch (Exception ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            msgs.add(ex.getCause().getMessage());
            if (ex.getCause().getCause().getClass().equals(AuthenticationException.class)) {
                msgs.add("Verifique si su teclado esta en Mayúsculas ó Minúsculas");
            }
        } finally {
            JsfUtil.addErrorMessages(null, msgs);
        }
        RequestContext.getCurrentInstance().addCallbackParam("loggedIn", loggedIn);
        return null;
    }

    private String parametrizarFiltro(List<Perfil> perfiles) {
        for (Perfil pf : perfiles) {
            if (pf.getIdPerfil().equals("USU_SIST")) {//  comparar con USU_SIST  
                return "T";
            } else if (pf.getIdPerfil().equals("SEC_AREA")) {// para filtro de areas comparar con SEC_AREA  
                return "A";
            }
        }
        return null;
    }

    public String logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().clear();
        ec.invalidateSession();
        return "/principal?faces-redirect=true";

    }

    public String cancelar() {
        userName = null;
        password = null;
        return null;
    }
}
