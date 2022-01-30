import java.awt.*;
import javax.swing.*;

public class Cross extends JPanel {
    public Cross() {
        setBackground(Color.white);
    }
    private void cross(int width, int height, Graphics page) {
        int[] x0 = {
                width/10, width - width/5,
                width - width/10, width/5
        };
        int[] y = {
                height/5, height - height/10,
                height - height/5, height/10
        };
        page.fillPolygon(x0,y,4);
        int[] x1 = {
                width - width/10, width/5,
                width/10, width - width/5
        };
        page.fillPolygon(x1,y,4);
    }
    public void paintComponent(Graphics page) {
        ((Graphics2D) page).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) page).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        page.setColor(Color.white);
        page.fillRect(0, 0, width, height);
        page.setColor(Color.black);
        cross(width, height, page);
    }
}