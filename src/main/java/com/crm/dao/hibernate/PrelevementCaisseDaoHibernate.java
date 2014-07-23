package com.crm.dao.hibernate;


import com.crm.dao.PrelevementCaisseDao;
import com.crm.model.PrelevementCaisse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

@Repository("prelevementCaisseDao")
public class PrelevementCaisseDaoHibernate extends GenericDaoHibernate<PrelevementCaisse, Long> implements PrelevementCaisseDao {

    public PrelevementCaisseDaoHibernate() {
        super(PrelevementCaisse.class);
    }

    @Override
    public List<PrelevementCaisse> getAfterDate(Date date) {
        return  getSession().createCriteria(PrelevementCaisse.class).add(Restrictions.between("datePrelevement", date, new Date(System.currentTimeMillis()))).list();
    }

    @Override
    public List<PrelevementCaisse> getByDate(Date date) {
        Date startDate=new Date();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start date "+startDate);
        Date endDate=new Date();
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        System.out.println("end date "+endDate);
        return  getSession().createCriteria(PrelevementCaisse.class).add(Restrictions.between("datePrelevement", startDate,endDate)).list();
    }

    @Override
    public List<PrelevementCaisse> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(PrelevementCaisse.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("datePrelevement", startDate,endDate));
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
        Criteria crit = getSession().createCriteria(PrelevementCaisse.class);
        crit.add(Restrictions.between("datePrelevement", startDate,endDate));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PrelevementCaisse> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(PrelevementCaisse.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        return crit.list();
    }

    @Override
    public int countAll() {
        Criteria crit = getSession().createCriteria(PrelevementCaisse.class);
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
}
