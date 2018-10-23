
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
public class PhanTichHTML {
    private String urlMain = "http://dantri.com.vn";
    public ArrayList<ChuDe> getListChuDe(){
        ArrayList<ChuDe> list = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(this.urlMain).get();
            Elements e = doc.getElementsByClass("nav");
            for(Element i : e){
                Elements tagLi=i.getElementsByTag("li");
                for(int j=2;j<tagLi.size();j++){
                    Element tagA = tagLi.get(j).child(0);
                    String urlChuDe=tagA.attr("href");
                    if(!urlChuDe.contains("dantri")){
                        urlChuDe=this.urlMain+urlChuDe;
                    }
                    System.out.println(urlChuDe);
                    String tenChuDe = tagA.attr("title");
                    System.out.println(tenChuDe);
                    ChuDe chuDe = new ChuDe(tenChuDe,urlChuDe);
                    list.add(chuDe);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<String> getListUrlTinTuc(String urlChuDe){
        ArrayList<String> list = new ArrayList<>();
        try {
            System.out.println(urlChuDe);
            Document doc = Jsoup.connect(urlChuDe).timeout(30*1000).get();
            Element e = doc.getElementById("listcheckepl");
            if(e != null) {
                for (Element i : e.children()) {
                    if (i.children().size() > 0) {
                        Element tagA = i.child(0);
                        String urlTemp = "http://dantri.com.vn" + tagA.attr("href");
                        TinTuc tinTucTemp = this.getTinTuc(urlTemp);
                        Database db = new Database();
                        db.insert(tinTucTemp);
                    }

                }
            }
            Elements tagAURLTrangSau=doc.getElementsByClass("fon27 mt1 mr2");
            //System.out.println(tagAURLTrangSau.first());
            if(tagAURLTrangSau.size()>=1 && tagAURLTrangSau.get(0).text().equals("[ Trang sau ]") ){
                String urlTrangSau=tagAURLTrangSau.get(0).attr("href");
                this.getListUrlTinTuc("http://dantri.com.vn"+urlTrangSau);
            }else if(tagAURLTrangSau.size()==2){
                String urlTrangSau=tagAURLTrangSau.get(1).attr("href");
                this.getListUrlTinTuc("http://dantri.com.vn"+urlTrangSau);
            }

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return list;
    }
    public TinTuc getTinTuc(String urlTinTuc){
        TinTuc tinTuc =  new TinTuc();
        try{
            System.out.println(urlTinTuc);
            Document doc = Jsoup.connect(urlTinTuc).get();
            String chuDe = urlTinTuc.substring(urlTinTuc.indexOf("/",8)+1,urlTinTuc.indexOf("/",22));
            tinTuc.setChuDe(chuDe);
            Element e = doc.getElementById("ctl00_IDContent_ctl00_divContent");
            if(e!=null) {
                Element tagH = e.getElementsByTag("h1").get(0);
                String title = "";
                if (tagH.getElementsByTag("a").size() > 0) {
                    title = tagH.getElementsByTag("a").get(0).text();
                } else {
                    title = e.getElementsByTag("h1").get(0).text();
                }
                tinTuc.setTitle(title);

                StringBuffer content = new StringBuffer();
                Elements tagP = e.getElementsByTag("p");
                for (Element i : tagP) {
                    content.append(i.text());
                }
                tinTuc.setContent(content.toString().replace("'", "`").replace("\"","`"));
                tinTuc.setUrl(urlTinTuc);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return tinTuc;
    }



    public static void main(String[] args) {
          new PhanTichHTML().getListUrlTinTuc("http://dantri.com.vn/su-kien.htm");


    }

}
