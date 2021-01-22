/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.extendedTags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.component.selectoneradio.SelectOneRadioRenderer;

/**
 *
 * @author victor.barba
 */
public class ExSelectOneRadioRenderer extends SelectOneRadioRenderer {

    @Override
    protected void encodeMarkup(FacesContext context, SelectOneRadio radio) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = radio.getClientId(context);
        String style = radio.getStyle();
        String styleClass = radio.getStyleClass();
        styleClass = styleClass == null ? SelectOneRadio.STYLE_CLASS : SelectOneRadio.STYLE_CLASS + " " + styleClass;
        String layout = radio.getLayout();
        boolean custom = layout != null && layout.equals("custom");
        boolean nroColumns = layout != null && layout.substring(0, layout.length() - 1).equals("nroColumns");

        List<SelectItem> selectItems = getSelectItems(context, radio);

        if (custom) {
            //populate selectitems for radiobutton access
            radio.setSelectItems(getSelectItems(context, radio));

            //render dummy markup to enable processing of ajax behaviors (finding form on client side)
            writer.startElement("span", radio);
            writer.writeAttribute("id", radio.getClientId(context), "id");
            writer.endElement("span");
        } else if (nroColumns) {
            writer.startElement("table", radio);
            writer.writeAttribute("id", clientId, "id");
            writer.writeAttribute("class", styleClass, "styleClass");
            if (style != null) {
                writer.writeAttribute("style", style, "style");
            }

            encodeSelectItemsColumns(context, radio, selectItems, layout);

            writer.endElement("table");

        } else {
            writer.startElement("table", radio);
            writer.writeAttribute("id", clientId, "id");
            writer.writeAttribute("class", styleClass, "styleClass");
            if (style != null) {
                writer.writeAttribute("style", style, "style");
            }

            encodeSelectItems(context, radio, selectItems, layout);

            writer.endElement("table");
        }
    }

    protected void encodeSelectItemsColumns(FacesContext context, SelectOneRadio radio, List<SelectItem> selectItems, String layout) throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        Converter converter = radio.getConverter();
        String name = radio.getClientId(context);
        int columns = Integer.parseInt(layout.substring(layout.length() - 1));

        Object value = radio.getSubmittedValue();
        if (value == null) {
            value = radio.getValue();
        }
        Class type = value == null ? String.class : value.getClass();
        int idx = -1;
        Iterator<SelectItem> selectItemsItr = selectItems.iterator();
        int nroFilas = selectItems.size() / columns;
        writer.startElement("tr", null);
        for (int i = 1; i <= columns; i++) {
            int fila = 0;
            SelectItem selectItem;
            writer.startElement("td", null);
            writer.startElement("table", null);
            while (selectItemsItr.hasNext() && fila < nroFilas+1) {
                selectItem = selectItemsItr.next();
                idx++;
                writer.startElement("tr", null);
                boolean disabled = selectItem.isDisabled() || radio.isDisabled();
                String id = name + UINamingContainer.getSeparatorChar(context) + idx;
                Object coercedItemValue = coerceToModelType(context, selectItem.getValue(), type);
                boolean selected = (coercedItemValue != null) && coercedItemValue.equals(value);
                encodeOption(context, radio, selectItem, id, name, converter, selected, disabled);
                writer.endElement("tr");
                fila++;
            }
            writer.endElement("table");
            writer.endElement("td");
        }
        writer.endElement("tr");

    }
}