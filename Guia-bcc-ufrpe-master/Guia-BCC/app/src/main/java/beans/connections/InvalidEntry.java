package beans.connections;

/**
 * Created by IsmaelCesar on 20/01/2018.
 * A classe a seguir serve para capturar o
 * objeto json caso o usuário tente fazer um
 * login inválido
 */

public class InvalidEntry {
    private String error;
    private String stacktrace;
    private String debuginfo;
    private String reproductionlink;

    public InvalidEntry(){

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getDebuginfo() {
        return debuginfo;
    }

    public void setDebuginfo(String debuginfo) {
        this.debuginfo = debuginfo;
    }

    public String getReproductionlink() {
        return reproductionlink;
    }

    public void setReproductionlink(String reproductionlink) {
        this.reproductionlink = reproductionlink;
    }
}
