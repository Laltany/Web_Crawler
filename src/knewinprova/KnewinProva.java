/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knewinprova;

import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author x_rom
 */
public class KnewinProva {       
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        for(int i = 1; i <= 3; ++i){
            Crawler page_mercado = new Crawler();
            Elements news = page_mercado.acess(false, i);
            for(Element e: news){
                /** Set **/
                page_mercado.setLink(e);
                page_mercado.setTitle(e);
                page_mercado.setCreator(e);
                page_mercado.setDate(e);
                page_mercado.setArticle(e);
                
                /** Show informations **/
                page_mercado.showLink();
                
                page_mercado.showTitle();
                page_mercado.showCreator();
                page_mercado.showDate();               
                page_mercado.showArticle();
                
                System.out.println("------------------------------ ");
            }
            
        }
        
        /* Noticias em destaque  */
        System.out.println("----------------Noticias em destaque-------------- ");
        boolean flag = true;
        int n_url = 1;
        int count = 0;
        
        while(flag==true){ 
            Crawler page_principal = new Crawler();
            Elements news = page_principal.acess(true, n_url);
            for(Element e: news){
                if(count<5){
                    org.jsoup.select.Elements category = e.select("category");
                    for(Element categoria: category){
                        if("Mercados".equals(categoria.text())){
                            /** Set **/
                            page_principal.setLink(e);
                            page_principal.setTitle(e);
                            page_principal.setCreator(e);
                            page_principal.setDate(e);                            
                            page_principal.setArticle(e);

                            /** Show informations **/
                            page_principal.showLink();
                            page_principal.showTitle();
                            page_principal.showCreator();
                            page_principal.showDate();
                            page_principal.showArticle();
                            System.out.println("------------------------------ ");
                            count++;
                        }
                    }
                }else if(count==5){flag = false;}
            }n_url++;
        }

    }
    
}
