/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Hamadi
 */
public class File {
    
    private String nomFichier;

    /**
     * Constructeur de File
     * @param nomFichier nom du fichier à lire
     */
    public File(String nomFichier){
        this.nomFichier = nomFichier;
    }
    
    public ArrayList<String[]> read(){
        String[] tabString = null;
        ArrayList<String[]> listString = new ArrayList<String[]>();
        try
        {
          // Le fichier d'entrée
          FileInputStream file = new FileInputStream(nomFichier);   
          Scanner scanner = new Scanner(file); 
          String sTemp;

          //renvoie true s'il y a une autre ligne à lire
          while(scanner.hasNextLine())
          {
            sTemp = scanner.nextLine();
            tabString = sTemp.split(" ");
            listString.add(tabString);
            //System.out.println(sTemp);
          }
          scanner.close();  
        }
        catch(IOException e)
        {
          e.printStackTrace();
        }
        return listString;
    }
  
}
