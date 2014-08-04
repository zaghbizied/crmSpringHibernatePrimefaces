/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
/**
 *
 * @author zied
 */
@Entity
@Table(name="achat",catalog="crm")
@Indexed
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Achat extends BaseObject implements Serializable{
    private static final long serialVersionUID = 3934526162173229871L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @Column(name="quantite", nullable=false)
    private int quantite;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="produit")
    private Produit produit;
    @Column(name="prixUnit", nullable=false)
    private float prixUnit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="opAchat")
    private OpAchat opAchat;    
    @Column(name="vendu", nullable=false)
    private int vendu;
    @Transient
    private float montant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Field
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Field
    public int getVendu() {
        return vendu;
    }

    public void setVendu(int vendu) {
        this.vendu = vendu;
    }

    @Field
    public float getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(float prixUnit) {
        this.prixUnit = prixUnit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    @JsonIgnore
    public OpAchat getOpAchat() {
        return opAchat;
    }

    public void setOpAchat(OpAchat opAchat) {
        this.opAchat = opAchat;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Achat))
        return false;
                
        return ((Achat)obj).getId()== this.id;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        return hash * 31 + id.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("quantite").append("='").append(getQuantite()).append("', ");
        sb.append("]");
        return sb.toString();
    }
}
