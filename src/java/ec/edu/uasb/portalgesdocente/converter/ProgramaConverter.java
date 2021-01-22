/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.converter;

import ec.edu.uasb.entities.Programa;
import ec.edu.uasb.entities.ProgramaPK;
import ec.edu.uasb.session.ProgramaFacadeLocal;
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
@FacesConverter("programaConverter")
public class ProgramaConverter implements Converter {

    ProgramaFacadeLocal programaFacadeLocal = lookupProgramaFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return programaFacadeLocal.findByPrg(Long.parseLong(value));
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
            Programa c = (Programa) value;
            if (c.getProgramaPK()!= null) {
                return c.getProgramaPK().getPrgCodigo().toString();
            }
        }
        return null;
    }

    private ProgramaFacadeLocal lookupProgramaFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ProgramaFacadeLocal) c.lookup("java:global/PortalGesDocente/ProgramaFacade!ec.edu.uasb.session.ProgramaFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
