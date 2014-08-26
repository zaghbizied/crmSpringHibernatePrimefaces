package com.crm.webapp.converter;

import com.crm.model.TypePaiement;
import com.crm.service.TypePaiementManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("typePaiementConverter")
public class TypePaiementConverter implements Converter {

    private TypePaiementManager typePaiementManager;
    
    @Autowired
    public void setTypePaiementManager(@Qualifier("typePaiementManager")TypePaiementManager typePaiementManager) {
        this.typePaiementManager = typePaiementManager;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(submittedValue);
                TypePaiement typePaiement=typePaiementManager.get(id);
                if(typePaiement!=null)
                    return typePaiement;
                
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((TypePaiement) value).getId());
        }
    }
}
