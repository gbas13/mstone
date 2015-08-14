package com.gbas.ms.net;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetListener;
import com.gbas.gnet.NetObject;

/**
 * Petición al servidor para hacer login de usuario conocido u anónimo
 */
public class NOVersionReq implements NetObject {
    private String versionClient;

    public NOVersionReq(String versionClient) {
        this.versionClient = versionClient;
    }

    public NOVersionReq() {
    }

    @Override
    public void action(ClientThread clientThread, NetListener netListener) {
        ((NetListenerServerMSGame) netListener).versionRequired(versionClient, clientThread);
    }
}
