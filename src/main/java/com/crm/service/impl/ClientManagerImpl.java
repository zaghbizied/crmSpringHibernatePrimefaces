package com.crm.service.impl;


import com.crm.dao.ClientDao;
import com.crm.model.Client;
import com.crm.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;

@Service("clientManager")
@WebService(serviceName = "ClientService", endpointInterface = "com.crm.service.ClientManager")
public class ClientManagerImpl extends GenericManagerImpl<Client, Long> implements ClientManager {
    ClientDao clientDao;

    @Autowired
    public ClientManagerImpl(ClientDao clientDao) {
        super(clientDao);
        this.clientDao = clientDao;
    }
}