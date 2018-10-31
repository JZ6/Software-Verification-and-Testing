package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test various aspects of board.
 *
 * @author Jeroen Roosen 
 */
class BoardTest {

    private static final int MAX_WIDTH = 2;
    private static final int MAX_HEIGHT = 3;

    private final Square[][] grid = {
        { mock(Square.class), mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class), mock(Square.class) },
    };
    private final Board board = new Board(grid);

    /**
     * Verifies the board has the correct width.
     */
    @Test
    void verifyWidth() {
        assertThat(board.getWidth()).isEqualTo(MAX_WIDTH);
    }

    /**
     * Verifies the board has the correct height.
     */
    @Test
    void verifyHeight() {
        assertThat(board.getHeight()).isEqualTo(MAX_HEIGHT);
    }

    /**
     * Verify that squares at key positions are properly set.
     * @param x Horizontal coordinate of relevant cell.
     * @param y Vertical coordinate of relevant cell.
     */
    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 2",
        "0, 1"
    })
    void testSquareAt(int x, int y) {
        assertThat(board.squareAt(x, y)).isEqualTo(grid[x][y]);
    }

        /**
     * Verify invalid WithinBorders parameters using Boundary Value Testing.
     * @param x Horizontal coordinate of relevant cell.
     * @param y Vertical coordinate of relevant cell.
     */

    // x >= 0 && x < 2 && y >= 0 && y < 3
    @ParameterizedTest
    @CsvSource({
            "-1, -1",   //Both parameters invalid.
            "-1, 99",
            "99, -1",
            "99, 99",
			
			"2, 1",		//X & Y on Boundary
            "1, 3",

            "-1, 1",    //One parameter invalid.
            "99, 1",
            "1, 99",
            "1, -1"
    })
    void verifyWithinBordersFalse(int x, int y) {
        assertThat(board.withinBorders(x, y)).isEqualTo(false);
    }

     /**
     * Verify valid WithinBorders parameters using Boundary Value Testing.
     * @param x Horizontal coordinate of relevant cell.
     * @param y Vertical coordinate of relevant cell.
     */
    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 0",
            "0, 1",
            "1, 1"
    })
    void verifyWithinBordersTrue(int x, int y) {
        assertThat(board.withinBorders(x, y)).isEqualTo(true);
    }
}
