package com.restinit.core.support;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

@Component(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Routes {

    private final String userEndPoint = "/api/users/{user}";
    private final String allUsersEndPoint = "/api/users";

    public String getUserEndPoint() {
        return userEndPoint;
    }

    public String getAllUsersEndPoint() {
        return allUsersEndPoint;
    }

}
