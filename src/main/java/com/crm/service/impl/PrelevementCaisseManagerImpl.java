package com.crm.service.impl;


import com.crm.dao.PrelevementCaisseDao;
import com.crm.model.PrelevementCaisse;
import com.crm.service.PrelevementCaisseManager;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("prelevementCaisseManager")
@WebService(serviceName = "PrelevementCaisseService", endpointInterface = "com.crm.service.PrelevementCaisseManager")
public class PrelevementCaisseManagerImpl extends GenericManagerImpl<PrelevementCaisse, Long> implements PrelevementCaisseManager {
    PrelevementCaisseDao prelevementCaisseDao;

    @Autowired
    public PrelevementCaisseManagerImpl(PrelevementCaisseDao prelevementCaisseDao) {
        super(prelevementCaisseDao);
        this.prelevementCaisseDao = prelevementCaisseDao;
    }

    @Override
    public List<PrelevementCaisse> getAfterDate(Date date) {
        return prelevementCaisseDao.getAfterDate(date);
    }

    @Override
    public List<PrelevementCaisse> getByDate(Date date) {
        return prelevementCaisseDao.getByDate(date);
    }
}