package beans;

/**
 * Created by Ismael on 20/01/2018.
 *
 * A classe alunos ava serve para reaproveitar dados retornados
 * do json da integração com o ava, sem que seja necessário alterar
 * qualquer outro módulo do projeto
 */

public class DadosDoAVA {

    private String sitename;
    private String username;
    private String firstaname;
    private String lastname;
    private String fullName;
    private String lang;
    private String userid;
    private String siteurl;
    private String userpictureurl;
    private WSFunctions []functions;

    public DadosDoAVA(){

    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstaname() {
        return firstaname;
    }

    public void setFirstaname(String firstaname) {
        this.firstaname = firstaname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public String getUserpictureurl() {
        return userpictureurl;
    }

    public void setUserpictureurl(String userpictureurl) {
        this.userpictureurl = userpictureurl;
    }

    public WSFunctions[] getFunctions() {
        return functions;
    }

    public void setFunctions(WSFunctions[] functions) {
        this.functions = functions;
    }
}
