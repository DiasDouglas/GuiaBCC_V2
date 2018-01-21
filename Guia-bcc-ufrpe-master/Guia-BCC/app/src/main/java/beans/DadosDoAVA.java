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
    private String firstname;
    private String lastname;
    private String fullname;
    private String lang;
    private String userid;
    private String siteurl;
    private String userpictureurl;
    private WSFunctions functions[];
    private String downloadfiles;
    private String uploadfiles;
    private String release;
    private String version;
    private String mobilecssurl;
    private AdvancedFeatures advancedfeatures[];
    private String usercanmanageownfiles;
    private String userquota;
    private String usermaxuploadfilesize;

    public DadosDoAVA(){

    }

    public String getDownloadfiles() {
        return downloadfiles;
    }

    public void setDownloadfiles(String downloadfiles) {
        this.downloadfiles = downloadfiles;
    }

    public String getUploadfiles() {
        return uploadfiles;
    }

    public void setUploadfiles(String uploadfiles) {
        this.uploadfiles = uploadfiles;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMobilecssurl() {
        return mobilecssurl;
    }

    public void setMobilecssurl(String mobilecssurl) {
        this.mobilecssurl = mobilecssurl;
    }

    public AdvancedFeatures[] getAdvancedfeatures() {
        return advancedfeatures;
    }

    public void setAdvancedfeatures(AdvancedFeatures[] advancedfeatures) {
        this.advancedfeatures = advancedfeatures;
    }

    public String getUsercanmanageownfiles() {
        return usercanmanageownfiles;
    }

    public void setUsercanmanageownfiles(String usercanmanageownfiles) {
        this.usercanmanageownfiles = usercanmanageownfiles;
    }

    public String getUserquota() {
        return userquota;
    }

    public void setUserquota(String userquota) {
        this.userquota = userquota;
    }

    public String getUsermaxuploadfilesize() {
        return usermaxuploadfilesize;
    }

    public void setUsermaxuploadfilesize(String usermaxuploadfilesize) {
        this.usermaxuploadfilesize = usermaxuploadfilesize;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstaname) {
        this.firstname = firstaname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
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
