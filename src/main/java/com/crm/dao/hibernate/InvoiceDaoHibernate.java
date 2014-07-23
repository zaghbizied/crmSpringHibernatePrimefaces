package com.crm.dao.hibernate;


import com.crm.dao.InvoiceDao;
import com.crm.model.Invoice;
import org.springframework.stereotype.Repository;

@Repository("invoiceDao")
public class InvoiceDaoHibernate extends GenericDaoHibernate<Invoice, Long> implements InvoiceDao {

    public InvoiceDaoHibernate() {
        super(Invoice.class);
    }
}
