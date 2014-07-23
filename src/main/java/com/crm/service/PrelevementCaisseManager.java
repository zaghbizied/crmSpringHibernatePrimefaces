/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.PrelevementCaisse;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface PrelevementCaisseManager extends GenericManager<PrelevementCaisse, Long> {
    List<PrelevementCaisse> getAfterDate(Date date);
    List<PrelevementCaisse> getByDate(Date date);
}