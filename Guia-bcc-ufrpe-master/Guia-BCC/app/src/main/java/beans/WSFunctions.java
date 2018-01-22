package beans;

import java.io.Serializable;

/**
 * Created by Ismael on 20/01/2018.
 * Essa classe representa as fuções do AVA que são retornadas no JSO
 * quando o usuário faz o login
 * OBS ela implementa serializable para que possa ser passada como parâmetro em um Extra
 */

public class WSFunctions implements Serializable{
    private String name;
    private String version;

    public WSFunctions(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
