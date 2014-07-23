package com.crm.dao.hibernate;

import com.crm.dao.OpVenteDao;
import com.crm.model.Client;
import com.crm.model.OpVente;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

@Repository("opVenteDao")
public class OpVenteDaoHibernate extends GenericDaoHibernate<OpVente, Long> implements OpVenteDao {

    public OpVenteDaoHibernate() {
        super(OpVente.class);
    }
    
    @Override
    public List<OpVente> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        return crit.list();
    }

    @Override
    public int countAll() {
        Criteria crit = getSession().createCriteria(OpVente.class);
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    @Override
    public List<OpVente> getByClient(Client c){
        List<OpVente> opVentes = getSession().createCriteria(OpVente.class).add(Restrictions.eq("client", c)).list();
        return opVentes;
    }
    
    @Override
    public List<OpVente> getLazyByClient(Client c,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.eq("client", c));
        return crit.list();
    }
    
    @Override
    public int countByClient(Client c){
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit.add(Restrictions.eq("client", c));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    @Override
    public List<OpVente> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
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
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("dateVente", startDate,endDate));
        return crit.list();
    }
    
    @Override
    public List<OpVente> getByDate(Date date){
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
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit.add(Restrictions.between("dateVente", startDate,endDate));
        return crit.list();
    }
    
    @Override
    public int countByDate(Date date){
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
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit.add(Restrictions.between("dateVente", startDate,endDate));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    @Override
    public List<OpVente> getByMonth(Date date){
        Date startDate=new Date();
        startDate.setYear(date.getYear());
        startDate.setMonth(date.getMonth());
        startDate.setDate(1);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(date.getYear());
        endDate.setMonth(date.getMonth());
        Calendar tmp = new GregorianCalendar(date.getYear(), date.getMonth(), 1);
        endDate.setDate(tmp.getActualMaximum(Calendar.DAY_OF_MONTH));
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        List<OpVente> opVentes = getSession().createCriteria(OpVente.class).add(Restrictions.between("dateVente", startDate,endDate)).list();
        return opVentes;
    }
    
    @Override
    public List<OpVente> getByYear(int year){
        Date startDate=new Date();
        startDate.setYear(year);
        startDate.setMonth(0);
        startDate.setDate(1);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(year);
        endDate.setMonth(11);
        endDate.setDate(31);
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        List<OpVente> opVentes = getSession().createCriteria(OpVente.class).add(Restrictions.between("dateVente", startDate,endDate)).list();
        return opVentes;
    }
    
    @Override
    public List<OpVente> getNonPayedByClient(Client client){
        List opVentes = getSession().createCriteria(OpVente.class).add(Restrictions.eq("client", client)).add(Restrictions.neProperty("montant", "montantPaye")).list();
        return opVentes;
    }

    @Override
    public List<OpVente> getByDateByClient(Date date, Client client) {
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
        List<OpVente> opVentes = getSession().createCriteria(OpVente.class).add(Restrictions.between("dateVente", startDate,endDate)).add(Restrictions.eq("client", client)).list();
        return opVentes;
    }
    
    @Override
    public List<OpVente> getLazyByDateByClient(Date date, Client client,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("dateVente", startDate,endDate)).add(Restrictions.eq("client", client));
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
        Criteria crit = getSession().createCriteria(OpVente.class);
        crit.add(Restrictions.between("dateVente", startDate,endDate)).add(Restrictions.eq("client", client));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    @Override
    public List<OpVente> getByMonthByClient(Date date, Client client) {
        Date startDate=new Date();
        startDate.setYear(date.getYear());
        startDate.setMonth(date.getMonth());
        startDate.setDate(1);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(date.getYear());
        endDate.setMonth(date.getMonth());
        Calendar tmp = new GregorianCalendar(date.getYear(), date.getMonth(), 1);
        endDate.setDate(tmp.getActualMaximum(Calendar.DAY_OF_MONTH));
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        List<OpVente> opVentes = getSession().createCriteria(OpVente.class).add(Restrictions.between("dateVente", startDate,endDate)).add(Restrictions.eq("client", client)).list();
        return opVentes;
    }
    
    @Override
    public List<OpVente> getByYearByClient(int year, Client client) {
        Date startDate=new Date();
        startDate.setYear(year);
        startDate.setMonth(0);
        startDate.setDate(1);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setYear(year);
        endDate.setMonth(11);
        endDate.setDate(31);
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        List<OpVente> opVentes = getSession().createCriteria(OpVente.class).add(Restrictions.between("dateVente", startDate,endDate)).add(Restrictions.eq("client", client)).list();
        return opVentes;
    }
    
    
}
