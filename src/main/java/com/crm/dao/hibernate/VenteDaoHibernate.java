package com.crm.dao.hibernate;


import com.crm.dao.VenteDao;
import com.crm.model.Vente;
import org.springframework.stereotype.Repository;

@Repository("venteDao")
public class VenteDaoHibernate extends GenericDaoHibernate<Vente, Long> implements VenteDao {

    public VenteDaoHibernate() {
        super(Vente.class);
    }
}
