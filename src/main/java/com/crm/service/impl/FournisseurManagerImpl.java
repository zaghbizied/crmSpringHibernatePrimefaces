package com.crm.service.impl;


import com.crm.dao.FournisseurDao;
import com.crm.model.Fournisseur;
import com.crm.service.FournisseurManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("fournisseurManager")
@WebService(serviceName = "FournisseurService", endpointInterface = "com.crm.service.FournisseurManager")
public class FournisseurManagerImpl extends GenericManagerImpl<Fournisseur, Long> implements FournisseurManager {
    FournisseurDao fournisseurDao;

    @Autowired
    public FournisseurManagerImpl(FournisseurDao fournisseurDao) {
        super(fournisseurDao);
        this.fournisseurDao = fournisseurDao;
    }
}