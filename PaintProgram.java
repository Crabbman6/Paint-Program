import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class PaintProgram {
    private static List<Point> points = new ArrayList<>();
    private static Color paintColor = Color.BLACK;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(paintColor);
                for (Point point : points) {
                    g.fillOval(point.x - 5, point.y - 5, 10, 10);
                }
            }
        };

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                points.add(new Point(e.getX(), e.getY()));
                panel.repaint();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                points.clear();
                panel.repaint();
            }
        });

        JButton colorButton = new JButton("Change color");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paintColor = JColorChooser.showDialog(frame, "Choose a color", paintColor);
            }
        });

        frame.add(panel);
        frame.add(resetButton, BorderLayout.NORTH);
        frame.add(colorButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
