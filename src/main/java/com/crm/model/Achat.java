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
    private Long id;
    private int quantite;
    private Produit produit;
    private float prixUnit;
    private OpAchat opAchat;    
    private int vendu;

    @Id @GeneratedValue(strategy=IDENTITY) @DocumentId    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="quantite", nullable=false)
    @Field
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Column(name="vendu", nullable=false)
    @Field
    public int getVendu() {
        return vendu;
    }

    public void setVendu(int vendu) {
        this.vendu = vendu;
    }

    @Column(name="prixUnit", nullable=false)
    @Field
    public float getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(float prixUnit) {
        this.prixUnit = prixUnit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="produit")
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="opAchat")
    @JsonIgnore
    public OpAchat getOpAchat() {
        return opAchat;
    }

    public void setOpAchat(OpAchat opAchat) {
        this.opAchat = opAchat;
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
