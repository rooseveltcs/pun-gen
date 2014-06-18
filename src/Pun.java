/**
 * Project: PunGen
 * File: Pun.java
 * @author Nick Trunkey, Michael Hannon
 * @version 061814
 *
 *
 */

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Pun {
    private final String keyword;
    private String pun;
    private final File punList = new File("puns.txt");
    private HashMap<String, String> punBase;
    private HashMap<String, Integer> accessedPuns;

    //Constructor for pun finding

    public Pun(String keyword) {
        this.keyword = keyword;
        if(!keyword.equals("rating")) {
            punBase = punBaseLoader();
            pun = punFinder();
        }
    }

    /**
     * THE Method loads the text file, then extracts the pun and keyword and returns the punBase
     * @return takes a file and extracts the keyword and pun
     */
    //Loads the text into the HashMap
    private HashMap<String, String> punBaseLoader()  {
        Scanner input = null;

        try {
            input = new Scanner(punList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        punBase = new HashMap<String, String>();
        assert input != null;
        while (input.hasNextLine()) {
                String fileWord = input.nextLine();
                String filePun = input.nextLine();
                punBase.put(fileWord, filePun);
            }
        return punBase;
    }

    /**
     * The method assigns the keyword and pun to Strings
     * @param keyword Assigns the key word to itself
     * @param pun Assigns the pun to itself
     */
    public Pun(String keyword, String pun)  {
        this.keyword = keyword;
        this.pun = pun;
        punBase = punBaseLoader();
    }

    /**
     * The method is a getter method for accessedPuns
     * @return the hashmaps that have been accessed by the user
     */
    public HashMap<String, Integer> getAccessedPuns() {
        return accessedPuns;
    }

    /**
     * Checks if punBase contains the keyword and returns if so
     * @return with keyword
     */
    public String punFinder() {
            if(!punBase.containsKey(keyword)) {
                return null;
            }
        return punBase.get(keyword);
    }

    /**
     * The method adds the pun the user gives
     */
    public void punAdder() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(punList, true));
            bw.write(keyword);
            bw.newLine();
            bw.write(pun);
            bw.newLine();
            bw.flush();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try {
                    bw.close();
                    punBaseLoader();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }
    }

    /**
     * The method creates a new hashmap for the accessedPuns and adds pun and rating to the hashmaps
     * @param rating
     */
    public void ratePun(int rating) {
        accessedPuns = new HashMap<String, Integer>();
        accessedPuns.put(pun, rating);
    }
    @Override
    /**
     * The method checks for the users input for if it is rating and returns the pun
     * @return the pun that goes with the keyword
     */
    public String toString() {
        if(keyword.equals("rating")) {
            return String.format(accessedPuns.toString());
        }
        return String.format(pun);
    }
}
