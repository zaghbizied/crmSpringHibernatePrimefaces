/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Client;
import com.crm.model.OpVente;
import com.crm.model.PaiementClient;
import com.crm.model.TypePaiement;
import com.crm.service.ClientManager;
import com.crm.service.OpVenteManager;
import com.crm.service.PaiementClientManager;
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
@Component("paiementClientController")
@Scope("view")

public class PaiementClientController extends BasePage implements Serializable{
    private LazyDataModel<PaiementClient> paiementsClient;
    private PaiementClient newPaiementClient;
    private PaiementClientManager paiementClientManager;
    private TypePaiementManager typePaiementManager;
    private ClientManager clientManager;
    private OpVenteManager opVenteManager;
    private PaiementClient searchObject;
    private List<Client> clients=new ArrayList<>();
    private List<TypePaiement> typesPaiement=new ArrayList<>();
    private boolean displayFiche;
    private float impaye;
    private float avance;
    private float montantSugg;
    
    
    @Autowired
    public void setClientManager(@Qualifier("clientManager")ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Autowired
    public void setPaiementClientManager(@Qualifier("paiementClientManager")PaiementClientManager paiementClientManager) {
        this.paiementClientManager = paiementClientManager;
    }
    
    @Autowired
    public void setOpVenteManager(@Qualifier("opVenteManager")OpVenteManager opVenteManager) {
        this.opVenteManager = opVenteManager;
    }
    
    @Autowired
    public void setTypePaiementManager(@Qualifier("typePaiementManager")TypePaiementManager typePaiementManager) {
        this.typePaiementManager = typePaiementManager;
    }
    
    public void search() throws ParseException{
        System.out.println("searching");
        if(searchObject.getClient()!=null){
            if(searchObject.getDatePaiement()!=null){
                this.paiementsClient = new LazyDataModel<PaiementClient>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementClient> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<PaiementClient> result = paiementClientManager.getLazyByDateByClient(searchObject.getDatePaiement(),searchObject.getClient(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementsClient.setRowCount(paiementClientManager.countByDateByClient(searchObject.getDatePaiement(),searchObject.getClient()));
            }
            else{
                this.paiementsClient = new LazyDataModel<PaiementClient>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementClient> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<PaiementClient> result = paiementClientManager.getLazyByClient(searchObject.getClient(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementsClient.setRowCount(paiementClientManager.countByClient(searchObject.getClient()));
            }
        }
        else{
           if(searchObject.getDatePaiement()!=null){
                this.paiementsClient = new LazyDataModel<PaiementClient>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementClient> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            System.out.println("loading by date "+first+" "+pageSize);
                            List<PaiementClient> result = paiementClientManager.getLazyByDate(searchObject.getDatePaiement(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementsClient.setRowCount(paiementClientManager.countByDate(searchObject.getDatePaiement()));
            }
           else{
               this.paiementsClient = new LazyDataModel<PaiementClient>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementClient> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<PaiementClient> result = paiementClientManager.getLazyAll(first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                paiementsClient.setRowCount(paiementClientManager.countAll());
           }
        }
    }
    
    @PostConstruct
    public void init(){
        searchObject=new PaiementClient();
        searchObject.setDatePaiement(new Date(System.currentTimeMillis()));
        this.paiementsClient = new LazyDataModel<PaiementClient>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<PaiementClient> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    List<PaiementClient> result = paiementClientManager.getLazyByDate(searchObject.getDatePaiement(),first, pageSize, sortField, sortOrder, filters);
                    return result;
                }
        };
        paiementsClient.setRowCount(paiementClientManager.countByDate(searchObject.getDatePaiement()));
        clients=clientManager.getAll();
        typesPaiement=typePaiementManager.getAll();
    }
    
    public void saveNew(){
        float montant=newPaiementClient.getMontant()+avance;
        newPaiementClient.setMontant(montant);
        List<OpVente> lst=opVenteManager.getNonPayedByClient(newPaiementClient.getClient());
        Collections.sort(lst, new Comparator<OpVente>() {
            @Override
            public int compare(OpVente op1, OpVente op2) {
                return op1.getDateVente().compareTo(op2.getDateVente());
            }
        });
        for(OpVente op:lst){
            float montantRestant=op.getMontant()-op.getMontantPaye();
            if(montantRestant<=montant){
                montant=montant-montantRestant;
                op.setMontantPaye(op.getMontant());
                opVenteManager.save(op);
            }
            else if(montant>0){
                op.setMontantPaye(op.getMontantPaye()+montant);
                opVenteManager.save(op);
                montant=0;
                break;
            }
            else
                break;
        }
        if(montant>0)newPaiementClient.setAvance(montant);
        PaiementClient p=paiementClientManager.getPaiementAvecAvance(newPaiementClient.getClient());
        if(p!=null){
            p.setAvance(0);
            paiementClientManager.save(p);
        }
        newPaiementClient.setDatePaiement(new Date(System.currentTimeMillis()));
        paiementClientManager.save(newPaiementClient);
    }
    
    public void prepareAdd(){
        newPaiementClient=new PaiementClient();
        displayFiche=false;
    }
    
    public void onClientSelect(){
        displayFiche=true;
        impaye=0;
        Client c=newPaiementClient.getClient();
        if (c!=null){
            for(OpVente op:opVenteManager.getNonPayedByClient(c))
            impaye=impaye+op.getMontant()-op.getMontantPaye();
            avance=paiementClientManager.getAvance(c);
            if(avance>0){
                montantSugg=impaye-avance;
            }
            else
                montantSugg=impaye;
            System.out.println("impaye "+impaye);
            System.out.println("montantSugg "+montantSugg);
            System.out.println("avance "+avance);
            System.out.println("dispayFiche "+displayFiche);
            System.out.println("Fournisseur "+c.getNom());
        }
    }

    public LazyDataModel<PaiementClient> getPaiementsClient() {
        return paiementsClient;
    }

    public void setPaiementsClient(LazyDataModel<PaiementClient> paiementsClient) {
        this.paiementsClient = paiementsClient;
    }

    public PaiementClient getNewPaiementClient() {
        return newPaiementClient;
    }

    public void setNewPaiementClient(PaiementClient newPaiementClient) {
        this.newPaiementClient = newPaiementClient;
    }
    
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public PaiementClient getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(PaiementClient searchObject) {
        this.searchObject = searchObject;
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