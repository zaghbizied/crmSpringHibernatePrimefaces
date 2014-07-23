/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.model.PrelevementCaisse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zied
 */
public interface PrelevementCaisseDao extends GenericDao<PrelevementCaisse, Long>{
    List<PrelevementCaisse> getAfterDate(Date date);
    List<PrelevementCaisse> getByDate(Date date);
}
