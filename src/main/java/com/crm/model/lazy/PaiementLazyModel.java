/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.PaiementClient;
import com.crm.service.PaiementClientManager;
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
public class PaiementLazyModel extends ListDataModel<PaiementClient> implements SelectableDataModel<PaiementClient>{
    
    private PaiementClientManager paiementManager;

    @Autowired
    public void setPaiementManager(PaiementClientManager paiementManager) {
        this.paiementManager = paiementManager;
    }
    
    public PaiementLazyModel() {  
    }  
  
    public PaiementLazyModel(List<PaiementClient> data) {  
        super(data);  
    }  
      
    @Override  
    public PaiementClient getRowData(String rowKey) {  
        return paiementManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(PaiementClient paiement) {  
        return paiement.getId();
    }  

}