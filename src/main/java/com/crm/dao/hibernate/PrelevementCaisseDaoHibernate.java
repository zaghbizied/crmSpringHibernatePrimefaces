package com.crm.dao.hibernate;


import com.crm.dao.PrelevementCaisseDao;
import com.crm.model.PrelevementCaisse;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
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
}
