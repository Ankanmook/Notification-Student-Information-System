/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.rit.notification.service.rest;

import edu.rit.notification.bean.SubscriptionFacade;
import edu.rit.notification.entity.Subscription;
import edu.rit.notification.entity.SubscriptionPK;
import edu.rit.notification.service.SubscriptionResponse;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("subscribe")
public class SubscriptionFacadeREST {
    @EJB
    private SubscriptionFacade subscriptionFacade;
          
    @GET
    @Path("student")
    @Produces({"application/xml", "application/json"})
    public Response subscribe(){
       String result = "<h1>RESTful Demo Application</h1>In real world application, a collection of users will be returned !!";
        return Response.status(200).entity(result).build();
    }
    
    
    @GET
    @Path("{studentId}/{classNumber}")
    @Produces({"application/xml", "application/json"})
    public SubscriptionResponse subscribe(@PathParam("studentId") String uid, @PathParam("classNumber") String classNumber) {
       System.out.println("*****************subscribe() called******************");
        SubscriptionResponse response;
        
        response= new SubscriptionResponse("success");
        response.setStatus("OK");
       
        System.out.println("SubscriptionRequest: " + uid + ": "+ classNumber);
        Subscription subscription = new Subscription();

        SubscriptionPK subPK = new SubscriptionPK();
        subPK.setClassNumber(classNumber);
        subPK.setUid(uid);
        subscription.setSubscriptionPK(subPK);

        System.out.println("*****************calling studentEjbRef.find(uid) ******************");
        //Student student = studentEjbRef.find(uid);
       // System.out.println("Student: " + student);
        //subscription.setStudent(student);

        System.out.println("*****************calling subscriptionEjbRef.create(subscription) ******************");
        subscriptionFacade.create(subscription);

        return response;
    }

   
    
}
