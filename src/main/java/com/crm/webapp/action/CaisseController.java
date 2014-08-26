/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Caisse;
import com.crm.model.PaiementClient;
import com.crm.model.PaiementByType;
import com.crm.model.PaiementFournisseur;
import com.crm.model.PrelevementCaisse;
import com.crm.model.TypePaiement;
import com.crm.service.CaisseManager;
import com.crm.service.PaiementClientManager;
import com.crm.service.PaiementFournisseurManager;
import com.crm.service.PrelevementCaisseManager;
import com.crm.service.TypePaiementManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Component("caisseController")
@Scope("view")
public class CaisseController extends BasePage implements Serializable{
    private CaisseManager caisseManager;
    private PrelevementCaisseManager prelevementCaisseManager;
    private PaiementClientManager paiementClientManager;
    private PaiementFournisseurManager paiementFournisseurManager;
    private TypePaiementManager typePaiementManager;
    private float montantInitial;
    private float paiementsClients;
    private float paiementsFournisseurs;
    private List<PaiementByType> pcByType=new ArrayList<>();
    private List<PaiementByType> pfByType=new ArrayList<>();
    private float prelevementsCaisse;
    private float montantCaisse;
    private float prelevement;
    private float reste;
    private List<TypePaiement> types=new ArrayList<>();
    
    @Autowired
    public void setCaisseManager(@Qualifier("caisseManager")CaisseManager caisseManager) {
        this.caisseManager = caisseManager;
    }
    
    @Autowired
    public void setTypePaiementManager(@Qualifier("typePaiementManager")TypePaiementManager typePaiementManager) {
        this.typePaiementManager = typePaiementManager;
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

    public List<TypePaiement> getTypes() {
        return types;
    }

    public void setTypes(List<TypePaiement> types) {
        this.types = types;
    }

    public List<PaiementByType> getPcByType() {
        return pcByType;
    }

    public void setPcByType(List<PaiementByType> pcByType) {
        this.pcByType = pcByType;
    }

    public List<PaiementByType> getPfByType() {
        return pfByType;
    }

    public void setPfByType(List<PaiementByType> pfByType) {
        this.pfByType = pfByType;
    }
    
    @PostConstruct
    public void init(){
        paiementsClients=0;
        paiementsFournisseurs=0;
        prelevementsCaisse=0;
        Caisse lastRecord=caisseManager.getLastOne();
        montantInitial=lastRecord.getMontant();
        types=typePaiementManager.getAll();
        for(TypePaiement tp:types){
            float montant_pc=0;
            List<PaiementClient> pcs=paiementClientManager.getAfterDateByType(lastRecord.getDateCloture(), tp);
            for(PaiementClient pc:pcs){
                System.out.println("client adding "+pc.getMontant());
                montant_pc=montant_pc+pc.getMontant();
            }  
            PaiementByType tmp=new PaiementByType();
            tmp.setMontant(montant_pc);
            tmp.setType(tp);
            System.out.println("adding to lst_pc_type "+tmp.getType().getType()+" "+tmp.getMontant());
            pcByType.add(tmp);
            paiementsClients=paiementsClients+montant_pc;
            float montant_pf=0;
            List<PaiementFournisseur> pfs=paiementFournisseurManager.getAfterDateByType(lastRecord.getDateCloture(), tp);
            for(PaiementFournisseur pf:pfs){
                System.out.println("fournisseur adding "+pf.getMontant());
                montant_pf=montant_pf+pf.getMontant();
            }
            tmp.setMontant(montant_pf);
            System.out.println("adding to lst_pf_type "+tmp.getType().getType()+" "+tmp.getMontant());
            paiementsFournisseurs=paiementsFournisseurs+montant_pf;
            pfByType.add(tmp);
        }
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
