package com.gbas.ms.net;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetListener;
import com.gbas.gnet.NetObject;

/**
 * Created by gbas on 7/01/14.
 */
public class NOVersionRes implements NetObject {
    boolean valid;
    String txtInvalid;

    public NOVersionRes(boolean valid) {
        this.valid = valid;
    }
    public NOVersionRes(boolean valid, String txtInvalid) {
        this.valid = valid;
        this.txtInvalid = txtInvalid;
    }

    public NOVersionRes() {
    }

    @Override
    public void action(ClientThread clientThread, NetListener netListener) {
        ((NetListenerClientMSGame) netListener).setVersion(valid, txtInvalid);
    }
}
