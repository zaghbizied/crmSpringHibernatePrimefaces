/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
/**
 *
 * @author zied
 */
@Entity
@Table(name="prelevementCaisse",catalog="crm")
@Indexed
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrelevementCaisse extends BaseObject implements Serializable{
    private static final long serialVersionUID = 3934526162173359871L;
    private Long id;
    private Date datePrelevement;
    private float montant;
    private String motif;

    @Id @GeneratedValue(strategy=IDENTITY) @DocumentId    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="datePrelevement", nullable=false,columnDefinition = "DATETIME")
    @Field
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDatePrelevement() {
        return datePrelevement;
    }

    public void setDatePrelevement(Date datePrelevement) {
        this.datePrelevement = datePrelevement;
    }

    @Column(name="montant", nullable=false)
    @Field
    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Column(name="motif", nullable=true)
    @Field
    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof PrelevementCaisse))
        return false;
                
        return ((PrelevementCaisse)obj).getId()== this.id;
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
        sb.append("datePrelevement").append("='").append(getDatePrelevement()).append("', ");
        sb.append("]");
        return sb.toString();
    }
}
