import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyDialog extends JDialog {
    JButton   repeat, kill;
    public MyDialog(MainFrame frame, String string) {
        setLocation(200,200);
        setSize(300,70);
        setBackground(Color.yellow);
        setForeground(Color.blue);

        setLayout(new FlowLayout());
        add(new Label(string));
        repeat = new JButton("Once again!");
        repeat.addActionListener(frame::dialogAction);
        add(repeat);
        kill = new JButton("Close");
        kill.addActionListener(frame::dialogAction);
        add(kill);
        setVisible(true);
    }
}