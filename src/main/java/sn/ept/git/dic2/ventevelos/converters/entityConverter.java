package sn.ept.git.dic2.ventevelos.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("entityConverter")
public class entityConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            Long id = Long.parseLong(value);
            return id;  // Return the ID as the value
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(value);  // Convert ID to String
        }
        return null;
    }
}
