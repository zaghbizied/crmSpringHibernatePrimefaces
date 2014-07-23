/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.service;

import com.crm.model.Client;
import com.crm.model.NumTel;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author zied
 */
@WebService
public interface NumTelManager extends GenericManager<NumTel, Long> {
    List<NumTel> getByClient(Client c);
}