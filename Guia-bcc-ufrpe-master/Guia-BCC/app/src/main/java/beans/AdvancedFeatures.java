package beans;

import java.io.Serializable;

/**
 * Created by Ismael on 20/01/2018.
 * A seguinte classe representa os objetos advanced features
 * que é retornado das requisições do AVA
 * OBS ela implementa serializable para que possa ser passada como parâmetro em um Extra
 */

public class AdvancedFeatures implements Serializable{
    private String name;
    private String value;

    public AdvancedFeatures(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
