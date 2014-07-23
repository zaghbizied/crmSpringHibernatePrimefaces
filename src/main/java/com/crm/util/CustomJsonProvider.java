/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.util;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author zied
 */
public class CustomJsonProvider extends JacksonJsonProvider {
    public CustomJsonProvider(){

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        _mapperConfig.setMapper(mapper);
        
       
    }
}
