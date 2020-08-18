package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;
import tictactoe.backend.ITicTacToeObservable;
import tictactoe.controller.IObserver;
import tictactoe.controller.MyEvent;

public class ConsoleTicTacToe extends Console implements IObserver {
    private ITicTacToeObservable gameObservable;
    public ConsoleTicTacToe(ITicTacToe game) {
        super(game);
        gameObservable = (ITicTacToeObservable) game; // Si no fuera de esta manera tendriamos que llamar
                                                      //especificamente a TicTacToe ya no a su interfaz
        gameObservable.addListener(this);
    }
    @Override
    public void update(MyEvent event) {
        if(event.getPropertyName().equals("markMove")){
            changeLabelTurn();
            showBoardGame();
            checkStatusGame();
        }
        if(event.getPropertyName().equals("create")){
            setLabelsMessagesOptionsInit();
        }

    }

    private void changeLabelTurn(){
        if(piece.equals("X")){
            piece = "O";
        }else{
            piece = "X";
        }
    }
    private void checkStatusGame(){
        if(game.checkTicTacToe()){
            String winner = String.valueOf(game.winner());
            System.out.println(helper.messageWinnerGame(winner) + "\n");
            starSubMenu();
        }else{
            if (game.draw()){
                System.out.println(helper.messageDrawGame() + "\n");
                starSubMenu();
            }else{
                System.out.print("- enter the play number " + piece + " : ");
            }
        }
    }

    private void starSubMenu() {
        System.out.println(helper.colorGreen() + "> Press 10 new game \n> Press 11 to the exit game" + helper.resetColor() + "\n");
        System.out.print("- enter the option: ");
    }

}
