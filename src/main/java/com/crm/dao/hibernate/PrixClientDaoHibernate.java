/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao.hibernate;

import com.crm.dao.PrixClientDao;
import com.crm.model.Client;
import com.crm.model.PrixClient;
import com.crm.model.Produit;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zied
 */
@Repository("prixClientDao")
public class PrixClientDaoHibernate extends GenericDaoHibernate<PrixClient, Long> implements PrixClientDao{
    
    public PrixClientDaoHibernate() {
        super(PrixClient.class);
    }
    
    @Override
    public List<PrixClient> getByClient(Client client){
        List<PrixClient> ps =  getSession().createCriteria(PrixClient.class).add(Restrictions.eq("client", client)).list();
        return ps;
    }
    
    @Override
    public PrixClient getByClientByProduit(Client client,Produit produit){
        PrixClient ps =  (PrixClient) getSession().createCriteria(PrixClient.class).add(Restrictions.eq("client", client)).add(Restrictions.eq("produit", produit)).uniqueResult();
        return ps;
    }
}
