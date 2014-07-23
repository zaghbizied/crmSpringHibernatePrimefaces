/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service.impl;

import com.crm.jasper.Customer;
import com.crm.jasper.InvoiceData;
import com.crm.jasper.InvoiceDesign;
import com.crm.jasper.Item;
import com.crm.model.Achat;
import com.crm.model.Caisse;
import com.crm.model.CaisseDetails;
import com.crm.model.Client;
import com.crm.model.ClotureObject;
import com.crm.model.FicheClient;
import com.crm.model.FicheFournisseur;
import com.crm.model.Fournisseur;
import com.crm.model.Invoice;
import com.crm.model.NumTel;
import com.crm.model.OpAchat;
import com.crm.model.OpVente;
import com.crm.model.Operateur;
import com.crm.model.PaiementClient;
import com.crm.model.PaiementFournisseur;
import com.crm.model.PrelevementCaisse;
import com.crm.model.PrixClient;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import com.crm.model.Vente;
import com.crm.service.AchatManager;
import com.crm.service.CaisseManager;
import com.crm.service.ClientManager;
import com.crm.service.FournisseurManager;
import com.crm.service.InvoiceManager;
import com.crm.service.MobileController;
import com.crm.service.NumTelManager;
import com.crm.service.OpAchatManager;
import com.crm.service.OpVenteManager;
import com.crm.service.OperateurManager;
import com.crm.service.PaiementClientManager;
import com.crm.service.PaiementFournisseurManager;
import com.crm.service.PrelevementCaisseManager;
import com.crm.service.PrixClientManager;
import com.crm.service.PrixFournisseurManager;
import com.crm.service.ProduitManager;
import com.crm.service.VenteManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author zied
 */
@Service("mobileController")
@WebService(serviceName = "mobileService", endpointInterface = "com.crm.service.MobileController")
public class MobileControllerImpl extends SpringBeanAutowiringSupport implements MobileController{
    
    private ClientManager clientManager;
    private NumTelManager numTelManager;
    private OperateurManager operateurManager;
    private ProduitManager produitManager;
    private FournisseurManager fournisseurManager;
    private PrelevementCaisseManager prelevementCaisseManager;
    private PrixFournisseurManager prixFournisseurManager;
    private PrixClientManager prixClientManager;
    private OpAchatManager opAchatManager;
    private OpVenteManager opVenteManager;
    private PaiementClientManager paiementClientManager;
    private PaiementFournisseurManager paiementFournisseurManager;
    private CaisseManager caisseManager;
    private AchatManager achatManager;
    private VenteManager venteManager;
    private InvoiceManager invoiceManager;
    
    @Autowired
    public void setPrixFournisseurManager(@Qualifier("prixFournisseurManager")PrixFournisseurManager prixFournisseurManager) {
        this.prixFournisseurManager = prixFournisseurManager;
    }
    
    @Autowired
    public void setPrixClientManager(@Qualifier("prixClientManager")PrixClientManager prixClientManager) {
        this.prixClientManager = prixClientManager;
    }
    
    @Autowired
    public void setInvoiceManager(@Qualifier("invoiceManager")InvoiceManager invoiceManager) {
        this.invoiceManager = invoiceManager;
    }
    
    @Autowired
    public void setFournisseurManager(@Qualifier("fournisseurManager")FournisseurManager fournisseurManager) {
        this.fournisseurManager = fournisseurManager;
    }
    
    @Autowired
    public void setPaiementClientManager(@Qualifier("paiementClientManager")PaiementClientManager paiementClientManager) {
        this.paiementClientManager = paiementClientManager;
    }
    
    @Autowired
    public void setPrelevementCaisseManager(@Qualifier("prelevementCaisseManager")PrelevementCaisseManager prelevementCaisseManager) {
        this.prelevementCaisseManager = prelevementCaisseManager;
    }
    
    @Autowired
    public void setPaiementFournisseurManager(@Qualifier("paiementFournisseurManager")PaiementFournisseurManager paiementFournisseurManager) {
        this.paiementFournisseurManager = paiementFournisseurManager;
    }
    
    @Autowired
    public void setProduitManager(@Qualifier("produitManager")ProduitManager produitManager) {
        this.produitManager = produitManager;
    }
	
    @Autowired
    public void setVenteManager(@Qualifier("venteManager")VenteManager venteManager) {
        this.venteManager = venteManager;
    }
	
    @Autowired
    public void setOpVenteManager(@Qualifier("opVenteManager")OpVenteManager opVenteManager) {
        this.opVenteManager = opVenteManager;
    }
	
