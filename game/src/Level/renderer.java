package Level;

import Player.player;
import city.cs.engine.StaticBody;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;
//Renders the map based on player location
public class renderer {
    private int[][] current_level_tilemap;
    private World world;
    // Array of current tiles
    public List<StaticBody> current_tiles =  new ArrayList<StaticBody>();
    // goalListener object
    private goalListener goalListener;
    private int themeNo;
    public renderer(int[][] level, World world, int themeNo) {
        this.current_level_tilemap = level;
        this.world = world;
        this.themeNo = themeNo;
    }
    // Renders based on how close the tiles are to the player
    // Done by calculating if the tiles are in a certain radius of the player
    public void render_level(player player) {
        int radius = 32;
        int y = 0;
        for (int[] row: current_level_tilemap) {
            int x = 0;
            for(int tile: row) {
                // Tile position
                int[] tile_pos = {4*x,4*y};
                // Distance between player and tile using pythagoras
                double distance = Math.sqrt(Math.pow(tile_pos[0] - player.getX(),2) + Math.pow(tile_pos[1] - player.getY(),2));
                //Renders a wall tile
                if (tile == 1){
                    if (distance <= radius) {
                        wallTile new_tile = new wallTile(world,themeNo);
                        new_tile.setPosition(new Vec2(4*x,4*y));
                        current_tiles.add(new_tile);
                    }
                }
                //Renders the goal tile
                if (tile == 2){
                    if (distance <= radius) {
                        goalTile new_tile = new goalTile(world, themeNo);
                        new_tile.setPosition(new Vec2(4*x,4*y));
                        new_tile.setFillColor(java.awt.Color.RED);
                        // Checks if the goalListener has already been made or not
                        if (goalListener == null) {
                            goalListener = new goalListener(player.getPlayerBody());
                        }
                        // Adds the goalListener to the tiles collision listener
                        new_tile.addCollisionListener(goalListener);
                        current_tiles.add(new_tile);
                    }
                }
                // Renders the floor tile
                if (tile == 0){
                    if (distance <= radius) {
                        floorTile new_tile = new floorTile(world, themeNo);
                        new_tile.setPosition(new Vec2(4*x,4*y));
                        new_tile.setFillColor(java.awt.Color.RED);
                        current_tiles.add(new_tile);
                    }
                }
                x++;
            }
            y++;
        }
    }
    // Destroys all tiles in the current_tiles stopping them from being rendered
    public void destroy_all_tiles() {
        for (StaticBody tile: current_tiles) {
            tile.destroy();
        }
        //Makes current_tiles empty
        current_tiles.clear();
    }
    //Checks if the goal has been reached or not
    public boolean check_goal_reached(){
        if (goalListener != null){
            if (goalListener.isReached()){
                return true;
            }
        }
        return false;
    }
}
