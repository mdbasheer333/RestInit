package com.restinit.core.support;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

@Component(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Routes {

    private final String userEndPoint = "/api/users/{user}";
    private final String allUsersEndPoint = "/api/users";
    private final String regSuccess="/api/users";

    public String getRegSuccess(){
        return regSuccess;
    }

    public String getUserEndPoint() {
        return userEndPoint;
    }

    public String getAllUsersEndPoint() {
        return allUsersEndPoint;
    }

}
