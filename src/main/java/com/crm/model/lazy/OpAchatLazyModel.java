/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.OpAchat;
import com.crm.service.OpAchatManager;
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
public class OpAchatLazyModel extends ListDataModel<OpAchat> implements SelectableDataModel<OpAchat>{
    
    private OpAchatManager opAchatManager;

    @Autowired
    public void setOpAchatManager(OpAchatManager opAchatManager) {
        this.opAchatManager = opAchatManager;
    }
    
    public OpAchatLazyModel() {  
    }  
  
    public OpAchatLazyModel(List<OpAchat> data) {  
        super(data);  
    }  
      
    @Override  
    public OpAchat getRowData(String rowKey) {  
        return opAchatManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(OpAchat opAchat) {  
        return opAchat.getId();
    }  

}