package GUI;

import MainGame.rankFinder;
import MainGame.timer;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//Displays the end screen menu
public class endMenu extends JPanel {
    private ImageIcon backgroundImage;
    private JButton exitButton = new JButton("Main Menu");
    private timer time;
    private Color fontColor;
    private String difficulty;
    //Initialises Background and Buttons
    public endMenu(Runnable onBackClicked) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(new GridBagLayout());
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(exitButton);
        exitButton.addActionListener(e -> {
            onBackClicked.run();
        });
    }
    //Stores and sets the difficulty
    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }
    //Changes background and font to match accordingly
    public void setBackground(int backgroundNo) {
        if (backgroundNo == 0) {
            backgroundImage = new ImageIcon("Textures/Level/Desert/background.jpg");
            fontColor = new Color(255, 200, 0);
        }
        else if (backgroundNo == 1) {
            backgroundImage = new ImageIcon("Textures/Level/Forest/background.png");
            fontColor = new Color(5, 237, 27);
        }
        else if (backgroundNo == 2) {
            backgroundImage = new ImageIcon("Textures/Level/Winter/background.png");
            fontColor = new Color(13, 178, 255);
        }
    }
    //Gets the timer from a parameter
    public void getTimer(timer time){
        this.time = time;
    }
    //Draws the rank and the best time of that difficulty
    private void draw_rank(Graphics g){
        rankFinder rankFinder = new rankFinder(time.convert_finish(), difficulty);
        int rank = rankFinder.findRank();
        Font time_font = new Font("SansSerif", Font.BOLD, 80);
        String rank_text = "Rank: " + rank;
        String best_text = "Best Time:" + rankFinder.getBestTime() + "s";
        FontMetrics fontMetrics = g.getFontMetrics(time_font);
        int rank_width = fontMetrics.stringWidth(rank_text);
        int best_width = fontMetrics.stringWidth(best_text);
        g.setFont(time_font);
        g.setColor(fontColor);
        g.drawString(rank_text,((1280-rank_width)/2),100);
        g.drawString(best_text,((1280-best_width)/2),250);
    }
    //Draws the players final end time
    private void draw_end_time(Graphics g) {
        Font time_font = new Font("SansSerif", Font.BOLD, 80);
        String time_text = "Time: " + time.convert_finish() + "s";
        FontMetrics fontMetrics = g.getFontMetrics(time_font);
        // Gets the width of the text so that it can be centered on the screen
        int text_width = fontMetrics.stringWidth(time_text);
        g.setFont(time_font);
        g.setColor(fontColor);
        g.drawString(time_text,((1280-text_width)/2),500);
    }
    //Draws background, end time, rank and best time
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
        draw_end_time(g);
        draw_rank(g);
    }
}
