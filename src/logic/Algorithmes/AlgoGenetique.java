/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.Algorithmes;

import java.util.ArrayList;
import java.util.Collection;
import logic.Tournee;
import logic.Ville;

/**
 *
 * @author Hamadi
 */
public class AlgoGenetique {
    private static Tournee tournee = null;
    
    public static void setTournee(Tournee t) { tournee = t;}
    
    public static Tournee recherche_genetique() {
        ArrayList<Tournee> listTournee = new ArrayList<Tournee>();
        ArrayList<Tournee> listEnfants = new ArrayList<Tournee>();//enfant avec création par croisement des parents
        ArrayList<Tournee> listParents = new ArrayList<Tournee>();//liste parents avec tournée au hasard
        ArrayList<Tournee> listEnfantsBest = new ArrayList<Tournee>();//10 enfants best + 10 hasard
        ArrayList<Tournee> listEnfants2opt = new ArrayList<Tournee>();//liste tournée avec 2opt
        Tournee t_best = null;
        double cout_best = Double.MAX_VALUE;
        int X = 800;
        int N = 400;
        int Y = 20;
        int tmp = 0;
        int pointCroisement = 10;
        int nb_max = 50;
        for(int i =0; i != X;i++){
            tournee.shuffle();
            Tournee t1 = tournee.copy();
            listTournee.add(t1);
        }
        ArrayList<Tournee> listTourneeClone = (ArrayList<Tournee>) listTournee.clone();
        int generation = 1;
        while(generation < nb_max)
        {
            System.out.println(" après ajout 2opt"+listTourneeClone.size());
            for(int i =0;i < N/2 ;i++){//on récupere 200 meilleurs tournée
                Tournee t = meilleurTournee(listTourneeClone);
                if(t.size() != 70)
                    System.out.println("probleme");
                listParents.add(t);//on ajoute
                listTourneeClone.remove(t);//on retire dans la liste celui qu'on vient d'ajouter
            }
            for(int i =0;i < N/2;i++){//on récupere 200 tournée au hasard
                listParents.add(listTourneeClone.get(i));//on ajoute
                listTourneeClone.remove(i);//on retire dans la liste celui qu'on vient d'ajouter
            }
            for(int i =0;i<N/2;i++){//on fait le croisement des 400 parents
                ArrayList<Tournee> temp = null;
                int nbAlea = (int)(Math.random() * ((399 - 0) + 1));
                int nbAlea2 = (int)(Math.random() * ((399 - 0) + 1));
                temp = croisement(listParents.get(nbAlea).copy(), listParents.get(nbAlea2).copy(), pointCroisement);
                listEnfants.add(temp.get(0));
                listEnfants.add(temp.get(1));
            }
            for(int i=0;i<15;i++){
                listEnfantsBest.add(meilleurTournee(listEnfants));//récuperer 10 meilleurs enfant
                listEnfants.remove(meilleurTournee(listEnfants));
            }
            for(int i=0;i<10;i++){
                listEnfantsBest.add(listEnfants.get(i));//récuperer 10 enfant au hasard
                
                listEnfants.remove(listEnfants.get(i));
            }
            for(int i =0;i<Y;i++){
                AlgoRechercheLocale.setTournee(listEnfantsBest.get(i));
                listEnfants2opt.add(AlgoRechercheLocale.recherche_locale("2OPT_PREMS"));
            }
            AlgoRechercheLocale.setTournee(meilleurTournee(listEnfants2opt));
            System.out.println("Cout de la meilleure : "+AlgoRechercheLocale.ma_super_recherche_locale().cout());
            if(cout_best == AlgoRechercheLocale.ma_super_recherche_locale().cout()){
                tmp++;  
                System.out.println("incrementation "+tmp);
            }
            if(cout_best > AlgoRechercheLocale.ma_super_recherche_locale().cout()){
                t_best = AlgoRechercheLocale.ma_super_recherche_locale();
                cout_best = AlgoRechercheLocale.ma_super_recherche_locale().cout();
                System.out.println(t_best);
                System.out.println("Meilleur trouvé ! ");
            }
            listTourneeClone.clear();
            listTourneeClone = (ArrayList<Tournee>) listEnfants2opt.clone();
            listTourneeClone.addAll((Collection<? extends Tournee>) listEnfantsBest.clone());
            AlgoRechercheLocale.setTournee(meilleurTournee(listEnfants2opt));
            if(tmp == 3){
                tmp =0;
                System.out.println("non ajout");
            }
            else{
                listTourneeClone.add(AlgoRechercheLocale.ma_super_recherche_locale().copy());
                System.out.println("Test bon ajout");
            }
            System.out.println(listTourneeClone.size());
            while(listTourneeClone.size() != 800){
                int nbAlea = (int)(Math.random() * ((1 - 0) + 1));
                int nbAleaParents = (int)(Math.random() * ((listParents.size()-1 - 0) + 1));
                int nbAleaEnfants = (int)(Math.random() * ((listEnfants.size()-1 - 0) + 1));
                if(nbAlea == 0)
                    listTourneeClone.add(listParents.get(nbAleaParents).copy());
                else
                    listTourneeClone.add(listEnfants.get(nbAleaEnfants).copy());
            }
            System.out.println("Taille liste tournée après ajout "+listTourneeClone.size());
            generation++;
            listParents.clear();
            listEnfants.clear();
            listEnfantsBest.clear();
            listEnfants2opt.clear();
            System.out.println("Nombre de génération : "+generation);
       }
       return t_best;
    }
    
    public static Tournee meilleurTournee(ArrayList<Tournee> listTournee){
        Tournee best = null;
        double temp = Double.MAX_VALUE;
        for(Tournee t : listTournee)
            if(t.cout() < temp){
                best = t;
                temp = t.cout();
            }
        return best;
    }
    
    public static ArrayList<Tournee> croisement(Tournee p1, Tournee p2,int point){
        ArrayList<Tournee> croisement = new ArrayList<>();
        Tournee e1 = new Tournee();
        Tournee e2 = new Tournee();
        for(int i=0;i<point;i++){
            e1.add(p1.get(i));
        }
        for(int i=0;i<point;i++){
            e2.add(p2.get(i));
        }
        for(int i=point; i<p2.size();i++){
            if(e1.getIndex(p2.get(i)) == -1){
                e1.add(p2.get(i));
            }
        }
        for(int i=point; i<p1.size();i++){
            if(e2.getIndex(p1.get(i)) == -1){
                e2.add(p1.get(i));
            }
        }
        for(int i=0; i<p1.size();i++){
            if(e1.getIndex(p1.get(i)) == -1){
                e1.add(p1.get(i));
            }
        }
        for(int i=0; i<p2.size();i++){
            if(e2.getIndex(p2.get(i)) == -1){
                e2.add(p2.get(i));
            }
        }
        croisement.add(e1);
        croisement.add(e2);
        return croisement;
    }
}
