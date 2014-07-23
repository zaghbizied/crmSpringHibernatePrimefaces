package com.crm.service.impl;


import com.crm.dao.PrixClientDao;
import com.crm.model.Client;
import com.crm.model.PrixClient;
import com.crm.model.Produit;
import com.crm.service.PrixClientManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("prixClientManager")
@WebService(serviceName = "PrixClientService", endpointInterface = "com.crm.service.PrixClientManager")
public class PrixClientManagerImpl extends GenericManagerImpl<PrixClient, Long> implements PrixClientManager {
    PrixClientDao prixClientDao;

    @Autowired
    public PrixClientManagerImpl(PrixClientDao prixClientDao) {
        super(prixClientDao);
        this.prixClientDao = prixClientDao;
    }
    
    @Override
    public List<PrixClient> getByClient(Client client){
        return prixClientDao.getByClient(client);
    }

    @Override
    public PrixClient getByClientByProduit(Client client, Produit produit) {
        return prixClientDao.getByClientByProduit(client, produit);
    }
}