package com.gbas.ms.net;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetListenerClient;
import com.gbas.ms.model.JugadorNet;

public interface NetListenerClientMSGame extends NetListenerClient {
    // Versión del servidor en ejecución
    void setVersion(boolean valid, String txtInvalid);

    // Mensaje de error
    void errorMessage(String txt);

    // Mensaje de error
    void loginResponse(JugadorNet jugadorNet, String txtProblem);

    // Contrincante abandono
    void oponentAbandoned(ClientThread clientThread);
}
