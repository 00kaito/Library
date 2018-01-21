package com.example.jpa.Model.services;

import com.example.jpa.Model.UserModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
//@Service, @Controller, @Bean, @Configuration, @Repository - wszystko to są beany

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS) //zasięg sesyjny ziarna
//bez proxymode, scrope ustawiłby sie do wszystkich istniejacych ziaren
// dzieki proxymode mowimy, ze w tej klasie w ktorej jestesmy zmien zasieg bina na sesyjny
public class UserService { //singleton - jedna instancja
    private String login;
    private boolean isLogIn;

    public boolean isLogIn() {
        return isLogIn;
    }

    public void setLogIn(boolean logIn) {
        isLogIn = logIn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
