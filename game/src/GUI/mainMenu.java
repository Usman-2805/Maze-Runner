package GUI;

import javax.swing.*;
import java.awt.*;
//Displays the Main Menu
public class mainMenu extends JPanel {
    private ImageIcon backgroundImage;
    private JButton startButton = new JButton("Play");
    private JButton settingsButton = new JButton("Settings");
    private JButton instructionsButton = new JButton("Instructions");
    private JButton exitButton = new JButton("Exit");
    //Initialised Background and Buttons
    public mainMenu(int backgroundNo){
        setBackground(backgroundNo);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        startButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(startButton);

        instructionsButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(instructionsButton);

        settingsButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(settingsButton);

        exitButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
    }
    //Getter Functions
    public JButton getStartButton(){
        return startButton;
    }
    public JButton getInstructionsButton(){
        return instructionsButton;
    }
    public JButton getSettingsButton(){
        return settingsButton;
    }
    public JButton getExitButton(){
        return exitButton;
    }
    //Sets the background according to main menu theme
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
    //Draws the Background
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
    }
}
