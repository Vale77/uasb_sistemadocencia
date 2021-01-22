/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.converter;

import ec.edu.uasb.portalgesdocente.entities.CandidatoDocente;
import ec.edu.uasb.portalgesdocente.session.CandidatoDocenteFacadeLocal;
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
@FacesConverter("candidatoConverter")
public class CandidatoConverter implements Converter {

    CandidatoDocenteFacadeLocal candidatoDocenteFacade = lookupCandidatoDocenteFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return candidatoDocenteFacade.getCandidato(Long.parseLong(value));
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
            CandidatoDocente c = (CandidatoDocente) value;
            if (c.getPrfCodigo() != null) {
                return c.getPrfCodigo().toString();
            }
        }
        return null;
    }

    private CandidatoDocenteFacadeLocal lookupCandidatoDocenteFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (CandidatoDocenteFacadeLocal) c.lookup("java:global/PortalGesDocente/CandidatoDocenteFacade!ec.edu.uasb.portalgesdocente.session.CandidatoDocenteFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
