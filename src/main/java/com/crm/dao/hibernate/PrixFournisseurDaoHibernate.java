/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao.hibernate;

import com.crm.dao.PrixFournisseurDao;
import com.crm.model.Fournisseur;
import com.crm.model.PrixFournisseur;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zied
 */
@Repository("prixFournisseurDao")
public class PrixFournisseurDaoHibernate extends GenericDaoHibernate<PrixFournisseur, Long> implements PrixFournisseurDao{
    
    public PrixFournisseurDaoHibernate() {
        super(PrixFournisseur.class);
    }
    
    @Override
    public List<PrixFournisseur> getByFournisseur(Fournisseur fournisseur){
        List<PrixFournisseur> ps =  getSession().createCriteria(PrixFournisseur.class).add(Restrictions.eq("fournisseur", fournisseur)).list();
        return ps;
    }

    @Override
    public PrixFournisseur getByFournisseurByProduit(Fournisseur fournisseur, Produit produit) {
        return  (PrixFournisseur) getSession().createCriteria(PrixFournisseur.class).add(Restrictions.eq("fournisseur", fournisseur)).add(Restrictions.eq("produit", produit)).uniqueResult();
    }
}
