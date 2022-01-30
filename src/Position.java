import java.util.*;
public class Position {

    public static int MIN  = -1;
    public static int NONE =  0;
    public static int EMPTY = 0;
    public static int MAX  =  1;

    private int[] arena;
    private int playerToMove = MIN;
    public Position() { arena = new int[9]; }
    public Position(Position p) {
        arena = (int[]) p.arena.clone();
        playerToMove = p.playerToMove;
    }
    public Position makeMove(int place) {
        Position p = new Position(this);
        p.arena[place] = playerToMove;
        p.playerToMove = -playerToMove;
        return p;
    }
    private boolean free(int place) {
        return (arena[place] == EMPTY);
    }
    public boolean movePossible(int pl) {
        return (getMoves().hasNext() && free(pl));
    }
    public int getPlToMv() {
        return playerToMove;
    }

    private String printLine(int l) {
        String res = "";
        for (int i=3*l; i<3*l+3; ++i) {
            if (i != 3*l) res+= "";
            res += arena[i] == MIN ?  "x" :
                    arena[i] == MAX ?  "o" :
                            "." ;
        }
        return res;
    }
    public String toString() {
        String res = "";
        for (int l=0; l<3; ++l) {
            res += printLine(l);
            if (l<2) res += '\n';
        }
        return res;
    }
    private class PossibleMoves implements Iterator<Integer> {
        private int nxt = 0;
        public boolean hasNext() {
            if (won() != NONE) return false;
            for (; nxt<9; nxt++)
                if (free(nxt)) return true;
            return false;
        }
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return nxt++;
        }   }
    public Iterator<Integer> getMoves() {
        return new PossibleMoves();
    }


    // winning triplets
    static final int [][] LINE = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    private boolean won(int who) {
        LineLoop:
        for (int line=0; line<8; ++line) {
            for (int i=0; i<3; i++)
                if (arena[LINE[line][i]] != who)
                    continue LineLoop;
            return true;
        }
        return false;
    }

    public int won() {
        if (won(MAX)) return MAX;
        if (won(MIN)) return MIN;
        return NONE;
    }


    private int force(int l, int who) {
        int result = -1;
        int[] currentLine = LINE[l];
        int currentPlace;
        int currentValue;
        int squares = 0;
        for(int i=0; i<3; i++) {
            currentPlace = currentLine[i];
            currentValue = arena[currentPlace];
            if (currentValue == EMPTY)
                result = currentPlace;
            else
                squares += who*currentValue;
        }
        if (squares == 2) return result;
        return -1;
    } // ... erzwungener Zug auf Zeile l

    public int forcedWin(int type) {
        int result;
        for (int l=0; l<8; l++) {
            result = force(l,type);
            if (result != -1) return result;
        }
        return -1;
    } // ... erzwungener Zug

    //public boolean outOfMoves() {
    //    return freeSquares == 0;
    //}
    //
    //public boolean finished() {
    //    return outOfMoves() || won(MIN) || won(MAX);
    //}
}  // end of class PlayGround



