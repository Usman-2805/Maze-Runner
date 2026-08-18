package Player;

import Level.renderer;
import MainGame.gameView;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
// PlayerTrack responsible for the camera to follow the play
public class playerTrack implements StepListener {
    private gameView view;
    private player player;
    private renderer renderer;
    private boolean render = true;
    // Constructor takes in view,player and the renderer objects
    public playerTrack(gameView view, player player, renderer renderer){
        this.view = view;
        this.player = player;
        this.renderer = renderer;
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }
    // After every physics step it centers the view to be on the player and renders the tiles
    @Override
    public void postStep(StepEvent stepEvent) {
        view.setCentre(player.getPlayerBody().getPosition());
        // Destroys all previous tiles
        renderer.destroy_all_tiles();
        // If still rendering it renders the level
        if (render) {
            renderer.render_level(this.player);
        }
        // Checks if the player has reached the goal stopping the timer and render
        if (renderer.check_goal_reached()){
            render = false;
            view.getTimer().stop_timer();
        }
        // Checks if the time has ran out stopping the timer and render
        if (view.getTimer().check_end_timer()){
            render = false;
        }
    }
}
