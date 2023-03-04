package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public User connect(int userId, String countryName) throws Exception{

        User user = userRepository2.findById(userId).get();
        if(user.getConnected()){
            throw new Exception("Already connected");
        }
        Country country = user.getCountry();
        String name = String.valueOf(country.getCountryName());
        if (name.equalsIgnoreCase(countryName)) {
            return user;
        }
        List<ServiceProvider>serviceProviderList = user.getServiceProviders();
        if(serviceProviderList==null){
            throw new Exception("Unable to connect");
        }
        for (ServiceProvider i : serviceProviderList){
            List<Country>countryList = i.getCountryList();
            if(countryList!=null){
                for(Country j : countryList){
                    String name1 = String.valueOf(country.getCountryName());
                    if(name1.equals(countryName)){
                        user.setMaskedIp(j.getCode());
                        user.setConnected(true);
                        Connection connection = new Connection();
                        connection.setUser(user);
                        connection.setServiceProvider(i);
                        List<Connection>connectionList=user.getConnectionList();
                        connectionList.add(connection);
                        List<Connection>connectionList1 = i.getConnectionList();
                        connectionList1.add(connection);
                        i.setConnectionList(connectionList1);
                        userRepository2.save(user);
                        return user;
                    }
                }
            }

        }
        throw new Exception("Unable to connect");
    }
    @Override
    public User disconnect(int userId) throws Exception {
        User user = userRepository2.findById(userId).get();
        if(!user.getConnected()){
            throw new Exception("Already disconnected");
        }
        user.setConnected(false);
        user.setMaskedIp(null);
        return user;
    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
return null;
    }
}
