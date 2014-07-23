package com.crm.service.impl;


import com.crm.dao.ProduitDao;
import com.crm.model.Produit;
import com.crm.service.ProduitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("produitManager")
@WebService(serviceName = "ProduitService", endpointInterface = "com.crm.service.ProduitManager")
public class ProduitManagerImpl extends GenericManagerImpl<Produit, Long> implements ProduitManager {
    ProduitDao produitDao;

    @Autowired
    public ProduitManagerImpl(ProduitDao produitDao) {
        super(produitDao);
        this.produitDao = produitDao;
    }
}