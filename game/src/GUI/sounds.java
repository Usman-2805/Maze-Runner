package GUI;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
//Class Responsible for dealing with all sounds
public class sounds {
    private SoundClip titleMusic;
    private SoundClip theme1;
    private SoundClip theme2;
    private SoundClip theme3;
    private SoundClip ending;
    public boolean sound = true;

    //Initialises all sounds
    public sounds(){
        initialiseTitleSound();
        initialiseLevelSound(1);
        initialiseLevelSound(2);
        initialiseLevelSound(3);
        initialiseEndingSound();
        playTitleSound();
    }
    private void initialiseTitleSound(){
        try {
            titleMusic = new SoundClip("Sounds/Title Screen.wav");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // This is the "backup plan" Java demanded.
            // If it fails, it will just print an error instead of crashing your whole game.
            System.out.println("Oops! Couldn't load the title music: " + e.getMessage());
        }
    }
    //Plays title sound if the sound is on, otherwise it stops it
    public void playTitleSound(){
        if (titleMusic != null && sound){
            titleMusic.loop();
        }
        else {
            stopTitleSound();
        }
    }
    private void stopTitleSound(){
        if (titleMusic != null){
            titleMusic.stop();
        }
    }
    private void initialiseLevelSound(int themeNo){
        try {
            if (themeNo == 1){
                theme1 = new SoundClip("Sounds/Level 1.wav");
            }
            if(themeNo == 2){
                theme2 = new SoundClip("Sounds/Level 2.wav");
            }
            if(themeNo == 3){
                theme3 = new SoundClip("Sounds/Level 3.wav");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // This is the "backup plan" Java demanded.
            // If it fails, it will just print an error instead of crashing your whole game.
            System.out.println("Oops! Couldn't load the title music: " + e.getMessage());
        }
    }
    //Plays Level sound accordingly to the themeNo and if the sound is on or not
    public void playLevelSound(int themeNo){
        if (sound) {
            if (themeNo == 0) {
                theme1.loop();
            }
            if (themeNo == 1) {
                theme2.loop();
            }
            if (themeNo == 2) {
                theme3.loop();
            }
        }
    }
    public void initialiseEndingSound(){
        try {
            ending = new SoundClip("Sounds/Ending.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // This is the "backup plan" Java demanded.
            // If it fails, it will just print an error instead of crashing your whole game.
            System.out.println("Oops! Couldn't load the title music: " + e.getMessage());
        }
    }
    //Plays at gameover or end of the game depending if the sound is on or not
    public void playEndingSound(){
        if (sound) {
            ending.loop();
        }
    }
    //Stops all sounds from playing
    public void stopAllSounds(){
        titleMusic.stop();
        theme1.stop();
        theme2.stop();
        theme3.stop();
        ending.stop();
    }
}
