package logic;

/**
 *
 * @author Hamadi
 */
public class Ville {
    
    private int numVille;
    private double latitude;
    private double longitude;
    private String nomVille;

    
    public Ville(String[] temp){
        numVille = Integer.parseInt(temp[0]);
        nomVille = temp[1];
        latitude = Double.parseDouble(temp[2]);
        longitude = Double.parseDouble(temp[3]);
    }

    public Ville(){
        numVille = 0;
        nomVille = "UNE VILLE";
        latitude = 0.0;
        longitude = 0.0;
    }

    public int getNumVille(){
        return this.numVille;
    }
    
    public double getLatitude(){
        return this.latitude;
    }
    
    public double getLongitude(){
        return this.longitude;
    }
    
    public String getNomVille(){
        return this.nomVille;
    }
    
    public double distance(Ville v2){
        double x1 = Math.toRadians(this.getLongitude());
        double x2 = Math.toRadians(v2.getLongitude());
        double y1 = Math.toRadians(this.getLatitude());
        double y2 = Math.toRadians(v2.getLatitude());
        double res = 0;
        double cos1 = Math.cos(x1-x2);
        double cos2 = (Math.cos(y1)*Math.cos(y2));
        double cos3 = cos2*cos1;
        double sin1 =  (Math.sin(y1)*Math.sin(y2));
        double b = Math.acos(sin1+cos3);
        double c = 6371*b;
        res = Math.abs(c);
        return res;
    }
    
    @Override
    public Object clone() {
        String[] ville = {String.valueOf(getNumVille()), getNomVille(), String.valueOf(getLatitude()), String.valueOf(getLongitude())};
        return new Ville(ville);
    }

    @Override
    public String toString(){
        return this.getNumVille() +" : "+this.getLatitude() +" : "+this.getLongitude() +" : "+this.getNomVille();
    }

    @Override
    public int hashCode() {
        return this.numVille;
    }

    @Override
    public boolean equals(Object o) {
        Ville v = (Ville)o;
        return v.getNumVille() == this.getNumVille();
    }
}
