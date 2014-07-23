package com.crm.dao.hibernate;


import com.crm.dao.FournisseurDao;
import com.crm.model.Fournisseur;
import org.springframework.stereotype.Repository;

@Repository("fournisseurDao")
public class FournisseurDaoHibernate extends GenericDaoHibernate<Fournisseur, Long> implements FournisseurDao {

    public FournisseurDaoHibernate() {
        super(Fournisseur.class);
    }
}
