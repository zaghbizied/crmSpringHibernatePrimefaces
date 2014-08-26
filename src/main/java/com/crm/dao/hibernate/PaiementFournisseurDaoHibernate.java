package com.crm.dao.hibernate;

import com.crm.dao.PaiementFournisseurDao;
import com.crm.model.Fournisseur;
import com.crm.model.PaiementFournisseur;
import com.crm.model.TypePaiement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

@Repository("paiementFournisseurDao")
public class PaiementFournisseurDaoHibernate extends GenericDaoHibernate<PaiementFournisseur, Long> implements PaiementFournisseurDao {

    public PaiementFournisseurDaoHibernate() {
        super(PaiementFournisseur.class);
    }
    
    @Override
    public float getAvance(Fournisseur fournisseur){
        List<PaiementFournisseur> paiements =  getSession().createCriteria(PaiementFournisseur.class).add(Restrictions.eq("fournisseur", fournisseur)).add(Restrictions.ne("avance",(Float.valueOf("0")))).list();
        if(paiements.size()>0)return paiements.get(0).getAvance();
        return 0;
    }
    
    @Override
    public PaiementFournisseur getPaiementAvecAvance(Fournisseur fournisseur){
        List<PaiementFournisseur> paiements =  getSession().createCriteria(PaiementFournisseur.class).add(Restrictions.eq("fournisseur", fournisseur)).add(Restrictions.ne("avance",(Float.valueOf("0")))).list();
        if(paiements.size()>0)return paiements.get(0);
        return null;
    }

    @Override
    public List<PaiementFournisseur> getAfterDate(Date date) {
        return  getSession().createCriteria(PaiementFournisseur.class).add(Restrictions.between("datePaiement", date, new Date(System.currentTimeMillis()))).list();
    }

    @Override
    public List<PaiementFournisseur> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
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
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        crit.add(Restrictions.between("datePaiement", startDate,endDate));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PaiementFournisseur> getLazyByDateByFournisseur(Date date, Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.between("datePaiement", startDate,endDate)).add(Restrictions.eq("fournisseur", f));
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
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        crit.add(Restrictions.between("datePaiement", startDate,endDate)).add(Restrictions.eq("fournisseur", f));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PaiementFournisseur> getLazyByFournisseur(Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        crit.add(Restrictions.eq("fournisseur", f));
        return crit.list();
    }

    @Override
    public int countByFournisseur(Fournisseur f) {
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        crit.add(Restrictions.eq("fournisseur", f));
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PaiementFournisseur> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        crit = crit.setFirstResult(first).setMaxResults(pageSize);
        return crit.list();
    }

    @Override
    public int countAll() {
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        return ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<PaiementFournisseur> getByType(TypePaiement type) {
        Criteria crit = getSession().createCriteria(PaiementFournisseur.class);
        crit.add(Restrictions.eq("typePaiement", type));
        return crit.list();
    }

    @Override
    public List<PaiementFournisseur> getAfterDateByType(Date date, TypePaiement type) {
        return  getSession().createCriteria(PaiementFournisseur.class).add(Restrictions.eq("typePaiement", type)).add(Restrictions.between("datePaiement", date, new Date(System.currentTimeMillis()))).list();
    }
}