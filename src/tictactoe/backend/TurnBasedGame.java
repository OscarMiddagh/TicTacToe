package tictactoe.backend;

public interface TurnBasedGame extends Game {
    void changeTurn(); // para hacerlo de manera manual
    //char getTurn();
    void activateAutomaticShiftChange();
    void disableAutomaticShiftChange();
    boolean getAutomaticShiftChange();
}
