package GUI;

import javax.swing.*;
import java.awt.*;
//Displays the Pause Menu
public class pauseMenu extends JPanel {
    private ImageIcon backgroundImage;
    private JButton resumeButton = new JButton("Resume");
    private JButton exitButton = new JButton("Main Menu");
    //Initalises Buttons
    public pauseMenu(Runnable onBackClicked, Runnable onResumeClicked) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        resumeButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(resumeButton);
        resumeButton.addActionListener(e -> onResumeClicked.run());
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(exitButton);
        exitButton.addActionListener(e -> {
            onBackClicked.run();
        });
    }
    //Intialises Background according to the theme
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
    //Draws the background
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
    }
}
