/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Caisse;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface CaisseManager extends GenericManager<Caisse, Long> {
    Caisse getLastOne();
}