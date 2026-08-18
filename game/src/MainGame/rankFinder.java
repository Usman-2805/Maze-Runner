package MainGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//Gets the Rank of the player
public class rankFinder {
    List<Integer> allScores = new ArrayList<Integer>();
    int currentTime;
    int bestTime;
    String fileName = "easyScores.txt";


    public rankFinder(int currentTime, String difficulty){
        this.currentTime = currentTime;
        getFileName(difficulty);
        getAllScores();
    }
    //Gets the file name according to the difficulty
    private void getFileName(String difficulty){
        if (difficulty == "easy") {
            fileName = "easyScores.txt";
        }
        else if (difficulty == "medium") {
            fileName = "medScores.txt";
        }
        else if (difficulty == "hard") {
            fileName = "hardScores.txt";
        }
    }
    //Stores all scores in an array list
    private void getAllScores(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                allScores.add(Integer.parseInt(currentLine));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error in reading file");
        }
        if  (allScores.size() > 0){
            //Sorts the scores in natural order, smallest to biggest
            allScores.sort(Comparator.naturalOrder());
        }
    }
    //Gets the best score
    public int  getBestTime() {
        return allScores.get(0);
    }
    //Finds the rank by comparing each time to the players time and getting the position
    public int findRank(){
        for (int i = 0; i < allScores.size(); i++){
            int rank = i + 1;
            int time = allScores.get(i);
            if (currentTime <= time){
                return rank;
            }
        }
        return -1;
    }
}
