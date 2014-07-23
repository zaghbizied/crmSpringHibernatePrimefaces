/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.model.Achat;
import com.crm.model.Produit;
import java.util.List;


/**
 *
 * @author zied
 */
public interface AchatDao extends GenericDao<Achat, Long>{
    List<Achat> getNotSelled(Produit produit);
    List<Achat> getByProduit(Produit produit);
}
