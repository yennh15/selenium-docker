package com.yenpractice.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yenpractice.datamodel.model.Customer;
import com.yenpractice.datamodel.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Customer getCustomerData(String path){

        try{
            InputStream stream = DataLoader.getData(path);
            return mapper.readValue(stream, Customer.class);
        }
        catch (Exception e){
            log.error("unable to get data from location{}", path);
        }
        return null;
    }

    public static Flight getFlightData(String path){
        try{
            InputStream stream = DataLoader.getData(path);
            return mapper.readValue(stream, Flight.class);
        }
        catch (Exception e){
            log.error("unable to get data from location{}", path);
        }
        return null;
    }
}
