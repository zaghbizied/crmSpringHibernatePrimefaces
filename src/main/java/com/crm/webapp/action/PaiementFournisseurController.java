/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Fournisseur;
import com.crm.model.OpAchat;
import com.crm.model.PaiementFournisseur;
import com.crm.model.TypePaiement;
import com.crm.service.FournisseurManager;
import com.crm.service.OpAchatManager;
import com.crm.service.PaiementFournisseurManager;
import com.crm.service.TypePaiementManager;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zied
 */
@Component("paiementFournisseurController")
@Scope("view")
public class PaiementFournisseurController extends BasePage implements Serializable{
    private LazyDataModel<PaiementFournisseur> paiementFournisseurs;
    private PaiementFournisseur newPaiementFournisseur;
    private PaiementFournisseurManager paiementFournisseurManager;
    private FournisseurManager fournisseurManager;
    private OpAchatManager opAchatManager;
    private TypePaiementManager typePaiementManager;
    private PaiementFournisseur searchObject;
    private Fournisseur selectedFournisseur;
    private List<Fournisseur> fournisseurs=new ArrayList<>();
    private List<TypePaiement> typesPaiement=new ArrayList<>();
    private float total=0;
    private boolean displayFiche;
    private float impaye;
    private float avance;
    private float montantSugg;
    
    @Autowired
    public void setFournisseurManager(@Qualifier("fournisseurManager")FournisseurManager fournisseurManager) {
        this.fournisseurManager = fournisseurManager;
    }
    
    @Autowired
    public void setPaiementFournisseurManager(@Qualifier("paiementFournisseurManager")PaiementFournisseurManager paiementFournisseurManager) {
        this.paiementFournisseurManager = paiementFournisseurManager;
    }
    
    @Autowired
    public void setOpAchatManager(@Qualifier("opAchatManager")OpAchatManager opAchatManager) {
        this.opAchatManager = opAchatManager;
    }
    
    @Autowired
    public void setTypePaiementManager(@Qualifier("typePaiementManager")TypePaiementManager typePaiementManager) {
        this.typePaiementManager = typePaiementManager;
    }
    
