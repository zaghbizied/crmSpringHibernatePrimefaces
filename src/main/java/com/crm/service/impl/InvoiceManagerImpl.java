package com.crm.service.impl;


import com.crm.dao.InvoiceDao;
import com.crm.model.Client;
import com.crm.model.Invoice;
import com.crm.service.InvoiceManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;
import org.primefaces.model.SortOrder;

@Service("invoiceManager")
@WebService(serviceName = "InvoiceService", endpointInterface = "com.crm.service.InvoiceManager")
public class InvoiceManagerImpl extends GenericManagerImpl<Invoice, Long> implements InvoiceManager {
    InvoiceDao invoiceDao;

    @Autowired
    public InvoiceManagerImpl(InvoiceDao invoiceDao) {
        super(invoiceDao);
        this.invoiceDao = invoiceDao;
    }

    @Override
    public List<Invoice> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return invoiceDao.getLazyByDate(date, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDate(Date date) {
        return invoiceDao.countByDate(date);
    }

    @Override
    public List<Invoice> getLazyByDateByClient(Date date, Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return invoiceDao.getLazyByDateByClient(date, client, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDateByClient(Date date, Client client) {
        return invoiceDao.countByDateByClient(date, client);
    }

    @Override
    public List<Invoice> getLazyByClient(Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return invoiceDao.getLazyByClient(client, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByClient(Client c) {
        return invoiceDao.countByClient(c);
    }

    @Override
    public List<Invoice> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return invoiceDao.getLazyAll(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countAll() {
        return invoiceDao.countAll();
    }
}