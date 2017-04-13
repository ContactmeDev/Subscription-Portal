/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cme.bean;

import com.cme.ejb.SubscriptionPortalFacade;
import com.cme.ejb.SubserviceMastFacade;
import com.cme.entity.SubscriptionPortal;
import com.cme.entity.SubscriptionPortalPK;
import com.cme.entity.SubserviceMast;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;

/**
 *
 * @author hp
 */
@Named
@SessionScoped

public class User {
    
    private String status;
    private String name;
    private String email;
    @Size(max=10)
    private String mobileNo;
    private String service;
    private String subService;
    private String source;
    private SubscriptionPortal subscriber;
    private SubscriptionPortalPK subPK;
    @EJB 
    private SubscriptionPortalFacade subSession;
    @EJB
    private SubserviceMastFacade subserviceSession;
    List<SubscriberList> subList = new ArrayList<SubscriberList>();
    
    

    /**
     * Creates a new instance of SubscriberBean
     */
    public User() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSubService() {
        return subService;
    }

    public void setSubService(String subService) {
        this.subService = subService;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    
    
    public List<SubscriptionPortal> getSubscribers(){
        return subSession.getSubscriberByStatus(status);
    }
    
    
    public List<SubscriptionPortal> getAll(){
        return subSession.findAll();
    }
    
    public String saveEntity(){
        subscriber = new SubscriptionPortal();
        subPK = new SubscriptionPortalPK();
        
        subPK.setPhoneNumber(mobileNo);
        subPK.setServiceId(service);
        subPK.setSubserviceId(subService);
        subscriber.setSubscriptionPortalPK(subPK);
        subscriber.setEMail(email);
        subscriber.setName(name);
        subscriber.setSource(source);
        subscriber.setStatus("N");
        
        subSession.create(subscriber);
        return "output";
        
    }
    
    public SubscriptionPortal getNewSubscriber(){
        return subscriber;
    }
    
    public List<SubscriberList> getPortalServices(){
       List<SubserviceMast> subServicesList =  subserviceSession.getPortalServices(subService);
       
       
       SubscriptionPortal   subPortal;
       SubscriptionPortalPK subPK;
       subList.clear();
       for(SubserviceMast ssMast: subServicesList){
           subPortal = new SubscriptionPortal();
           subPK = new SubscriptionPortalPK();
           
           subPK.setServiceId(ssMast.getSsrvSrvCode().getSrvCode());
           subPK.setSubserviceId(ssMast.getSsrvCode());
           subPK.setPhoneNumber(mobileNo);
           
           subPortal.setSubscriptionPortalPK(subPK);
           subPortal.setEMail(email);
           subPortal.setName(name);
           subPortal.setSource(source);
           subPortal.setStatus("N");
           
           
           subList.add(new SubscriberList(subPK,subPortal,false));
       }
       return subList;
    } 
    
    public String saveAdditionalServices(){
        for(SubscriberList portal: subList){
            if (portal.isCheck()){
                subSession.create(portal.getSubEntity());
            }
        }
        return "subscriber";
    }
    
    public String getArabicServiceName(String ssrv){
        return subserviceSession.getServiceNameArabic(ssrv);
    }
    
    public static class SubscriberList {
        private SubscriptionPortal subEntity;
        private SubscriptionPortalPK subPK;
        private boolean check;
        private boolean edit;
        
        public SubscriberList(){
            
        }
        public SubscriberList(SubscriptionPortalPK pk,SubscriptionPortal portal, boolean sub){
            subEntity = portal;
            subEntity.setSubscriptionPortalPK(pk);
            check = sub;
        }

        public SubscriptionPortal getSubEntity() {
            return subEntity;
        }

        public void setSubEntity(SubscriptionPortal subEntity) {
            this.subEntity = subEntity;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public SubscriptionPortalPK getSubPK() {
            return subPK;
        }

        public void setSubPK(SubscriptionPortalPK subPK) {
            this.subPK = subPK;
        }
        
        
        
        
    } 
}
