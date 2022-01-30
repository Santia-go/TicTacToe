import java.util.*;
public class GameTreeNode {
    static public int nodeCount = 0;
    public static int MIN  = -1;
    public static int NONE =  0;
    public static int DRAW =  0;
    public static int MAX  =  1;

    private int value;
    private int type;
    private int bestMove = -1;
    private Position pos;
    private GameTreeNode[] child = new GameTreeNode[9];

    public int getBestMove() {
        return bestMove;
    }

    // Option 1

    private GameTreeNode(Position p, int goalMin, int goalMax) {
        nodeCount++;
        pos = p; type = p.getPlToMv();
        if (p.won() != NONE) { value = p.won(); return; }
        Iterator<Integer> moves = p.getMoves();
        if (!moves.hasNext()) { value = DRAW; return; }

        value = -2 * type;
        while (moves.hasNext()) {
            int m = moves.next();
            child[m] = new GameTreeNode(p.makeMove(m),
                    goalMin,goalMax);
            if (type == MIN && child[m].value < value || type == MAX && child[m].value > value) {
                value = child[m].value;
                bestMove = m;

                // update goals
                if (type == MIN && goalMax > value) goalMax=value;
                if (type == MAX && goalMin < value) goalMin=value;
                // leave if goal is reached
                if (type == MIN && value <= goalMin) return;
                if (type == MAX && value >= goalMax) return;
            }
        }
    }
    public GameTreeNode(Position p) {
        this(p, MIN, MAX);
    }


    //Option 2

    /*public GameTreeNode(Position p) { nodeCount++;
        pos = p; type = p.getPlToMv();
        // hab ich schon verloren?
        if (p.won() != NONE) { value = p.won(); return; }
        // no more moves --> no winner
        Iterator<Integer> moves = p.getMoves();
        if (!moves.hasNext()) { value = DRAW; return; }
        value = -2*type;
        while (moves.hasNext()) {
            int m = moves.next();
            child[m] = new GameTreeNode(p.makeMove(m));
            if (type == MIN && child[m].value < value ||
                    type == MAX && child[m].value > value) {
                value = child[m].value;
                bestMove = m;
            }
        }
    }*/


    // Option 3
    /*
    public GameTreeNode(Position p) { nodeCount++;
        pos = p; type = p.getPlToMv();
        // hab ich schon verloren?
        if (p.won() != NONE) { value = p.won(); return; }
        // no more moves --> no winner
        Iterator<Integer> moves = p.getMoves();
        if (!moves.hasNext()) { value = DRAW; return; }
        value = -2*type;
        while (moves.hasNext()) {
            int m = moves.next();
            child[m] = new GameTreeNode(p.makeMove(m));
            if (type == MIN && child[m].value < value ||
                    type == MAX && child[m].value > value) {
                value = child[m].value;
                bestMove = m;
                // we won; don't search further
                if (value == type) return;
            }
        }
    }
*/
/*
    private GameTreeNode(Position p,
                         int goalMin, int goalMax) {
        nodeCount++;
        pos = p; type = p.getPlToMv();
        if (p.won() != NONE) { value = p.won(); return; }
        Iterator<Integer> moves = p.getMoves();
        if (!moves.hasNext()) { value = DRAW; return; }

        value = -2*type;
        while (moves.hasNext()) {
            int m = moves.next();
            child[m] = new GameTreeNode(p.makeMove(m),
                    goalMin,goalMax);
// continued...
            if (type == MIN && child[m].value < value ||
                    type == MAX && child[m].value > value) {
                value = child[m].value;
                bestMove = m;

                // update goals
                if (type == MIN && goalMax > value) goalMax=value;
                if (type == MAX && goalMin < value) goalMin=value;
                // leave if goal is reached
                if (type == MIN && value <= goalMin) return;
                if (type == MAX && value >= goalMax) return;
            }  }} // if, while, Konstruktor
    public GameTreeNode(Position p) {
        this(p, MIN, MAX);
    }
*/

}