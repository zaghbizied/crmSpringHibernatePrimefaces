/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Client;
import com.crm.model.NumTel;
import com.crm.model.Operateur;
import com.crm.model.PrixClient;
import com.crm.model.Produit;
import com.crm.service.ClientManager;
import com.crm.service.NumTelManager;
import com.crm.service.OperateurManager;
import com.crm.service.PrixClientManager;
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
@Component("clientController")
@Scope("view")
public class ClientController extends BasePage implements Serializable{
    private List<Client> clients=new ArrayList<>();
    private ClientManager clientManager;
    private PrixClientManager prixClientManager;
    private NumTelManager numTelManager;
    private ProduitManager produitManager;
    private OperateurManager operateurManager;
    private List<NumTel> numTels;
    private List<Operateur> operateurs=new ArrayList<>();
    private Client searchObject;
    private Client selectedClient;
    private Client newClient;
    private NumTel newNumTel;
    private List<PrixClient> prixClients=new ArrayList<>();

    public NumTel getNewNumTel() {
        return newNumTel;
    }

    public void setNewNumTel(NumTel newNumTel) {
        this.newNumTel = newNumTel;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    @Autowired
    public void setClientManager(@Qualifier("clientManager")ClientManager clientManager) {
        this.clientManager=clientManager;
    }
    
    @Autowired
    public void setProduitManager(@Qualifier("produitManager")ProduitManager produitManager) {
        this.produitManager=produitManager;
    }
    
    @Autowired
    public void setPrixClientManager(@Qualifier("prixClientManager")PrixClientManager prixClientManager) {
        this.prixClientManager=prixClientManager;
    }
    
    @Autowired
    public void setOperateurManager(@Qualifier("operateurManager")OperateurManager operateurManager) {
        this.operateurManager=operateurManager;
    }
    
    @Autowired
    public void setNumTelManager(@Qualifier("numTelManager")NumTelManager numTelManager) {
        this.numTelManager=numTelManager;
    }
    
    public Client getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(Client searchObject) {
        this.searchObject = searchObject;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public Client getNewClient() {
        return newClient;
    }

    public void setNewClient(Client newClient) {
        this.newClient = newClient;
    }

    public List<NumTel> getNumTels() {
        return numTels;
    }

    public void setNumTels(List<NumTel> numTels) {
        this.numTels = numTels;
    }

    public List<Operateur> getOperateurs() {
        return operateurs;
    }

    public void setOperateurs(List<Operateur> operateurs) {
        this.operateurs = operateurs;
    }

    public List<PrixClient> getPrixClients() {
        return prixClients;
    }

    public void setPrixClients(List<PrixClient> prixClients) {
        this.prixClients = prixClients;
    }
    
    @PostConstruct
    public void init(){
        clients=clientManager.getAll();
        searchObject=new Client();
        operateurs=operateurManager.getAll();
    }
    
    public void prepareAddNumTel(){
        newNumTel=new NumTel();
    }
    
    public void addNumTel(){
        numTels.add(newNumTel);
    }
   
    public void search() {
    	clients=new ArrayList<>();
    	for(Client c:clientManager.getAll()){
    		boolean match=true;
    		if(searchObject!=null){
    			if(!c.getNom().toLowerCase().contains(searchObject.getNom().toLowerCase()))match=false;
    			if(!c.getPrenom().toLowerCase().contains(searchObject.getPrenom().toLowerCase()))match=false;
    		}
    		if(match)clients.add(c);
    	}	
    }
    
    public void delete() {
        clientManager.remove(selectedClient.getId());
    }    

    public void saveNew() {
    	Client c=clientManager.save(newClient);
        for(NumTel numTel:numTels){
            numTel.setClient(c);
            numTelManager.save(numTel);
        }
        for(PrixClient pc:prixClients){
            if(pc.getPrixUnit()!=0){
                pc.setClient(c);
                prixClientManager.save(pc);
            }
        }
    }
    
    public void prepareUpdate(Client selectedClient){
        numTels=new ArrayList<>(selectedClient.getNumTels());
    	this.selectedClient=selectedClient;
        prixClients=prixClientManager.getByClient(selectedClient);
        for(Produit p:produitManager.getAll()){
            if(!existInArray(p,prixClients)){
                PrixClient tmp=new PrixClient();
                tmp.setProduit(p);
                tmp.setPrixUnit(0);
                prixClients.add(tmp);
            }
        }
        
    }
    
    public boolean existInArray(Produit p,List<PrixClient> prixClients){
        for(PrixClient a:prixClients)
            if(a.getProduit().getId()==p.getId())
                return true;
        return false;
    }
    
    public void prepareAdd(){
        numTels=new ArrayList<>();
        newClient=new Client();
        prixClients=new ArrayList<>();
        for(Produit p:produitManager.getAll()){
            PrixClient pc=new PrixClient();
            pc.setProduit(p);
            prixClients.add(pc);
        }
    }
    
    public void update(){
        Client c=clientManager.save(selectedClient);
        for(NumTel numTel:numTels){
            numTel.setClient(c);
            numTelManager.save(numTel);
        }
        for(PrixClient pc:prixClients){
            if(pc.getPrixUnit()!=0){
                pc.setClient(c);
                prixClientManager.save(pc);
            }
        }
    }
}
