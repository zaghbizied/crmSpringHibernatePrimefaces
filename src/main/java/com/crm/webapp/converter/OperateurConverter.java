package com.crm.webapp.converter;

import com.crm.model.Operateur;
import com.crm.service.OperateurManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("operateurConverter")
public class OperateurConverter implements Converter {

    private OperateurManager operateurManager;
    
    @Autowired
    public void setOperateurManager(@Qualifier("operateurManager")OperateurManager operateurManager) {
        this.operateurManager = operateurManager;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(submittedValue);
                Operateur operateur=operateurManager.get(id);
                if(operateur!=null)
                    return operateur;
                
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
            return String.valueOf(((Operateur) value).getId());
        }
    }
}
