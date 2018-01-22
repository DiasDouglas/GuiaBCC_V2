package beans.connections;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Ismael on 21/01/2018.
 * A classe a seguir serve para adicionar dados
 * extras retornados do aluno de acordo com os
 * alunos
 */

public class CoursesListFromJson implements Serializable {

    private LinkedList<CoursesFromJson> enrolledcourses;

    public CoursesListFromJson(){

    }

    public LinkedList<CoursesFromJson> getEnrolledcourses() {
        return enrolledcourses;
    }

    public void setEnrolledcourses(LinkedList<CoursesFromJson> enrolledcourses) {
        this.enrolledcourses = enrolledcourses;
    }

    public void addCourse(CoursesFromJson course){
        this.enrolledcourses.add(course);
    }

    public CoursesFromJson getCourse(int i){
        return this.enrolledcourses.get(i);
    }
}
