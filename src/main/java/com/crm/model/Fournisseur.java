/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="fournisseur",catalog="crm")
@Indexed
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Fournisseur extends BaseObject implements Serializable{
    private static final long serialVersionUID = 3932627862173357611L;
    private Long id;
    private String nom;
    private String address;
    private String ville;
    private String email;
    private int numTel;
    private Set<PaiementFournisseur> paiementsFournisseur=new HashSet<>();
    private Set<PrixFournisseur> prixFournisseur=new HashSet<>();
    
    @Id @GeneratedValue(strategy=IDENTITY) @DocumentId    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="nom", nullable=false,length = 40)
    @Field
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Column(name="numTel", nullable=false,length = 10)
    @Field
    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }
    
    @Column(name="ville", nullable=false,length = 20)
    @Field
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Column(name="adresse", nullable=false,length = 255)
    @Field
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="email", nullable=true,length = 40)
    @Field
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="fournisseur")
    public Set<PaiementFournisseur> getPaiementsFournisseur() {
        return paiementsFournisseur;
    }
    
    public void setPaiementsFournisseur(Set<PaiementFournisseur> paiementsFournisseur){
        this.paiementsFournisseur=paiementsFournisseur;
    }
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="fournisseur")
    public Set<PrixFournisseur> getPrixFournisseur() {
        return prixFournisseur;
    }

    public void setPrixFournisseur(Set<PrixFournisseur> prixFournisseur) {
        this.prixFournisseur = prixFournisseur;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Fournisseur))
        return false;
                
        return ((Fournisseur)obj).getId()== this.id;
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
        sb.append("nom").append("='").append(getNom()).append("', ");
        sb.append("ville").append("='").append(getVille()).append("', ");
        sb.append("]");
        return sb.toString();
    }
}
