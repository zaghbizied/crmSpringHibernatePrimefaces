/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Vente;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface VenteManager extends GenericManager<Vente, Long> {
    
}