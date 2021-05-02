package logic;

public class Tournee extends ListeVille {
    public Tournee() {
        super();
    }

    public Tournee(String fileName) {
        super(fileName);
    }

    public Tournee(ListeVille lVille) {
        super.setListe(lVille.getListe());
    }

    public double cout() {
        double res = 0.0;

        for(int i=0; i < super.size(); i++)
            res += super.get(i).distance(super.get(i+1));

        return res;
    }

    public Tournee copy() {
        return new Tournee(new ListeVille(super.getListe()));
    }


    @Override
    public String toString() {
        Ville v0 = super.get(0);
        String res = "La tournée débute par [" + v0.getNumVille() + "] ";
        res += v0.getNomVille() + " et suit l'ordre suivant ";
        res += super.toString();

        return res;
    }
}
