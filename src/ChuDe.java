/**
 * Created by trananh bacninh on 12-Mar-18.
 */
public class ChuDe {
    private String ten;
    private String url;

    public ChuDe() {
    }

    public String getTen() {
        return ten;
    }

    public ChuDe(String ten, String url) {
        this.ten = ten;
        this.url = url;
    }

    public void setTen(String ten) {

        this.ten = ten;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
