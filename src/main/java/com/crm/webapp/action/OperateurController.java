package com.crm.webapp.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import com.crm.model.Operateur;
import com.crm.service.OperateurManager;
import com.crm.webapp.action.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("operateurController")
@Scope("view")
public class OperateurController extends BasePage implements Serializable {
    
    private OperateurManager operateurManager;
    private List<Operateur> operateurs;
    private Operateur selectedOperateur;
    private Operateur newOperateur;

    @Autowired
    public void setOperateurManager(OperateurManager operateurManager) {
        this.operateurManager = operateurManager;
    }

    @PostConstruct
    public void init(){
    	operateurs=operateurManager.getAll();
    }

    public List<Operateur> getOperateurs() {
        return operateurs;
    }
    
    public void setOperateurs(List<Operateur> operateurs){
    	this.operateurs=operateurs;
    }

	public Operateur getNewOperateur() {
		return newOperateur;
	}

	public void setNewOperateur(Operateur newOperateur) {
		this.newOperateur = newOperateur;
	}

	public Operateur getSelectedOperateur() {
		return selectedOperateur;
	}

	public void setSelectedOperateur(Operateur selectedOperateur) {
		this.selectedOperateur = selectedOperateur;
	}
	
	public void delete() {
        operateurManager.remove(selectedOperateur.getId());
        init();
        addMessage("operateur.deleted");        
    }    

    public void saveNew() {
    	operateurManager.save(newOperateur);
        String key = "operateur.added" ;
        init();
        addMessage(key);
    }
    
    public void prepareUpdate(Operateur selectedOperateur){
    	this.selectedOperateur=selectedOperateur;
    }
    
    public void prepareAdd(){
        newOperateur=new Operateur();
    }
    
    public void update(){
    	operateurManager.save(selectedOperateur);
        String key = "operateur.updated" ;
        init();
        addMessage(key);
    }
}