/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
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
@Table(name="opAchat",catalog="crm")
@Indexed
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OpAchat extends BaseObject implements Serializable{
    private static final long serialVersionUID = 3934526162173359871L;
    private Long id;
    private Date dateAchat;
    private Fournisseur fournisseur;
    private float montant;
    private float montantPaye;
    private Set<Achat> achats=new HashSet<>();

    @Id @GeneratedValue(strategy=IDENTITY) @DocumentId    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="dateAchat", nullable=false,columnDefinition = "DATETIME")
    @Field
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="opAchat")
    public Set<Achat> getAchats() {
        return achats;
    }

    public void setAchats(Set<Achat> achats) {
        this.achats = achats;
    }
    
    
    @Column(name="montantPaye", nullable=false)
    @Field
    public float getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(float montantPaye) {
        this.montantPaye = montantPaye;
    }
    
    
    @Column(name="montant", nullable=false)
    @Field
    public float getMontant(){
        return montant;
    }
    
    public void setMontant(float montant){
        this.montant=montant;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fournisseur")
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    @Transient
    public List<Achat> getNestedAchats(){
        return new ArrayList<>(achats);
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof OpVente))
        return false;
                
        return ((OpVente)obj).getId()== this.id;
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
        sb.append("dateVente").append("='").append(getDateAchat()).append("', ");
        sb.append("]");
        return sb.toString();
    }
}
