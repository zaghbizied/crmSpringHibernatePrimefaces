/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.Achat;
import com.crm.model.BeneficeClient;
import com.crm.model.BilanAV;
import com.crm.model.Client;
import com.crm.model.OpAchat;
import com.crm.model.OpVente;
import com.crm.model.Produit;
import com.crm.model.Vente;
import com.crm.service.ClientManager;
import com.crm.service.OpAchatManager;
import com.crm.service.OpVenteManager;
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
@Component("bilanJournalierController")
@Scope("view")
public class BilanJournalierController extends BasePage implements Serializable{
    private List<BilanAV> bilanAV=new ArrayList<>();
    private List<BeneficeClient> bilanBen=new ArrayList<>();
    private Date dateBilan;
    private float totalAchats;
    private float totalVentes;
    private float totalBenefices;
    private OpVenteManager opVenteManager;
    private ClientManager clientManager;
    private OpAchatManager opAchatManager;
    
    @Autowired
    public void setClientManager(@Qualifier("clientManager")ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Autowired
    public void setOpVenteManager(@Qualifier("opVenteManager")OpVenteManager opVenteManager) {
        this.opVenteManager = opVenteManager;
    }
    
    @Autowired
    public void setOpAchatManager(@Qualifier("opAchatManager")OpAchatManager opAchatManager) {
        this.opAchatManager = opAchatManager;
    }
    
    public void search(){
        bilanAV=new ArrayList<>();
        totalVentes=0;
        totalAchats=0;
        List<Produit> produits=new ArrayList<>();
        for(OpVente opV:opVenteManager.getByDate(dateBilan)){
            for(Vente v:opV.getNestedVentes()){
                totalVentes=totalVentes+v.getPrixUnit()*v.getQuantite();
                if(produits.contains(v.getProduit())){
                    for(BilanAV b:bilanAV){
                        if(b.getProduit().getId()==v.getProduit().getId()){
                            b.setMontantVente(b.getMontantVente()+v.getPrixUnit()*v.getQuantite());
                            break;
                        }
                    }
                }
                else{
                    produits.add(v.getProduit());
                    BilanAV bav=new BilanAV();
                    bav.setProduit(v.getProduit());
                    bav.setMontantVente(v.getPrixUnit()*v.getQuantite());
                    bilanAV.add(bav);
                }
            }
        }
        
        for(OpAchat opA:opAchatManager.getByDate(dateBilan)){
            for(Achat a:opA.getNestedAchats()){
                totalAchats=totalAchats+a.getPrixUnit()*a.getQuantite();
                if(produits.contains(a.getProduit())){
                    for(BilanAV b:bilanAV){
                        if(b.getProduit().getId()==a.getProduit().getId()){
                            b.setMontantAchat(b.getMontantAchat()+a.getPrixUnit()*a.getQuantite());
                            break;
                        }
                    }
                }
                else{
                    produits.add(a.getProduit());
                    BilanAV bav=new BilanAV();
                    bav.setProduit(a.getProduit());
                    bav.setMontantAchat(a.getPrixUnit()*a.getQuantite());
                    bilanAV.add(bav);
                }
            }
        }
        bilanBen=new ArrayList<>();
        totalBenefices=0;
        for(Client c:clientManager.getAll()){
            BeneficeClient bc=new BeneficeClient();
            bc.setClient(c);
            float benefice=0;
            List<OpVente> opVs=opVenteManager.getByDateByClient(dateBilan, c);
            if(opVs!=null&&opVs.size()>0){
                for(OpVente opV:opVs)
                    for(Vente v:opV.getVentes())
                        benefice=benefice+v.getPrixUnit()*v.getQuantite()-v.getPrixAchat();
            }
            totalBenefices=totalBenefices+benefice;
            bc.setBenefice(benefice);
            bilanBen.add(bc);
        }
    }
    
    @PostConstruct
    public void init(){
        bilanAV=new ArrayList<>();
        dateBilan=new Date(System.currentTimeMillis());
        totalVentes=0;
        totalAchats=0;
        List<Produit> produits=new ArrayList<>();
        for(OpVente opV:opVenteManager.getByDate(dateBilan)){
            for(Vente v:opV.getNestedVentes()){
                totalVentes=totalVentes+v.getPrixUnit()*v.getQuantite();
                if(produits.contains(v.getProduit())){
                    for(BilanAV b:bilanAV){
                        if(b.getProduit().getId()==v.getProduit().getId()){
                            b.setMontantVente(b.getMontantVente()+v.getPrixUnit()*v.getQuantite());
                            break;
                        }
                    }
                }
                else{
                    produits.add(v.getProduit());
                    BilanAV bav=new BilanAV();
                    bav.setProduit(v.getProduit());
                    bav.setMontantVente(v.getPrixUnit()*v.getQuantite());
                    bilanAV.add(bav);
                }
            }
        }
        
        for(OpAchat opA:opAchatManager.getByDate(dateBilan)){
            for(Achat a:opA.getNestedAchats()){
                totalAchats=totalAchats+a.getPrixUnit()*a.getQuantite();
                if(produits.contains(a.getProduit())){
                    for(BilanAV b:bilanAV){
                        if(b.getProduit().getId()==a.getProduit().getId()){
                            b.setMontantAchat(b.getMontantAchat()+a.getPrixUnit()*a.getQuantite());
                            break;
                        }
                    }
                }
                else{
                    produits.add(a.getProduit());
                    BilanAV bav=new BilanAV();
                    bav.setProduit(a.getProduit());
                    bav.setMontantAchat(a.getPrixUnit()*a.getQuantite());
                    bilanAV.add(bav);
                }
            }
        }
        bilanBen=new ArrayList<>();
        totalBenefices=0;
        for(Client c:clientManager.getAll()){
            BeneficeClient bc=new BeneficeClient();
            bc.setClient(c);
            float benefice=0;
            List<OpVente> opVs=opVenteManager.getByDateByClient(dateBilan, c);
            if(opVs!=null&&opVs.size()>0){
                for(OpVente opV:opVs)
                    for(Vente v:opV.getVentes())
                        benefice=benefice+v.getPrixUnit()*v.getQuantite()-v.getPrixAchat();
            }
            totalBenefices=totalBenefices+benefice;
            bc.setBenefice(benefice);
            bilanBen.add(bc);
        }
    }

    public Date getDateBilan() {
        return dateBilan;
    }

    public void setDateBilan(Date dateBilan) {
        this.dateBilan = dateBilan;
    }

    public float getTotalAchats() {
        return totalAchats;
    }

    public void setTotalAchats(float totalAchats) {
        this.totalAchats = totalAchats;
    }

    public float getTotalVentes() {
        return totalVentes;
    }

    public void setTotalVentes(float totalVentes) {
        this.totalVentes = totalVentes;
    }

    public List<BilanAV> getBilanAV() {
        return bilanAV;
    }

    public void setBilanAV(List<BilanAV> bilanAV) {
        this.bilanAV = bilanAV;
    }

    public List<BeneficeClient> getBilanBen() {
        return bilanBen;
    }

    public void setBilanBen(List<BeneficeClient> bilanBen) {
        this.bilanBen = bilanBen;
    }

    public float getTotalBenefices() {
        return totalBenefices;
    }

    public void setTotalBenefices(float totalBenefices) {
        this.totalBenefices = totalBenefices;
    }
}
