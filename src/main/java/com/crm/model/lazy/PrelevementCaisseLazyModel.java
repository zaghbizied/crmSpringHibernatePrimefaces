/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.PrelevementCaisse;
import com.crm.service.PrelevementCaisseManager;
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
public class PrelevementCaisseLazyModel extends ListDataModel<PrelevementCaisse> implements SelectableDataModel<PrelevementCaisse>{
    
    private PrelevementCaisseManager paiementManager;

    @Autowired
    public void setPaiementManager(PrelevementCaisseManager paiementManager) {
        this.paiementManager = paiementManager;
    }
    
    public PrelevementCaisseLazyModel() {  
    }  
  
    public PrelevementCaisseLazyModel(List<PrelevementCaisse> data) {  
        super(data);  
    }  
      
    @Override  
    public PrelevementCaisse getRowData(String rowKey) {  
        return paiementManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(PrelevementCaisse paiement) {  
        return paiement.getId();
    }  

}