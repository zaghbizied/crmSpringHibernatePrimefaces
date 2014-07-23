/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.Fournisseur;
import com.crm.service.FournisseurManager;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author zied
 */
@Component
public class FournisseurLazyModel extends ListDataModel<Fournisseur> implements SelectableDataModel<Fournisseur>{
    
    private FournisseurManager fournisseurManager;

    @Autowired
    public void setFournisseurManager(FournisseurManager fournisseurManager) {
        this.fournisseurManager = fournisseurManager;
    }
    
    public FournisseurLazyModel() {  
    }  
  
    public FournisseurLazyModel(List<Fournisseur> data) {  
        super(data);  
    }  
      
    @Override  
    public Fournisseur getRowData(String rowKey) {  
        return fournisseurManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(Fournisseur fournisseur) {  
        return fournisseur.getId();
    }  

}