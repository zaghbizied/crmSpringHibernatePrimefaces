package com.crm.webapp.converter;

import com.crm.model.Produit;
import com.crm.service.ProduitManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("produitConverter")
public class ProduitConverter implements Converter {

    private ProduitManager produitManager;
    
    @Autowired
    public void setProduitManager(@Qualifier("produitManager")ProduitManager produitManager) {
        this.produitManager = produitManager;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(submittedValue);
                Produit produit=produitManager.get(id);
                if(produit!=null)
                    return produit;
                
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
            return String.valueOf(((Produit) value).getId());
        }
    }
}
