/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Achat;
import com.crm.model.Client;
import com.crm.model.NumTel;
import com.crm.model.OpVente;
import com.crm.model.PaiementClient;
import com.crm.model.PrixClient;
import com.crm.model.Produit;
import com.crm.model.TypePaiement;
import com.crm.model.Vente;
import com.crm.service.AchatManager;
import com.crm.service.ClientManager;
import com.crm.service.NumTelManager;
import com.crm.service.OpVenteManager;
import com.crm.service.PaiementClientManager;
import com.crm.service.PrixClientManager;
import com.crm.service.ProduitManager;
import com.crm.service.TypePaiementManager;
import com.crm.service.VenteManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
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
@Component("opVenteController")
@Scope("view")
public class OpVenteController extends BasePage implements Serializable{
    private LazyDataModel<OpVente> opVentes;
    private VenteManager venteManager;
    private OpVenteManager opVenteManager;
    private AchatManager achatManager;
    private ProduitManager produitManager;
    private ClientManager clientManager;
    private PrixClientManager prixClientManager;
    private NumTelManager numTelManager;
    private TypePaiementManager typePaiementManager;
    private PaiementClientManager paiementClientManager;
    private List<Vente> ventes=new ArrayList<>();
    private List<Client> clients=new ArrayList<>();
    private List<Produit> produits=new ArrayList<>();
    private OpVente searchObject;
    private OpVente selectedOpVente;
    private OpVente newOpVente;
    private List<NumTel> numTels=new ArrayList<>();
    private List<PaiementClient> paiements=new ArrayList<>();
    private List<TypePaiement> typesPaiement=new ArrayList<>();
    private boolean displayFiche;
    private float impaye;
    private float avance;
    private float montantSugg;
    
    @Autowired
    public void setAchatManager(@Qualifier("achatManager")AchatManager achatManager) {
        this.achatManager = achatManager;
    }
    
    @Autowired
    public void setTypePaiementManager(@Qualifier("typePaiementManager")TypePaiementManager typePaiementManager) {
        this.typePaiementManager = typePaiementManager;
    }
    
    @Autowired
    public void setPaiementClientManager(@Qualifier("paiementClientManager")PaiementClientManager paiementClientManager) {
        this.paiementClientManager = paiementClientManager;
    }

    @Autowired
    public void setVenteManager(@Qualifier("venteManager")VenteManager venteManager) {
        this.venteManager = venteManager;
    }
    
