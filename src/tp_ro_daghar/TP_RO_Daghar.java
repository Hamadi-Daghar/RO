/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_ro_daghar;
import logic.Algorithmes.AlgoGenetique;
import logic.ListeVille;
import logic.Tournee;
import logic.Algorithmes.AlgoGlouton;
import logic.Algorithmes.AlgoRechercheLocale;
/**
 *
 * @author Hamadi
 */
public class TP_RO_Daghar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ListeVille lVille = new ListeVille("top70.txt");

        System.out.println("LISTE DES VILLES");
        System.out.println(lVille);

        System.out.println("\n----------------------------------\n");
        System.out.println("DISTANCE VILLE 0 VILLE 1");
        System.out.println(lVille.get(0).distance(lVille.get(1))+" KM");

        System.out.println("\n----------------------------------\n");
        System.out.println("AFFICHAGE TOURNEE");
        Tournee tournee = new Tournee(lVille);
        System.out.println(tournee);

        System.out.println("\n----------------------------------\n");
        System.out.println("AFFICHAGE DISTANCE TOTALE TOURNÉE CROISSANTE");
        System.out.println("Coût: " + tournee.cout() + "KM");

        System.out.println("\n----------------------------------\n");
        System.out.println("AFFICHAGE DISTANCE TOTALE TOURNÉE ALÉATOIRE");
        for(int i=0; i<25; i++) {
            ListeVille l = new ListeVille("top70.txt");
            l.shuffle();
            Tournee t = new Tournee(l);
            System.out.println("[" + (i+1) + "] " + t.cout() + " KM");
        }

        System.out.println("\n----------------------------------\n");
        System.out.println("GLOUTON PLUS PROCHE VOISIN");
        AlgoGlouton.setTournee(new Tournee("top70.txt"));
        tournee = AlgoGlouton.plus_proche_voisin(lVille.get(0));
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);

        System.out.println("\n----------------------------------\n");
        System.out.println("GLOUTON INSERTION PROCHE");
        tournee = AlgoGlouton.insertion_proche();
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);

        System.out.println("\n----------------------------------\n");
        System.out.println("GLOUTON INSERTION LOIN");
        tournee = AlgoGlouton.insertion_loin();
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);

        System.out.println("\n----------------------------------\n");
        System.out.println("GLOUTON MEILLEUR PLUS PROCHE");
        tournee = AlgoGlouton.meilleurPlusProche();
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);

        System.out.println("\n----------------------------------\n");
        System.out.println("RECHERCHE LOCALE SUCCESSEUR PREMIER");
        AlgoRechercheLocale.setTournee(AlgoGlouton.plus_proche_voisin(lVille.get(0)));
        tournee = AlgoRechercheLocale.recherche_locale("SUCCESSEUR_PREMS");
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);
        
        System.out.println("\n----------------------------------\n");
        System.out.println("RECHERCHE LOCALE SOMMET PREMIER");
        AlgoRechercheLocale.setTournee(AlgoGlouton.plus_proche_voisin(lVille.get(0)));
        tournee = AlgoRechercheLocale.recherche_locale("SOMMET_PREMS");
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);
        
        System.out.println("\n----------------------------------\n");
        System.out.println("RECHERCHE LOCALE 2OPT PREMIER");
        AlgoRechercheLocale.setTournee(AlgoGlouton.plus_proche_voisin(lVille.get(0)));
        tournee = AlgoRechercheLocale.recherche_locale("2OPT_PREMS");
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);
        
        System.out.println("\n----------------------------------\n");
        System.out.println("MA SUPER RECHERCHE LOCALE");
        AlgoRechercheLocale.setTournee(AlgoGlouton.plus_proche_voisin(lVille.get(0)));
        tournee = AlgoRechercheLocale.ma_super_recherche_locale();
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);
      
        System.out.println("\n----------------------------------\n");
        System.out.println("RECHERCHE GENETIQUE");
        AlgoGenetique.setTournee(tournee);
        tournee = AlgoGenetique.recherche_genetique();
        System.out.println("Coût: " + tournee.cout() + "KM");
        System.out.println(tournee);
        System.out.println(tournee.size());
    }
    
}
