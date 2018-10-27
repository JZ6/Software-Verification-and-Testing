package nl.tudelft.jpacman.level;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests freezeUnfreeze() method of level.
 *
 * @author Tong Shen
 */
// The four suppress warnings ignore the same rule, which results in 4 same string literals
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.TooManyStaticImports"})
class whiteBoxTest {

    /**
     * The level under test.
     */
    private Level level;

    /**
     * An NPC on this level.
     */
    private final Ghost ghost = mock(Ghost.class);

    /**
     * Starting position 1.
     */
    private final Square square1 = mock(Square.class);

    /**
     * Starting position 2.
     */
    private final Square square2 = mock(Square.class);

    /**
     * The board for this level.
     */
    private final Board board = mock(Board.class);

    /**
     * The collision map.
     */
    private final CollisionMap collisions = mock(CollisionMap.class);

    /**
     * Sets up the level with the default board, a single NPC and a starting
     * square.
     */
    @BeforeEach
    void setUp() {
        final long defaultInterval = 100L;
        level = new Level(board, Lists.newArrayList(ghost), Lists.newArrayList(
            square1, square2), collisions);
        when(ghost.getInterval()).thenReturn(defaultInterval);
    }
    
    // WhiteBox Testing for freezeUnfreeze() method in Level class
    // Path Coverage complete
    
    // check the initial value of isFreezed
    /**
     * Validates the level to be not freezed when it isn't started yet.
     */
    @Test
    void noStartFreezed() {
        assertThat(level.isFreezed()).isFalse();
    }
    
    /**
     * Validates the level to be not freezed when it is stopped without starting.
     */
    @Test
    void stopFreezed() {
        level.stop();
        assertThat(level.isFreezed()).isFalse();
    }
    
    /**
     * Validates the level to be not freezed when it is started.
     */
    @Test
    void startFreezed() {
        level.start();
        assertThat(level.isFreezed()).isFalse();
    }
    
    // through path "isInProgress && !isFreezed"
    /**
     * Validates the level to be freezed and NPC's to be stopped
     * when it is started then freezed.
     */
    @Test
    void startFreezeFreezed() {
    	level.start();
        level.freezeUnfreeze();
        assertThat(level.isFreezed()).isTrue();
        assertThat(level.areNpcsStopped()).isTrue();
    }
    
    // through path "isInProgress && isFreezed"
    /**
     * Validates the level to be not freezed and NPC's to be not stopped
     * when it is started then freezed then unfreezed.
     */
    @Test
    void startFreezeUnfreezeFreezed() {
    	level.start();
        level.freezeUnfreeze();
        level.freezeUnfreeze();
        assertThat(level.isFreezed()).isFalse();
        assertThat(level.areNpcsStopped()).isFalse();
    }
    
    // through path "!isInProgress"
    /**
     * Validates the level to be not freezed and NPC's to be stopped
     * when it is started then stopped then freezed.
     */
    @Test
    void startStopFreezeFreezed() {
    	level.start();
    	level.stop();
        level.freezeUnfreeze();
        assertThat(level.isFreezed()).isFalse();
        assertThat(level.areNpcsStopped()).isTrue();
    }
    
    /**
     * Validates the level to be not freezed and NPC's to be not stopped
     * when it is started then stopped then freezed then started.
     */
    @Test
    void startStopFreezeStartFreezed() {
    	level.start();
    	level.stop();
        level.freezeUnfreeze();
        level.start();
        assertThat(level.isFreezed()).isFalse();
        assertThat(level.areNpcsStopped()).isFalse();
    }
    
}
