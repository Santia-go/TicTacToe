import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.locks.*;

public class MyController extends Thread implements Controller {
    private Model game;
    private View view;
    final Lock lock = new ReentrantLock();
    final Condition cond = lock.newCondition();

    Runnable r = null;
    public void exec(Runnable r) {
        if (lock.tryLock()) {
            if (r != null) this.r = r;
            cond.signal();
            lock.unlock();
        }  }
    public void run() {
        lock.lock(); try {
            while (true) {
                while (r == null)
                    cond.await();
                r.run();
                r = null;
            }}
        catch (InterruptedException e) {}
        finally { lock.unlock(); }
    }
    public void setup(Model m, View v) {
        game = m; view = v;
        start();
    }
    public void checkMove(int place) {
        if (game.movePossible(place)) {
            game.makePlayerMove(place);
        }
        else view.illegalMove(place);
    }
    public void switchPlayer() {
        if (game.finished()) return;
        game.makeBestMove();
    }
    public void restart() {
        view.init();
        game = new Game(view);
    }
}




