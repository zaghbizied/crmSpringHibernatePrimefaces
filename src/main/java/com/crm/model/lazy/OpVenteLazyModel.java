/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.OpVente;
import com.crm.service.OpVenteManager;
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
public class OpVenteLazyModel extends ListDataModel<OpVente> implements SelectableDataModel<OpVente>{
    
    private OpVenteManager opVenteManager;

    @Autowired
    public void setOpVenteManager(OpVenteManager opVenteManager) {
        this.opVenteManager = opVenteManager;
    }
    
    public OpVenteLazyModel() {  
    }  
  
    public OpVenteLazyModel(List<OpVente> data) {  
        super(data);  
    }  
      
    @Override  
    public OpVente getRowData(String rowKey) {  
        return opVenteManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(OpVente opVente) {  
        return opVente.getId();
    }  

}