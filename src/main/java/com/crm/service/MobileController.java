/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Achat;
import com.crm.model.Client;
import com.crm.model.ClotureObject;
import com.crm.model.Fournisseur;
import com.crm.model.Invoice;
import com.crm.model.NumTel;
import com.crm.model.OpAchat;
import com.crm.model.OpVente;
import com.crm.model.Operateur;
import com.crm.model.PaiementClient;
import com.crm.model.PaiementFournisseur;
import com.crm.model.PrixClient;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import com.crm.model.Vente;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author zied
 */
@WebService
public interface MobileController {
    @GET
    @Path("/clients")
    @Produces("application/json") 
    List<Client> getClients();
	
    @GET
    @Path("/clients")
    @Produces("application/json") 
    List<Produit> getProduits();
    
    @GET
    @Path("/fournisseur")
    @Produces("application/json") 
    List<Fournisseur> getFournisseur();
    
    @GET
    @Path("/prixFournisseur/{id}")
    @Produces("application/json") 
    List<PrixFournisseur> getPrixFournisseurs(@PathParam("id") Long id);
    
    @GET
    @Path("/ficheFournisseur/{id}")
    @Produces("application/json") 
    Response getFicheFournisseur(@PathParam("id") Long id);
    
    @GET
    @Path("/caisseDetails")
    @Produces("application/json") 
    Response getCaisseDetails();
    
    @POST
    @Path("/cloturerCaisse")
    @Consumes("application/json") 
    @Produces("application/json") 
    Response cloturerCaisse(ClotureObject c);
    
    @POST
    @Path("/savePaiementFournisseur/{id}")
    @Consumes("application/json") 
    Response savePaiementFournisseur(@PathParam("id") Long id,PaiementFournisseur paiement);
    
    @GET
    @Path("/prixFournisseur/{idF}/{idP}")
    @Produces("application/json") 
    PrixFournisseur getPrixFournisseur(@PathParam("idF") Long idf,@PathParam("idP") Long idp);
	
    @POST
    @Path("/saveClient")
    @Consumes("application/json") 
    @Produces("application/json") 
    Response saveClient(Client client);
	
    @POST
    @Path("/saveProduit")
    @Consumes("application/json") 
    Response saveProduit(Produit produit);
	
    @GET
    @Path("/operateurs")
    @Produces("application/json") 
    List<Operateur> getOperateurs();
	
	
    @POST
    @Path("/saveNumTels/{id}")
    @Consumes("application/json") 
    Response saveNumtels(@PathParam("id") Long id,List<NumTel> numTels);
	
    @POST
    @Path("/saveOpAchat")
    @Consumes("application/json")  
    @Produces("application/json")
    Response saveOpAchat(OpAchat opAchat);
	
    @POST
    @Path("/opAchats")
    @Produces("application/json") 
    @Consumes("application/json") 
    Response getOpAchats(Date date);
    
    @GET
    @Path("/paiementsClient")
    @Produces("application/json") 
    List<PaiementClient> getPaiementsClient();
    
    @GET
    @Path("/paiementsFournisseur")
    @Produces("application/json") 
    List<PaiementFournisseur> getPaiementsFournisseur() ;
    
    @POST
    @Path("/savePaiementClient/{id}")
    @Consumes("application/json") 
    Response savePaiementClient(@PathParam("id") Long id,PaiementClient paiement);
	
    @GET
    @Path("/opVentesByClient/{id}")
    @Produces("application/json") 
    List<OpVente> getOpVentesByClient(@PathParam("id") Long id);
    
    @GET
    @Path("/numTelsByClient/{id}")
    @Produces("application/json") 
    List<NumTel> getNumTelsByClient(@PathParam("id") Long id);
    
    @POST
    @Path("/validateVente/{id}")
    @Consumes("application/json") 
    Response validateVente(@PathParam("id") Long id);
    
    @GET
    @Path("/prixClient/{idC}/{idP}")
    @Produces("application/json") 
    PrixClient getPrixClient(@PathParam("idC") Long idc,@PathParam("idP") Long idp);
    
    @GET
    @Path("/disponible/{id}")
    @Produces("application/json") 
    Response getDisponibilite(@PathParam("id") Long id);
	
    @POST
    @Path("/saveAchats/{id}")
    @Consumes("application/json") 
    Response saveAchats(@PathParam("id") Long id,List<Achat> achats);
	
    @POST
    @Path("/saveVentes/{id}")
    @Consumes("application/json") 
    Response saveVentes(@PathParam("id") Long id,List<Vente> ventes);
    
    @GET
    @Path("/ficheClient/{id}")
    @Produces("application/json") 
    Response getFicheClient(@PathParam("id") Long id);
	
    @POST
    @Path("/saveOpVente")
    @Consumes("application/json") 
    @Produces("application/json") 
    Response saveOpVente(OpVente opVente);
    
    @GET
    @Path("/prixClient/{idC}")
    @Produces("application/json") 
    List<PrixClient> getPrixClients(@PathParam("idC") Long idc);
	
    @POST
    @Path("/opVentes")
    @Produces("application/json") 
    @Consumes("application/json") 
    Response getOpVentes(Date date);
        
    @POST
    @Path("/saveInvoice")
    @Consumes("application/json") 
    Response saveInvoice(Invoice invoice,List<OpVente> opVentes);
    
    @GET
    @Path("/invoices")
    @Produces("application/json") 
    List<Invoice> getInvoices();
    
    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN) 
    String login();
}
