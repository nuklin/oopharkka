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
        
        Intro.Alku();
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
            System.out.print("Aseta panos 2 -"+ luku +": ");
            // Paljonko panostetaan 2 - 100? if raha < 100, max bet on rahatilanne
            // Korjaa ylisuuri panos!!!!!!!!!
            // Tarkista että jakajalla on enemmän kuin 200.
            int pa = intLukija.nextInt();
            while(pa>p.RahaTilanne() && pa>2){
                System.out.println("Sinulla ei ole sellaista määrää.");
                System.out.print("Aseta panos 2 -"+ luku +": ");
                pa=intLukija.nextInt();
            }
            p.asetaPanos(pa);

            p.LisaaKortti(new Kortti().annaKortti());
            p.LisaaKortti(new Kortti().annaKortti());
            j.LisaaKortti(new Kortti().annaKortti());

            System.out.println();

            while(ottaaLisaa = true){
            // tähän looppi joka: antaa kortin pelaajalle, näyttää kortit
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
                String valinta = lukija.nextLine();
                if (valinta.equalsIgnoreCase("J")) {
                    pelaajaHavio = false;
                    break;
                }
                p.LisaaKortti(new Kortti().annaKortti());
                System.out.println();
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
            System.out.print("Lopetatko (L) vai Pelataanko (P)? ");
            String valinta = lukija.nextLine();
            System.out.println("");
            if (valinta.equalsIgnoreCase("L")) {
                break;
            }
        }
        
    
        
        Intro.loppu(p); 
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
    
    public static void havio(Pelaaja p, Jakaja j){
        System.out.println("Voi voi, nyt tuli häviö tässä pelissä.");
        System.out.println("Sait korttien arvoksi " + p.AnnaArvot2()+".");
        System.out.println("Ja jakajalla oli "+ j.AnnaArvot2()+".");
    }
    
    public static void vertailu(Pelaaja p, Jakaja j){
        
        // vertailu ei toimi niin kuin pitäisi. Menee uusiksi.
        if(p.AnnaArvot()<22 && p.AnnaArvot()>j.AnnaArvot()
                || p.AnnaArvot()<22 && p.AnnaArvot() >j.AnnaArvot2()
                || p.AnnaArvot2()<22 && p.AnnaArvot2() >j.AnnaArvot()
                || p.AnnaArvot2()<22 && p.AnnaArvot2() >j.AnnaArvot2()
                || j.AnnaArvot()>21 && j.AnnaArvot2()>21)
                {
            System.out.println(p.annaNimi()+", sinähän voitit. Onneksi olkoon!");
            j.VahennaRahaa(p.kerroPanos());
            p.LisaaRahaa(p.kerroPanos());
            System.out.println(p.kerroPanos()+" credittiä on lisätty tilillesi.");
        } 
        else if(j.AnnaArvot2()<22 && j.AnnaArvot2() > p.AnnaArvot2() || p.AnnaArvot2()>21){
            havio(p, j);
            p.VahennaRahaa(p.kerroPanos());
            j.LisaaRahaa(p.kerroPanos());
            System.out.println("Menetit "+p.kerroPanos()+" credittiä.");
        } else {
            System.out.println("Tasapeli. Panos on palautettu pelaajalle.");
        }
        
    }
}
    