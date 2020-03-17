/*
 * This work is licensed under a Creative Commons Attribution-NonCommercial 4.0
 * International License by University of Turku, Educational Support Services.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
 */
package testi;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jpeant
 */
public class Testi {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws InterruptedException {
        boolean ottaaLisaa = true;
        boolean jakajaLisaa = true;
        boolean jatketaanko = true;
        boolean pelaajaHavio = false;
        Scanner lukija = new Scanner(System.in); 
        Scanner intLukija = new Scanner(System.in); // int ja string käyttö samassa lukijassa sekoittaa
        Scanner valLukija = new Scanner(System.in);
        
        Tekstit.Alku();
        System.out.print("Kerrohan ensin nimesi: ");
        Pelaaja p = new Pelaaja(lukija.nextLine());
        Jakaja j = new Jakaja();
        
        //looppaa tästä (Jatketaanko)
        while(jatketaanko = true){
            System.out.println("Pelaajalla "+p.annaNimi()+" on " + p.RahaTilanne()+" credittiä.");
            System.out.println("Jakajalla on "+ j.RahaTilanne()+ " credittiä.");
            int luku = 0;
            if(p.RahaTilanne()<100){
                luku = p.RahaTilanne();
            } else {
                luku = 100;
            }
            System.out.print("Aseta panos 2 - "+ luku +": ");
            // Paljonko panostetaan 2 - 100? if raha < 100, max bet on rahatilanne
            // Tarkista että jakajalla on enemmän kuin 200.
            int pa = intLukija.nextInt();
            while(pa>p.RahaTilanne() || pa<2){
                System.out.println("Luku ei ole kelvollinen.");
                System.out.print("Aseta panos 2 - "+ luku +": ");
                pa=intLukija.nextInt();
            }
            p.asetaPanos(pa);

            p.LisaaKortti(new Kortti().annaKortti());
            p.LisaaKortti(new Kortti().annaKortti());
            j.LisaaKortti(new Kortti().annaKortti());

            System.out.println();

            while(true){
            // looppi joka: antaa kortin pelaajalle, näyttää kortit
            // ja näyttää jakajan kortit. samalla testaa menikö yli!!    
            // kunnes pelaaja Jää.
            // Ässä EI toimi!!!!

                pelaajanKasi(p);
                jakajanKasi(j);
                if (p.AnnaArvot()>21 && p.AnnaArvot2()>21){
                    pelaajaHavio = true;
                    break;
                }
                System.out.print("Otatko Lisää (L) vai Jäätkö (J)? ");
         
                while (true) { // hyväksyy vain L/l tai J/j syötteet.
                    String in = valLukija.nextLine();
                    if (in.equalsIgnoreCase("L")) {
                        ottaaLisaa = true;
                        break;
                    } else if (in.equalsIgnoreCase("J")) {
                        ottaaLisaa = false;
                        pelaajaHavio = false;
                        break;
                    } else {
                        System.out.print("Ole hyvä ja näppäile joko L tai J:");
                    }
                }
                if (ottaaLisaa == true){
                    p.LisaaKortti(new Kortti().annaKortti());
                    System.out.println();
                } else {
                    break;
                }
            }

            // seuraava looppi, on jakajan vuoro ottaa kortteja kunnes 17 tai yli
            // Ässä EI toimi
            if(pelaajaHavio == false){
                while(jakajaLisaa = true){
                    if(j.AnnaArvot()==21 || j.AnnaArvot2()==21){
                        break;
                    }
                    if(j.AnnaArvot()<17 || j.AnnaArvot()>21 &&j.AnnaArvot2()<17){
                        j.LisaaKortti(new Kortti().annaKortti());
                        jakajanKasi(j);
                        TimeUnit.SECONDS.sleep(1);
                    }
                    if(j.AnnaArvot()>16 || j.AnnaArvot2()>16){
                        break;
                    }
                }
            }

            // loppuvertailu ja rahojen siirto
            System.out.println("");
            vertailu(p, j);
            
            // tyhjennetään kädet mahdollista seuraavaa peliä varten
            p.tyhjennaKasi();
            j.tyhjennaKasi();

            // Jatketaanko pelaamista
            System.out.println("");
            System.out.print("Lopetatko (X) vai Pelataanko (U)? ");
            String valinta = lukija.nextLine();
            System.out.println("");
            if (valinta.equalsIgnoreCase("X")) {
                break;
            }
        }
        
    
        
        Tekstit.loppu(p); 
        lukija.close();
    }
    
    public static void pelaajanKasi(Pelaaja p){
        System.out.print(p.annaNimi()+", sinun kortit: ");
        p.NaytaKasi();
        if(p.AnnaArvot() == p.AnnaArvot2()){
        System.out.println("Yhteensä: "+ p.AnnaArvot());
        } else {
            System.out.println("Yhteensä: "+ p.AnnaArvot2() +" tai "+ p.AnnaArvot());
        }
    }
    
    public static void jakajanKasi(Jakaja j){
        System.out.print("Jakajalla on: ");
        j.NaytaKasi();
        if(j.AnnaArvot() == j.AnnaArvot2()){
        System.out.println("Yhteensä: "+ j.AnnaArvot());
        } else {
            System.out.println("Yhteensä: "+ j.AnnaArvot2() +" tai "+ j.AnnaArvot());
        }
    }
    
    public static void havio(Pelaaja p, Jakaja j, int pArvo1, int jArvo1){
        System.out.println("Voi voi, nyt tuli häviö tässä pelissä.");
        System.out.println("Sait korttien arvoksi " + pArvo1+".");
        System.out.println("Ja jakajalla oli "+ jArvo1+".");
        p.VahennaRahaa(p.kerroPanos());
        j.LisaaRahaa(p.kerroPanos());
        System.out.println("Menetit "+p.kerroPanos()+" credittiä.");
    }
    
    public static void voitto(Pelaaja p, Jakaja j){
        System.out.println(p.annaNimi()+", sinähän voitit. Onneksi olkoon!");
        j.VahennaRahaa(p.kerroPanos());
        p.LisaaRahaa(p.kerroPanos());
        System.out.println(p.kerroPanos()+" credittiä on lisätty tilillesi.");
    }
    
    public static void vertailu(Pelaaja p, Jakaja j){
        int pArvo1 = p.AnnaArvot();
        int pArvo2 = p.AnnaArvot2();
        int jArvo1 = j.AnnaArvot();
        int jArvo2 = j.AnnaArvot2();
        
        if (pArvo1 >21 && pArvo2 <22){
            pArvo1 = pArvo2;
        }
        
        if (jArvo1 >21 && jArvo2 <22){
            jArvo1 = jArvo2;
        }

        // vertailu korjattu. Täytyy tarkastaa.
        if(pArvo1 > 21){ // häviö jos pelaaja >21
            havio(p, j, pArvo1, jArvo1);
        } 
        else if(jArvo1 >21){ //voitto jos jakaja >21
            voitto(p, j);
        } 
        else if (pArvo1 > jArvo1){ // voitto jos pelaajalla isompi luku
            voitto(p, j);
        }
        else if (pArvo1 < jArvo1){ // häviö jos jakajalla isompi luku
            havio(p, j, pArvo1, jArvo1);
        }
        
        
        else {
            System.out.println("Tasapeli. Panos on palautettu pelaajalle.");
        }
        
    }
}
    