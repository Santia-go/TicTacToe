interface View {
    void put(int place, int type);
    void showWinner(int type);
    void illegalMove(int type);
    void init();
}