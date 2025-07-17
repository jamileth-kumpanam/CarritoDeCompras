package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class MiJDesktopPane extends JDesktopPane {

    public MiJDesktopPane() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        GradientPaint cielo = new GradientPaint(0, 0, new Color(135, 206, 250), 0, height / 2, new Color(25, 150, 255));
        g2d.setPaint(cielo);
        g2d.fillRect(0, 0, width, height / 2);

        GradientPaint cesped = new GradientPaint(0, height / 2, new Color(34, 139, 34), 0, height, new Color(10, 80, 10));
        g2d.setPaint(cesped);
        g2d.fillRect(0, height / 2, width, height / 2);

        g2d.setColor(new Color(255, 223, 0));
        g2d.fillOval(width - 120, 40, 80, 80);
        g2d.setColor(new Color(255, 255, 180, 100));
        g2d.fillOval(width - 130, 30, 100, 100);

        g2d.setColor(new Color(80, 80, 80));
        int[] xMont1 = {50, 150, 250};
        int[] yMont1 = {height / 2, height / 4, height / 2};
        g2d.fillPolygon(xMont1, yMont1, 3);

        g2d.setColor(new Color(100, 100, 100));
        int[] xMont2 = {180, 280, 380};
        int[] yMont2 = {height / 2, height / 3, height / 2};
        g2d.fillPolygon(xMont2, yMont2, 3);

        g2d.setColor(Color.WHITE);
        int[] xNieve = {180, 230, 280};
        int[] yNieve = {height / 3, height / 4, height / 3};
        g2d.fillPolygon(xNieve, yNieve, 3);

        g2d.setColor(new Color(101, 67, 33));
        g2d.fillRect(350, height / 2 - 80, 20, 80);

        g2d.setColor(new Color(34, 139, 34));
        g2d.fillOval(330, height / 2 - 120, 60, 60);
    }
}
