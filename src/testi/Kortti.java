/*
 * This work is licensed under a Creative Commons Attribution-NonCommercial 4.0
 * International License by University of Turku, Educational Support Services.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
 */
package testi;
import java.util.Random;

/**
 *  
 * @author jpeant
 */
public class Kortti {
    String[] maat = {"Pata","Hertta","Risti","Ruutu"};
    String[] nimet = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    int arvo;
    String nimi;
    int valearvo;

    public Kortti(String nimi, int arvo, int valearvo) {
  	this.nimi=nimi;
  	this.arvo=arvo;
  	this.valearvo=valearvo;
    }
    
    public Kortti(){
        
    }
    
    public Kortti annaKortti(){
        /*  Arpoo Kortti oliolle (string nimi, int arvo, int valeArvo)
            nimi muodostuu ["maa" "arvo"]
            arvo on kortin arvo (esim Q = 10, 2 = 2)
            valeArvo on assan toinen arvo 1
        **/
        Random r = new Random();
        String m = maat[r.nextInt(4)];
        int ran = r.nextInt(13);
        String n = nimet[ran];
        int arvo = -1;
        int arvo2 = -1;

        if(ran == 0){
            arvo = 11;
            arvo2 = 1;
        }
        else if(ran >= 1 && ran <= 9){
            arvo = ran +1;
            arvo2 = arvo;
        } else{
            arvo = 10;
            arvo2 = 10;
        }
        String ni = "["+m +" "+n+"]";
        Kortti k = new Kortti(ni, arvo, arvo2);
        return k;
    }
    
    public int annaArvo() {
        return arvo;
    }
    public int annaArvo2(){
        return valearvo;
    }
    
    public String annaNimi() {
        return nimi;
    }
    public String toString(){
        return this.nimi+" "+this.arvo+" "+this.valearvo;
    }
}
