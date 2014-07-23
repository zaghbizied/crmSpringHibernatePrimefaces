/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao.hibernate;

import com.crm.dao.PaiementClientDao;
import com.crm.model.Client;
import com.crm.model.PaiementClient;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zied
 */
@Repository("paiementClientDao")
public class PaiementClientDaoHibernate extends GenericDaoHibernate<PaiementClient, Long> implements PaiementClientDao{
    
    public PaiementClientDaoHibernate() {
        super(PaiementClient.class);
    }
    
    @Override
    public float getAvance(Client client){
        List<PaiementClient> paiements =  getSession().createCriteria(PaiementClient.class).add(Restrictions.eq("client", client)).add(Restrictions.ne("avance",(Float.valueOf("0")))).list();
        if(paiements.size()>0)return paiements.get(0).getAvance();
        return 0;
    }
    
    @Override
    public PaiementClient getPaiementAvecAvance(Client client){
        List<PaiementClient> paiements =  getSession().createCriteria(PaiementClient.class).add(Restrictions.eq("client", client)).add(Restrictions.ne("avance",(Float.valueOf("0")))).list();
        if(paiements.size()>0)return paiements.get(0);
        return null;
    }

    @Override
    public List<PaiementClient> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("datePaiement", startDate,endDate));
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
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        crit.add(Restrictions.between("datePaiement", startDate,endDate));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PaiementClient> getLazyByDateByClient(Date date, Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("datePaiement", startDate,endDate)).add(Restrictions.eq("client", client));
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
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        crit.add(Restrictions.between("datePaiement", startDate,endDate)).add(Restrictions.eq("client", client));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PaiementClient> getLazyByClient(Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.eq("client", client));
        return crit.list();
    }

    @Override
    public int countByClient(Client c) {
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        crit.add(Restrictions.eq("client", c));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PaiementClient> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        return crit.list();
    }

    @Override
    public int countAll() {
        Criteria crit = getSession().createCriteria(PaiementClient.class);
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    @Override
    public List<PaiementClient> getAfterDate(Date date) {
        return  getSession().createCriteria(PaiementClient.class).add(Restrictions.between("datePaiement", date, new Date(System.currentTimeMillis()))).list();
    }
}
