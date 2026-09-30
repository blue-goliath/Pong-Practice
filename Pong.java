import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Pong extends JPanel {
//Initial Values
    double x = 400;
    double y = 300;
    double y1 = 250;
    double y2 = 250;
    double velocityx = 5;
    double velocityy = 0;
    double rx = 0;
    double rw = 10;
    double rh = 100;
    double rc = 20;
    double scoreLeft = 0;
    double scoreRight = 0;

    // Paddle Class
    static class RectangleObj {
        double x, y, w, h;

        RectangleObj(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }
    // Ball Class
    static class CircleObj {
        double x, y, r;

        CircleObj(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    // Paddle List (Set in Array for Possibility of Multiple Objects
    ArrayList<RectangleObj> rectangles = new ArrayList<>();

    // Ball List (Easily Updatable for New Objects)
    ArrayList<CircleObj> circles = new ArrayList<>();

    // Create Paddle and Ball
    RectangleObj Paddle1 = new RectangleObj(rx,y1,rw,rh);
    RectangleObj Paddle2 = new RectangleObj(790,y2,rw,rh);
    CircleObj Ball = new CircleObj(x-rc,y-rc,rc);

    // Paddle-Ball Collision Logic
    void checkCollisions() {
        //Diagonal Distance of Screen - Paddles' Width
        double dist = Math.hypot(780 , y);
        for (CircleObj c : circles) {
            for (RectangleObj r : rectangles) {
                //Define Center of Paddle & Ball
                double rectcentx = r.x + rw/2;
                double rectcenty = r.y + rh/2;
                double circcentx = c.x + c.r/2;
                double circcenty = c.y + c.r/2;

                double dx = Math.abs(rectcentx - circcentx);
                double dy = Math.abs(rectcenty - circcenty);


                if (dx <= (rw/2+c.r/2) && dy <= (rh/2+c.r/2)) {
                    velocityx = -velocityx;
                    double maxdeg = Math.asin(Ball.y/dist);
                    // Ball Reflection Logic
                    if (x>400) {
                        double incidence = (Paddle1.y + rh/2 - Ball.y) / (rh/2)*maxdeg;
                        velocityy = velocityx * Math.tan(incidence);

                    }
                    else {
                        double incidence = (Paddle2.y + rh/2 - Ball.y) / (rh/2)*maxdeg;
                        velocityy = velocityx * Math.tan(incidence);
                    }

                }
            }
        }
    }



    public Pong() {
        //Game Object Initialization
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.CYAN);
        rectangles.add(Paddle1);
        rectangles.add(Paddle2);
        circles.add(Ball);
        Timer timer = new Timer(16, new ActionListener() {
            // Scoring/Ball Logic
            public void actionPerformed(ActionEvent d) {
            if (scoreLeft < 10 && scoreRight < 10) {
                if (Ball.x > 800) {
                    Ball.x = 400;
                    Ball.y = 300;
                    scoreLeft += 1;
                }
                if (Ball.x < 0) {
                    Ball.x = 400;
                    Ball.y = 300;
                    scoreRight += 1;
                }
                if (Ball.y  > 600 - Ball.r) {
                    velocityy = -velocityy;
                }
                if (Ball.y  < 0 - Ball.r) {
                    velocityy = -velocityy;
                } else Ball.x += velocityx;
                Ball.y += velocityy;


                checkCollisions();
                repaint();
            }
            }
        });

        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (Paddle1.y > 0) {
                        Paddle1.y -= 10;
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    if (Paddle1.y + Paddle1.h < 600) {
                        Paddle1.y += 10;
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    if (Paddle2.y > 0) {
                        Paddle2.y -= 10;
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    if (Paddle2.y + Paddle2.h < 600) {
                        Paddle2.y += 10;
                    }

                }
                //Pause Feature
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    timer.stop();
                }
                if (e.getKeyCode() == KeyEvent.VK_K) {
                    timer.start();
                }
                //Game End
                if (scoreLeft >=10 || scoreRight >= 10) {
                    timer.stop();
                }
                repaint();
            }
        });
        timer.start();

    }
    @Override
    //Graphics
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        for (RectangleObj r : rectangles) {
            g.fillRect((int)r.x, (int)r.y, (int)r.w, (int)r.h);
        }

        g.setColor(Color.BLACK);
        for (CircleObj c : circles) {
            g.fillOval( (int) c.x, (int) c.y, (int) c.r, (int) c.r);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(""+ scoreLeft, 300, 50);
        g.drawString(""+scoreRight,500,50);
        if (scoreLeft >=10 || scoreRight >= 10) {
            if (scoreLeft >= 10) g.drawString("Player 1 Wins!", 100, 300);
            if (scoreRight >= 10) g.drawString("Player 2 Wins!", 400, 300);
        }
    }
    //Game Initialization
    public static void main(String[] args) {
        JFrame window = new JFrame("Pong");
        Pong game = new Pong();
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
