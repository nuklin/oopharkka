/*
 * This work is licensed under a Creative Commons Attribution-NonCommercial 4.0
 * International License by University of Turku, Educational Support Services.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
 */

package testi;
import java.io.*; 
import java.util.*; 

/**
 *
 * @author jpeant
 */

public class Pelaaja {
    int raha;
    int panos;
    String nimi;
    ArrayList<Kortti> kasi = new ArrayList<Kortti>();
    
    public Pelaaja(String nimi){
        if(nimi.length()<1){
            this.nimi = "Nimetön";
        } else {
            this.nimi = nimi;
        }
        this.raha = 100;
    }
    
    public Pelaaja(){
        
    }

    public void LisaaRahaa(int luku){
        this.raha = raha + luku;
    }
    
    public void VahennaRahaa(int luku){
        this.raha = raha - luku;
    }
    
    public int RahaTilanne(){
        return raha;
    }
    
    public void LisaaKortti(Kortti akortti){
        kasi.add(akortti);
    }
  
    public void NaytaKasi(){
        for(int i =0; i<kasi.size(); i++){
            System.out.print(kasi.get(i).annaNimi()+" ");
        }
    }
    
    public int AnnaArvot(){
        int summa =0;
        for(int i = 0; i < kasi.size(); i++){
            summa = summa +(kasi.get(i).annaArvo());
        }
        return summa;
    }

    public int AnnaArvot2(){
        int summa =0;
        for(int i = 0; i < kasi.size(); i++){
            summa = summa +(kasi.get(i).annaArvo2());
        }
        return summa;
    }
    
    public String annaNimi(){
        return nimi;
    }
    
    public void tyhjennaKasi(){
        this.kasi.clear();
    }
    
    public void LisaaNimi(String nimi){
        if(nimi.length()<1){
            this.nimi = "Nimetön";
        } else {
            this.nimi = nimi;
        }
    }
    
    public void asetaPanos(int panos){
        this.panos = panos;
    }
    
    public int kerroPanos(){
        return panos;
    }
}

class Jakaja extends Pelaaja{
    public Jakaja(){
        this.raha = 600;
    }
}
