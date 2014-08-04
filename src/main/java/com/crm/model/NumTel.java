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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author zied
 */
@Entity
@Table(name="numTel",catalog="crm")
@Indexed
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NumTel extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3932626162176759411L;
    private Long id;
    private int numTel;
    private Operateur operateur;
    private Client client;

    @Id @GeneratedValue(strategy=IDENTITY) @DocumentId    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="numTel", nullable=false)
    @Field
    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="operateur")
    public Operateur getOperateur() {
        return operateur;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client")
    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof NumTel))
        return false;
                
        return ((NumTel)obj).getId()== this.id;
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
        sb.append("numTel").append("='").append(getNumTel()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }
    
    
}
