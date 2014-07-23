package com.crm.service.impl;


import com.crm.dao.OpAchatDao;
import com.crm.model.Fournisseur;
import com.crm.model.OpAchat;
import com.crm.service.OpAchatManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;
import org.primefaces.model.SortOrder;

@Service("opAchatManager")
@WebService(serviceName = "OpAchatService", endpointInterface = "com.crm.service.OpAchatManager")
public class OpAchatManagerImpl extends GenericManagerImpl<OpAchat, Long> implements OpAchatManager {
    OpAchatDao opAchatDao;

    @Autowired
    public OpAchatManagerImpl(OpAchatDao opAchatDao) {
        super(opAchatDao);
        this.opAchatDao = opAchatDao;
    }

    @Override
    public List<OpAchat> getByDate(Date date) {
        return opAchatDao.getByDate(date);
    }
    
    @Override
    public List<OpAchat> getByMonth(Date date){
        return opAchatDao.getByMonth(date);
    }
    
    @Override
    public List<OpAchat> getNonPayedByFournisseur(Fournisseur fournisseur){
        return opAchatDao.getNonPayedByFournisseur(fournisseur);
    }

    @Override
    public List<OpAchat> getByYear(int year) {
        return opAchatDao.getByYear(year);
    }

    @Override
    public List<OpAchat> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return opAchatDao.getLazyByDate(date, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDate(Date date) {
        return opAchatDao.countByDate(date);
    }

    @Override
    public List<OpAchat> getLazyByDateByFournisseur(Date date, Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return opAchatDao.getLazyByDateByFournisseur(date, f, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDateByFournisseur(Date date, Fournisseur f) {
        return opAchatDao.countByDateByFournisseur(date, f);
    }

    @Override
    public List<OpAchat> getLazyByFournisseur(Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return opAchatDao.getLazyByFournisseur(f, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByFournisseur(Fournisseur f) {
        return opAchatDao.countByFournisseur(f);
    }

    @Override
    public List<OpAchat> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return opAchatDao.getLazyAll(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countAll() {
        return opAchatDao.countAll();
    }
    
}