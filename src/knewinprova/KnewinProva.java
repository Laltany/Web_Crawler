/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knewinprova;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author x_rom
 */
public class KnewinProva {

       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try{
           boolean flag = true;
           int n_url = 1;
           int count = 0;
           
           /* 5 primeiros da página de mercados  */
           
           while(flag==true){                              
               String n = Integer.toString(n_url);
               final String url = "https://www.infomoney.com.br/ultimas-noticias/feed/?paged=" + n;
               Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
               org.jsoup.select.Elements news = doc.select("item");
               for(Element e: news){
                   if(count<5){
                        org.jsoup.select.Elements category = e.select("category");
                        for(Element categoria: category){
                            if("Mercados".equals(categoria.text())){
                                 org.jsoup.select.Elements link = e.select("link");
                                 org.jsoup.select.Elements title = e.select("title");
                                 org.jsoup.select.Elements subtitle_ = e.select("description");
                                 org.jsoup.select.Elements creator = e.getElementsByTag("dc:creator");
                                 org.jsoup.select.Elements datetime_ = e.getElementsByTag("pubDate");
                                 org.jsoup.select.Elements article_ = e.getElementsByTag("content:encoded");

                                 /* parse subtitle */
                                 String subtitle_temp = subtitle_.text();
                                 Document doc_subtitle = Jsoup.parse(subtitle_temp);
                                 org.jsoup.select.Elements subtitle = doc_subtitle.select("p");

                                 /* parse article_ */

                                 String article_text = article_.text();
                                 Document doc_article = Jsoup.parse(article_text);
                                 org.jsoup.select.Elements article_body = doc_article.select("p");
                                 String article = article_body.text();
                                 article = article.replace(" appeared first on InfoMoney", "");

                                 /* parse e conversão datetime*/
                                 String datetime_temp = datetime_.text();
                                 String[] list_datetime = datetime_temp.split(",");
                                 String date = list_datetime[1].replaceFirst(" ","");
                                 /*lempeza date*/
                                 date = date.replaceFirst(" ","/");
                                 date = date.replaceFirst(" ","/");
                                 date = date.replace(" +0000","");
                                 int tamanho = date.length();
                                 date = date.substring(0, tamanho-3);

                                 /* imprimir */
                                 System.out.println("link: " + link.text());
                                 System.out.println("Titulo: " + title.text());
                                 System.out.println("Subtitulo: " + (subtitle.first()).text());
                                 System.out.println("Autor: " + creator.text());
                                 System.out.println("Data de Publicação: " + date);
                                 System.out.println("Noticia: " + article);
                                 System.out.println("---------------------------");

                                 count++;                                         

                            }                      

                        }
                   }else if(count==5){
                    flag = false;
                   }
                   
               }               
                n_url++;
               
           }
           
           /* 3 paginas do feed */
           for(int i = 1; i <= 3; ++i){ //paginação: i representa a pagina
            String n = Integer.toString(i);
            final String url = "https://www.infomoney.com.br/mercados/feed/?paged=" + n; // adiciona pagina no link
           // Document doc = Jsoup.connect(url).get();
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
            org.jsoup.select.Elements news = doc.select("item");
            for(Element e: news){
                org.jsoup.select.Elements link = e.select("link");
                org.jsoup.select.Elements title = e.select("title");
                org.jsoup.select.Elements subtitle_ = e.select("description");
                org.jsoup.select.Elements creator = e.getElementsByTag("dc:creator");
                org.jsoup.select.Elements datetime_ = e.getElementsByTag("pubDate");
                org.jsoup.select.Elements article_ = e.getElementsByTag("content:encoded");

                /* parse subtitle */
                String subtitle_temp = subtitle_.text();
                Document doc_subtitle = Jsoup.parse(subtitle_temp);
                org.jsoup.select.Elements subtitle = doc_subtitle.select("p");

                /* parse article_ */

                String article_text = article_.text();
                Document doc_article = Jsoup.parse(article_text);
                org.jsoup.select.Elements article_body = doc_article.select("p");
                String article = article_body.text();
                article = article.replace(" appeared first on InfoMoney", "");


                /* parse e conversão datetime*/
                String datetime_temp = datetime_.text();
                String[] list_datetime = datetime_temp.split(",");
                String date = list_datetime[1].replaceFirst(" ","");
                /*lempeza date*/
                date = date.replaceFirst(" ","/");
                date = date.replaceFirst(" ","/");
                date = date.replace(" +0000","");
                int tamanho = date.length();
                date = date.substring(0, tamanho-3);


                /* imprimir */
                System.out.println("link: " + link.text());
                System.out.println("Titulo: " + title.text());
                System.out.println("Subtitulo: " + (subtitle.first()).text());
                System.out.println("Autor: " + creator.text());
                System.out.println("Data de Publicação: " + date);
                System.out.println("Noticia: " + article);
                System.out.println("---------------------------");
            }
           }
       }catch(IOException ex){
           Logger.getLogger(KnewinProva.class.getName()).log(Level.SEVERE, null, ex);
       }
        // TODO code application logic here
    }
    
}
