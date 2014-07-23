package com.crm.service.impl;


import com.crm.dao.PrixFournisseurDao;
import com.crm.model.Fournisseur;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import com.crm.service.PrixFournisseurManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("prixFournisseurManager")
@WebService(serviceName = "PrixFournisseurService", endpointInterface = "com.crm.service.PrixFournisseurManager")
public class PrixFournisseurManagerImpl extends GenericManagerImpl<PrixFournisseur, Long> implements PrixFournisseurManager {
    PrixFournisseurDao prixFournisseurDao;

    @Autowired
    public PrixFournisseurManagerImpl(PrixFournisseurDao prixFournisseurDao) {
        super(prixFournisseurDao);
        this.prixFournisseurDao = prixFournisseurDao;
    }
    
    @Override
    public List<PrixFournisseur> getByFournisseur(Fournisseur fournisseur){
        return prixFournisseurDao.getByFournisseur(fournisseur);
    }

    @Override
    public PrixFournisseur getByFournisseurByProduit(Fournisseur fournisseur, Produit produit) {
        return prixFournisseurDao.getByFournisseurByProduit(fournisseur, produit);
    }
}