package com.crm.webapp.action;

import com.crm.model.NumTel;
import com.crm.model.Operateur;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import com.crm.model.Produit;
import com.crm.service.OperateurManager;
import com.crm.service.ProduitManager;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("produitController")
@Scope("view")
public class ProduitController extends BasePage implements Serializable {
    
    private ProduitManager produitManager;
    private List<Produit> produits=new ArrayList<>();
    private Produit selectedProduit;
    private Produit newProduit;
    private OperateurManager operateurManager;
    private List<Operateur> operateurs=new ArrayList<>();

    @Autowired
    public void setOperateurManager(@Qualifier("operateurManager")OperateurManager operateurManager) {
        this.operateurManager=operateurManager;
    }
    
    @Autowired
    public void setProduitManager(@Qualifier("produitManager")ProduitManager produitManager) {
        this.produitManager = produitManager;
    }

    @PostConstruct
    public void init(){
    	produits=produitManager.getAll();
        operateurs=operateurManager.getAll();
    }

    public List<Operateur> getOperateurs() {
        return operateurs;
    }

    public void setOperateurs(List<Operateur> operateurs) {
        this.operateurs = operateurs;
    }
    
    public List<Produit> getProduits() {
        return produits;
    }
    
    public void setProduits(List<Produit> produits){
    	this.produits=produits;
    }

    public Produit getNewProduit() {
	return newProduit;
    }

    public void setNewProduit(Produit newProduit) {
	this.newProduit = newProduit;
    }

    public Produit getSelectedProduit() {
    	return selectedProduit;
    }

    public void setSelectedProduit(Produit selectedProduit) {
	this.selectedProduit = selectedProduit;
    }
	
    public void delete() {
        produitManager.remove(selectedProduit.getId());
        init();
        addMessage("produit.deleted");        
    }    

    public void saveNew() {
    	produitManager.save(newProduit);
        String key = "produit.added" ;
        init();
        addMessage(key);
    }
    
    public void prepareUpdate(Produit selectedProduit){
    	this.selectedProduit=selectedProduit;
    }
    
    public void prepareAdd(){
        newProduit=new Produit();
    }
    
    public void update(){
    	produitManager.save(selectedProduit);
        String key = "produit.updated" ;
        init();
        addMessage(key);
    }
}