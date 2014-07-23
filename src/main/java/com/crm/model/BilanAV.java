/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model;



/**
 *
 * @author zied
 */
public class BilanAV {
    private Produit produit;
    private float montantAchat;
    private float montantVente;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public float getMontantAchat() {
        return montantAchat;
    }

    public void setMontantAchat(float montantAchat) {
        this.montantAchat = montantAchat;
    }

    public float getMontantVente() {
        return montantVente;
    }

    public void setMontantVente(float montantVente) {
        this.montantVente = montantVente;
    }
}
