package GUI;

import MainGame.gameView;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// Listens if the player clicks escape
public class pauseListener extends KeyAdapter {
    private boolean pause = false;
    private Runnable onPauseTriggered;
    public pauseListener(Runnable onPauseTriggered) {
        this.onPauseTriggered = onPauseTriggered;
    }
    public boolean getPause(){
        return this.pause;
    }
    //Shows pause menu when ESC clicked
    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            onPauseTriggered.run();
        }
    }
}
