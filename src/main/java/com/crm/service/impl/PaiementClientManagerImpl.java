package com.crm.service.impl;


import com.crm.dao.PaiementClientDao;
import com.crm.model.Client;
import com.crm.model.PaiementClient;
import com.crm.service.PaiementClientManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;
import org.primefaces.model.SortOrder;

@Service("paiementClientManager")
@WebService(serviceName = "PaiementClientService", endpointInterface = "com.crm.service.PaiementClientManager")
public class PaiementClientManagerImpl extends GenericManagerImpl<PaiementClient, Long> implements PaiementClientManager {
    PaiementClientDao paiementClientDao;

    @Autowired
    public PaiementClientManagerImpl(PaiementClientDao paiementClientDao) {
        super(paiementClientDao);
        this.paiementClientDao = paiementClientDao;
    }
    
    @Override
    public float getAvance(Client client){
        return paiementClientDao.getAvance(client);
    }

    @Override
    public PaiementClient getPaiementAvecAvance(Client client) {
        return paiementClientDao.getPaiementAvecAvance(client);
    }

    @Override
    public List<PaiementClient> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementClientDao.getLazyByDate(date, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDate(Date date) {
        return paiementClientDao.countByDate(date);
    }

    @Override
    public List<PaiementClient> getLazyByDateByClient(Date date, Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementClientDao.getLazyByDateByClient(date, client, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDateByClient(Date date, Client client) {
        return paiementClientDao.countByDateByClient(date, client);
    }

    @Override
    public List<PaiementClient> getLazyByClient(Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementClientDao.getLazyByClient(client, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByClient(Client c) {
        return paiementClientDao.countByClient(c);
    }

    @Override
    public List<PaiementClient> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementClientDao.getLazyAll(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countAll() {
        return paiementClientDao.countAll();
    }

    @Override
    public List<PaiementClient> getAfterDate(Date date) {
        return paiementClientDao.getAfterDate(date);
    }
}