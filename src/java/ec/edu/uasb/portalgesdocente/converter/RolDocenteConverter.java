/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.converter;

import ec.edu.uasb.portalgesdocente.entities.RolDocente;
import ec.edu.uasb.portalgesdocente.session.RolDocenteFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author victor.barba
 */
@FacesConverter("rolDocenteConverter")
public class RolDocenteConverter implements Converter {

    RolDocenteFacadeLocal rolDocenteFacadeLocal = lookupRolFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return rolDocenteFacadeLocal.find(value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            RolDocente c = (RolDocente) value;
            if (c.getRdoCodigo()!= null) {
                return c.getRdoCodigo();
            }
        }
        return null;
    }

    private RolDocenteFacadeLocal lookupRolFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (RolDocenteFacadeLocal) c.lookup("java:global/PortalGesDocente/RolDocenteFacade!ec.edu.uasb.portalgesdocente.session.RolDocenteFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
