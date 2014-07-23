/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Fournisseur;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface PrixFournisseurManager extends GenericManager<PrixFournisseur, Long>{
    List<PrixFournisseur> getByFournisseur(Fournisseur fournisseur);
    PrixFournisseur getByFournisseurByProduit(Fournisseur fournisseur, Produit produit);
}
