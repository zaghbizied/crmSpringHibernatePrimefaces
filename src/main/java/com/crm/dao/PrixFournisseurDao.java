/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.model.Fournisseur;
import com.crm.model.PrixFournisseur;
import com.crm.model.Produit;
import java.util.List;

/**
 *
 * @author zied
 */
public interface PrixFournisseurDao extends GenericDao<PrixFournisseur, Long>{
    List<PrixFournisseur> getByFournisseur(Fournisseur fournisseur);
    PrixFournisseur getByFournisseurByProduit(Fournisseur fournisseur,Produit produit);
}
