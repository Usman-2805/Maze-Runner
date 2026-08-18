package Level;

import city.cs.engine.*;
// Floor tile inheriting from a staticbody and has no collisions
public class floorTile extends StaticBody {
    private BodyImage floorImage;
    public floorTile(World w, int themeNo) {
        super(w);
        setTexture(themeNo);
        new GhostlyFixture(this,new BoxShape(2f,2f));
        this.addImage(floorImage);
    }
    //Sets texture according to the theme
    private void setTexture(int themeNo){
        if (themeNo == 0){
            floorImage = new BodyImage("Textures/Level/Desert/floor.png",4f);
        }
        else if (themeNo == 1){
            floorImage = new BodyImage("Textures/Level/Forest/floor.png",4f);
        }
        else if (themeNo == 2){
            floorImage = new BodyImage("Textures/Level/Winter/floor.png",4f);
        }

    }
}
