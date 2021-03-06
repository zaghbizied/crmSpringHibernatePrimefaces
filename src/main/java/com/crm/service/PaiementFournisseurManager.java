/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Fournisseur;
import com.crm.model.PaiementFournisseur;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import org.primefaces.model.SortOrder;

/**
 *
 * @author zied
 */
@WebService
public interface PaiementFournisseurManager extends GenericManager<PaiementFournisseur, Long> {
    float getAvance(Fournisseur fournisseur);
    PaiementFournisseur getPaiementAvecAvance(Fournisseur fournisseur);
    List<PaiementFournisseur> getAfterDate(Date date);
    List<PaiementFournisseur> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDate(Date date);
    List<PaiementFournisseur> getLazyByDateByFournisseur(Date date,Fournisseur f,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDateByFournisseur(Date date, Fournisseur f);
    List<PaiementFournisseur> getLazyByFournisseur(Fournisseur f,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByFournisseur(Fournisseur f);
    List<PaiementFournisseur> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countAll();
}