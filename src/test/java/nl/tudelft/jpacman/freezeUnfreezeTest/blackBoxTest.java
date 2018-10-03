package nl.tudelft.jpacman;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Functional testing of Freeze/Unfreeze feature
 *
 * @author Tong Shen
 */
public class blackBoxTest {

    private Launcher launcher;

    /**
     * Launch the user interface.
     */
    @BeforeEach
    void setUpPacman() {
        launcher = new Launcher();
        launcher.launch();
    }

    /**
     * Quit the user interface when we're done.
     */
    @AfterEach
    void tearDown() {
        launcher.dispose();
    }
    
    /**
     * Launch the game, and imitate what would happen in a typical game.
     * Test Freeze/Unfreeze button (spacebar) and its effect on game play.
     *
     * @throws InterruptedException Since we're sleeping in this test.
     */
    @SuppressWarnings({"magicnumber", "methodlength", "PMD.JUnitTestContainsTooManyAsserts"})
    @Test
    void functionalTestingFreeze() throws InterruptedException {
        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);

        // start cleanly and freeze level.
        assertThat(game.isFreezed()).isFalse();
        game.start();
        game.freezeUnfreeze();
        assertThat(game.isFreezed()).isTrue();
        assertThat(game.areNpcsStopped()).isTrue();
        assertThat(player.getScore()).isZero();

        // move left by 1 and get 10 points
        game.move(player, Direction.WEST);
        assertThat(player.getScore()).isEqualTo(10);

        // move up by 2 and get 20 points
        move(game, Direction.NORTH, 2);
        assertThat(player.getScore()).isEqualTo(30);

        // move left by 3 and get 30 points
        move(game, Direction.WEST, 3);
        assertThat(player.getScore()).isEqualTo(60);
        
        // move up by 6 then move right by 3 then move down by 2
        // get no point
        // we're close to monsters
        move(game, Direction.NORTH, 6);
        move(game, Direction.EAST, 3);
        move(game, Direction.SOUTH, 2);
        assertThat(player.getScore()).isEqualTo(60);
        
        // sleep to let monsters move (though they cannot)
        Thread.sleep(500L);
        
        // since monsters are freezed, we should expect to be alive
        assertThat(player.isAlive()).isTrue();
        
        // start game again
        game.start();
        assertThat(game.isInProgress()).isTrue();
        
        // sleep to let monsters move (though they cannot)
        // since start button is not supposed to interfere freeze/unfreeze,
        // we should expect player to be alive and NPC's to be stopped
        Thread.sleep(500L);
        assertThat(game.areNpcsStopped()).isTrue();
        assertThat(player.isAlive()).isTrue();
        
        // stop game
        game.stop();
        assertThat(game.isInProgress()).isFalse();
        assertThat(game.isFreezed()).isTrue();
        
        // unfreeze then start game
        // since freeze/unfreeze is not supposed to take effect when game
        // is not in progress, we should expect NPC's to be stopped and
        // level to be freezed still.
        game.freezeUnfreeze();
        game.start();
        assertThat(game.isInProgress()).isTrue();
        assertThat(game.areNpcsStopped()).isTrue();
        assertThat(game.isFreezed()).isTrue();
        
        // sleep to let monsters move (though they cannot)
        // we should expect player to be alive
        Thread.sleep(500L);
        assertThat(player.isAlive()).isTrue();
        
        // unfreeze
        game.freezeUnfreeze();
        assertThat(game.isFreezed()).isFalse();
        assertThat(game.areNpcsStopped()).isFalse();
        
        // sleep to let monsters move
        Thread.sleep(500L);
        
        // since monsters are not freezed, we should expect to be killed
        assertThat(player.isAlive()).isFalse();

        game.stop();
        assertThat(game.isInProgress()).isFalse();
    }

    /**
     * Make number of moves in given direction.
     *
     * @param game The game we're playing
     * @param dir The direction to be taken
     * @param numSteps The number of steps to take
     */
    public static void move(Game game, Direction dir, int numSteps) {
        Player player = game.getPlayers().get(0);
        for (int i = 0; i < numSteps; i++) {
            game.move(player, dir);
        }
    }
}
