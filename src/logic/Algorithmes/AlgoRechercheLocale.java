package logic.Algorithmes;

import logic.Tournee;

public class AlgoRechercheLocale {
    
    private static Tournee tournee = null;
    public static void setTournee(Tournee t) { tournee = t; }

    public static Tournee recherche_locale(String type) {
        Tournee t_courante = tournee;
        boolean fini = false;

        while (!fini) {
            fini = true;
            Tournee t_voisin = null;
            switch(type) {
                case "SUCCESSEUR_PREMS":
                    t_voisin = successeurs_premier_d_abord(t_courante.copy());
                    break;
                case "SOMMET_PREMS":
                    t_voisin = sommet_premier_d_abord(t_courante.copy());
                    break;
                case "2OPT_PREMS":
                    t_voisin = _2opt_premier_d_abord(t_courante.copy());
                    break;
            }

            if(t_voisin.cout() < t_courante.cout()) {
                fini = false;
                t_courante = t_voisin.copy();
            }
        }

        return t_courante;
    }
    
    public static Tournee ma_super_recherche_locale() {
        Tournee t_courante = tournee;
        boolean continuer = true;
        Tournee t_meilleur = tournee;
        while (continuer) {
            continuer = false;
            t_courante = sommet_premier_d_abord(t_courante.copy());
            t_courante = _2opt_premier_d_abord(t_courante.copy());
            if(t_meilleur.cout() > t_courante.cout()) {
                continuer = true;
                t_meilleur = t_courante.copy();
            }
        }

        return t_meilleur;
    }
    
    private static Tournee successeurs_premier_d_abord(Tournee t_courante) {
        for (int i = 0; i < t_courante.size(); i++) {
            Tournee t_voisin = t_courante.copy();
            t_voisin.echanger(i, i + 1);

            if(t_voisin.cout() < t_courante.cout())
                t_courante = t_voisin.copy();
        }
        return t_courante;
    }
    
    private static Tournee sommet_premier_d_abord(Tournee t_courante) {
        for (int i = 0; i < t_courante.size(); i++) {
            for(int j =0;j <t_courante.size(); j++){
                Tournee t_voisin = t_courante.copy();
                t_voisin.echanger(i, j);
                if(t_voisin.cout() < t_courante.cout())
                t_courante = t_voisin.copy();
            }
        }
        return t_courante;
    }
    
    private static Tournee _2opt_premier_d_abord(Tournee t_courante) {
        for (int i = 0; i < t_courante.size(); i++) {
            for(int j =i+1;j < t_courante.size(); j++){
                Tournee t_voisin = t_courante.copy();
                if(t_voisin.get(i).distance(t_voisin.get(i+1)) + t_voisin.get(j).distance(t_voisin.get(j+1))>t_voisin.get(i).distance(t_voisin.get(j)) + t_voisin.get(i+1).distance(t_voisin.get(j+1)))
                    t_voisin.retourner(i+1, j);
                if(t_voisin.cout() < t_courante.cout())
                t_courante = t_voisin.copy();
            }
        }
        return t_courante;
    }
}
