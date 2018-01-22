package beans.connections;

import java.io.Serializable;

/**
 * Created by Ismael on 20/01/2018.
 * A classe a seguir representa o token que será utilizado
 * caso a autenticação seja válida
 */

public class Token implements Serializable {

    private String token;

    public Token(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
