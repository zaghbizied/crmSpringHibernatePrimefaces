package com.crm.dao.hibernate;


import com.crm.dao.OperateurDao;
import com.crm.model.Operateur;
import org.springframework.stereotype.Repository;

@Repository("operateurDao")
public class OperateurDaoHibernate extends GenericDaoHibernate<Operateur, Long> implements OperateurDao {

    public OperateurDaoHibernate() {
        super(Operateur.class);
    }
}
