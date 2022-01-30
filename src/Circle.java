import java.awt.*;
import javax.swing.*;

public class Circle extends JPanel {
    // Komponenten haben gleichen Hintergrund ...
    private void circle(int width, int height, Graphics page) {
        page.setColor(Color.red);
        page.fillOval(width/10, height/10, width - width/5, height - height/5);
        page.setColor(Color.white);
        page.fillOval(width/5, height/5, width - 2*width/5, height - 2*height/5);
    }

    @Override
    public void paintComponent(Graphics page) {
        ((Graphics2D) page).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) page).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        page.setColor(Color.white);
        page.fillRect(0, 0, width, height);
        circle(width, height, page);
    }
}