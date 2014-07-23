package com.crm.dao.hibernate;


import com.crm.dao.ProduitDao;
import com.crm.model.Produit;
import org.springframework.stereotype.Repository;

@Repository("produitDao")
public class ProduitDaoHibernate extends GenericDaoHibernate<Produit, Long> implements ProduitDao {

    public ProduitDaoHibernate() {
        super(Produit.class);
    }
}
