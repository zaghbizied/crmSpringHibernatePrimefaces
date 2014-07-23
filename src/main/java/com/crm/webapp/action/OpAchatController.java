/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Achat;
import com.crm.model.Fournisseur;
import com.crm.model.OpAchat;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import com.crm.service.AchatManager;
import com.crm.service.FournisseurManager;
import com.crm.service.OpAchatManager;
import com.crm.service.PrixFournisseurManager;
import com.crm.service.ProduitManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
@Component("opAchatController")
@Scope("view")
public class OpAchatController extends BasePage implements Serializable{
    private LazyDataModel<OpAchat> opAchats;
    private AchatManager achatManager;
    private OpAchatManager opAchatManager;
    private ProduitManager produitManager;
    private FournisseurManager fournisseurManager;
    private PrixFournisseurManager prixFournisseurManager;
    private List<Achat> achats;
    private List<Produit> produits=new ArrayList<>();
    private List<Fournisseur> fournisseurs=new ArrayList<>();
    private OpAchat searchObject;
    private OpAchat selectedOpAchat;
    private OpAchat newOpAchat;
    private Achat newAchat;
    
    @Autowired
    public void setPrixFournisseurManager(@Qualifier("prixFournisseurManager")PrixFournisseurManager prixFournisseurManager) {
        this.prixFournisseurManager = prixFournisseurManager;
    }

    @Autowired
    public void setAchatManager(@Qualifier("achatManager")AchatManager achatManager) {
        this.achatManager = achatManager;
    }
    
    @Autowired
    public void setFournisseurManager(@Qualifier("fournisseurManager")FournisseurManager fournisseurManager) {
        this.fournisseurManager = fournisseurManager;
    }

    @Autowired
    public void setOpAchatManager(@Qualifier("opAchatManager")OpAchatManager opAchatManager) {
        this.opAchatManager = opAchatManager;
    }
    
    @Autowired
    public void setProduitManager(@Qualifier("produitManager")ProduitManager produitManager) {
        this.produitManager = produitManager;
    }
    
    public Achat getNewAchat() {
        return newAchat;
    }
    
    public void setNewAchat(Achat newAchat){
        this.newAchat=newAchat;
    }
    
    public LazyDataModel<OpAchat> getOpAchats() {
        return opAchats;
    }

    public void setOpAchats(LazyDataModel<OpAchat> opAchats) {
        this.opAchats = opAchats;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public OpAchat getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(OpAchat searchObject) {
        this.searchObject = searchObject;
    }

    public OpAchat getSelectedOpAchat() {
        return selectedOpAchat;
    }

    public void setSelectedOpAchat(OpAchat selectedOpAchat) {
        this.selectedOpAchat = selectedOpAchat;
    }

    public OpAchat getNewOpAchat() {
        return newOpAchat;
    }

    public void setNewOpAchat(OpAchat newOpAchat) {
        this.newOpAchat = newOpAchat;
    }
    
    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }
    
