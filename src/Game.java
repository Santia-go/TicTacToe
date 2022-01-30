class DummyView implements View {
    public void put(int place, int type){};
    public void showWinner(int type){};
    public void illegalMove(int type){};
    public void init(){};
}
public class Game implements Model {
    private Position p;
    private GameTreeNode g;
    private View view;

    Game(View v) {
        view = v;
        p = new Position();
    }
    private void initTree() {
        try {Thread.sleep(500);}
        catch(InterruptedException e) {}
        g.nodeCount = 0;
        g = new GameTreeNode(p);
        System.out.println("Generate tree... (" + g.nodeCount + " nodes)");
    }
    private void makeMove(int place) {
        view.put(place,p.getPlToMv());
        p = p.makeMove(place);
        if (finished())
            view.showWinner(p.won());
    }
    public void makeBestMove() {
        initTree();
        makeMove(g.getBestMove());
    }
    public void makePlayerMove(int place) {
        makeMove(place);
        if (!finished()) {
            makeBestMove();
        }
    }
    public boolean movePossible(int place) {
        return p.movePossible(place);
    }
    public boolean finished() {
        return !p.getMoves().hasNext();
    }
    public static void main(String[] args) {
        Game game = new Game(new DummyView());
        for (int i = 0; i < 9; ++i) {
            if (!game.finished()) {
                game.makeBestMove();
                System.out.println(game.p);
            }
            else System.out.println("No more moves!");
        }
        /*while (!game.finished()) {
            game.makeBestMove();
            System.out.println(game.p);
        }*/
    }
}