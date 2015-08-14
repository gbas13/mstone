package com.gbas.ms.server.dao.impl;

import com.gbas.ms.server.logic.UserConfiguration;
import com.gbas.ms.server.dao.UserDescriptionDAO;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDescriptionDAOImpl implements UserDescriptionDAO {
    static Logger logger = Logger.getLogger(UserDescriptionDAOImpl.class);
    Map<String, UserConfiguration> userMap = new HashMap<String, UserConfiguration>();
    private static UserDescriptionDAO instance;
    private static int count = 0;

    private UserDescriptionDAOImpl() {
        loadUserMap();
    }

    public static UserDescriptionDAO getInstance() {
        if (instance == null) {
            instance = new UserDescriptionDAOImpl();
        }
        return instance;
    }

    private void loadUserMap() {
        try {
            FileInputStream fis = new FileInputStream("user.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userMap = (Map<String, UserConfiguration>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            logger.error("Cargando mapa de usaurio", e);
        } catch (ClassNotFoundException e) {
            logger.error("Cargando mapa de usaurio", e);
        }
    }

    private void saveUserMap() {
        try {
            FileOutputStream fos = new FileOutputStream("user.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userMap);
            oos.close();
        } catch (IOException e) {
            logger.error("Salvando mapa de usaurio", e);
        }

    }

    @Override
    public UserConfiguration loadUserDescription(String name, String password) {
        UserConfiguration userConfiguration = userMap.get(name);
        if (userConfiguration != null) {
            if (!password.equals(userConfiguration.getPasswd())) {
                return null;
            }
            return userConfiguration;
        } else {
            // Creamos nuevo user
            UserConfiguration nuevoUsuario = UserConfiguration.getInstance(name);
            nuevoUsuario.setPasswd(password);
            nuevoUsuario.setAnonimo(false);

            userMap.put(name, nuevoUsuario);
            saveUserMap();

            return nuevoUsuario;
        }

    }

    @Override
    public void updateUserDescription(UserConfiguration userConfiguration) {
        saveUserMap();
    }

    @Override
    public UserConfiguration createAnonimousUser() {
        UserConfiguration instance = UserConfiguration.getInstance("Anonimo " + count++);
        instance.setAnonimo(true);

        return instance;
    }
}
