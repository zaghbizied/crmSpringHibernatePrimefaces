/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.PaiementFournisseur;
import com.crm.service.PaiementFournisseurManager;
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
public class PaiementFournisseurLazyModel extends ListDataModel<PaiementFournisseur> implements SelectableDataModel<PaiementFournisseur>{
    
    private PaiementFournisseurManager paiementFournisseurManager;

    @Autowired
    public void setPaiementFournisseurManager(PaiementFournisseurManager paiementFournisseurManager) {
        this.paiementFournisseurManager = paiementFournisseurManager;
    }
    
    public PaiementFournisseurLazyModel() {  
    }  
  
    public PaiementFournisseurLazyModel(List<PaiementFournisseur> data) {  
        super(data);  
    }  
      
    @Override  
    public PaiementFournisseur getRowData(String rowKey) {  
        return paiementFournisseurManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(PaiementFournisseur paiementFournisseur) {  
        return paiementFournisseur.getId();
    }  

}