    @Autowired
    public void setClientManager(@Qualifier("clientManager")ClientManager clientManager) {
        this.clientManager = clientManager;
    }
	
    @Autowired
    public void setNumTelManager(@Qualifier("numTelManager")NumTelManager numTelManager) {
        this.numTelManager = numTelManager;
    }
    
    @Autowired
    public void setCaisseManager(@Qualifier("caisseManager")CaisseManager caisseManager) {
        this.caisseManager = caisseManager;
    }
	
    @Autowired
    public void setOperateurManager(@Qualifier("operateurManager")OperateurManager operateurManager) {
        this.operateurManager = operateurManager;
    }
	
    @Autowired
    public void setOpAchatManager(@Qualifier("opAchatManager")OpAchatManager opAchatManager) {
        this.opAchatManager = opAchatManager;
    }
	
    @Autowired
    public void setAchatManager(@Qualifier("achatManager")AchatManager achatManager) {
        this.achatManager = achatManager;
    }
    
    @GET
    @Path("/clients")
    @Produces("application/json") 
    @Override
    public List<Client> getClients() {
        List<Client> results = clientManager.getAll();
        if(!results.isEmpty())
        return results;
        return null;     
    }
    
    @GET
    @Path("/fournisseur")
    @Produces("application/json") 
    @Override
    public List<Fournisseur> getFournisseur() {
        List<Fournisseur> results = fournisseurManager.getAll();
        if(!results.isEmpty())
        return results;
        return null;     
    }
    
    @GET
    @Path("/caisseDetails")
    @Produces("application/json") 
    @Override
    public Response getCaisseDetails() {
        CaisseDetails c=new CaisseDetails();
        float paiementsClients=0;
        float paiementsFournisseurs=0;
        float prelevementsCaisse=0;
        Caisse lastRecord=caisseManager.getLastOne();
        float montantInitial=lastRecord.getMontant();
        for(PaiementClient pc:paiementClientManager.getAfterDate(lastRecord.getDateCloture()))
            paiementsClients=paiementsClients+pc.getMontant();
        for(PaiementFournisseur pf:paiementFournisseurManager.getAfterDate(lastRecord.getDateCloture()))
            paiementsFournisseurs=paiementsFournisseurs+pf.getMontant();
        for(PrelevementCaisse pc:prelevementCaisseManager.getAfterDate(lastRecord.getDateCloture()))
            prelevementsCaisse=prelevementsCaisse+pc.getMontant();
        float montantCaisse=montantInitial+paiementsClients-paiementsFournisseurs-prelevementsCaisse;
        c.setMontantInitial(montantInitial);
        c.setPaiementsClient(paiementsClients);
        c.setPaiementsFournisseur(paiementsFournisseurs);
        c.setPrelevementsCaisse(prelevementsCaisse);
        c.setMontantCaisse(montantCaisse);
        return Response.status(Response.Status.OK).entity(c).build();
    }
    
