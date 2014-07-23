/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.model.Client;
import com.crm.model.OpVente;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author zied
 */
public interface OpVenteDao extends GenericDao<OpVente, Long>{
    List<OpVente> getNonPayedByClient(Client client);
    List<OpVente> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    List<OpVente> getByDate(Date date);
    int countByDate(Date date);
    List<OpVente> getByMonth(Date date);
    List<OpVente> getByYear(int year);
    List<OpVente> getByMonthByClient(Date date, Client client);
    List<OpVente> getByDateByClient(Date date,Client client);
    List<OpVente> getLazyByDateByClient(Date date,Client client,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDateByClient(Date date, Client client);
    List<OpVente> getByYearByClient(int year,Client client);
    List<OpVente> getByClient(Client c);
    List<OpVente> getLazyByClient(Client client,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByClient(Client c);
    List<OpVente> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countAll();
}
