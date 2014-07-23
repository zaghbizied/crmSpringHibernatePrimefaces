/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.model;

/**
 *
 * @author zied
 */
public class CaisseDetails {
    float montantInitial;
    float paiementsClient;
    float paiementsFournisseur;
    float prelevementsCaisse;
    float montantCaisse;

    public float getMontantCaisse() {
        return montantCaisse;
    }

    public void setMontantCaisse(float montantCaisse) {
        this.montantCaisse = montantCaisse;
    }

    public float getMontantInitial() {
        return montantInitial;
    }

    public void setMontantInitial(float montantInitial) {
        this.montantInitial = montantInitial;
    }

    public float getPaiementsClient() {
        return paiementsClient;
    }

    public void setPaiementsClient(float paiementsClient) {
        this.paiementsClient = paiementsClient;
    }

    public float getPaiementsFournisseur() {
        return paiementsFournisseur;
    }

    public void setPaiementsFournisseur(float paiementsFournisseur) {
        this.paiementsFournisseur = paiementsFournisseur;
    }

    public float getPrelevementsCaisse() {
        return prelevementsCaisse;
    }

    public void setPrelevementsCaisse(float prelevementsCaisse) {
        this.prelevementsCaisse = prelevementsCaisse;
    }
}
