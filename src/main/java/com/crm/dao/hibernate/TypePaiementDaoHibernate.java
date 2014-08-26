/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.dao.hibernate;

import com.crm.dao.TypePaiementDao;
import com.crm.model.TypePaiement;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zied
 */
@Repository("typePaiementDao")
public class TypePaiementDaoHibernate extends GenericDaoHibernate<TypePaiement, Long> implements TypePaiementDao{

    public TypePaiementDaoHibernate() {
        super(TypePaiement.class);
    }
    
}
