/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Client;
import com.crm.model.PaiementClient;
import com.crm.model.TypePaiement;
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
public interface PaiementClientManager extends GenericManager<PaiementClient, Long>{
    float getAvance(Client fournisseur);
    PaiementClient getPaiementAvecAvance(Client fournisseur);
    List<PaiementClient> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDate(Date date);
    List<PaiementClient> getLazyByDateByClient(Date date,Client client,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDateByClient(Date date, Client client);
    List<PaiementClient> getLazyByClient(Client client,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByClient(Client c);
    List<PaiementClient> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countAll();
    List<PaiementClient> getAfterDate(Date date);
    List<PaiementClient> getByType(TypePaiement type);
    List<PaiementClient> getAfterDateByType(Date date, TypePaiement type);
}
