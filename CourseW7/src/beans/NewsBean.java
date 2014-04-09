package beans;

import CRUD.CRUD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class NewsBean
{
    private int newsID;
    private String newstext;
    private String urlvideo;
    private ArrayList<NewsBean> news;
    private ArrayList<String> describes;
    private int number;
    private NewsBean chosenNew;


    public void setChosenNew(NewsBean chosenNew) {
        this.chosenNew = chosenNew;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescribes(ArrayList<String> describes) {
        this.describes = describes;
    }

    public void setNews(ArrayList<NewsBean> news) {
        this.news = news;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getNewstext() {
        return newstext;
    }

    public void setNewstext(String newstext) {
        this.newstext = newstext;
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public void setUrlvideo(String urlvideo) {
        this.urlvideo = urlvideo;
    }

    public ArrayList<NewsBean> getNews()
    {
        CRUD crud = new CRUD();
        news = new ArrayList<NewsBean>();
        news = crud.ReadNews();
        return news;
    }

    public ArrayList<String> getDescribes()
    {
        int i = 0;
        describes = new ArrayList<String>();
        ArrayList<NewsBean> newsBeans;
        newsBeans = getNews();
        NewsBean somenew;
        while (i!=6)
        {
            somenew = newsBeans.get(i);
            describes.add(somenew.newstext.substring(0,somenew.newstext.indexOf('.')));
            i++;
        }
        return describes;
    }

    public String newsRequest(int i)
    {
        setNumber(i);
        return "newsPage.xhtml";

    }

    public NewsBean getChosenNew(int id) throws Exception {
        NewsBean somenew;
        CRUD crud = new CRUD();
        somenew = crud.ReadNew(id);
        setNewstext(somenew.newstext);
        setUrlvideo(somenew.urlvideo);
        return somenew;


    }

}
