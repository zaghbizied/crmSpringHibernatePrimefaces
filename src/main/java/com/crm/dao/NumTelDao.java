/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.model.Client;
import com.crm.model.NumTel;
import java.util.List;

/**
 *
 * @author zied
 */
public interface NumTelDao extends GenericDao<NumTel, Long>{
    List<NumTel> getByClient(Client c);
}
