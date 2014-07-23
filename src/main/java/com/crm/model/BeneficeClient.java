/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model;

/**
 *
 * @author zied
 */
public class BeneficeClient {
    private Client client;
    private float benefice;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getBenefice() {
        return benefice;
    }

    public void setBenefice(float benefice) {
        this.benefice = benefice;
    }
}
