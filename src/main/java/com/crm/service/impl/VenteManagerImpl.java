package com.crm.service.impl;


import com.crm.dao.VenteDao;
import com.crm.model.Vente;
import com.crm.service.VenteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("venteManager")
@WebService(serviceName = "VenteService", endpointInterface = "com.crm.service.VenteManager")
public class VenteManagerImpl extends GenericManagerImpl<Vente, Long> implements VenteManager {
    VenteDao venteDao;

    @Autowired
    public VenteManagerImpl(VenteDao venteDao) {
        super(venteDao);
        this.venteDao = venteDao;
    }
}