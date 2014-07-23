package com.crm.service.impl;


import com.crm.dao.OperateurDao;
import com.crm.model.Operateur;
import com.crm.service.OperateurManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("operateurManager")
@WebService(serviceName = "OperateurService", endpointInterface = "com.crm.service.OperateurManager")
public class OperateurManagerImpl extends GenericManagerImpl<Operateur, Long> implements OperateurManager {
    OperateurDao operateurDao;

    @Autowired
    public OperateurManagerImpl(OperateurDao operateurDao) {
        super(operateurDao);
        this.operateurDao = operateurDao;
    }
}