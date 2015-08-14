package com.gbas.ms.server.dao;


import com.gbas.ms.server.logic.UserConfiguration;

public interface UserDescriptionDAO {
    UserConfiguration loadUserDescription(String name, String password);

    void updateUserDescription(UserConfiguration userConfiguration);

    UserConfiguration createAnonimousUser();
}
