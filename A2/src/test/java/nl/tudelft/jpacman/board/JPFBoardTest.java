package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class JPFBoardTest {

    private static final int MAX_WIDTH = 2;
    private static final int MAX_HEIGHT = 3;

    private final Square[][] grid = { { mock(Square.class), mock(Square.class), mock(Square.class) },
            { mock(Square.class), mock(Square.class), mock(Square.class) }, };
    private final Board board = new Board(grid);

    @Test
    void test0() {
        assertThat(board.withinBorders(0, 0)).isEqualTo(true);
    }

    @Test
    public void test1() {
        assertThat(board.withinBorders(0, 3)).isEqualTo(false);
    }

    @Test
    public void test2() {
        assertThat(board.withinBorders(0, -10)).isEqualTo(false);
    }

    @Test
    public void test3() {
        assertThat(board.withinBorders(2, -10)).isEqualTo(false);
    }

    @Test
    public void test4() {
        assertThat(board.withinBorders(-10, -10)).isEqualTo(false);
    }

    @Test
    public void test5() {
        assertThat(board.withinBorders(1, 1)).isEqualTo(true);
    }

    @Test
    public void test6() {
        assertThat(board.withinBorders(2, 0)).isEqualTo(false);
    }

    @Test
    public void test7() {
        assertThat(board.withinBorders(-10, 3)).isEqualTo(false);
    }

    @Test
    public void test8() {
        assertThat(board.withinBorders(-10, 0)).isEqualTo(false);
    }

    @Test
    public void test9() {
        assertThat(board.withinBorders(2, 3)).isEqualTo(false);
    }
}
