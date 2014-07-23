package com.crm.dao.hibernate;


import com.crm.dao.CaisseDao;
import com.crm.model.Caisse;
import org.springframework.stereotype.Repository;

@Repository("caisseDao")
public class CaisseDaoHibernate extends GenericDaoHibernate<Caisse, Long> implements CaisseDao {

    public CaisseDaoHibernate() {
        super(Caisse.class);
    }

    @Override
    public Caisse getLastOne() {
        return (Caisse) getSession().createQuery("from Caisse order by dateCloture desc").setMaxResults(1).uniqueResult();
    }
}
