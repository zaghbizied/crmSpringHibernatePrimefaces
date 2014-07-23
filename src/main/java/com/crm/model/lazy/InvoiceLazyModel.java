/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model.lazy;

import com.crm.model.Invoice;
import com.crm.service.InvoiceManager;
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
public class InvoiceLazyModel extends ListDataModel<Invoice> implements SelectableDataModel<Invoice>{
    
    private InvoiceManager invoiceManager;

    @Autowired
    public void setInvoiceManager(InvoiceManager invoiceManager) {
        this.invoiceManager = invoiceManager;
    }
    
    public InvoiceLazyModel() {  
    }  
  
    public InvoiceLazyModel(List<Invoice> data) {  
        super(data);  
    }  
      
    @Override  
    public Invoice getRowData(String rowKey) {  
        return invoiceManager.get(Long.valueOf(rowKey));  
    }  
  
    @Override  
    public Object getRowKey(Invoice invoice) {  
        return invoice.getId();
    }  

}