package com.gbas.ms.net;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetListener;
import com.gbas.gnet.NetObject;
import com.gbas.ms.model.UserConfiguration;

/**
 * Created by gbas on 7/01/14.
 */
public class NOLoginRes implements NetObject {
    String txtInvalid;
    UserConfiguration userConfiguration;

    public NOLoginRes(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    public NOLoginRes(String txtInvalid) {
        this.txtInvalid = txtInvalid;
    }

    public NOLoginRes() {
    }

    @Override
    public void action(ClientThread clientThread, NetListener netListener) {
        ((NetListenerClientMSGame) netListener).loginResponse(userConfiguration, txtInvalid);
    }
}
