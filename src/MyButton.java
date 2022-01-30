import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MyButton extends JButton {
    private int number;
    public MyButton(int i) {
        number = i;
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.RED, 2));

    }
    public int getNumber() { return number;}
}