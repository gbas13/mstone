package com.gbas.ms.net;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetListenerServer;

import java.util.Set;

/**
 * Created by gbas on 7/01/14.
 */
public interface NetListenerServerMSGame extends NetListenerServer {
    void versionRequired(String versionClient, ClientThread clientThread);

    void loginUsuario(String name, String password, ClientThread clientThread);

}
