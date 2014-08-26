package com.crm.service.impl;


import com.crm.dao.PaiementFournisseurDao;
import com.crm.model.Fournisseur;
import com.crm.model.PaiementFournisseur;
import com.crm.model.TypePaiement;
import com.crm.service.PaiementFournisseurManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;
import org.primefaces.model.SortOrder;

@Service("paiementFournisseurManager")
@WebService(serviceName = "PaiementFournisseurService", endpointInterface = "com.crm.service.PaiementFournisseurManager")
public class PaiementFournisseurManagerImpl extends GenericManagerImpl<PaiementFournisseur, Long> implements PaiementFournisseurManager {
    PaiementFournisseurDao paiementFournisseurDao;

    @Autowired
    public PaiementFournisseurManagerImpl(PaiementFournisseurDao paiementFournisseurDao) {
        super(paiementFournisseurDao);
        this.paiementFournisseurDao = paiementFournisseurDao;
    }
    
    @Override
    public float getAvance(Fournisseur fournisseur){
        return paiementFournisseurDao.getAvance(fournisseur);
    }

    @Override
    public PaiementFournisseur getPaiementAvecAvance(Fournisseur fournisseur) {
        return paiementFournisseurDao.getPaiementAvecAvance(fournisseur);
    }

    @Override
    public List<PaiementFournisseur> getAfterDate(Date date) {
        return paiementFournisseurDao.getAfterDate(date);
    }

    @Override
    public List<PaiementFournisseur> getLazyByDate(Date date, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementFournisseurDao.getLazyByDate(date, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDate(Date date) {
        return paiementFournisseurDao.countByDate(date);
    }

    @Override
    public List<PaiementFournisseur> getLazyByDateByFournisseur(Date date, Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementFournisseurDao.getLazyByDateByFournisseur(date, f, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByDateByFournisseur(Date date, Fournisseur f) {
        return paiementFournisseurDao.countByDateByFournisseur(date, f);
    }

    @Override
    public List<PaiementFournisseur> getLazyByFournisseur(Fournisseur f, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementFournisseurDao.getLazyByFournisseur(f, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countByFournisseur(Fournisseur f) {
        return paiementFournisseurDao.countByFournisseur(f);
    }

    @Override
    public List<PaiementFournisseur> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return paiementFournisseurDao.getLazyAll(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public int countAll() {
        return paiementFournisseurDao.countAll();
    }

    @Override
    public List<PaiementFournisseur> getByType(TypePaiement type) {
        return paiementFournisseurDao.getByType(type);
    }

    @Override
    public List<PaiementFournisseur> getAfterDateByType(Date date, TypePaiement type) {
        return paiementFournisseurDao.getAfterDateByType(date, type);
    }
}