    public void search() throws ParseException{
        System.out.println("searching");
        if(searchObject.getFournisseur()!=null){
            if(searchObject.getDatePaiement()!=null){
                this.paiementFournisseurs = new LazyDataModel<PaiementFournisseur>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementFournisseur> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<PaiementFournisseur> result = paiementFournisseurManager.getLazyByDateByFournisseur(searchObject.getDatePaiement(),searchObject.getFournisseur(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementFournisseurs.setRowCount(paiementFournisseurManager.countByDateByFournisseur(searchObject.getDatePaiement(),searchObject.getFournisseur()));
            }
            else{
                this.paiementFournisseurs = new LazyDataModel<PaiementFournisseur>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementFournisseur> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<PaiementFournisseur> result = paiementFournisseurManager.getLazyByFournisseur(searchObject.getFournisseur(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementFournisseurs.setRowCount(paiementFournisseurManager.countByFournisseur(searchObject.getFournisseur()));
            }
        }
        else{
           if(searchObject.getDatePaiement()!=null){
                this.paiementFournisseurs = new LazyDataModel<PaiementFournisseur>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementFournisseur> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            System.out.println("loading by date "+first+" "+pageSize);
                            List<PaiementFournisseur> result = paiementFournisseurManager.getLazyByDate(searchObject.getDatePaiement(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementFournisseurs.setRowCount(paiementFournisseurManager.countByDate(searchObject.getDatePaiement()));
            }
           else{
               this.paiementFournisseurs = new LazyDataModel<PaiementFournisseur>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementFournisseur> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<PaiementFournisseur> result = paiementFournisseurManager.getLazyAll(first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementFournisseurs.setRowCount(paiementFournisseurManager.countAll());
           }
        }
    }
    
    @PostConstruct
    public void init(){
        fournisseurs=fournisseurManager.getAll();
        searchObject=new PaiementFournisseur();
        searchObject.setDatePaiement(new Date(System.currentTimeMillis()));
        this.paiementFournisseurs = new LazyDataModel<PaiementFournisseur>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementFournisseur> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    List<PaiementFournisseur> result = paiementFournisseurManager.getLazyByDate(searchObject.getDatePaiement(),first, pageSize, sortField, sortOrder, filters);
                    return result;
                }
        };
        paiementFournisseurs.setRowCount(paiementFournisseurManager.countByDate(new Date(System.currentTimeMillis())));
        typesPaiement=typePaiementManager.getAll();
    }
    
    public void saveNew(){
        float montant=newPaiementFournisseur.getMontant()+avance;
        newPaiementFournisseur.setMontant(montant);
        List<OpAchat> lst=opAchatManager.getNonPayedByFournisseur(newPaiementFournisseur.getFournisseur());
        Collections.sort(lst, new Comparator<OpAchat>() {
            @Override
            public int compare(OpAchat op1, OpAchat op2) {
                return op1.getDateAchat().compareTo(op2.getDateAchat());
            }
        });
        for(OpAchat op:lst){
            float montantRestant=op.getMontant()-op.getMontantPaye();
            if(montantRestant<=montant){
                montant=montant-montantRestant;
                op.setMontantPaye(op.getMontant());
                opAchatManager.save(op);
            }
            else if(montant>0){
                op.setMontantPaye(op.getMontantPaye()+montant);
                opAchatManager.save(op);
                montant=0;
                break;
            }
            else
                break;
        }
        if(montant>0)newPaiementFournisseur.setAvance(montant);
        PaiementFournisseur p=paiementFournisseurManager.getPaiementAvecAvance(newPaiementFournisseur.getFournisseur());
        if(p!=null){
            p.setAvance(0);
            paiementFournisseurManager.save(p);
        }
        newPaiementFournisseur.setDatePaiement(new Date(System.currentTimeMillis()));
        paiementFournisseurManager.save(newPaiementFournisseur);
    }
    
    public void prepareAdd(){
        newPaiementFournisseur=new PaiementFournisseur();
        displayFiche=false;
    }
    
    public void onFournisseurSelect(){
        displayFiche=true;
        impaye=0;
        Fournisseur f=newPaiementFournisseur.getFournisseur();
        for(OpAchat op:opAchatManager.getNonPayedByFournisseur(f))
            impaye=impaye+op.getMontant()-op.getMontantPaye();
        avance=paiementFournisseurManager.getAvance(f);
        if(avance>0){
            montantSugg=impaye-avance;
        }
        else
            montantSugg=impaye;
        System.out.println("impaye "+impaye);
        System.out.println("montantSugg "+montantSugg);
        System.out.println("avance "+avance);
        System.out.println("dispayFiche "+displayFiche);
        System.out.println("Fournisseur "+f.getNom());
    }

    public LazyDataModel<PaiementFournisseur> getPaiementFournisseurs() {
        return paiementFournisseurs;
    }

    public void setPaiementFournisseurs(LazyDataModel<PaiementFournisseur> paiementFournisseurs) {
        this.paiementFournisseurs = paiementFournisseurs;
    }

    public PaiementFournisseur getNewPaiementFournisseur() {
        return newPaiementFournisseur;
    }

    public void setNewPaiementFournisseur(PaiementFournisseur newPaiementFournisseur) {
        this.newPaiementFournisseur = newPaiementFournisseur;
    }

    public Fournisseur getSelectedFournisseur() {
        return selectedFournisseur;
    }

    public void setSelectedFournisseur(Fournisseur selectedFournisseur) {
        this.selectedFournisseur = selectedFournisseur;
    }

    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public PaiementFournisseur getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(PaiementFournisseur searchObject) {
        this.searchObject = searchObject;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isDisplayFiche() {
        return displayFiche;
    }

    public void setDisplayFiche(boolean displayFiche) {
        this.displayFiche = displayFiche;
    }

    public float getImpaye() {
        return impaye;
    }

    public void setImpaye(float impaye) {
        this.impaye = impaye;
    }

    public float getAvance() {
        return avance;
    }

    public void setAvance(float avance) {
        this.avance = avance;
    }

    public float getMontantSugg() {
        return montantSugg;
    }

    public void setMontantSugg(float montantSugg) {
        this.montantSugg = montantSugg;
    }

    public List<TypePaiement> getTypesPaiement() {
        return typesPaiement;
    }

    public void setTypesPaiement(List<TypePaiement> typesPaiement) {
        this.typesPaiement = typesPaiement;
    }
}