package com.gbas.ms.server.dao;

//import com.gbas.mus.server.dao.sqlimp.UserDescriptionDAOImpl;

import com.gbas.ms.server.dao.impl.UserDescriptionDAOImpl;

public class DaoService {
    public static UserDescriptionDAO getUserDescriptionDAO() {
        return UserDescriptionDAOImpl.getInstance();
    }
}
