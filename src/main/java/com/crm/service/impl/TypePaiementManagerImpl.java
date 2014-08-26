/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.service.impl;

import com.crm.dao.TypePaiementDao;
import com.crm.model.TypePaiement;
import com.crm.service.TypePaiementManager;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zied
 */
@Service("typePaiementManager")
@WebService(serviceName = "TypePaiementService", endpointInterface = "com.crm.service.TypePaiementManager")
public class TypePaiementManagerImpl extends GenericManagerImpl<TypePaiement, Long> implements TypePaiementManager{
    TypePaiementDao typePaiementDao;

    @Autowired
    public TypePaiementManagerImpl(TypePaiementDao typePaiementDao) {
        super(typePaiementDao);
        this.typePaiementDao = typePaiementDao;
    }    
}