    @PostConstruct
    public void init(){
        searchObject=new OpAchat();
        produits=produitManager.getAll();
        fournisseurs=fournisseurManager.getAll();
        this.opAchats = new LazyDataModel<OpAchat>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpAchat> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    List<OpAchat> result = opAchatManager.getLazyByDate(new Date(System.currentTimeMillis()),first, pageSize, sortField, sortOrder, filters);
                    return result;
                }
        };
        opAchats.setRowCount(opAchatManager.countByDate(new Date(System.currentTimeMillis())));
    }
   
    public void search() {
    	System.out.println("searching");
        if(searchObject.getFournisseur()!=null){
            if(searchObject.getDateAchat()!=null){
                this.opAchats = new LazyDataModel<OpAchat>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpAchat> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<OpAchat> result = opAchatManager.getLazyByDateByFournisseur(searchObject.getDateAchat(),searchObject.getFournisseur(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                opAchats.setRowCount(opAchatManager.countByDateByFournisseur(searchObject.getDateAchat(),searchObject.getFournisseur()));
            }
            else{
                this.opAchats = new LazyDataModel<OpAchat>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpAchat> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<OpAchat> result = opAchatManager.getLazyByFournisseur(searchObject.getFournisseur(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                opAchats.setRowCount(opAchatManager.countByFournisseur(searchObject.getFournisseur()));
            }
        }
        else{
           if(searchObject.getDateAchat()!=null){
                this.opAchats = new LazyDataModel<OpAchat>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpAchat> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            System.out.println("loading by date "+first+" "+pageSize);
                            List<OpAchat> result = opAchatManager.getLazyByDate(searchObject.getDateAchat(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                opAchats.setRowCount(opAchatManager.countByDate(searchObject.getDateAchat()));
            }
           else{
               this.opAchats = new LazyDataModel<OpAchat>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpAchat> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<OpAchat> result = opAchatManager.getLazyAll(first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                opAchats.setRowCount(opAchatManager.countAll());
           }
        }
    }
    
    public void delete() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        for(Achat a:achats){
            if(a.getVendu()>0){
               facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Impossible de supprimer cette opération !!","Impossible de supprimer cette opération !!"));
               return;
            }
        }
        if(selectedOpAchat.getMontantPaye()>0){
            facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Impossible de supprimer cette opération !!","Impossible de supprimer cette opération !!"));
            return;
        }
        opAchatManager.remove(selectedOpAchat.getId());
    }    

    public void saveNew() {
        float montant=0;
        for(Achat a:achats)
            montant=montant+a.getQuantite()*a.getPrixUnit();
        newOpAchat.setMontant(montant);
        newOpAchat.setDateAchat(new Date(System.currentTimeMillis()));
        OpAchat o=opAchatManager.save(newOpAchat);
        for(Achat a:achats){
            if(a.getPrixUnit()!=0&&a.getQuantite()!=0){
                a.setOpAchat(o);
                achatManager.save(a);
            }
        }
    }
    
    public void onFournisseurSelect(){
        Fournisseur c=newOpAchat.getFournisseur();
        if(c!=null){
            List<PrixFournisseur> pcs=prixFournisseurManager.getByFournisseur(c);
            List<Achat> tmp=new ArrayList<>();
            for(Achat a:achats){
                for(PrixFournisseur pc:pcs){
                    if(pc.getProduit().getId()==a.getProduit().getId()){
                        a.setPrixUnit(pc.getPrixUnit());
                        break;
                    }
                }
                tmp.add(a);
            }
            achats=tmp;
        }
    }
    
    public void prepareUpdate(OpAchat selectedOpAchat){
        this.selectedOpAchat=selectedOpAchat;
        achats=new ArrayList<>(selectedOpAchat.getNestedAchats());
        for(Produit p:produits){
            if(!existInArray(p,achats)){
                Achat tmp=new Achat();
                tmp.setProduit(p);
                tmp.setQuantite(0);
                tmp.setPrixUnit(0);
                achats.add(tmp);
            }
        }
    }
    
    public boolean existInArray(Produit p,List<Achat> achs){
        for(Achat a:achs)
            if(a.getProduit().getId()==p.getId())
                return true;
        return false;
    }
    
    public void prepareAdd(){
        achats=new ArrayList<>();
        for(Produit p:produits){
            Achat tmp=new Achat();
            tmp.setProduit(p);
            tmp.setQuantite(0);
            tmp.setPrixUnit(0);
            achats.add(tmp);
        }
        newOpAchat=new OpAchat();
    }
    
    public void update(){
        FacesContext facescontext = FacesContext.getCurrentInstance();
        float montant=0;
        
        for(Achat a:achats){
            Achat tmp=achatManager.get(a.getId());
            if(tmp.getQuantite()>a.getQuantite()){ //Diminution de quantité
                if(tmp.getVendu()>a.getQuantite()){
                    facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Impossible de modifier la quantité !! Déjà vendu !!","Impossible de modifier la quantité !! Déjà vendu !!"));
                    return;
                }
            }
            montant=montant+a.getQuantite()*a.getPrixUnit();
        }
        if(selectedOpAchat.getMontantPaye()>montant){
            facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Impossible de modifier la quantité !! Paiement déjà effectué !!","Impossible de modifier la quantité !! Paiement déjà effectué !!"));
            return;
        }
        selectedOpAchat.setMontant(montant);
        OpAchat o=opAchatManager.save(selectedOpAchat);
        for(Achat a:achats){
            if(a.getPrixUnit()!=0&&a.getQuantite()!=0){
                a.setOpAchat(o);
                achatManager.save(a);
            }
        }
    }
}
