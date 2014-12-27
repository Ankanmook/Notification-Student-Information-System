/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.bean;

import edu.rit.notification.entity.Subscription;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class SubscriptionFacade extends AbstractFacade<Subscription> {
    @PersistenceContext(unitName = "SISPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubscriptionFacade() {
        super(Subscription.class);
    }
    
}
