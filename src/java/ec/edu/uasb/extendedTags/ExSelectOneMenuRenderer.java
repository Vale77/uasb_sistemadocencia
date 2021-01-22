/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.extendedTags;

import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectonemenu.SelectOneMenuRenderer;

/**
 *
 * @author victor.barba
 */
public class ExSelectOneMenuRenderer extends SelectOneMenuRenderer {

    @Override
    protected void encodeMarkup(FacesContext context, SelectOneMenu menu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        List<SelectItem> selectItems = getSelectItems(context, menu);
        String clientId = menu.getClientId(context);
        Converter converter = menu.getConverter();
        Object values = getValues(menu);
        Object submittedValues = getSubmittedValues(menu);
        boolean valid = menu.isValid();

        String style = menu.getStyle();
        String styleClass = menu.getStyleClass();
        styleClass = styleClass == null ? SelectOneMenu.STYLE_CLASS : SelectOneMenu.STYLE_CLASS + " " + styleClass;
        styleClass = !valid ? styleClass + " ui-state-error" : styleClass;
        styleClass = menu.isDisabled() ? styleClass + " ui-state-disabled" : styleClass;

//        TABLA 
        writer.startElement("table", null);
        writer.startElement("tbody", null);
        writer.startElement("tr", null);
        writer.startElement("td", null);

        writer.startElement("div", menu);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", styleClass, "styleclass");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }

        encodeInput(context, menu, clientId, selectItems, values, submittedValues, converter);
        encodeLabel(context, menu, selectItems);
        encodeMenuIcon(context, menu, valid);
        encodePanel(context, menu, selectItems);
        writer.endElement("div");

        writer.endElement("td");
        writer.startElement("td", null);
            writer.startElement("img", null);
                writer.writeAttribute("id", menu.getClientId() + ":" + "selectMenuCstDivId", "id");
                writer.writeAttribute("class"," opacRequired", null);
                writer.writeAttribute("src", context.getExternalContext().getRequestContextPath()+"/javax.faces.resource/images/aserisk.gif.xhtml", "src");
            writer.endElement("img");
        writer.endElement("td");
        writer.endElement("tr");
        writer.endElement("tbody");
        writer.endElement("table");

    }
}
