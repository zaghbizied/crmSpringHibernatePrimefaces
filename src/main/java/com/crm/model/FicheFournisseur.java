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
public class FicheFournisseur {
    private float impayes;
    private float avance;
    private float apayer;   
    
    public FicheFournisseur(float impayes,float avance,float apayer){
        this.apayer=apayer;
        this.avance=avance;
        this.impayes=impayes;
    }

    public float getImpayes() {
        return impayes;
    }

    public void setImpayes(float impayes) {
        this.impayes = impayes;
    }

    public float getAvance() {
        return avance;
    }

    public void setAvance(float avance) {
        this.avance = avance;
    }

    public float getApayer() {
        return apayer;
    }

    public void setApayer(float apayer) {
        this.apayer = apayer;
    }
}
