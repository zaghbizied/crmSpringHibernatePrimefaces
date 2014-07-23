package com.crm.service.impl;


import com.crm.dao.NumTelDao;
import com.crm.model.Client;
import com.crm.model.NumTel;
import com.crm.service.NumTelManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("numTelManager")
@WebService(serviceName = "NumTelService", endpointInterface = "com.crm.service.NumTelManager")
public class NumTelManagerImpl extends GenericManagerImpl<NumTel, Long> implements NumTelManager {
    NumTelDao numTelDao;

    @Autowired
    public NumTelManagerImpl(NumTelDao numTelDao) {
        super(numTelDao);
        this.numTelDao = numTelDao;
    }
    
    public List<NumTel> getByClient(Client c){
        return numTelDao.getByClient(c);
    }
}