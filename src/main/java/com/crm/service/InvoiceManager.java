/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Client;
import com.crm.model.Invoice;
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
public interface InvoiceManager extends GenericManager<Invoice, Long> {
      List<Invoice> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
      int countByDate(Date date);
      List<Invoice> getLazyByDateByClient(Date date,Client client,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
      int countByDateByClient(Date date, Client client);
      List<Invoice> getLazyByClient(Client client,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
      int countByClient(Client c);
      List<Invoice> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
      int countAll();
}