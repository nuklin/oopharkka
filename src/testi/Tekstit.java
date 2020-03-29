/*
 * This work is licensed under a Creative Commons Attribution-NonCommercial 4.0
 * International License by University of Turku, Educational Support Services.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
 */
package testi;

/**
 *
 * @author jpeant
 */
public class Tekstit {
    public static void Alku(){
        System.out.println("********************************************************");
        System.out.println("Tämä Blackjack-peli on Turun Yliopiston OOP harjoitustyö.");
        System.out.println("Normaalit säännöt pätevät. Jakaja jää lukuun >= 17.");
        System.out.println("Double ja Split toiminnot EIVÄT ole käytössä.");
        System.out.println("********************************************************");
    }
    
    public static void loppu(Pelaaja p){
        System.out.println("********************************************************");
        System.out.println("           Kiitos, että pelasit peliämme. :)");
        System.out.println(  p.annaNimi()+", sinulle jäi pelaamisen jälkeen " + p.RahaTilanne()+" credittiä.");
        System.out.println("         ---< Pelaa maltilla - Veikkaus >---");
        System.out.println("********************************************************");
    }
    
    public static void taloRahaton(Pelaaja p){
        System.out.println("********************************************************");
        System.out.println("      Onnittelut olet tyhjentänyt kasinomme emmekä enää kykene jatkamaan pelaamista");
        System.out.println(      p.annaNimi()+" sinulla on "+p.RahaTilanne()+" crediittiä");
        System.out.println("      Voitokasta päivän jatkoa");
        System.out.println("********************************************************");
    }
}
