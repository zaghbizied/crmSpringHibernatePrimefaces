/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.model.Caisse;

/**
 *
 * @author zied
 */
public interface CaisseDao extends GenericDao<Caisse, Long>{
    Caisse getLastOne();
}
