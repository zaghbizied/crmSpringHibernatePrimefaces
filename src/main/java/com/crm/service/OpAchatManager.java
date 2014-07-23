/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Fournisseur;
import com.crm.model.OpAchat;
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
public interface OpAchatManager extends GenericManager<OpAchat, Long> {
    List<OpAchat> getByDate(Date date);
    List<OpAchat> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDate(Date date);
    List<OpAchat> getLazyByDateByFournisseur(Date date,Fournisseur f,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDateByFournisseur(Date date, Fournisseur f);
    List<OpAchat> getByMonth(Date date);
    List<OpAchat> getByYear(int year);
    List<OpAchat> getNonPayedByFournisseur(Fournisseur fournisseur);
    List<OpAchat> getLazyByFournisseur(Fournisseur f,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByFournisseur(Fournisseur f);
    List<OpAchat> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countAll();
}