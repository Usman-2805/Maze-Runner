package GUI;

import javax.swing.*;
import java.awt.*;

// Displays the difficulty menu
public class difficultyMenu extends JPanel {
    private ImageIcon backgroundImage;
    private JButton easyButton = new JButton("Easy");
    private JButton mediumButton = new JButton("Medium");
    private JButton hardButton = new JButton("Hard");
    private JButton exitButton = new JButton("Main Menu");
    //Initialises Background and Buttons
    public difficultyMenu(Runnable onBackClicked, int backgroundNo) {
        setBackground(backgroundNo);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        easyButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(easyButton);
        mediumButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(mediumButton);
        hardButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(hardButton);
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(exitButton);
        exitButton.addActionListener(e -> {
            onBackClicked.run();
        });
    }
    //Getter Functions
    public JButton getEasyButton() {
        return easyButton;
    }
    public JButton getMediumButton() {
        return mediumButton;
    }
    public JButton getHardButton() {
        return hardButton;
    }
    //Sets the Background
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
    //Draws the background
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
    }
}
