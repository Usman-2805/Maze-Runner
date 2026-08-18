package GUI;

import javax.swing.*;
import java.awt.*;

//Displays instructions to the player
public class instructionMenu extends JPanel {
    private ImageIcon backgroundImage;
    private JButton exitButton = new JButton("Main Menu");
    //Background and Button is initialised
    public instructionMenu(Runnable onBackClicked, int backgroundNo) {
        setBackground(backgroundNo);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(new GridBagLayout());
        //Moves Main Menu button to the bottom of the screen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(exitButton, gbc);
        exitButton.addActionListener(e -> {
            onBackClicked.run();
        });
    }
    //Sets background according to the main menu theme
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
    //Draws the instructions
    private void draw_instructions(Graphics g) {
        //Draws slightly transparent black box
        int boxX = 50;
        int boxY = 50;
        int boxWidth = 1180;
        int boxHeight = 200;

        g.setColor(new Color(0,0,0,150));
        g.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);

        g.setColor(Color.white);
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        //Instructions in a list of Strings
        String[] instructions = {
                "OBJECTIVE : NAVIGATE THE MAZE AND REACH THE GOAL BEFORE THE TIMER RUNS OUT",
                "",
                "USE W, A, S, D TO MOVE",
                "HOLD SHIFT TO RUN FASTER",
                "PRESS ESC TO PAUSE THE GAME"
        };

        int textY = boxY + 40;
        int textX = boxX + 20;
        //Loops through each string and draws it
        for (String instruction : instructions) {
            g.drawString(instruction, textX, textY);
            textY += 30;
        }
    }
    //Draws image and instructions
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
        draw_instructions(g);
    }

}
