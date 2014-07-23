/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Invoice;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface InvoiceManager extends GenericManager<Invoice, Long> {
    
}