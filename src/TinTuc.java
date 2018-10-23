/**
 * Created by trananh bacninh on 12-Mar-18.
 */
public class TinTuc {
    private String chuDe;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TinTuc(String chuDe, String url, String title, String content, String id) {
        this.chuDe = chuDe;
        this.url = url;
        this.title = title;
        this.content = content;
        this.id = id;
    }

    private String title;
    private String content;
    private String id;
    public TinTuc(){

    }
    public TinTuc(String chuDe,String url, String title, String content){
        this.chuDe=chuDe;
        this.url = url;
        this.title = title;
        this.content = content;


    }

    public String getChuDe() {
        return chuDe;
    }

    public void setChuDe(String chuDe) {
        this.chuDe = chuDe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String toJson(){
        return String.format("{\n\t\t\"id\":\"%s\",\n\t\t\"chude\":\"%s\",\n\t\t\"title\":\"%s\",\n\t\t\"content\":\"%s\",\n\t\t\"url\":\"%s\"\n}",this.getId(),this.getChuDe(),this.getTitle().replaceAll("\"",""),this.getContent().replaceAll("\"",""),this.getUrl());
    }
    public String toXML(){
        return String.format("\t<doc>\n\t\t<field name=\"id\">%s</field>\n\t\t<field name=\"chude\">%s</field>\n\t\t<field name=\"title\">%s</field>\n\t\t<field name=\"content\">%s</field>\n\t\t<field name=\"url\">%s</field>\n\t</doc>",this.getId(),this.getChuDe(),this.getTitle().replaceAll("\"",""),this.getContent().replaceAll("\"",""),this.getUrl());
    }
}
