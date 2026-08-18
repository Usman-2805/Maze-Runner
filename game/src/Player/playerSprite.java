package Player;

import city.cs.engine.*;
// Sprite implements a StepListener
public class playerSprite implements StepListener {
    private Walker playerBody;
    private AttachedImage currentSprite;
    private BodyImage[] bodyImages = new BodyImage[3];
    private  int image_counter = 0;
    private int rotation = 0;
    private int timer = 0;

    public playerSprite(Walker playerBody){
        this.playerBody = playerBody;
        bodyImages[0] = new BodyImage("Textures/player/Walk1.png",2.5f);
        bodyImages[1] = new BodyImage("Textures/player/Walk2.png",2.5f);
        bodyImages[2] = new BodyImage("Textures/player/Walk3.png",2.5f);
    }
    // Changes the rotation of the image depending on which direction the player is facing
    public void change_image(boolean[] current_direction){
        if (current_direction[0]){
            rotation = 270;
        }
        else if (current_direction[1]){
            rotation = 90;
        }
        else if (current_direction[2]){
            rotation = 180;

        }
        else if (current_direction[3]){
            rotation = 0;
        }
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }
    // Runs after every physics step changing between the different animations
    @Override
    public void postStep(StepEvent stepEvent) {
        // Timer is repsonsible for controlling the speed that the sprites change
        if (timer == 5){
            image_counter++;
            timer = 0;
            // Removes all the previous images rendered so that they do not stack
            playerBody.removeAllImages();
        }
        if (image_counter == 3){
            image_counter = 0;
        }
        // Changes the current sprite and rotation depending on the direction
        currentSprite = playerBody.addImage(bodyImages[image_counter]);
        currentSprite.setRotation((float)Math.toRadians(rotation));
        timer++;
    }
}
