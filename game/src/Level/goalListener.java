package Level;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
// is a collision listener that checks when the player collides with the goal tile
public class goalListener implements CollisionListener {
    private DynamicBody playerBody;
    private boolean reached = false;
    public goalListener(DynamicBody playerBody){
        this.playerBody = playerBody;
    }
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody().equals(playerBody)) {
            reached = true;
        }
    }
    public boolean isReached() {
        return reached;
    }
}
