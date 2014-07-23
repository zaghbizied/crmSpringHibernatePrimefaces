/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.webapp.action;

import com.crm.model.PrelevementCaisse;
import com.crm.model.lazy.PrelevementCaisseLazyModel;
import com.crm.service.PrelevementCaisseManager;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zied
 */
@Component("prelevementCaisseController")
@Scope("view")
public class PrelevementCaisseController extends BasePage implements Serializable {
    private PrelevementCaisseManager prelevementCaisseManager;
    private PrelevementCaisseLazyModel prelevements;
    private PrelevementCaisse searchObject;
    private PrelevementCaisse newPrelevement;
    private PrelevementCaisse selectedPrelevement;
    
    @Autowired
    public void setPrelevementCaisseManager(@Qualifier("prelevementCaisseManager")PrelevementCaisseManager prelevementCaisseManager) {
        this.prelevementCaisseManager = prelevementCaisseManager;
    }

    public PrelevementCaisseLazyModel getPrelevements() {
        return prelevements;
    }

    public void setPrelevements(PrelevementCaisseLazyModel prelevements) {
        this.prelevements = prelevements;
    }

    public PrelevementCaisse getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(PrelevementCaisse searchObject) {
        this.searchObject = searchObject;
    }

    public PrelevementCaisse getNewPrelevement() {
        return newPrelevement;
    }

    public void setNewPrelevement(PrelevementCaisse newPrelevement) {
        this.newPrelevement = newPrelevement;
    }

    public PrelevementCaisse getSelectedPrelevement() {
        return selectedPrelevement;
    }

    public void setSelectedPrelevement(PrelevementCaisse selectedPrelevement) {
        this.selectedPrelevement = selectedPrelevement;
    }
    
    public void search(){
        if(searchObject!=null&&searchObject.getDatePrelevement()!=null){
            prelevements=new PrelevementCaisseLazyModel(prelevementCaisseManager.getByDate(searchObject.getDatePrelevement()));
        }
        else
            prelevements=new PrelevementCaisseLazyModel();
    }
    
    public void prepareAdd(){
        newPrelevement=new PrelevementCaisse();
    }
    
    public void prepareUpdate(PrelevementCaisse pc){
        selectedPrelevement=pc;
    }
    
    @PostConstruct
    public void init(){
        searchObject=new PrelevementCaisse();
        searchObject.setDatePrelevement(new Date(System.currentTimeMillis()));
        prelevements=new PrelevementCaisseLazyModel(prelevementCaisseManager.getByDate(searchObject.getDatePrelevement()));
    }
    
    public void saveNew(){
        newPrelevement.setDatePrelevement(new Date(System.currentTimeMillis()));
        prelevementCaisseManager.save(newPrelevement);
    }
    
    public void update(){
        prelevementCaisseManager.save(selectedPrelevement);
    }
}