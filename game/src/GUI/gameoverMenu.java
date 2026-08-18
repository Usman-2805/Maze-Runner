package GUI;

import javax.swing.*;
import java.awt.*;
//Displays the Game Over Menu
public class gameoverMenu extends JPanel {
    private ImageIcon backgroundImage;
    private JButton restartButton = new JButton("Restart");
    private JButton exitButton = new JButton("Main Menu");
    //Initialises Background and Buttons
    public gameoverMenu(Runnable onBackClicked, Runnable onRestartClicked) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(new GridBagLayout());
        restartButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(restartButton);
        //Generates a new level when clicked
        restartButton.addActionListener(e -> {
            onRestartClicked.run();
        });
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(exitButton);
        exitButton.addActionListener(e -> {
            onBackClicked.run();
        });
    }
    //Sets the background according to the theme
    public void setBackground(int backgroundNo) {
        if (backgroundNo == 0) {
            backgroundImage = new ImageIcon("Textures/Level/Desert/background.jpg");
        }
        else if (backgroundNo == 1) {
            backgroundImage = new ImageIcon("Textures/Level/Forest/background.png");
        }
        else if (backgroundNo == 2) {
            backgroundImage = new ImageIcon("Textures/Level/Winter/background.png");
        }
    }
    //Displays the Game Over Text
    private void draw_gameover(Graphics g) {
        Font time_font = new Font("SansSerif", Font.BOLD, 80);
        String end_text = "GAME OVER";
        FontMetrics fontMetrics = g.getFontMetrics(time_font);
        int text_width = fontMetrics.stringWidth(end_text);
        g.setFont(time_font);
        g.setColor(Color.RED);
        g.drawString(end_text,((1280-text_width)/2),200);
    }
    //Draws background and Game Over text
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
        draw_gameover(g);
    }
}
