import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import static javax.swing.SwingUtilities.*;


public class MainFrame extends JFrame implements View {
    public static int MIN  = -1;
    private Controller controller;
    private JDialog dia;
    private JPanel  arena;
    private JPanel  side;
    private Container c;

    public MainFrame(Controller con) {
        controller = con;
        setupUI();
        init();
    }
    private void setupUI() {
        c = getContentPane();
        setLocation(100, 100);
        c.setLayout(new FlowLayout());
        setVisible(true);
    }
    public void init() { invokeLater(()->{
        c.removeAll();
        arena = new JPanel();
        arena.setBackground(Color.WHITE);
        arena.setBorder(new LineBorder(Color.WHITE, 5));
        arena.setLayout(new GridLayout(3, 3, 5, 5));
        arena.setPreferredSize(new Dimension(600, 600));
        for (int i=0; i<9;i++) {
            MyButton b = new MyButton(i);
            b.setBorder(new LineBorder(Color.RED, 2));
            b.addActionListener( this::buttonAction );
            arena.add(b); }
        side = new JPanel();
        side.setPreferredSize(new Dimension(0, 600));
        side.setLayout(new BorderLayout());
        c.add(arena);
        c.add(side);
        pack();  });}
    public void put(int place, int type) {
        invokeLater(()->{
            JPanel canvas;
            if (type == MIN) canvas = new Cross();
            else canvas = new Circle();
            arena.remove(place);
            arena.add(canvas, place);
            revalidate();
            repaint();
        });
    }
    public void illegalMove(int place) {
        System.out.println("Illegal move: " + place);
    }
    public void showWinner(int who) {
        String str = "";
        switch(who) {
            case -1: str = "You Win!"; break;
            case  0: str = "Drawn!"; break;
            case  1: str = "Java Wins!"; break;
        }
        final String s = str;
        invokeLater(() -> dia = new MyDialog(this,s));
    }

    public void switchAction(ActionEvent e) {
        new Thread(() -> controller.switchPlayer()).start();
    }
    public void buttonAction(ActionEvent e) {
        MyButton button = (MyButton) e.getSource();
        int place = button.getNumber();
        controller.checkMove(place);
    }
    public void dialogAction(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (e.getActionCommand() == "Close") {
            System.exit(0);
        } else {
            new Thread(() -> controller.restart()).start();
            dia.dispose();
        }
    }

    public static void main(String[] args) {
        invokeLater(()->{
            Controller c = new MyController();
            View  v = new MainFrame(c);
            Model m = new Game(v);
            c.setup(m,v);
        });
    }
}

//comment again
