package logic.Algorithmes;

import java.util.LinkedHashMap;

import logic.Tournee;
import logic.Ville;

public class AlgoGlouton {
    private static Tournee tournee = null;
    private static LinkedHashMap<Ville, Boolean> visite;
    public static void setTournee(Tournee t) { tournee = t; }

    public static Tournee plus_proche_voisin(Ville v) throws Exception {
        Tournee newT = new Tournee();
        
        if (execute()) {
            visite = new LinkedHashMap<>();
            for(Ville ville : tournee.getListe())
                visite.put(ville, false);
            newT.add(v);
            visite.put(v, true);
            while (visite.containsValue(false)) {
                Ville suivant = plus_proche(v);

                visite.put(suivant, true);
                newT.add(suivant);
                v = suivant;
            }
            visite = null;
        } else {
            throw new Exception("[AlgoGlouton] tournee is not initialized.");
        }

        return newT;
    }  

    private static Ville plus_proche(Ville v) {
        Ville plusProche = new Ville();
        double dist = Double.MAX_VALUE;

        for(Ville ville : visite.keySet()) {
            double newDist = ville.distance(v);
            if(newDist < dist && !visite.get(ville)) {
                plusProche = ville;
                dist = newDist;
            }
        }

        return plusProche;
    }


    public static Tournee insertion_proche() throws Exception {
        Tournee newT = new Tournee();
        
        if (execute()) {
            visite = new LinkedHashMap<>();
            for(Ville ville : tournee.getListe())
                visite.put(ville, false);
            Ville[] deux_plus_eloignee = deux_plus_eloignee();
            newT.add(deux_plus_eloignee[0]);
            newT.add(deux_plus_eloignee[1]);
            visite.put(deux_plus_eloignee[0], true);
            visite.put(deux_plus_eloignee[1], true);
            while (visite.containsValue(false)) {
                // Retourne la ville a ajouté et la ville après cette dernière
                Ville[] suivant = plus_proche_tournee(newT);

                newT.insert(suivant[0], newT.getIndex(suivant[1]));
                visite.put(suivant[0], true);
            }

            visite = null;
        } else {
            throw new Exception("[AlgoGlouton] tournee is not initialized.");
        }

        return newT;
    }

    private static Ville[] deux_plus_eloignee() {
        Ville[] plusEloignes = new Ville[2];
        double dist = Double.MIN_VALUE;

        for(int i = 0; i < tournee.size(); i++) {
            for(int j = 0; j < tournee.size(); j++) {
                double cout = tournee.get(i).distance(tournee.get(j));
                if (cout > dist) {
                    dist = cout;
                    plusEloignes[0] = tournee.get(i);
                    plusEloignes[1] = tournee.get(j);
                }
            }
        }

        return plusEloignes;
    }

    private static Ville[] plus_proche_tournee(Tournee t) {
        Ville[] plusProche = new Ville[2];
        double dist = Double.MAX_VALUE;

        for (Ville v : tournee.getListe()) {
            for (int i = 0; i < t.size(); i++) {
                double cout = t.get(i).distance(v) + t.get(i+1).distance(v) - t.get(i).distance(t.get(i+1));

                if(cout < dist && !visite.get(v)) {
                    dist = cout;
                    plusProche[0] = v;
                    plusProche[1] = t.get(i+1);
                }
            }
        }

        return plusProche;
    }


    public static Tournee insertion_loin() throws Exception {
        Tournee newT = new Tournee();
        
        if (execute()) {
            visite = new LinkedHashMap<>();
            for(Ville ville : tournee.getListe())
                visite.put(ville, false);
            Ville[] deux_plus_eloignee = deux_plus_eloignee();
            newT.add(deux_plus_eloignee[0]);
            newT.add(deux_plus_eloignee[1]);
            visite.put(deux_plus_eloignee[0], true);
            visite.put(deux_plus_eloignee[1], true);
            while (visite.containsValue(false)) {
                // Retourne la ville a ajouté et la ville après cette dernière
                Ville[] suivant = plus_eloigne_tournee(newT);

                if(suivant[0].getNomVille().equals("MARCILLY-SUR-TILLE"))
                    System.out.println(suivant[0].getNomVille());
                newT.insert(suivant[0], newT.getIndex(suivant[1]));
                visite.put(suivant[0], true);
            }

            visite = null;
        } else {
            throw new Exception("[AlgoGlouton] tournee is not initialized.");
        }

        return newT;
    }

    private static Ville[] plus_eloigne_tournee(Tournee t) {
        Ville[] plusEloigne = new Ville[2];
        double distPlusEloigne = -1.0;
        
        
        for (Ville v : tournee.getListe()) {
            if(!visite.get(v)) {
                Ville[] plusProche = new Ville[2];
                double dist = Double.MAX_VALUE;
                for (int i = 0; i < t.size(); i++) {
                    double cout = t.get(i).distance(v) + t.get(i+1).distance(v) - t.get(i).distance(t.get(i+1));

                    if(cout < dist) {
                        dist = cout;
                        plusProche[0] = v;
                        plusProche[1] = t.get(i+1);
                    }
                }
                if(distPlusEloigne < dist) {
                    distPlusEloigne = dist;
                    plusEloigne = plusProche.clone();
                }
            }
        }

        return plusEloigne;
    }


    public static Tournee meilleurPlusProche() throws Exception {
        Tournee t_courant = tournee;

        for (Ville v : tournee.getListe()) {
            Tournee t_voisin = plus_proche_voisin(v);
            if(t_voisin.cout() < t_courant.cout())
                t_courant = t_voisin;
        }

        return t_courant;
    }


    private static boolean execute() {
        return tournee != null;
    }
}
