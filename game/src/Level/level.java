package Level;

import city.cs.engine.World;

public class level {
    private int[][] level_tilemap;
    public level(String difficulty, World world) {
        // Generates a level randomly and returns the map
        levelGeneration generator = new levelGeneration(difficulty);
        this.level_tilemap= generator.get_level();
    }
    public int[][] return_level(){
        return this.level_tilemap;
    }
}
