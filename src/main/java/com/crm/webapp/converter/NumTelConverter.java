package com.crm.webapp.converter;

import com.crm.model.NumTel;
import com.crm.service.NumTelManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("numTelConverter")
public class NumTelConverter implements Converter {

    private NumTelManager numTelManager;
    
    @Autowired
    public void setNumTelManager(@Qualifier("numTelManager")NumTelManager numTelManager) {
        this.numTelManager = numTelManager;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(submittedValue);
                NumTel numTel=numTelManager.get(id);
                if(numTel!=null)
                    return numTel;
                
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
            return String.valueOf(((NumTel) value).getId());
        }
    }
}
