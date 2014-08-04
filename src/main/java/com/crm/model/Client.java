/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author zied
 */
@Entity
@Table(name="client",catalog="crm")
@Indexed
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3932627862173359411L;
    private Long id;
    private String nom;
    private String prenom;
    private String address;
    private String ville;
    private String email;
    private Set<NumTel> numTels=new HashSet<>();
    private List<NumTel> nestedNumTels=new ArrayList<>();
    private Set<OpVente> opVentes=new HashSet<>();
    private Set<Invoice> invoices=new HashSet<>();
    private Set<PaiementClient> paiements=new HashSet<>();

    @Id @GeneratedValue(strategy=IDENTITY) @DocumentId    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="nom", nullable=false,length = 20)
    @Field
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name="prenom", nullable=false,length = 20)
    @Field
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="client")
    public Set<OpVente> getOpVentes() {
        return opVentes;
    }

    public void setOpVentes(Set<OpVente> opVentes) {
        this.opVentes = opVentes;
    }
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="client")
    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="client")
    public Set<PaiementClient> getPaiements() {
        return paiements;
    }

    public void setPaiements(Set<PaiementClient> paiements) {
        this.paiements = paiements;
    }

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="client")
    public Set<NumTel> getNumTels() {
        return numTels;
    }

    public void setNumTels(Set<NumTel> numTels) {
        this.numTels = numTels;
    }
    
    @Transient
    public String getFullName() {
        return nom + ' ' + prenom;
    }
    
    @Transient
    public List<NumTel> getNestedNumTels() {
        return new ArrayList<>(numTels);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Client))
        return false;
                
        return ((Client)obj).getId()== this.id;
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
        sb.append("prenom").append("='").append(getPrenom()).append("', ");
        sb.append("]");
        return sb.toString();
    }
}
