package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import utils.File;

public class ListeVille {
    private ArrayList<Ville> list;
    protected void setListe(ArrayList<Ville> array) { list = array; } 
    public ArrayList<Ville> getListe(){ return this.list; }
    public Ville get(int i) { return list.get(i % list.size()); }


    public ListeVille() {
        list = new ArrayList<>();
    }

    public ListeVille(String fileName) {
        File f = new File(fileName);
        ArrayList<Ville> newList = new ArrayList<>();
        
        ArrayList<String[]> listTab;
        listTab = f.read();

        for(String[] temp : listTab)
            newList.add(new Ville(temp));
        
        list = newList;
    }

    public ListeVille(ArrayList<Ville> l) {
        list = (ArrayList<Ville>) l.clone();
    }

    public void remove(Ville v){ list.remove(v); }
    
    public void add(Ville v) { list.add(v); }

    public int getIndex(Ville v) { return list.indexOf(v); }
    
    
    public void insert(Ville v, int position) { list.add(position % list.size(), v); }

    public void echanger(int i, int j) {
        i = i % list.size();
        j = j % list.size();
        Ville temp = get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
    public void retourner(int i, int j) {
        for (int w = i, s = j; w < s;  w++) 
            list.add(w, list.remove(s));
    }
    
    public int size() { return list.size(); }

    public void shuffle() {
        ArrayList<Ville> listeTemp = list;
        Collections.shuffle(listeTemp);
        list = listeTemp;
    }


    public String toStringComplet(){
        String res = "";
        for(Ville v : list)
            res += v.toString() + "\n";
        return res;
    }
   
    @Override
    public String toString(){
        String res = "[";
        int i =0;
        for(Ville v : list){
            i++;
            if(i == list.size())
                res += v.getNumVille();
            else
            res += v.getNumVille()+",";
        }
        res += "]";

        return res;
    }
}
