package com.crm.service.impl;


import com.crm.dao.AchatDao;
import com.crm.model.Achat;
import com.crm.model.Produit;
import com.crm.service.AchatManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("achatManager")
@WebService(serviceName = "AchatService", endpointInterface = "com.crm.service.AchatManager")
public class AchatManagerImpl extends GenericManagerImpl<Achat, Long> implements AchatManager {
    AchatDao achatDao;

    @Autowired
    public AchatManagerImpl(AchatDao achatDao) {
        super(achatDao);
        this.achatDao = achatDao;
    }

    @Override
    public List<Achat> getNotSelled(Produit produit) {
        return achatDao.getNotSelled(produit);
    }

    @Override
    public List<Achat> getByProduit(Produit produit) {
        return achatDao.getByProduit(produit);
    }
}