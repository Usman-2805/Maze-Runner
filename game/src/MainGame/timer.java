package MainGame;

import java.awt.*;
// Used to time the player and get there time at the end or show they ran out of time
public class timer {
    // Maximum amount of time the player has
    private double maxTime;
    // The time elapsed
    private double currentTime;
    // The time the player finished
    private double finishTime;
    // How much of the bar should be displayed to the player to show that the time has elapsed
    private double percentBar;
    // If the player has finished or not
    private boolean finished = false;
    private int width;
    private Rectangle full_bar;
    private Rectangle missing_bar;

    // Timer constructor
    public timer(int width, String difficulty) {
        get_max_time(difficulty);
        this.width = width;
        currentTime = 0;
        percentBar = ((currentTime)/maxTime);
    }
    // Gets the max time depending on the difficulty
    private void get_max_time(String difficulty){
        int minutes = 0;
        if (difficulty.equals("easy")){
            minutes = 2;
        }
        if (difficulty.equals("medium")){
            minutes = 6;
        }
        if (difficulty.equals("hard")){
            minutes = 12;
        }
        // Converts max time to match the physics step loops
        this.maxTime = (minutes * 50) * 60;

    }
    // Gets the rectangle of the background/full bar
    public Rectangle get_full_bar(){
        full_bar = new Rectangle(640-(width/2),10,width,50);
        return full_bar;
    }
    // Gets the rectangle of the elapsed time
    public Rectangle get_missing_bar(){
        missing_bar = new Rectangle(640-(width/2),10, (int) (width * this.percentBar),50);
        return missing_bar;
    }
    // Gets the outline bar to show the bar clearly
    public Rectangle get_outline_bar(){
        Rectangle outline_bar = new Rectangle(640-((width+15)/2),3,width+15,65);
        return outline_bar;
    }
    // Checks if the timer has ended
    public boolean check_end_timer(){
        if (currentTime >= maxTime){
            return true;
        }
        else {
            return false;
        }
    }
    // Stops the time
    public void stop_timer(){
        finishTime = currentTime;
        finished = true;
    }
    // Converts the finish time into seconds
    public int convert_finish(){
        return (int)(currentTime/50);
    }
    // Checks if the player has finished or not
    public boolean check_finish(){
        return finished;
    }
    // Updates the percentBar and current time
    public void update(){
        this.percentBar = ((currentTime)/maxTime);
        // Current time is not updated if the timer has ended or the player has finished
        if (!check_end_timer() && !finished){
            currentTime++;
        }
    }
}
