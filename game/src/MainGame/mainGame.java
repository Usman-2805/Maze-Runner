package MainGame;
// Importing custom game classes and the City Engine World class
import GUI.*;
import Level.level;
import Level.renderer;
import Player.player;
import Player.playerTrack;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
// Importing Swing library for the graphical user interface (window)
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
// Setup class for the game
//Initialises the game world,player,level and the display window
public class mainGame {
    private String difficulty = null;
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);
    private World world;
    private gameView view;
    private Random random = new Random();
    private sounds sounds = new sounds();


    //Main Game constructor
    public mainGame() {
        //Creates the main frame
        int backgroundNo = random.nextInt(3);
        JFrame frame = new JFrame("Maze Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Initialises Menus
        mainMenu menuPanel = new mainMenu(backgroundNo);

        instructionMenu instructionPanel = new instructionMenu(() -> {
            cardLayout.show(mainContainer, "MenuCard");
        },backgroundNo);

        settingsMenu settingPanel = new settingsMenu(() -> {
            cardLayout.show(mainContainer, "MenuCard");
        }, backgroundNo);

        difficultyMenu difficultyPanel = new difficultyMenu(() -> {
            cardLayout.show(mainContainer, "MenuCard");
        }, backgroundNo);

        pauseMenu pausePanel = new pauseMenu(() -> {
            sounds.stopAllSounds();
            sounds.playTitleSound();
            cardLayout.show(mainContainer, "MenuCard");
        },
            () -> {
            cardLayout.show(mainContainer,"GameCard");
            world.start();
            view.requestFocusInWindow();
        });
        endMenu endPanel = new endMenu(() -> {
            cardLayout.show(mainContainer, "MenuCard");
            sounds.stopAllSounds();
            sounds.playTitleSound();
        });
        gameoverMenu gameoverPanel = new gameoverMenu(() -> {
            cardLayout.show(mainContainer, "MenuCard");
            sounds.stopAllSounds();
            sounds.playTitleSound();
        },
        () -> {
            int themeNo = startGame();
            pausePanel.setBackground(themeNo);
            endPanel.setBackground(themeNo);
            endPanel.getTimer(view.getTimer());
            }
        );

        //Adds menus to the container
        mainContainer.add(menuPanel, "MenuCard");
        mainContainer.add(instructionPanel, "InstructionCard");
        mainContainer.add(settingPanel, "SettingsCard");
        mainContainer.add(difficultyPanel, "DifficultyCard");
        mainContainer.add(pausePanel, "PauseCard");
        mainContainer.add(gameoverPanel, "GameOverCard");
        mainContainer.add(endPanel, "EndCard");
        //Button Clicked Functions
        menuPanel.getInstructionsButton().addActionListener(e -> {
            cardLayout.show(mainContainer, "InstructionCard");
        });

        menuPanel.getSettingsButton().addActionListener(e -> {
            cardLayout.show(mainContainer, "SettingsCard");
        });


        settingPanel.volumeButton.addActionListener(e -> {
            settingPanel.isSoundOn = !settingPanel.isSoundOn; // Flip the boolean from true to false (or vice versa)
            checkSoundSetting(settingPanel,sounds);
            if (settingPanel.isSoundOn) {
                settingPanel.volumeButton.setText("Sound: ON");
            } else {
                settingPanel.volumeButton.setText("Sound: OFF");
            }
        });
        menuPanel.getStartButton().addActionListener(e -> {
            cardLayout.show(mainContainer, "DifficultyCard");
            difficultyPanel.getEasyButton().addActionListener(e1 -> {
                difficulty = "easy";
                gameSetup(settingPanel,pausePanel,gameoverPanel,endPanel);
            });
            difficultyPanel.getMediumButton().addActionListener(e1 -> {
                difficulty = "medium";
                gameSetup(settingPanel,pausePanel,gameoverPanel,endPanel);
            });
            difficultyPanel.getHardButton().addActionListener(e1 -> {
                difficulty = "hard";
                gameSetup(settingPanel,pausePanel,gameoverPanel,endPanel);
            });


        });
        frame.add(mainContainer);
        // Since CardLayout adapts to its largest card, we pack the frame
        // to fit the 1280x720 game view perfectly.
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    //Starts the game and creates the Level
    private int startGame(){
        //Stops any sounds from playing
        sounds.stopAllSounds();
        //Randomised Theme chosen: Desert, Forest, Winter
        int themeNo = random.nextInt(3);
        sounds.playLevelSound(themeNo);
        //Initialize the physics world with a frame rate of 60 frames per second
        world = new World(60);
        //Disable gravity (typically done for top-down games where you move in x and y axes)
        world.setGravity(0);
        //Set up the game view (the visual area where the game is rendered) with a screen size of 1280x720
        view = new gameView(world, 1280, 720,difficulty, themeNo, () -> {
            cardLayout.show(mainContainer, "GameOverCard");
            sounds.stopAllSounds();
            sounds.playEndingSound();
        },() -> {
            cardLayout.show(mainContainer, "EndCard");
            sounds.stopAllSounds();
            sounds.playEndingSound();
        });
        //Create the game level based on the selected difficulty
        level level = new level(difficulty,world);
        //Create the player object with a width (1.2f)
        player player = new player(1.2f,world);
        //Initialize tracking to keep the camera/view focused on the player
        playerTrack Tracking = new playerTrack(view,player,new renderer(level.return_level(),world, themeNo));
        //Shows the pause menu when ESC is pressed
        pauseListener pauseTracker = new pauseListener(() -> {
            world.stop();
            cardLayout.show(mainContainer, "PauseCard");
        });
        //Add Listeners for inputs and world updates
        view.addKeyListener(pauseTracker);
        //Listen for keyboard inputs to move the player
        view.addKeyListener(player.playerMove);
        //Update the player's sprite animation every physics step
        world.addStepListener(player.playerSprite);
        //Update the tracking/camera every physics step
        world.addStepListener(Tracking);
        mainContainer.add(view, "GameCard");
        world.start();
        cardLayout.show(mainContainer, "GameCard");
        view.requestFocusInWindow();
        return themeNo;
    }
    //Sets up the game and menus to have the correct settings
    private void gameSetup(settingsMenu sP, pauseMenu pP, gameoverMenu goP, endMenu emP){
        checkSoundSetting(sP,sounds);
        int themeNo = startGame();
        pP.setBackground(themeNo);
        goP.setBackground(themeNo);
        emP.setBackground(themeNo);
        emP.getTimer(view.getTimer());
        emP.setDifficulty(difficulty);
    }
    //Checks if  the sound is on or not and changes the variable within sounds
    private void checkSoundSetting(settingsMenu sP, sounds s){
        if (sP.isSoundOn){
            s.sound = true;
        }
        else{
            s.sound = false;
        }
        sounds.playTitleSound();
    }

    //Main method running by default without a class needing to be created initiating the game
    public static void main() {
        new mainGame();
    }
}


