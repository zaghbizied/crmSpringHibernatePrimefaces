package com.crm.dao.hibernate;


import com.crm.dao.AchatDao;
import com.crm.model.Achat;
import com.crm.model.Produit;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("achatDao")
public class AchatDaoHibernate extends GenericDaoHibernate<Achat, Long> implements AchatDao {

    public AchatDaoHibernate() {
        super(Achat.class);
    }
    
    @Override
    public List<Achat> getNotSelled(Produit produit){
        List<Achat> achats = getSession().createCriteria(Achat.class).add(Restrictions.neProperty("vendu", "quantite")).add(Restrictions.eq("produit", produit)).list();
        if(achats.size()>0)return achats;
        return null;
    }

    @Override
    public List<Achat> getByProduit(Produit produit) {
        List<Achat> achats = getSession().createCriteria(Achat.class).add(Restrictions.eq("produit", produit)).list();
        if(achats.size()>0)return achats;
        return null;
    }
}
