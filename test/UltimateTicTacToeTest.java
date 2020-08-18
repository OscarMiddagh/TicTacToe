import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.ITicTacToe;
import tictactoe.backend.UltimateTicTacToe;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UltimateTicTacToeTest {

    ITicTacToe ticTacToe;
    public UltimateTicTacToeTest(){
        ticTacToe = new UltimateTicTacToe();
    }
    @Before
    public void setUp(){
        ticTacToe.create();
    }

    @After
    public void tearDown(){
    }
    @Test
    public void createBoard(){
        Assert.assertNotNull(ticTacToe.getBoard());
    }
    //MarkMoveTests
    @Test
    public void markMoveXValid(){
        boolean status = ticTacToe.markMove(5,5);
        Assert.assertTrue(status);
    }

    @Test
    public void markMoveOValid(){
        ticTacToe.markMove(5,5);
        //Tienes que marcar en el lugar que le corresponde
        boolean status = ticTacToe.markMove(7,7);
        Assert.assertTrue(status);
    }

    @Test
    public void markMoveXRemarkNotValid(){
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(2,2);
        boolean status = ticTacToe.markMove(2,2);
        Assert.assertFalse(status);
    }

    @Test
    public void markMoveORemarkNotValid(){
        ticTacToe.markMove(1,2);
        boolean status = ticTacToe.markMove(1,2);
        Assert.assertFalse(status);
    }

    @Test
    public void markMoveXLimitRowNotValid(){
        boolean status = ticTacToe.markMove(10,5);
        Assert.assertFalse(status);
    }
    @Test
    public void markMoveXLimitColumnNotValid(){
        boolean status = ticTacToe.markMove(5,10);
        Assert.assertFalse(status);
    }
    //GetBoarTests
    @Test
    public void changeBoardUltimate(){

        ticTacToe.markMove(7,1);
        ticTacToe.markMove(4,4);
        ticTacToe.markMove(3,4);
        ticTacToe.markMove(0,4);
        ticTacToe.markMove(2,5);
        char[][] board1 =    {{'\0','\0','\0',   '\0','O','\0',    '\0','\0','\0'}
                            ,{'\0','\0','\0',    '\0','\0','\0',   '\0','\0','\0'}
                            ,{'\0','\0','\0',    '\0','\0','X',    '\0','\0','\0'}

                            ,{'\0','\0','\0',   '\0','X','\0',     '\0','\0','\0'}
                            ,{'\0','\0','\0',   '\0','O','\0',     '\0','\0','\0'}
                            ,{'\0','\0','\0',   '\0','\0','\0',    '\0','\0','\0'}

                            ,{'\0','\0','\0',   '\0','\0','\0',    '\0','\0','\0'}
                            ,{'\0','X', '\0',   '\0','\0','\0',    '\0','\0','\0'}
                            ,{'\0','\0','\0',   '\0','\0','\0',    '\0','\0','\0'}};
        char[][] board2 = ticTacToe.getBoard();
        Assert.assertArrayEquals(board1, board2);
    }
    @Test
    public void getBoard() {
        char[][] mockBoard = new char[9][9];
        mockBoard[5][5]='X';
        ticTacToe.markMove(5,5);
        assertArrayEquals(ticTacToe.getBoard(),mockBoard);
    }
    @Test
    public void getBoardError() {
        char[][] mockBoard = new char[9][9];
        mockBoard[0][1]='X';
        ticTacToe.markMove(5,5);
        boolean boardsEquals = Arrays.deepEquals(ticTacToe.getBoard(),mockBoard);
        assertFalse(boardsEquals);
    }
    @Test
    public void getBoardNoEmpty() {
        char[][] mockBoard = new char[9][9];
        ticTacToe.markMove(5,5);
        char[][] actual = ticTacToe.getBoard();
        boolean boardsEquals = Arrays.deepEquals(actual,mockBoard);
        assertFalse(boardsEquals);
    }
    @Test
    public void getBoardEmpty() {
        char[][] mockBoard = new char[9][9];
        assertArrayEquals(ticTacToe.getBoard(),mockBoard);
    }
    @Test
    public void isCurrentBoardLocked(){
        ticTacToe.markMove(7,1);
        assertTrue(ticTacToe.markMove(4,4));
    }
    @Test
    public void isNotCurrentBoardLocked(){
        ticTacToe.markMove(7,1);
        assertFalse(ticTacToe.markMove(4,2));
    }
    @Test
    public void isActualBoardFinished(){
        ticTacToe.markMove(7,1);
        ticTacToe.markMove(4,4);
        ticTacToe.markMove(3,4);
        ticTacToe.markMove(0,4);
        ticTacToe.markMove(2,5);
        ticTacToe.markMove(6,6);
        ticTacToe.markMove(2,2);
        ticTacToe.markMove(6,8);
        ticTacToe.markMove(2,8);
        ticTacToe.markMove(6,7);
        ticTacToe.markMove(2,3);
        ticTacToe.markMove(8,0);
        ticTacToe.markMove(8,2);
        assertTrue(ticTacToe.markMove(3,3));
    }

}