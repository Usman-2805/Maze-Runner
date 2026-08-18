package MainGame;
import GUI.gameoverMenu;
import GUI.pauseListener;
import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// gameView inherits from UserView
public class gameView extends UserView {
    //Background image
    private Image backgroundImage = new ImageIcon("Textures/Level/background.png").getImage();
    // Timer object made
    private timer timer;
    private World w;
    private int themeNo;
    //Runnables allowing functions to be passed in
    private Runnable gameOverMenu;
    private Runnable endMenu;
    private String difficulty;
//    public pauseMouseListener pauseMouse = new pauseMouseListener();
    // gameView constructor sets the zoom and makes it the window of focus and sets up the timer
    public gameView(World w, int width, int height, String difficulty, int themeNo, Runnable gameOverMenu, Runnable endMenu) {
        this.w = w;
        super(w, width, height);
        this.themeNo = themeNo;
        setBackground(themeNo);
        this.setZoom(40);
        this.setFocusable(true);
        this.requestFocus();
        this.timer = new timer(500,difficulty);
        this.gameOverMenu = gameOverMenu;
        this.endMenu = endMenu;
        this.difficulty = difficulty;

    }
    // Sets the background depending on the theme
    private void setBackground(int backgroundNo) {
        if (backgroundNo == 0) {
            backgroundImage = new ImageIcon("Textures/Level/Desert/background.jpg").getImage();
        }
        else if (backgroundNo == 1) {
            backgroundImage = new ImageIcon("Textures/Level/Forest/background.png").getImage();
        }
        else if (backgroundNo == 2) {
            backgroundImage = new ImageIcon("Textures/Level/Winter/background.png").getImage();
        }
    }
    //Returns the timer so it can be accessed
    public timer getTimer() {
        return timer;
    }
    @Override
    // Overrides paintBackground method to display the background
    protected void paintBackground(Graphics2D g) {
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void draw_timer(Graphics2D g) {
        // Draws the outline bar
        g.draw(timer.get_outline_bar());
        g.setPaint(new Color(0,0,0));
        g.fill(timer.get_outline_bar());
        // Draws the back full bar
        g.draw(timer.get_full_bar());
        g.setPaint(Color.lightGray);
        g.fill(timer.get_full_bar());
        // Draws the amount of time passed
        g.draw(timer.get_missing_bar());
        if (themeNo == 0) {
            g.setPaint(new Color(255, 200, 0));
        }
        else if (themeNo == 1) {
            g.setPaint(new Color(2, 105, 12));
        }
        else if (themeNo == 2) {
            g.setPaint(new Color(8, 0, 161));
        }
        g.fill(timer.get_missing_bar());
        // Updates the timer
        timer.update();
    }


    // Overrides paintForeground method to display the timer, updates it and checks whether the player has finished or ran out of time
    @Override
    protected void paintForeground(Graphics2D g) {
        draw_timer(g);
        // Checks if the player has finished and displays it if they have
        if (timer.check_finish()){
            //Loads the end screen
            endMenu.run();
            //Saves the score to a txt file
            saveScoretoTXT();
        }
        // Checks if the player has ran out of time or not and displays gameover if they have
        if (timer.check_end_timer()){
            gameOverMenu.run();
        }

    }
    //Saves the players time to a TXT file
    private void saveScoretoTXT(){
        String fileName = "easyScores.txt";
        if (difficulty == "easy") {
            fileName = "easyScores.txt";
        }
        else if (difficulty == "medium") {
            fileName = "medScores.txt";
        }
        else if (difficulty == "hard") {
            fileName = "hardScores.txt";
        }
        try {
            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter pw = new PrintWriter(fw);

            int txtLine = timer.convert_finish();
            pw.println(txtLine);
            pw.close();
        } catch (IOException e){
            System.out.println("Error saving to TXT: " + e.getMessage());
        }
    }

}
