package com.crm.service.impl;


import com.crm.dao.InvoiceDao;
import com.crm.model.Invoice;
import com.crm.service.InvoiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("invoiceManager")
@WebService(serviceName = "InvoiceService", endpointInterface = "com.crm.service.InvoiceManager")
public class InvoiceManagerImpl extends GenericManagerImpl<Invoice, Long> implements InvoiceManager {
    InvoiceDao invoiceDao;

    @Autowired
    public InvoiceManagerImpl(InvoiceDao invoiceDao) {
        super(invoiceDao);
        this.invoiceDao = invoiceDao;
    }
}