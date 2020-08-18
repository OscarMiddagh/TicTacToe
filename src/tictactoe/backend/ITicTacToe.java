package tictactoe.backend;

public interface ITicTacToe extends TurnBasedGame{
    boolean markMove (int row, int column); //Esto deberia pertenecer a una interface llamada BoardGame
                                            // Pero no cree esa interface por que remplazaria a ITicTacToe
    boolean checkTicTacToe(); // este metodo deberia llamarse checkWin()
                              // y deberia estar en la interface de Game
    char [][] getBoard();
}
