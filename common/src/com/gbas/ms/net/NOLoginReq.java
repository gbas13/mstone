package com.gbas.ms.net;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetListener;
import com.gbas.gnet.NetObject;

/**
 * Petición al servidor para hacer login de usuario conocido u anónimo
 */
public class NOLoginReq implements NetObject {
    String nombre;
    String password;

    public NOLoginReq(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public NOLoginReq() {
    }

    @Override
    public void action(ClientThread clientThread, NetListener netListener) {
        ((NetListenerServerMSGame) netListener).loginUsuario(nombre, password, clientThread);
    }
}
