package com.crm.service.impl;


import com.crm.dao.CaisseDao;
import com.crm.model.Caisse;
import com.crm.service.CaisseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("caisseManager")
@WebService(serviceName = "CaisseService", endpointInterface = "com.crm.service.CaisseManager")
public class CaisseManagerImpl extends GenericManagerImpl<Caisse, Long> implements CaisseManager {
    CaisseDao caisseDao;

    @Autowired
    public CaisseManagerImpl(CaisseDao caisseDao) {
        super(caisseDao);
        this.caisseDao = caisseDao;
    }

    @Override
    public Caisse getLastOne() {
        return caisseDao.getLastOne();
    }
}