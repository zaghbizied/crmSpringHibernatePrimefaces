package com.crm.webapp.converter;

import com.crm.model.Fournisseur;
import com.crm.service.FournisseurManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("fournisseurConverter")
public class FournisseurConverter implements Converter {

    private FournisseurManager fournisseurManager;
    
    @Autowired
    public void setFournisseurManager(@Qualifier("fournisseurManager")FournisseurManager fournisseurManager) {
        this.fournisseurManager = fournisseurManager;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(submittedValue);
                Fournisseur fournisseur=fournisseurManager.get(id);
                if(fournisseur!=null)
                    return fournisseur;
                
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
            return String.valueOf(((Fournisseur) value).getId());
        }
    }
}
