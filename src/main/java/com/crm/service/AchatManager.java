/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Achat;
import com.crm.model.Produit;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface AchatManager extends GenericManager<Achat, Long> {
    List<Achat> getNotSelled(Produit produit);
    List<Achat> getByProduit(Produit produit);
}