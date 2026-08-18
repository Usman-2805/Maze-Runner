package GUI;

import javax.swing.*;
import java.awt.*;
//Displays Settings Menu
public class settingsMenu extends JPanel {
    public boolean isSoundOn = true;
    private ImageIcon backgroundImage;
    public JButton volumeButton = new JButton("Sound: ON");
    private JButton exitButton = new JButton("Main Menu");
    //Initialises Background and Buttons
    public settingsMenu(Runnable onBackClicked, int backgroundNo) {
        setBackground(backgroundNo);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        volumeButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(volumeButton);
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(exitButton);
        exitButton.addActionListener(e -> {
            onBackClicked.run();
        });
    }
    //Sets Background image according to theme
    private void setBackground(int backgroundNo) {
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
    //Draws background
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
    }
}
