/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.extendedTags;

import java.io.IOException;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.api.InputHolder;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.outputlabel.OutputLabelRenderer;
import org.primefaces.util.HTML;

/**
 *
 * @author victor.barba
 */
public class ExOutputLabelRenderer extends OutputLabelRenderer {

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        OutputLabel label = (OutputLabel) component;
        String clientId = label.getClientId();
        Object value = label.getValue();
        UIComponent target = null;
        String targetClientId = null;
        UIInput input = null;
        String styleClass = label.getStyleClass();
        styleClass = styleClass == null ? OutputLabel.STYLE_CLASS : OutputLabel.STYLE_CLASS + " " + styleClass;
        String _for = label.getFor();

        if (_for != null) {
            target = findTarget(context, label);
//            target = SearchExpressionFacade.resolveComponent(context, label, _for);
            targetClientId = (target instanceof InputHolder) ? ((InputHolder) target).getInputClientId() : target.getClientId(context);

            if (target instanceof UIInput) {
                input = (UIInput) target;

                if (input.getAttributes().get("label") == null || input.getValueExpression("label") == null) {
                    ValueExpression ve = label.getValueExpression("value");
                    if (ve != null) {
                        input.setValueExpression("label", ve);
                    } else {
                        input.getAttributes().put("label", value);
                    }
                }

                if (!input.isValid()) {
                    styleClass = styleClass + " ui-state-error";
                }
            }
        }

        writer.startElement("label", label);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", styleClass, "id");
        renderPassThruAttributes(context, label, HTML.LABEL_ATTRS);

        if (target != null) {
            writer.writeAttribute("for", targetClientId, "for");
        }

        if (value != null) {
            String text = value.toString();

            if (label.isEscape()) {
                writer.writeText(text, "value");
            } else {
                writer.write(text);
            }
        }

        renderChildren(context, label);

        if (input != null && input.isRequired()) {
//            writer.startElement("span", label);
//            writer.writeAttribute("class", OutputLabel.REQUIRED_FIELD_INDICATOR_CLASS, null);
//            writer.write("*");
//            writer.endElement("span");   
            writer.startElement("img", null);
            writer.writeAttribute("id", clientId + ":imgRequired", "id");
            writer.writeAttribute("class", OutputLabel.REQUIRED_FIELD_INDICATOR_CLASS + " opacRequired", null);
            writer.writeAttribute("src", context.getExternalContext().getRequestContextPath() + "/javax.faces.resource/images/aserisk.gif.xhtml", "src");
            writer.endElement("img");
        }

        writer.endElement("label");
    }

}
