/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Client;
import com.crm.model.PrixClient;
import com.crm.model.Produit;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface PrixClientManager extends GenericManager<PrixClient, Long>{
    List<PrixClient> getByClient(Client client);
    PrixClient getByClientByProduit(Client client,Produit produit);
}
