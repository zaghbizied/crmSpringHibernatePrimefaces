/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Fournisseur;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface FournisseurManager extends GenericManager<Fournisseur, Long> {
    
}