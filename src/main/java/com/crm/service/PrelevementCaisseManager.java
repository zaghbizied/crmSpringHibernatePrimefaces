/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.PrelevementCaisse;
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
public interface PrelevementCaisseManager extends GenericManager<PrelevementCaisse, Long> {
    List<PrelevementCaisse> getAfterDate(Date date);
    List<PrelevementCaisse> getByDate(Date date);
    List<PrelevementCaisse> getLazyByDate(Date date,int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countByDate(Date date);
    List<PrelevementCaisse> getLazyAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
    int countAll();
}