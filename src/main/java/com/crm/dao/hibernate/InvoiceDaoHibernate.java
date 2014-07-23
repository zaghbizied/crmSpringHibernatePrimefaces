package com.crm.dao.hibernate;

import com.crm.dao.InvoiceDao;
import com.crm.model.Client;
import com.crm.model.Invoice;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

@Repository("invoiceDao")
public class InvoiceDaoHibernate extends GenericDaoHibernate<Invoice, Long> implements InvoiceDao {

    public InvoiceDaoHibernate() {
        super(Invoice.class);
    }

    @Override
    public List<Invoice> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Date startDate=new Date();
        startDate.setYear(date.getYear());
        startDate.setMonth(date.getMonth());
        startDate.setDate(date.getDate());
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(date.getYear());
        endDate.setMonth(date.getMonth());
        endDate.setDate(date.getDate());
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        Criteria crit = getSession().createCriteria(Invoice.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("dateFact", startDate,endDate));
        return crit.list();
    }

    @Override
    public int countByDate(Date date) {
        Date startDate=new Date();
        startDate.setYear(date.getYear());
        startDate.setMonth(date.getMonth());
        startDate.setDate(date.getDate());
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(date.getYear());
        endDate.setMonth(date.getMonth());
        endDate.setDate(date.getDate());
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        Criteria crit = getSession().createCriteria(Invoice.class);
        crit.add(Restrictions.between("dateFact", startDate,endDate));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<Invoice> getLazyByDateByClient(Date date, Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Date startDate=new Date();
        startDate.setYear(date.getYear());
        startDate.setMonth(date.getMonth());
        startDate.setDate(date.getDate());
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(date.getYear());
        endDate.setMonth(date.getMonth());
        endDate.setDate(date.getDate());
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        Criteria crit = getSession().createCriteria(Invoice.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("dateFact", startDate,endDate)).add(Restrictions.eq("client", client));
        return crit.list();
    }

    @Override
    public int countByDateByClient(Date date, Client client) {
        Date startDate=new Date();
        startDate.setYear(date.getYear());
        startDate.setMonth(date.getMonth());
        startDate.setDate(date.getDate());
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(date.getYear());
        endDate.setMonth(date.getMonth());
        endDate.setDate(date.getDate());
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        Criteria crit = getSession().createCriteria(Invoice.class);
        crit.add(Restrictions.between("dateFact", startDate,endDate)).add(Restrictions.eq("client", client));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<Invoice> getLazyByClient(Client c,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        Criteria crit = getSession().createCriteria(Invoice.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.eq("client", c));
        return crit.list();
    }
    
    @Override
    public int countByClient(Client c){
        Criteria crit = getSession().createCriteria(Invoice.class);
        crit.add(Restrictions.eq("client", c));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    @Override
    public List<Invoice> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(Invoice.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        return crit.list();
    }

    @Override
    public int countAll() {
        Criteria crit = getSession().createCriteria(Invoice.class);
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
}
