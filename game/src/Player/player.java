package Player;

import city.cs.engine.CircleShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
// Player class containing the shape, body, sprite and movement
public class player {
    private Shape playerShape;
    private Walker playerBody;
    public playerSprite playerSprite;
    public move playerMove;
    // Constructor creating the shape, body and default start position
    public player(float width, World world){
        playerShape = new CircleShape(width);
        playerBody = new Walker(world,playerShape);
        playerBody.setPosition(new Vec2(4,4));
        playerSprite = new playerSprite(playerBody);
        playerMove = new move(playerBody,playerSprite);
    }
    public Walker getPlayerBody() {
        return playerBody;
    }
    public float getX(){
        return playerBody.getPosition().x;
    }
    public float getY(){
        return playerBody.getPosition().y;
    }
}