    @GET
    @Path("/invoices")
    @Produces("application/json") 
    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> results = invoiceManager.getAll();
        if(!results.isEmpty())
        return results;
        return null;     
    }
	
    @GET
    @Path("/produits")
    @Produces("application/json") 
    @Override
    public List<Produit> getProduits() {
        List<Produit> results = produitManager.getAll();
        if(!results.isEmpty())
        return results;
        return null;     
    }
	
    @GET
    @Path("/opVentesByClient/{id}")
    @Produces("application/json") 
    @Override
    public List<OpVente> getOpVentesByClient(@PathParam("id") Long id) {
	Client c=clientManager.get(id);
        List<OpVente> results = opVenteManager.getNonPayedByClient(c);
        if(!results.isEmpty())
        return results;
        return null;     
    }
    
    @GET
    @Path("/prixFournisseur/{id}")
    @Produces("application/json") 
    @Override
    public List<PrixFournisseur> getPrixFournisseurs(@PathParam("id") Long id) {
	Fournisseur f=fournisseurManager.get(id);
        List<PrixFournisseur> results = new ArrayList<>(f.getPrixFournisseur());
        if(!results.isEmpty())
        return results;
        return null;     
    }
    
    @GET
    @Path("/disponible/{id}")
    @Produces("application/json") 
    @Override
    public Response getDisponibilite(@PathParam("id") Long id) {
	Produit p=produitManager.get(id);
        int dispo=0;
        List<Achat> achats=achatManager.getNotSelled(p);
        if(achats!=null)
            for(Achat achat:achats)
                dispo=dispo+achat.getQuantite()-achat.getVendu();
        return Response.status(Response.Status.OK).entity(dispo).build();
    }
    
    @GET
    @Path("/prixFournisseur/{idF}/{idP}")
    @Produces("application/json") 
    @Override
    public PrixFournisseur getPrixFournisseur(@PathParam("idF") Long idf,@PathParam("idP") Long idp) {
	Fournisseur f=fournisseurManager.get(idf);
        Produit p=produitManager.get(idp);
        PrixFournisseur result = prixFournisseurManager.getByFournisseurByProduit(f, p);
        return result;
    }
    
    @GET
    @Path("/prixClient/{idC}")
    @Produces("application/json") 
    @Override
    public List<PrixClient> getPrixClients(@PathParam("idC") Long idc) {
	Client c=clientManager.get(idc);
        List<PrixClient> result = prixClientManager.getByClient(c);
        return result;
    }
    
    @GET
    @Path("/prixClient/{idC}/{idP}")
    @Produces("application/json") 
    @Override
    public PrixClient getPrixClient(@PathParam("idC") Long idc,@PathParam("idP") Long idp) {
	Client c=clientManager.get(idc);
        Produit p=produitManager.get(idp);
        PrixClient result = prixClientManager.getByClientByProduit(c, p);
        return result;
    }
	
    @POST
    @Path("/opAchats")
    @Produces("application/json") 
    @Consumes("application/json") 
    @Override
    public Response getOpAchats(Date date) {
        List<OpAchat> results = opAchatManager.getByDate(date);
        if(!results.isEmpty())
        return Response.status(Response.Status.OK).entity(results).build();
        return Response.status(Response.Status.NO_CONTENT).build();     
    }
    
    @GET
    @Path("/paiementsClient")
    @Produces("application/json") 
    @Override
    public List<PaiementClient> getPaiementsClient() {
        List<PaiementClient> results = paiementClientManager.getAll();
        if(!results.isEmpty())
        return results;
        return null;     
    }
    
    @GET
    @Path("/paiementsFournisseur")
    @Produces("application/json") 
    @Override
    public List<PaiementFournisseur> getPaiementsFournisseur() {
        List<PaiementFournisseur> results = paiementFournisseurManager.getAll();
        if(!results.isEmpty())
        return results;
        return null;     
    }
    
    
	
    @POST
    @Path("/opVentes")
    @Produces("application/json") 
    @Consumes("application/json") 
    @Override
    public Response getOpVentes(Date date) {
        List<OpVente> results = opVenteManager.getByDate(date);
        if(!results.isEmpty())
        return Response.status(Response.Status.OK).entity(results).build();
        return Response.status(Response.Status.NO_CONTENT).build();     
    }
	
    @POST
    @Path("/saveOpVente")
    @Consumes("application/json") 
    @Produces("application/json") 
    @Override
    public Response saveOpVente(OpVente opVente) {
        OpVente tmp=new OpVente();
        if(opVente.getId()==null){
            tmp.setDateVente(new Date(System.currentTimeMillis()));
            tmp.setClient(opVente.getClient());
        }
        else {
            tmp=opVenteManager.get(opVente.getId());
            tmp.setClient(opVente.getClient());
        }
        OpVente opV=opVenteManager.save(tmp);
	Response s= Response.status(Response.Status.OK).entity(opV.getId().toString()).build();
        return s;
    }
    
    @POST
    @Path("/cloturerCaisse")
    @Consumes("application/json") 
    @Produces("application/json") 
    @Override
    public Response cloturerCaisse(ClotureObject c) {
        if(c.getPrelevement()>0){
            PrelevementCaisse pc=new PrelevementCaisse();
            pc.setDatePrelevement(new Date(System.currentTimeMillis()));
            pc.setMontant(c.getReste());
            pc.setMotif("Clotûre caisse");
            prelevementCaisseManager.save(pc);
        }
        Date dateCaisse=new Date(System.currentTimeMillis());
        Caisse caisse=new Caisse();
        caisse.setDateCloture(dateCaisse);
        caisse.setMontant(c.getReste());
        caisseManager.save(caisse);
        Response s= Response.status(Response.Status.OK).build();
	return s;
    }
    
    @POST
    @Path("/savePaiementClient/{id}")
    @Consumes("application/json") 
    @Override
    public Response savePaiementClient(@PathParam("id") Long id,PaiementClient paiement) {
        Client c=clientManager.get(id);
        float avance=paiementClientManager.getAvance(c);
        float montant=paiement.getMontant()+avance;
        paiement.setMontant(montant);
        List<OpVente> lst=opVenteManager.getNonPayedByClient(c);
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
        if(montant>0)paiement.setAvance(montant);
        PaiementClient p=paiementClientManager.getPaiementAvecAvance(c);
        if(p!=null){
            p.setAvance(0);
            paiementClientManager.save(p);
        }
        paiement.setDatePaiement(new Date(System.currentTimeMillis()));
        paiement.setClient(c);
        paiementClientManager.save(paiement);
        Response s= Response.status(Response.Status.OK).build();
	return s;
    }
    
    @POST
    @Path("/savePaiementFournisseur/{id}")
    @Consumes("application/json") 
    @Override
    public Response savePaiementFournisseur(@PathParam("id") Long id,PaiementFournisseur paiement) {
        Fournisseur f=fournisseurManager.get(id);
        float avance=paiementFournisseurManager.getAvance(f);
        float montant=paiement.getMontant()+avance;
        paiement.setMontant(montant);
        List<OpAchat> lst=opAchatManager.getNonPayedByFournisseur(f);
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
        if(montant>0)paiement.setAvance(montant);
        PaiementFournisseur p=paiementFournisseurManager.getPaiementAvecAvance(f);
        if(p!=null){
            p.setAvance(0);
            paiementFournisseurManager.save(p);
        }
        paiement.setDatePaiement(new Date(System.currentTimeMillis()));
        paiement.setFournisseur(f);
        paiementFournisseurManager.save(paiement);
        Response s= Response.status(Response.Status.OK).build();
	return s;
    }
	
    @POST
    @Path("/saveOpAchat")
    @Consumes("application/json") 
    @Produces("application/json") 
    @Override
    public Response saveOpAchat(OpAchat opAchat) {
        OpAchat tmp=new OpAchat();
        if(opAchat.getId()==null){
            tmp.setDateAchat(new Date(System.currentTimeMillis()));
            tmp.setFournisseur(opAchat.getFournisseur());
        }
        else {
            tmp=opAchatManager.get(opAchat.getId());
            tmp.setFournisseur(opAchat.getFournisseur());
        }
        OpAchat opA=opAchatManager.save(tmp);
	Response s= Response.status(Response.Status.OK).entity(opA.getId().toString()).build();;
        return s;
    }
	
    @POST
    @Path("/saveClient")
    @Consumes("application/json") 
    @Produces("application/json") 
    @Override
    public Response saveClient(Client client) {
        Client cl=clientManager.save(client);
        Response s= Response.status(Response.Status.OK).entity(cl.getId().toString()).build();
        return s;
    }
    
    
    @POST
    @Path("/validateVente/{id}")
    @Consumes("application/json") 
    @Override
    public Response validateVente(@PathParam("id") Long id) {
        Vente v=venteManager.get(id);
        v.setValidated(true);
        venteManager.save(v);
        return Response.status(Response.Status.OK).build();
    }
    
    @POST
    @Path("/saveInvoice")
    @Consumes("application/json") 
    @Override
    public Response saveInvoice(Invoice invoice,List<OpVente> opVentes) {
        Customer c=new Customer();
        c.setAddress(invoice.getClient().getAddress());
        c.setCity(invoice.getClient().getVille());
        c.setEmail(invoice.getClient().getEmail());
        c.setName(invoice.getClient().getFullName());
        List<Item> items=new ArrayList<>();
        for(OpVente o:opVentes)
            for(Vente v:o.getVentes()){
                Item item=new Item();
                item.setDescription(v.getProduit().getDescription());
                item.setQuantity(v.getQuantite());
                item.setUnitprice(new BigDecimal(v.getPrixUnit()));
                items.add(item);
            }
        InvoiceData inD=new InvoiceData(c,items,0.2);
        InvoiceDesign design = new InvoiceDesign(inD,invoice.getDateFact());
        ServletContext s=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String pathname =s.getRealPath("/invoices"); 
        String filename=invoice.getClient().getFullName().replace(" ","_")+"_"+invoice.getDateFact().toString().replace(" ","_").replace(":","_")+".pdf";
        System.out.print(filename);
        try {
            JasperReportBuilder report = design.build();
            File f=new File(pathname+"/"+filename);
            OutputStream out=new FileOutputStream(f);
            report.toPdf(out);                        
        } catch (DRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MobileControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        invoice.setUrl("/invoices/"+filename);
        invoiceManager.save(invoice);
        return Response.status(Response.Status.OK).build();
    }
	
    @POST
    @Path("/saveProduit")
    @Consumes("application/json") 
    @Override
    public Response saveProduit(Produit produit) {
        produitManager.save(produit);
        return Response.status(Response.Status.OK).build();
    }
	
    @POST
    @Path("/saveNumTels/{id}")
    @Consumes("application/json") 
    @Override
    public Response saveNumtels(@PathParam("id") Long id,List<NumTel> numTels) {
		Client client=clientManager.get(id);
		for(NumTel n:numTels){
			n.setClient(client);
			numTelManager.save(n);
		}
        return Response.status(Response.Status.OK).build();
    }
	
    @POST
    @Path("/saveAchats/{id}")
    @Consumes("application/json") 
    @Override
    public Response saveAchats(@PathParam("id") Long id,List<Achat> achats) {
	OpAchat opAchat=opAchatManager.get(id);
        float montant=0;
	for(Achat a:achats){
            Achat tmp=null;
            if(a.getId()!=null){
                tmp=achatManager.get(a.getId());
                if(tmp.getQuantite()>a.getQuantite()){ //Diminution de quantité vendu
                    if(tmp.getVendu()>a.getQuantite()){
                        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
                    }
                }
            }
            if(a.getPrixUnit()!=0&&a.getQuantite()!=0){
                a.setOpAchat(opAchat);
                achatManager.save(a);
            }
            else{
                achatManager.remove(a.getId());
            }
            
            montant=montant+a.getPrixUnit()*a.getQuantite();
	}
        if(opAchat.getMontantPaye()>montant){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        opAchat.setMontant(montant);
        if(opAchat.getDateAchat()==null)opAchat.setDateAchat(new Date(System.currentTimeMillis()));
        opAchatManager.save(opAchat);
        return Response.status(Response.Status.OK).build();
    }
    
    @GET
    @Path("/ficheClient/{id}")
    @Produces("application/json") 
    @Override
    public Response getFicheClient(@PathParam("id") Long id){
        float impaye=0;
        float avance=0;
        float apayer=0;
        Client c=clientManager.get(id);
        if (c!=null){
            for(OpVente op:opVenteManager.getNonPayedByClient(c))
            impaye=impaye+op.getMontant()-op.getMontantPaye();
            avance=paiementClientManager.getAvance(c);
            if(avance>0){
                apayer=impaye-avance;
            }
            else
                apayer=impaye;
        }
        FicheClient fc=new FicheClient(impaye, avance, apayer);
        return Response.status(Response.Status.OK).entity(fc).build();
    }
    
    @GET
    @Path("/ficheFournisseur/{id}")
    @Produces("application/json") 
    @Override
    public Response getFicheFournisseur(@PathParam("id") Long id){
        float impaye=0;
        float avance=0;
        float apayer=0;
        Fournisseur f=fournisseurManager.get(id);
        if (f!=null){
            for(OpAchat op:opAchatManager.getNonPayedByFournisseur(f))
                impaye=impaye+op.getMontant()-op.getMontantPaye();
            avance=paiementFournisseurManager.getAvance(f);
            if(avance>0){
                apayer=impaye-avance;
            }
            else
                apayer=impaye;
        }
        FicheFournisseur ff=new FicheFournisseur(impaye, avance, apayer);
        return Response.status(Response.Status.OK).entity(ff).build();
    }
	
    @POST
    @Path("/saveVentes/{id}")
    @Consumes("application/json") 
    @Override
    public Response saveVentes(@PathParam("id") Long id,List<Vente> ventes) {
	OpVente opVente=opVenteManager.get(id);
	float montant=0;
        for(Vente a:ventes)
            montant=montant+a.getQuantite()*a.getPrixUnit();
        opVente.setMontant(montant);
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
                        a.setOpVente(opVente);
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
                        a.setOpVente(opVente);
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
                    a.setOpVente(opVente);
                    venteManager.save(a);
                }
            }
        }
        opVenteManager.save(opVente);
        return Response.status(Response.Status.OK).build();
    }
	
    @GET
    @Path("/operateurs")
    @Produces("application/json") 
    @Override
    public List<Operateur> getOperateurs() {
        List<Operateur> results = operateurManager.getAll();
        if(!results.isEmpty())
        return results;
        return null;     
    }
    
    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN) 
    @Override
    public String login() {
        return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    @GET
    @Path("/numTelsByClient/{id}")
    @Produces("application/json") 
    @Override
    public List<NumTel> getNumTelsByClient(@PathParam("id") Long id) {
        Client c=clientManager.get(id);
        return numTelManager.getByClient(c);
    }
}
