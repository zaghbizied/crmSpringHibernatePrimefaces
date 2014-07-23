/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.Client;
import com.crm.service.ClientManager;
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
public class ClientLazyModel extends ListDataModel<Client> implements SelectableDataModel<Client>{
    
    private ClientManager clientManager;

    @Autowired
    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    public ClientLazyModel() {  
    }  
  
    public ClientLazyModel(List<Client> data) {  
        super(data);  
    }  
      
    @Override  
    public Client getRowData(String rowKey) {  
        return clientManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(Client client) {  
        return client.getId();
    }  

}