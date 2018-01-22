package beans.connections;

import java.io.Serializable;

/**
 * Created by Ismael on 21/01/2018.
 * A classe a seguir serve para adicionar dados
 * extras retornados do aluno de acordo com os
 * alunos.
 * Objetos referentes à disciplina cursada pelos alunos
 */

public class CoursesFromJson implements Serializable{
    private String id;
    private String fullname;
    private String shortName;

    public CoursesFromJson(){

    }

    public CoursesFromJson(String id, String fullname, String shortName){
        this.setId(id);
        this.setFullname(fullname);
        this.setShortName(shortName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
