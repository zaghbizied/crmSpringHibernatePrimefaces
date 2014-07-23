package com.crm.dao.hibernate;


import com.crm.dao.ClientDao;
import com.crm.model.Client;
import org.springframework.stereotype.Repository;

@Repository("clientDao")
public class ClientDaoHibernate extends GenericDaoHibernate<Client, Long> implements ClientDao {

    public ClientDaoHibernate() {
        super(Client.class);
    }
}