    @Autowired
    public void setClientManager(@Qualifier("clientManager")ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Autowired
    public void setPrixClientManager(@Qualifier("prixClientManager")PrixClientManager prixClientManager) {
        this.prixClientManager = prixClientManager;
    }

    @Autowired
    public void setNumTelManager(NumTelManager numTelManager) {
        this.numTelManager = numTelManager;
    }

    @Autowired
    public void setOpVenteManager(@Qualifier("opVenteManager")OpVenteManager opVenteManager) {
        this.opVenteManager = opVenteManager;
    }
    
    @Autowired
    public void setProduitManager(@Qualifier("produitManager")ProduitManager produitManager) {
        this.produitManager = produitManager;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public LazyDataModel<OpVente> getOpVentes() {
        return opVentes;
    }

    public void setOpVentes(LazyDataModel<OpVente> opVentes) {
        this.opVentes = opVentes;
    }

    public List<Vente> getVentes() {
        return ventes;
    }

    public void setVentes(List<Vente> ventes) {
        this.ventes = ventes;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public OpVente getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(OpVente searchObject) {
        this.searchObject = searchObject;
    }

    public OpVente getSelectedOpVente() {
        return selectedOpVente;
    }

    public void setSelectedOpVente(OpVente selectedOpVente) {
        this.selectedOpVente = selectedOpVente;
    }

    public OpVente getNewOpVente() {
        return newOpVente;
    }

    public void setNewOpVente(OpVente newOpVente) {
        this.newOpVente = newOpVente;
    }    

    public List<NumTel> getNumTels() {
        return numTels;
    }

    public void setNumTels(List<NumTel> numTels) {
        this.numTels = numTels;
    }

    public List<PaiementClient> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<PaiementClient> paiements) {
        this.paiements = paiements;
    }

    public List<TypePaiement> getTypesPaiement() {
        return typesPaiement;
    }

    public void setTypesPaiement(List<TypePaiement> typesPaiement) {
        this.typesPaiement = typesPaiement;
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
    
    @PostConstruct
    public void init(){
        produits=produitManager.getAll();
        this.opVentes = new LazyDataModel<OpVente>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpVente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    List<OpVente> result = opVenteManager.getLazyByDate(new Date(System.currentTimeMillis()),first, pageSize, sortField, sortOrder, filters);
                    return result;
                }
        };
        opVentes.setRowCount(opVenteManager.countByDate(new Date(System.currentTimeMillis())));
        clients=clientManager.getAll();
        searchObject=new OpVente();
        searchObject.setDateVente(new Date(System.currentTimeMillis()));
        typesPaiement=typePaiementManager.getAll();
        displayFiche=false;
    }
   
    public void search() {
        System.out.println("searching");
        if(searchObject.getClient()!=null){
            if(searchObject.getDateVente()!=null){
                this.opVentes = new LazyDataModel<OpVente>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpVente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<OpVente> result = opVenteManager.getLazyByDateByClient(searchObject.getDateVente(),searchObject.getClient(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                opVentes.setRowCount(opVenteManager.countByDateByClient(searchObject.getDateVente(),searchObject.getClient()));
            }
            else{
                this.opVentes = new LazyDataModel<OpVente>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpVente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<OpVente> result = opVenteManager.getLazyByClient(searchObject.getClient(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                opVentes.setRowCount(opVenteManager.countByClient(searchObject.getClient()));
            }
        }
        else{
           if(searchObject.getDateVente()!=null){
                this.opVentes = new LazyDataModel<OpVente>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpVente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            System.out.println("loading by date "+first+" "+pageSize);
                            List<OpVente> result = opVenteManager.getLazyByDate(searchObject.getDateVente(),first, pageSize, sortField, sortOrder, filters);
                            return result;
                        }
                };
                opVentes.setRowCount(opVenteManager.countByDate(searchObject.getDateVente()));
            }
           else{
               this.opVentes = new LazyDataModel<OpVente>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<OpVente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                            List<OpVente> result = opVenteManager.getLazyAll(first, pageSize, sortField, sortOrder, filters);
                          //  opVentes.setRowCount(result.size());
                            return result;
                        }
                };
                opVentes.setRowCount(opVenteManager.countAll());
           }
        }
    }
    
    public void delete() {
        for(Vente a:ventes){
            System.out.println("okok ! 185 "+a.getQuantite());
            List<Achat> achats=achatManager.getByProduit(a.getProduit());
            Collections.sort(achats, new Comparator<Achat>() {
                @Override
                public int compare(Achat a1, Achat a2) {
                    return a1.getOpAchat().getDateAchat().compareTo(a2.getOpAchat().getDateAchat());
                }
            });
            int nbre=a.getQuantite();
            System.out.println("okok ! 196 "+nbre);
            for(int i=0;i<achats.size();i++){
                if(nbre>0){
                    Achat achat=achats.get(i);
                    int vendu=achat.getVendu();
                    System.out.println("okok ! 199 vendu == "+vendu);
                    if(nbre>vendu){
                        achat.setVendu(0);
                        nbre=nbre-vendu;
                        achatManager.save(achat);
                    }
                    else{
                        achat.setVendu(vendu-nbre);
                        achatManager.save(achat);
                        break;
                    }
                }
                else{
                    System.out.println("okok ! 212 nbre 0");
                    break;
                }
            }
            venteManager.remove(a);
        }
        opVenteManager.remove(selectedOpVente.getId());
    }    

    public void saveNew(ActionEvent action) {
        boolean fail=false;
        FacesContext facescontext = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        if(newOpVente.getClient()==null){
            fail=true;
            facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Le champ client est requis","Le champ client est requis"));
        }
        for(Vente v:ventes)
            if(v.getQuantite()>v.getDisponible()){
                fail=true;
                System.out.println("error");
                facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de saisie des quantités","Quantité non disponible"));
                break;
            }
        
        context.addCallbackParam("fail", fail);  
        if(fail){
            context.reset(":addForm:displayAdd");
        }
        else{
            System.out.println("saving new");
            newOpVente.setDateVente(new Date(System.currentTimeMillis()));
            float montant=0;
            for(Vente a:ventes)    
                    montant=montant+a.getQuantite()*a.getPrixUnit();
            newOpVente.setMontant(montant);
            OpVente o=opVenteManager.save(newOpVente);
            for(Vente a:ventes){
                if(a.getPrixUnit()!=0&&a.getQuantite()!=0){
                    List<Achat> achats=achatManager.getNotSelled(a.getProduit());
                    Collections.sort(achats, new Comparator<Achat>() {
                        @Override
                        public int compare(Achat a1, Achat a2) {
                            return a1.getOpAchat().getDateAchat().compareTo(a2.getOpAchat().getDateAchat());
                        }
                    });
                    int nbre=a.getQuantite();
                    float prixAchat=0;
                    for(int i=achats.size()-1;i>=0;i--){
                        if(nbre>0){
                            Achat achat=achats.get(i);
                            int dispo=achat.getQuantite()-achat.getVendu();
                            int vendu;
                            if(dispo>nbre){
                                vendu=nbre;
                            }
                            else vendu=dispo;
                            achat.setVendu(achat.getVendu()+vendu);
                            nbre=nbre-vendu;
                            prixAchat=prixAchat+vendu*achat.getPrixUnit();
                            achatManager.save(achat);
                        }
                        else{
                            break;
                        }
                    }
                    a.setPrixAchat(prixAchat);
                    a.setOpVente(o);
                    a.setValidated(false);
                    venteManager.save(a);
                }
            }
        }
        for(PaiementClient pc:paiements){
            float montant=pc.getMontant()+avance;
            pc.setMontant(montant);
            List<OpVente> lst=opVenteManager.getNonPayedByClient(pc.getClient());
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
            if(montant>0)pc.setAvance(montant);
            PaiementClient p=paiementClientManager.getPaiementAvecAvance(pc.getClient());
            if(p!=null){
                p.setAvance(0);
                paiementClientManager.save(p);
            }
            pc.setDatePaiement(new Date(System.currentTimeMillis()));
            paiementClientManager.save(pc);
        }
            
    }
    
    public void onClientSelect(){
        System.out.println("client selected");
        Client c=newOpVente.getClient();
        numTels=new ArrayList<>();
        for(NumTel n:numTelManager.getByClient(c))
            numTels.add(n);
        System.out.println("numTemls size "+numTels);
        displayFiche=true;
        impaye=0;
        if(c!=null){
            List<PrixClient> pcs=prixClientManager.getByClient(c);
            List<Vente> tmp=new ArrayList<>();
            for(Vente v:ventes){
                for(PrixClient pc:pcs){
                    if(pc.getProduit().getId()==v.getProduit().getId()){
                        v.setPrixUnit(pc.getPrixUnit());
                        tmp.add(v);
                        break;
                    }
                }
                int dispo=0;
                List<Achat> achats=achatManager.getNotSelled(v.getProduit());
                if(achats!=null)
                for(Achat a:achats){
                    dispo=dispo+a.getQuantite()-a.getVendu();
                }
                
                v.setDisponible(dispo);
                v.setMontant(v.getQuantite()*v.getPrixUnit());
                /*if(dispo>0)v.setDisable(false);
                else v.setDisable(true);*/
            }
            ventes=tmp;
            for(OpVente op:opVenteManager.getNonPayedByClient(c))
                impaye=impaye+op.getMontant()-op.getMontantPaye();
            
            avance=paiementClientManager.getAvance(c);
            if(avance>0){
                montantSugg=impaye-avance;
            }
            else
                montantSugg=impaye;
        }
        
    }
    
    public void prepareUpdate(OpVente selectedOpVente){
        this.selectedOpVente=selectedOpVente;
        Client c=selectedOpVente.getClient();
        numTels=new ArrayList<>();
        for(NumTel n:numTelManager.getByClient(c))
            numTels.add(n);
        ventes=new ArrayList<>(selectedOpVente.getNestedVentes());
        for(Vente v:ventes){
            int dispo=0;
            List<Achat> achats=achatManager.getNotSelled(v.getProduit());
            if(achats!=null){
                for(Achat a:achats){
                    dispo=dispo+a.getQuantite()-a.getVendu();
                }
                v.setDisponible(dispo);
                /*if(dispo>0)v.setDisable(false);
                else v.setDisable(true);*/
            }
            else{
                    //v.setDisable(true);
                    v.setDisponible(0);
            }
            v.setMontant(v.getQuantite()*v.getPrixUnit());
        }
        for(Produit p:produits){
            if(!existInArray(p,ventes)){
                Vente tmp=new Vente();
                tmp.setProduit(p);
                tmp.setQuantite(0);
                PrixClient pc=prixClientManager.getByClientByProduit(selectedOpVente.getClient(),p);
                if(pc!=null)tmp.setPrixUnit(pc.getPrixUnit());
                else tmp.setPrixUnit(0);
                int dispo=0;
                List<Achat> achats=achatManager.getNotSelled(p);
                if(achats!=null){
                    for(Achat a:achatManager.getNotSelled(p)){
                        dispo=dispo+a.getQuantite()-a.getVendu();
                    }
                    tmp.setDisponible(dispo);
                    /*if(dispo>0)tmp.setDisable(false);
                    else tmp.setDisable(true);*/
                }
                else{
                    //tmp.setDisable(true);
                    tmp.setDisponible(0);
                }
                ventes.add(tmp);
            }
        }
        System.out.println("finished preparing update" +numTels);
    }
    
    public boolean existInArray(Produit p,List<Vente> vents){
        for(Vente a:vents)
            if(a.getProduit().getId()==p.getId())
                return true;
        return false;
    }
    
    public void prepareAdd(){
       numTels=new ArrayList<>();
       ventes=new ArrayList<>();
       paiements=new ArrayList<>();
       for(Produit p:produits){
            Vente tmp=new Vente();
            tmp.setProduit(p);
            tmp.setQuantite(0);
            tmp.setPrixUnit(0);
            tmp.setMontant(0);
            int dispo=0;
            List<Achat> achats=achatManager.getNotSelled(p);
            if(achats!=null){
                for(Achat a:achatManager.getNotSelled(p)){
                    dispo=dispo+a.getQuantite()-a.getVendu();
                }
                tmp.setDisponible(dispo);
                /*if(dispo>0)tmp.setDisable(false);
                else tmp.setDisable(true);*/
            }
            else{
                //tmp.setDisable(true);
                tmp.setDisponible(0);
            }
            ventes.add(tmp);
       }
       newOpVente=new OpVente();
    }
    
    public void update(){
        boolean fail=false;
        FacesContext facescontext = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        if(selectedOpVente.getClient()==null){
            fail=true;
            facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Le champ client est requis","Le champ client est requis"));
        }
        for(Vente v:ventes){
            Vente prev=venteManager.get(v.getId());
            if(v.getQuantite()-prev.getQuantite()>v.getDisponible()){
                fail=true;
                System.out.println("error");
                facescontext.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de saisie des quantités","Quantité non disponible"));
                break;
            }
        }
            
        context.addCallbackParam("fail", fail);  
        if(fail){
            context.reset(":editForm:displayEdit");
        }
        else{
        float montant=0;
        for(Vente a:ventes)
            montant=montant+a.getQuantite()*a.getPrixUnit();
        selectedOpVente.setMontant(montant);
        for(Vente a:ventes){
            if(a.getId()!=null){
                System.out.println("modification d'une vente existante");
                Vente previousVersion=venteManager.get(a.getId());
                if(a.getPrixUnit()!=0&&a.getQuantite()!=0){
                    System.out.println("okok ! 346");
                    System.out.println("okok ! 347 "+a.getId()+" "+a.getProduit().getDescription()+" "+a.getQuantite());
                    System.out.println("okok ! 347 "+previousVersion.getId()+" "+previousVersion.getProduit().getDescription()+" "+previousVersion.getQuantite());
                    if(a.getQuantite()>previousVersion.getQuantite()){
                        System.out.println("okok ! 348 "+a.getQuantite()+" "+previousVersion.getQuantite());
                        List<Achat> achats=achatManager.getNotSelled(a.getProduit());
                        Collections.sort(achats, new Comparator<Achat>() {
                            @Override
                            public int compare(Achat a1, Achat a2) {
                                return a1.getOpAchat().getDateAchat().compareTo(a2.getOpAchat().getDateAchat());
                            }
                        });
                        int nbre=a.getQuantite()-previousVersion.getQuantite();
                        System.out.println("okok ! 357 "+nbre);
                        float prixAchat=previousVersion.getPrixAchat();
                        for(int i=achats.size()-1;i>=0;i--){
                            System.out.println("okok ! 360 "+nbre);
                            if(nbre>0){
                                Achat achat=achats.get(i);
                                int dispo=achat.getQuantite()-achat.getVendu();
                                int vendu;
                                if(dispo>nbre){
                                    vendu=nbre;
                                }
                                else vendu=dispo;
                                achat.setVendu(achat.getVendu()+vendu);
                                nbre=nbre-vendu;
                                prixAchat=prixAchat+vendu*achat.getPrixUnit();
                                achatManager.save(achat);
                            }
                            else{
                                break;
                            }
                        }
                        a.setPrixAchat(prixAchat);
                        a.setOpVente(selectedOpVente);
                        venteManager.save(a);
                    }
                    else{
                        System.out.println("okok ! 387 "+a.getQuantite()+" "+previousVersion.getQuantite());
                        List<Achat> achats=achatManager.getByProduit(a.getProduit());
                        Collections.sort(achats, new Comparator<Achat>() {
                            @Override
                            public int compare(Achat a1, Achat a2) {
                                return a1.getOpAchat().getDateAchat().compareTo(a2.getOpAchat().getDateAchat());
                            }
                        });
                        int nbre=previousVersion.getQuantite()-a.getQuantite();
                        System.out.println("okok ! 396 "+nbre);
                        float prixAchat=a.getPrixAchat();
                        for(int i=0;i<achats.size();i++){
                            if(nbre>0){
                                Achat achat=achats.get(i);
                                int vendu=achat.getVendu();
                                System.out.println("okok ! 402 vendu == "+vendu);
                                if(nbre>vendu){
                                    achat.setVendu(0);
                                    nbre=nbre-vendu;
                                    prixAchat=prixAchat-vendu*achat.getPrixUnit();
                                    achatManager.save(achat);
                                }
                                else{
                                    achat.setVendu(vendu-nbre);
                                    prixAchat=prixAchat-nbre*achat.getPrixUnit();
                                    achatManager.save(achat);
                                    break;
                                }
                            }
                            else{
                                System.out.println("okok ! 417 nbre 0");
                                break;
                            }
                        }
                        a.setPrixAchat(prixAchat);
                        a.setOpVente(selectedOpVente);
                        venteManager.save(a);
                    }
                }
                else if (a.getQuantite()==0){
                    System.out.println("okok ! 427 "+a.getQuantite()+" "+previousVersion.getQuantite());
                    List<Achat> achats=achatManager.getByProduit(a.getProduit());
                    Collections.sort(achats, new Comparator<Achat>() {
                        @Override
                        public int compare(Achat a1, Achat a2) {
                            return a1.getOpAchat().getDateAchat().compareTo(a2.getOpAchat().getDateAchat());
                        }
                    });
                    int nbre=previousVersion.getQuantite();
                    System.out.println("okok ! 436 "+nbre);
                    for(int i=0;i<achats.size();i++){
                        if(nbre>0){
                            Achat achat=achats.get(i);
                            int vendu=achat.getVendu();
                            System.out.println("okok ! 402 vendu == "+vendu);
                            if(nbre>vendu){
                                achat.setVendu(0);
                                nbre=nbre-vendu;
                                achatManager.save(achat);
                            }
                            else{
                                achat.setVendu(vendu-nbre);
                                achatManager.save(achat);
                                break;
                            }
                        }
                        else{
                            System.out.println("okok ! 455 nbre 0");
                            break;
                        }
                    }
                    a.setOpVente(null);
                    venteManager.remove(a.getId());
                }
            }
            else{
                System.out.println("ajout d'une vente non existante");
                if(a.getPrixUnit()!=0&&a.getQuantite()!=0){
                    System.out.println("okok 423 !");
                    List<Achat> achats=achatManager.getNotSelled(a.getProduit());
                    Collections.sort(achats, new Comparator<Achat>() {
                        @Override
                        public int compare(Achat a1, Achat a2) {
                            return a1.getOpAchat().getDateAchat().compareTo(a2.getOpAchat().getDateAchat());
                        }
                    });
                    int nbre=a.getQuantite();
                    System.out.println("okok 423 nbre ! "+nbre);
                    float prixAchat=0;
                    for(int i=achats.size()-1;i>=0;i--){
                        if(nbre>0){
                            Achat achat=achats.get(i);
                            int dispo=achat.getQuantite()-achat.getVendu();
                            int vendu;
                            if(dispo>nbre){
                                vendu=nbre;
                            }
                            else vendu=dispo;
                            achat.setVendu(achat.getVendu()+vendu);
                            nbre=nbre-vendu;
                            prixAchat=prixAchat+vendu*achat.getPrixUnit();
                            achatManager.save(achat);
                        }
                        else{
                            break;
                        }
                    }
                    a.setPrixAchat(prixAchat);
                    a.setOpVente(selectedOpVente);
                    venteManager.save(a);
                }
            }
        }
        opVenteManager.save(selectedOpVente);
        }
        
    }
    
    public void handleInputChange(int index) {
        Vente tmp=ventes.get(index);
        tmp.setMontant(tmp.getQuantite()*tmp.getPrixUnit());
        ventes.set(index,tmp);
    }
    
    public void addPaiement(){
        PaiementClient pc=new PaiementClient();
        if(newOpVente.getClient()!=null)pc.setClient(newOpVente.getClient());
        paiements.add(pc);
    }
}