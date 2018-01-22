package beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Ismael on 21/11/2017.
 * Essa classe representa o usuario aluno
 * que acessará as funcionalidades do programa
 */

public class Usuario implements Serializable{

    private String login;
    private String senha;


    public Usuario(String login, String senha){
        this.setLogin(login);
        this.setSenha(senha);
    }


    public void setLogin(String login) {
        if(login != null)
            this.login = login;
    }

    public void setSenha(String senha) {
        if(senha != null)
            this.senha = senha;
    }

}
