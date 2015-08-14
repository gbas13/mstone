package com.gbas.ms.net;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetListener;
import com.gbas.gnet.NetObject;

/**
 * Petición al servidor para hacer login de usuario conocido u anónimo
 */
public class NOGameAbandonedByOpponent implements NetObject {
    public NOGameAbandonedByOpponent() {
    }

    @Override
    public void action(ClientThread clientThread, NetListener netListener) {
        ((NetListenerClientMSGame) netListener).oponentAbandoned(clientThread);
    }
}
