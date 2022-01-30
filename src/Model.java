interface Model {
    void    makePlayerMove(int place);
    void    makeBestMove();
    boolean movePossible(int place);
    boolean finished();
}