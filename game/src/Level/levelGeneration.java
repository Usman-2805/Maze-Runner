package Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class levelGeneration {
    // 2D array represeting the world using tiles
    private int[][] level_tilemap;
    // 2D array to keep track of tiles visited during the generation
    private boolean[][] visted_tiles;
    private String difficulty;
    private Random random = new Random();
    // Acts as a stack that keeps track of the current path it took to get to a point on the map
    private List<int[]> path_locations = new ArrayList<int[]>();
    // Depending on the difficulty a different sized map is made
    public levelGeneration(String difficulty) {
        this.difficulty = difficulty;
        if (difficulty.equals("easy")){
            level_tilemap = new int[25][25];
            visted_tiles = new boolean[25][25];
        }
        if (difficulty.equals("medium")){
            level_tilemap = new int[55][55];
            visted_tiles = new boolean[55][55];
        }
        if (difficulty.equals("hard")){
            level_tilemap = new int[105][105];
            visted_tiles = new boolean[105][105];
        }
        // level is then generated
        generate_level();
    }
    private void generate_level(){
        // Border is generated stoping it from being carved into and used as a path
        generate_border();
        // Generates the path
        generate_path();
        set_goal();
    }
    private void generate_path(){
        // Begins the process at {1,1}
        int[] start = {1,1};
        // Starting location made into a walkable path represented by 0
        level_tilemap[start[0]][start[1]] = 0;
        visted_tiles[start[0]][start[1]] = true;
        // First path pushed onto the stack
        path_locations.add(new int[]{start[0], start[1]});
        // While loop runs once it has to every possible location
        while (!path_locations.isEmpty()){
            // Gets the current point on the path
            int[] current = path_locations.get(path_locations.size() - 1);
            // Gets a list of neighbours to the current tile
            List<int[]> neighbours = get_neighbours(current, 2);
            // Checks if there are neighbours around the current tile that are valid (meaning they are not visited and within range)
            // Ensures a direct path is only made and not a big area of space
            if (check_neighbours_exist(neighbours)){
                // Selects a random neighbour from the list
                int neighbour_index = random.nextInt(neighbours.size());
                int[] neighbour = neighbours.get(neighbour_index);
                // Gets the direction of the neighbour relative to the current block
                String direction = get_neighbour_direction(neighbour,current);
                //Gets rid of the wall between the current tile and the chosen neighbour
                if (direction.equals("Up")){
                    level_tilemap[current[0]-1][current[1]] = 0;
                }
                if (direction.equals("Down")){
                    level_tilemap[current[0]+1][current[1]] = 0;
                }
                if (direction.equals("Left")){
                    level_tilemap[current[0]][current[1]-1] = 0;
                }
                if (direction.equals("Right")){
                    level_tilemap[current[0]][current[1]+1] = 0;
                }
                //Marks the neighbor as visited and changes it to a path tile
                visted_tiles[neighbour[0]][neighbour[1]] = true;
                level_tilemap[neighbour[0]][neighbour[1]] = 0;
                // Adds the neighbour to the stack
                path_locations.add(new int[]{neighbour[0], neighbour[1]});
            }
            else{
                // If no more neighbours that have not been visited then it is a dead end and goes back on itself
                path_locations.remove(current);
            }
        }
    }
    // Checks all 4 directions that are a certain distance away and then makes sure they are within range and not visited
    private List<int[]> get_neighbours(int[] current,int distance){
        List<int[]> neighbours = new ArrayList<int[]>();
        int[] up = {current[0]-distance, current[1]};
        int[] down = {current[0]+distance, current[1]};
        int[] left = {current[0], current[1]-distance};
        int[] right = {current[0], current[1]+distance};
        if (check_within_range(up)){
            if (!visted_tiles[up[0]][up[1]]){
                neighbours.add(up);
            }
        }
        if (check_within_range(down)){
            if (!visted_tiles[down[0]][down[1]]){
                neighbours.add(down);
            }
        }
        if (check_within_range(left)){
            if (!visted_tiles[left[0]][left[1]]){
                neighbours.add(left);
            }
        }
        if (check_within_range(right)){
            if (!visted_tiles[right[0]][right[1]]){
                neighbours.add(right);
            }
        }
        return neighbours;
    }
    // Gets the direction of the neighbour based on the difference of the i and j values
    private String get_neighbour_direction(int[] neighbour,int[] current){
        int difference_i = current[0]-neighbour[0];
        int difference_j = current[1]-neighbour[1];
        if (difference_i > 0){
            return "Up";
        }
        if (difference_i < 0){
            return "Down";
        }
        if (difference_j > 0){
            return "Left";
        }
        if (difference_j < 0){
            return "Right";
        }
        return null;
    }
    // Checks that there are neighbours in the list passed in
    private boolean check_neighbours_exist(List<int[]> neighbours){
        for (int i = 0; i < neighbours.size(); i++){
            if (neighbours.get(i) != null){
                return true;
            }
        }
        return false;
    }
    // Checks a position exists on the tile map
    private boolean check_within_range(int[] current){
        if (current[0] < level_tilemap.length-1 && current[0] > -1 && current[1] < level_tilemap.length-1 && current[1] > -1 ){
            return true;
        }
        return false;
    }
    // Makes all 4 walls around the edge visited
    private void generate_border(){
        for (int i = 0; i < level_tilemap.length; i++) {
            for (int j = 0; j < level_tilemap[i].length; j++) {
                level_tilemap[i][j] = 1;
                if (i==0 || i==level_tilemap.length-1 || j==0 || j==level_tilemap.length-1){
                    visted_tiles[i][j] = true;
                }
            }
        }
    }
    // Returns the tile map
    public int[][] get_level(){
        return level_tilemap;
    }
    // Sets the goal/destination for the player to find
    private void set_goal(){
        // Chooses either the left,top or right wall but not bottom due to the player spawning there
        int wall = random.nextInt(0,3);
        // The goal is also at least half of the tile map away from the player
        int distance = level_tilemap.length/2;
        // Valid used make sure the index is valid or not
        boolean valid = false;
        int index = 0;
        // Left wall
        if (wall == 0){
            // Loops until it finds an empty space on the wall
            while (!valid){
                index =  random.nextInt(distance,level_tilemap.length);
                if (level_tilemap[index][1] == 0){
                    level_tilemap[index][1] = 2;
                    valid = true;
                }
            }
        }
        // Top Wall
        if (wall == 1){
            // Loops until it finds an empty space on the wall
            while (!valid){
                index = random.nextInt(distance, level_tilemap.length);
                if (level_tilemap[level_tilemap.length-2][index] == 0) {
                        level_tilemap[level_tilemap.length - 2][index] = 2;
                        valid = true;
                    }
            }
        }
        // Right Wall
        if (wall == 2){
            // Loops until it finds an empty space on the wall
            while (!valid){
                index = random.nextInt(distance, level_tilemap.length);
                if (level_tilemap[index][level_tilemap.length- 2] == 0) {
                    level_tilemap[index][level_tilemap.length - 2] = 2;
                    valid = true;
                }
            }
        }
    }
}
