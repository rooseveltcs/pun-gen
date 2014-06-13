import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Michael Hannon on 4 June 2014.
 * Alpha 2
 * Latest Change: Adding more rating functionality.
 * Last updated: 10 June 2014
 */
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


    public Pun(String keyword, String pun)  {
        this.keyword = keyword;
        this.pun = pun;
        punBase = punBaseLoader();
    }

    public HashMap<String, Integer> getAccessedPuns() {
        return accessedPuns;
    }

    public String punFinder() {
            if(!punBase.containsKey(keyword)) {
                return null;
            }
        return punBase.get(keyword);
    }

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
    public void ratePun(int rating) {
        accessedPuns = new HashMap<String, Integer>();
        accessedPuns.put(pun, rating);
    }
    @Override
    public String toString() {
        if(keyword.equals("rating")) {
            return String.format(accessedPuns.toString());
        }
        return String.format(pun);
    }
}
