package edu.rit.notification.entity;

import edu.rit.notification.entity.Student;
import edu.rit.notification.entity.SubscriptionPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-10T16:29:50")
@StaticMetamodel(Subscription.class)
public class Subscription_ { 

    public static volatile SingularAttribute<Subscription, SubscriptionPK> subscriptionPK;
    public static volatile SingularAttribute<Subscription, Student> student;

}