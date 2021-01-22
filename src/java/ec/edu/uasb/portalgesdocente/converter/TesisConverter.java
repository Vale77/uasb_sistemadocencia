/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.converter;

import ec.edu.uasb.portalgesdocente.entities.Tesis;
import ec.edu.uasb.portalgesdocente.session.TesisFacadeLocal;
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
@FacesConverter("tesisConverter")
public class TesisConverter implements Converter {

    TesisFacadeLocal tesisFacadeLocal = lookupTesisFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return tesisFacadeLocal.find(Long.parseLong(value));
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
            Tesis c = (Tesis) value;
            if (c.getCodNum() != null) {
                return c.getCodNum().toString();
            }
        }
        return null;
    }

    private TesisFacadeLocal lookupTesisFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (TesisFacadeLocal) c.lookup("java:global/PortalGesDocente/TesisFacade!ec.edu.uasb.portalgesdocente.session.TesisFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
