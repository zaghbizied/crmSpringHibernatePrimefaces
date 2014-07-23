package com.crm.service.impl;


import com.crm.dao.OpVenteDao;
import com.crm.model.Client;
import com.crm.model.OpVente;
import com.crm.service.OpVenteManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;
import org.primefaces.model.SortOrder;

@Service("opVenteManager")
@WebService(serviceName = "OpVenteService", endpointInterface = "com.crm.service.OpVenteManager")
public class OpVenteManagerImpl extends GenericManagerImpl<OpVente, Long> implements OpVenteManager {
    OpVenteDao opVenteDao;

    @Autowired
    public OpVenteManagerImpl(OpVenteDao opVenteDao) {
        super(opVenteDao);
        this.opVenteDao = opVenteDao;
    }
    
    @Override
    public List<OpVente> getNonPayedByClient(Client client){
        return opVenteDao.getNonPayedByClient(client);
    }
    
    @Override
    public List<OpVente> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        return opVenteDao.getLazyByDate(date, first, pageSize, sortField, sortOrder, filters);
    }
    
    @Override
    public List<OpVente> getByDate(Date date){
        return opVenteDao.getByDate(date);
    }
    
    @Override
    public List<OpVente> getByMonth(Date date){
        return opVenteDao.getByMonth(date);
    }
    
    @Override
    public List<OpVente> getByYear(int year){
        return opVenteDao.getByYear(year);
    }
    
    @Override
    public List<OpVente> getByMonthByClient(Date date, Client client){
        return opVenteDao.getByMonthByClient(date, client);
    }

    @Override
    public List<OpVente> getByDateByClient(Date date, Client client) {
        return opVenteDao.getByDateByClient(date, client);
    }
    
    @Override
    public List<OpVente> getByYearByClient(int year, Client client) {
        return opVenteDao.getByYearByClient(year, client);
    }
    
    @Override
    public List<OpVente> getByClient(Client c){
        return opVenteDao.getByClient(c);
    }

    @Override
    public int countByDate(Date date) {
        return opVenteDao.countByDate(date);
    }

    @Override
    public List<OpVente> getLazyByDateByClient(Date date, Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return opVenteDao.getLazyByDateByClient(date, client, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDateByClient(Date date, Client client) {
        return opVenteDao.countByDateByClient(date, client);
    }

    @Override
    public List<OpVente> getLazyByClient(Client client, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return opVenteDao.getLazyByClient(client, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByClient(Client c) {
        return opVenteDao.countByClient(c);
    }

    @Override
    public List<OpVente> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return opVenteDao.getLazyAll(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countAll() {
        return opVenteDao.countAll();
    }
}