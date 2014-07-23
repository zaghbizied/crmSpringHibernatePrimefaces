/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Fournisseur;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import com.crm.service.FournisseurManager;
import com.crm.service.PrixFournisseurManager;
import com.crm.service.ProduitManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zied
 */
@Component("fournisseurController")
@Scope("view")
public class FournisseurController extends BasePage implements Serializable{
    private List<Fournisseur> fournisseurs=new ArrayList<>();
    private FournisseurManager fournisseurManager;
    private PrixFournisseurManager prixFournisseurManager;
    private Fournisseur searchObject;
    private Fournisseur selectedFournisseur;
    private Fournisseur newFournisseur;
    private List<PrixFournisseur> prixFournisseurs=new ArrayList<>();
    private ProduitManager produitManager;
    
    @Autowired
    public void setFournisseurManager(@Qualifier("fournisseurManager")FournisseurManager fournisseurManager) {
        this.fournisseurManager=fournisseurManager;
    }
    
    @Autowired
    public void setProduitManager(@Qualifier("produitManager")ProduitManager produitManager) {
        this.produitManager=produitManager;
    }
    
    @Autowired
    public void setPrixFournisseurManager(@Qualifier("prixFournisseurManager")PrixFournisseurManager prixFournisseurManager) {
        this.prixFournisseurManager=prixFournisseurManager;
    }
    
    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }
    
    public Fournisseur getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(Fournisseur searchObject) {
        this.searchObject = searchObject;
    }

    public Fournisseur getSelectedFournisseur() {
        return selectedFournisseur;
    }

    public void setSelectedFournisseur(Fournisseur selectedFournisseur) {
        this.selectedFournisseur = selectedFournisseur;
    }

    public Fournisseur getNewFournisseur() {
        return newFournisseur;
    }

    public void setNewFournisseur(Fournisseur newFournisseur) {
        this.newFournisseur = newFournisseur;
    }
    
    @PostConstruct
    public void init(){
        fournisseurs=fournisseurManager.getAll();
        searchObject=new Fournisseur();
    }
   
    public void search() {
    	fournisseurs=new ArrayList<>();
    	for(Fournisseur c:fournisseurManager.getAll()){
    		boolean match=true;
    		if(searchObject!=null){
    			if(!c.getNom().toLowerCase().contains(searchObject.getNom().toLowerCase()))match=false;
    			if(!c.getVille().toLowerCase().contains(searchObject.getVille().toLowerCase()))match=false;
    		}
    		if(match)fournisseurs.add(c);
    	}	
    }
    
    public void delete() {
        fournisseurManager.remove(selectedFournisseur.getId());
    }    

    public void saveNew() {
    	Fournisseur f=fournisseurManager.save(newFournisseur);
        for(PrixFournisseur pf:prixFournisseurs){
            if(pf.getPrixUnit()!=0){
                pf.setFournisseur(f);
                prixFournisseurManager.save(pf);
            }
        }
    }
    
     public boolean existInArray(Produit p,List<PrixFournisseur> prixFournisseurs){
        for(PrixFournisseur a:prixFournisseurs)
            if(a.getProduit().getId()==p.getId())
                return true;
        return false;
    }
    
    public void prepareUpdate(Fournisseur selectedFournisseur){
    	this.selectedFournisseur=selectedFournisseur;
        prixFournisseurs=prixFournisseurManager.getByFournisseur(selectedFournisseur);
        for(Produit p:produitManager.getAll()){
            if(!existInArray(p,prixFournisseurs)){
                PrixFournisseur tmp=new PrixFournisseur();
                tmp.setProduit(p);
                tmp.setPrixUnit(0);
                prixFournisseurs.add(tmp);
            }
        }
    }
    
    public void prepareAdd(){
        newFournisseur=new Fournisseur();
        prixFournisseurs=new ArrayList<>();
        for(Produit p:produitManager.getAll()){
            PrixFournisseur pc=new PrixFournisseur();
            pc.setProduit(p);
            prixFournisseurs.add(pc);
        }
    }
    
    public void update(){
        Fournisseur f=fournisseurManager.save(selectedFournisseur);
        for(PrixFournisseur pf:prixFournisseurs){
            if(pf.getPrixUnit()!=0){
                pf.setFournisseur(f);
                prixFournisseurManager.save(pf);
            }
        }
    }

    public List<PrixFournisseur> getPrixFournisseurs() {
        return prixFournisseurs;
    }

    public void setPrixFournisseurs(List<PrixFournisseur> prixFournisseurs) {
        this.prixFournisseurs = prixFournisseurs;
    }
}
