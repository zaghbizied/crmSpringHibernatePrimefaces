/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Caisse;
import com.crm.model.PaiementClient;
import com.crm.model.PaiementFournisseur;
import com.crm.model.PrelevementCaisse;
import com.crm.service.CaisseManager;
import com.crm.service.PaiementClientManager;
import com.crm.service.PaiementFournisseurManager;
import com.crm.service.PrelevementCaisseManager;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zied
 */
@Component("caisseController")
@Scope("view")
public class CaisseController extends BasePage implements Serializable{
    private CaisseManager caisseManager;
    private PrelevementCaisseManager prelevementCaisseManager;
    private PaiementClientManager paiementClientManager;
    private PaiementFournisseurManager paiementFournisseurManager;
    private float montantInitial;
    private float paiementsClients;
    private float paiementsFournisseurs;
    private float prelevementsCaisse;
    private float montantCaisse;
    private float prelevement;
    private float reste;
    
    @Autowired
    public void setCaisseManager(@Qualifier("caisseManager")CaisseManager caisseManager) {
        this.caisseManager = caisseManager;
    }

    @Autowired
    public void setPrelevementCaisseManager(@Qualifier("prelevementCaisseManager")PrelevementCaisseManager prelevementCaisseManager) {
        this.prelevementCaisseManager = prelevementCaisseManager;
    }

    @Autowired
    public void setPaiementClientManager(@Qualifier("paiementClientManager")PaiementClientManager paiementClientManager) {
        this.paiementClientManager = paiementClientManager;
    }

    @Autowired
    public void setPaiementFournisseurManager(@Qualifier("paiementFournisseurManager")PaiementFournisseurManager paiementFournisseurManager) {
        this.paiementFournisseurManager = paiementFournisseurManager;
    }

    public float getMontantInitial() {
        return montantInitial;
    }

    public void setMontantInitial(float montantInitial) {
        this.montantInitial = montantInitial;
    }

    public float getPaiementsClients() {
        return paiementsClients;
    }

    public void setPaiementsClients(float paiementsClients) {
        this.paiementsClients = paiementsClients;
    }

    public float getPaiementsFournisseurs() {
        return paiementsFournisseurs;
    }

    public void setPaiementsFournisseurs(float paiementsFournisseurs) {
        this.paiementsFournisseurs = paiementsFournisseurs;
    }

    public float getPrelevementsCaisse() {
        return prelevementsCaisse;
    }

    public void setPrelevementsCaisse(float prelevementsCaisse) {
        this.prelevementsCaisse = prelevementsCaisse;
    }

    public float getMontantCaisse() {
        return montantCaisse;
    }

    public void setMontantCaisse(float montantCaisse) {
        this.montantCaisse = montantCaisse;
    }

    public float getPrelevement() {
        return prelevement;
    }

    public void setPrelevement(float prelevement) {
        this.prelevement = prelevement;
    }

    public float getReste() {
        return reste;
    }

    public void setReste(float reste) {
        this.reste = reste;
    }
    
    @PostConstruct
    public void init(){
        paiementsClients=0;
        paiementsFournisseurs=0;
        prelevementsCaisse=0;
        Caisse lastRecord=caisseManager.getLastOne();
        montantInitial=lastRecord.getMontant();
        for(PaiementClient pc:paiementClientManager.getAfterDate(lastRecord.getDateCloture()))
            paiementsClients=paiementsClients+pc.getMontant();
        for(PaiementFournisseur pf:paiementFournisseurManager.getAfterDate(lastRecord.getDateCloture()))
            paiementsFournisseurs=paiementsFournisseurs+pf.getMontant();
        for(PrelevementCaisse pc:prelevementCaisseManager.getAfterDate(lastRecord.getDateCloture()))
            prelevementsCaisse=prelevementsCaisse+pc.getMontant();
        montantCaisse=montantInitial+paiementsClients-paiementsFournisseurs-prelevementsCaisse;
    }
    
    public void cloturer(){
        System.out.println("cloture de caisse");
        if(prelevement>0){
            PrelevementCaisse pc=new PrelevementCaisse();
            pc.setDatePrelevement(new Date(System.currentTimeMillis()));
            pc.setMontant(reste);
            pc.setMotif("Clotûre caisse");
            prelevementCaisseManager.save(pc);
        }
        Date dateCaisse=new Date(System.currentTimeMillis());
        Caisse caisse=new Caisse();
        caisse.setDateCloture(dateCaisse);
        caisse.setMontant(reste);
        caisseManager.save(caisse);
        init();
    }
    
    public void prelevementChanged(){
        reste=montantCaisse-prelevement;
    }
}
