/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.Algorithmes;

import logic.Tournee;
import logic.Ville;

/**
 *
 * @author Hamadi
 */
public class AlgoTabou {
    private static Tournee tournee = null;
    
    public static void setTournee(Tournee t) { tournee = t;}
    
    public static Tournee tabou(Tournee t){
        Tournee t_best = null;
        Tournee voisin = t;
        Ville bestI = null;
        int N = t.size();
        Ville bestJ = null;
        Double meilleurCout = t.cout();
        Double meilleurGain = null;
        Double gain;
        int nb_ite = 0;
        
        while(nb_ite < 1000){
            nb_ite++;
            meilleurGain = (double)-100000000;
            for(int i = 0;i<N+1;i++){
                for(int j = i+2;j<N+1;j++){
                    if(i<j){
                        
                        gain = t.get(i%N).distance(t.get((i+1)%N)) + t.get(j%N).distance(t.get((j+1)%N)) - t.get(i%N).distance(t.get(j%N)) - t.get((i+1)%N).distance(t.get((j+1)%N)) ;
                        if(gain > meilleurGain){
                            meilleurGain = gain;
                            bestI = t.get(i);
                            bestJ = t.get(j);
                        }
                    }
                }
            }
            //voisin = voisin.echanger(t.get((bestI)%N), t.get((bestJ)%N));
        }
        return t_best;
    }
}
