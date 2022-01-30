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

    //Variant A

    public GameTreeNode(Position p) { nodeCount++;
        pos = p; type = p.getPlToMv();
        // have I lost already?
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
    }

    // Variant B
    /*
    public GameTreeNode(Position p) { nodeCount++;
        pos = p; type = p.getPlToMv();
        // have I lost already?
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

    //Variant C

    /*
    private GameTreeNode(Position p, int goalMin, int goalMax) {
        nodeCount++;
        pos = p;
        type = p.getPlToMv();
        if (p.won() != NONE) { value = p.won(); return; }
        Iterator<Integer> moves = p.getMoves();
        if (!moves.hasNext()) { value = DRAW; return; }
        value = -2 * type;
        while (moves.hasNext()) {
            int m = moves.next();
            child[m] = new GameTreeNode(p.makeMove(m), goalMin, goalMax);
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
    */

    //Variant D

    /*
    private GameTreeNode(Position p, int goalMin, int goalMax) {
        nodeCount++; pos = p; type = p.getPlToMv();
        if (p.won() != NONE) { value = p.won(); return; }
        Iterator<Integer> moves = p.getMoves();
        if (!moves.hasNext()) { value = DRAW; return; }
        int m;
        if ((m=p.forcedWin(type)) != -1) {
            bestMove = m;
            value = type;
            return;
        }
        if ((m=p.forcedWin(-type)) != -1) {
            bestMove = m;
            child[m] = new GameTreeNode(p.makeMove(m), goalMin, goalMax);
            value = child[m].value;
            return;
        }
        value = -2 * type;
        while (moves.hasNext()) {
            m = moves.next();
            child[m] = new GameTreeNode(p.makeMove(m), goalMin, goalMax);

            if (type == MIN && child[m].value < value || type == MAX && child[m].value > value) {
                value = child[m].value;
                bestMove = m;
                // update goals
                if (type == MIN && goalMax > value) goalMax=value;
                if (type == MAX && goalMin < value) goalMin=value;
                // leave if goal is reached
                if (goalMin >= goalMax) return;
            }
        }
    }
    public GameTreeNode(Position p) {
        this(p, MIN, MAX);
    }
    */

}