package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;
import tictactoe.controller.IObservable;
import tictactoe.controller.IObserver;
import tictactoe.controller.MyEvent;

public class ConsoleTicTacToe extends Console implements IObserver {
    private IObservable gameObservable;
    public ConsoleTicTacToe(ITicTacToe game) {
        super(game);
        gameObservable = (IObservable) game; // Si no fuera de esta manera tendriamos que llamar
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

}
