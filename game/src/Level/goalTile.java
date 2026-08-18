package Level;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
// Goal tile inherits from staticsBody
public class goalTile extends StaticBody {
    private BodyImage exitImage;
    public goalTile(World w, int themeNo) {
        super(w, new BoxShape(2f,2f));
        setTexture(themeNo);
        this.addImage(this.exitImage);
    }
    //Sets texture according to the theme
    private void setTexture(int themeNo){
        if (themeNo == 0){
            exitImage = new BodyImage("Textures/Level/Desert/exit.png",4f);
        }
        else if (themeNo == 1){
            exitImage = new BodyImage("Textures/Level/Forest/exit.png",4f);
        }
        else if (themeNo == 2){
            exitImage = new BodyImage("Textures/Level/Winter/exit.png",4f);
        }

    }
}
