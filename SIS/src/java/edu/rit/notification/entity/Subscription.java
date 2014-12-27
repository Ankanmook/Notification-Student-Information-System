/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "subscription")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s"),
    @NamedQuery(name = "Subscription.findByUid", query = "SELECT s FROM Subscription s WHERE s.subscriptionPK.uid = :uid"),
    @NamedQuery(name = "Subscription.findByClassNumber", query = "SELECT s FROM Subscription s WHERE s.subscriptionPK.classNumber = :classNumber")})
public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SubscriptionPK subscriptionPK;
    @JoinColumn(name = "UID", referencedColumnName = "UID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
//    @JoinColumn(name = "CLASS_NUMBER", referencedColumnName = "CLASS_NUMBER", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private ClassSchedule classSchedule;

    public Subscription() {
    }

    public Subscription(SubscriptionPK subscriptionPK) {
        this.subscriptionPK = subscriptionPK;
    }

    public Subscription(String uid, String classNumber) {
        this.subscriptionPK = new SubscriptionPK(uid, classNumber);
    }

    public SubscriptionPK getSubscriptionPK() {
        return subscriptionPK;
    }

    public void setSubscriptionPK(SubscriptionPK subscriptionPK) {
        this.subscriptionPK = subscriptionPK;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subscriptionPK != null ? subscriptionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscription)) {
            return false;
        }
        Subscription other = (Subscription) object;
        if ((this.subscriptionPK == null && other.subscriptionPK != null) || (this.subscriptionPK != null && !this.subscriptionPK.equals(other.subscriptionPK))) {
            return false;
        }
        return true;
    }

//    public ClassSchedule getClassSchedule() {
//        return classSchedule;
//    }
//
//    public void setClassSchedule(ClassSchedule classSchedule) {
//        this.classSchedule = classSchedule;
//    }

    
    @Override
    public String toString() {
        return "edu.rit.notification.entity.Subscription[ subscriptionPK=" + subscriptionPK + " ]";
    }
    
}
