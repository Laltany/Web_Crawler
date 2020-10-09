/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knewinprova;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
/**
 *
 * @author x_rom
 */
public class Crawler {
    private String link;
    private String title;
    private String subtitle;
    private String description;
    private String creator;
    private String date;
    private String article;
    
    public Elements acess(boolean type, int n_url) throws MalformedURLException, IOException{        
        org.jsoup.select.Elements news = null;
        String n = Integer.toString(n_url);
        if (type){                        
            final String url = "https://www.infomoney.com.br/ultimas-noticias/feed/?paged=" + n;
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
            news = doc.select("item");          
        }else{
            final String url = "https://www.infomoney.com.br/mercados/feed/?paged=" + n; // adiciona pagina no link
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
            news = doc.select("item");
        }
        
        return news; 
    }
    
    public void setLink(Element e){  
        String link_ = e.select("link").text();
        this.link = link_;
    }
    
    public void showLink(){
         System.out.println("Link da noticia: " + this.link);
    }
    
    public void setTitle(Element e){  
        String title_ = e.select("title").text();
        this.title = title_;
    }
    
    public void showTitle(){
        System.out.println("Título: " + this.title);
    }
    
    public void setSubtitle(Element e){  
        String subtitle_temp = e.select("description").text();
        Document doc_subtitle = Jsoup.parse(subtitle_temp);
        String subtitle_ = doc_subtitle.select("p").text();
        this.subtitle = subtitle_;
    }
    
    public void showSubtitle(){
        System.out.println("Subtitulo: " + this.subtitle);
    }
    
    public void setCreator(Element e){  
        String creator_ = e.getElementsByTag("dc:creator").text();
        this.creator = creator_;
    }
    
    public void showCreator(){
        System.out.println("Autor: " + this.creator);
    }
    
    
    public void setDate(Element e){  
        String date_temp = e.getElementsByTag("pubDate").text();
        String[] list_datetime = date_temp.split(",");
        String date_ = list_datetime[1].replaceFirst(" ","");
        /*lempeza date*/
        date_ = date_.replaceFirst(" ","/");
        date_ = date_.replaceFirst(" ","/");
        date_ = date_.replace(" +0000","");
        int tamanho = date_.length();
        date_ = date_.substring(0, tamanho-3);
        this.date = date_;
    }
    
    public void showDate(){
        System.out.println("Data de publicação: " + this.date);
    }
    
    public void setArticle(Element e){  
        String article_temp = e.getElementsByTag("content:encoded").text();
        Document doc_article = Jsoup.parse(article_temp);
        org.jsoup.select.Elements article_body = doc_article.select("p");
        String article_ = article_body.text();
        article_ = article_.replace(" appeared first on InfoMoney", "");
        this.article = article_;
    }
    
    public void showArticle(){
        System.out.println("Artigo: " + this.article);
    }

   
    
    
}
