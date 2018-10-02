package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
/**
 * Test various aspects of board.
 *
 * @author Jeroen Roosen 
 */
class BoardTest {

    private static final int MAX_WIDTH = 2;
    private static final int MAX_HEIGHT = 3;

    /**
     * Verify assertThrows and exceptions is working.
     * From https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions
     */
    @Test
    void exceptionTesting() {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
    }

    /**
     * Verifies grid not null.
     */
    @Test
    void verifyGridNotNull() {
        assertThrows(AssertionError.class, () -> {
            new Board(null);
        });
    }

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
    @ParameterizedTest
    @CsvSource({
            "-1, -1",   //Both parameters invalid.
            "-1, 99",
            "99, -1",
            "99, 99",
			
			"2, 3",		//X & Y on Maximum

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
            "0, 2",
            "1, 2"
    })
    void verifyWithinBordersTrue(int x, int y) {
        assertThat(board.withinBorders(x, y)).isEqualTo(true);
    }

    /**
     * Verifies withinBorders assertion error when square not withinBorders.
     */
    @Test
    void verifySquareAtWithinBorders() {
        assertThrows(AssertionError.class, () -> {
            board.squareAt(-1, -1);
        });
    }

    /**
     * Verifies invariant assertion error when some grid squares are null.
     */
    private Board boardWithNullSquares;
    @Test
    void verifyGridSquaresNotNull() {
        final Square[][] gridWithNullSquares = {
            { mock(Square.class), null, mock(Square.class) },
            { mock(Square.class), mock(Square.class), mock(Square.class) },
        };
        assertThrows(AssertionError.class, () -> {
            boardWithNullSquares = new Board(gridWithNullSquares);
        });
    }
}
