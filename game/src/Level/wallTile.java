package Level;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
// wallTile inherits from StaticBody
public class wallTile extends StaticBody {
    private BodyImage wallImage;
    public wallTile(World w, int themeNo) {
        super(w, new BoxShape(2f,2f));
        setTexture(themeNo);
        this.addImage(this.wallImage);
    }
    //Sets texture according to the theme
    private void setTexture(int themeNo){
        if (themeNo == 0){
            wallImage = new BodyImage("Textures/Level/Desert/wall.png",4f);
        }
        else if (themeNo == 1){
            wallImage = new BodyImage("Textures/Level/Forest/wall.png",4f);
        }
        else if (themeNo == 2){
            wallImage = new BodyImage("Textures/Level/Winter/wall.png",4f);
        }

    }
}
