package com.crm.dao.hibernate;


import com.crm.dao.NumTelDao;
import com.crm.model.Client;
import com.crm.model.NumTel;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("numTelDao")
public class NumTelDaoHibernate extends GenericDaoHibernate<NumTel, Long> implements NumTelDao {

    public NumTelDaoHibernate() {
        super(NumTel.class);
    }
    
    public List<NumTel> getByClient(Client c){
        List<NumTel> numtels = getSession().createCriteria(NumTel.class).add(Restrictions.eq("client", c)).list();
        return numtels;
    }
}
