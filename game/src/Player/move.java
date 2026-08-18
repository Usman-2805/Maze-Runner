package Player;

import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// Move class takes in keyboard input
public class move extends KeyAdapter {
    private Walker playerBody;
    // Gets the current direction and if the player is sprinting or not
    private boolean[] currentDirection = {false,false,false,false,false};
    // Gets the playersprite
    private playerSprite playerSprite;
    // Move constructor
    public move(Walker playerBody, playerSprite playerSprite) {
        this.playerBody = playerBody;
        this.playerSprite = playerSprite;
    }
    @Override
    // Overrides the keyPressed and depending on the keypressed the player will move and sprint
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            currentDirection[0] = true;
        }
        if (key == KeyEvent.VK_D) {
            currentDirection[1] = true;
        }
        if (key == KeyEvent.VK_W) {
            currentDirection[2] = true;
        }
        if (key == KeyEvent.VK_S) {
            currentDirection[3] = true;
        }
        if (key == KeyEvent.VK_SHIFT){
            currentDirection[4] = true;
        }
        updateVelocity();
    }
    @Override
    // The player will stop moving when the key is released
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A){
            currentDirection[0] = false;
        }
        if (key == KeyEvent.VK_D){
            currentDirection[1] = false;
        }
        if (key == KeyEvent.VK_W){
            currentDirection[2] = false;
        }
        if (key == KeyEvent.VK_S){
            currentDirection[3] = false;
        }
        if (key == KeyEvent.VK_SHIFT){
            currentDirection[4] = false;
        }
        updateVelocity();
    }
    // Smooth movement by updating the x and why velocity at the same time and allows for sprinting
    private void updateVelocity(){
        int xSpeed = 0;
        int ySpeed = 0;
        int moveSpeed = 15;
        int sprint_speed = 10;
        if (currentDirection[4]) {
            moveSpeed += sprint_speed;
        }
        if (!currentDirection[4]) {
            moveSpeed = 15;
        }
        if (currentDirection[0]) {
            xSpeed = -moveSpeed;
        }
        if (currentDirection[1]) {
            xSpeed = moveSpeed;
        }
        if (currentDirection[2]) {
            ySpeed = moveSpeed;
        }
        if (currentDirection[3]) {
            ySpeed = -moveSpeed;
        }
        // Sets the linear velocity of the player using the xSpeed and ySpeed
        playerBody.setLinearVelocity(new Vec2(xSpeed,ySpeed));
        // Changes the sprite of the player depending on the direction they are going
        playerSprite.change_image(currentDirection);
    }
}
