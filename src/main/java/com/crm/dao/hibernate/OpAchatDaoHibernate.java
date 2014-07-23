package com.crm.dao.hibernate;


import com.crm.dao.OpAchatDao;
import com.crm.model.Fournisseur;
import com.crm.model.OpAchat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

@Repository("opAchatDao")
public class OpAchatDaoHibernate extends GenericDaoHibernate<OpAchat, Long> implements OpAchatDao {

    public OpAchatDaoHibernate() {
        super(OpAchat.class);
    }

    @Override
    public List<OpAchat> getByDate(Date date) {
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
        List<OpAchat> opAchats = getSession().createCriteria(OpAchat.class).add(Restrictions.between("dateAchat", startDate,endDate)).list();
        return opAchats;
    }
    
    @Override
    public List<OpAchat> getByMonth(Date date) {
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
        List<OpAchat> opAchats = getSession().createCriteria(OpAchat.class).add(Restrictions.between("dateAchat", startDate,endDate)).list();
        return opAchats;
    }
    
    @Override
    public List<OpAchat> getByYear(int year) {
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
        List<OpAchat> opAchats = getSession().createCriteria(OpAchat.class).add(Restrictions.between("dateAchat", startDate,endDate)).list();
        return opAchats;
    }
    
    @Override
    public List<OpAchat> getNonPayedByFournisseur(Fournisseur fournisseur){
        List opAchats = getSession().createCriteria(OpAchat.class).add(Restrictions.eq("fournisseur", fournisseur)).add(Restrictions.neProperty("montant", "montantPaye")).list();
        return opAchats;
    }

    @Override
    public List<OpAchat> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(OpAchat.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("dateAchat", startDate,endDate));
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
        Criteria crit = getSession().createCriteria(OpAchat.class);
        crit.add(Restrictions.between("dateAchat", startDate,endDate));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<OpAchat> getLazyByDateByFournisseur(Date date, Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(OpAchat.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("dateAchat", startDate,endDate)).add(Restrictions.eq("fournisseur", f));
        return crit.list();
    }

    @Override
    public int countByDateByFournisseur(Date date, Fournisseur f) {
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
        Criteria crit = getSession().createCriteria(OpAchat.class);
        crit.add(Restrictions.between("dateAchat", startDate,endDate)).add(Restrictions.eq("fournisseur", f));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<OpAchat> getLazyByFournisseur(Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(OpAchat.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.eq("fournisseur", f));
        return crit.list();
    }

    @Override
    public int countByFournisseur(Fournisseur f) {
        Criteria crit = getSession().createCriteria(OpAchat.class);
        crit.add(Restrictions.eq("fournisseur", f));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<OpAchat> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(OpAchat.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        return crit.list();
    }

    @Override
    public int countAll() {
        Criteria crit = getSession().createCriteria(OpAchat.class);
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
}
