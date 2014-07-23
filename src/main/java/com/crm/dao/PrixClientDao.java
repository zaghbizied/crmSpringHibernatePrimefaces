/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.model.Client;
import com.crm.model.PrixClient;
import com.crm.model.Produit;
import java.util.List;

/**
 *
 * @author zied
 */
public interface PrixClientDao extends GenericDao<PrixClient, Long>{
    List<PrixClient> getByClient(Client client);
    PrixClient getByClientByProduit(Client client,Produit produit);
}
