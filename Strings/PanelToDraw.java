package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class PanelToDraw extends JPanel {

    public List<Point> points = new ArrayList<>();
    public List<Point> lines = new ArrayList<>();
    public Point center = new Point( 0 ,0);
    public int radius = 1200;
    private int pointRadius = 7;
    float hsbStep = 1 , hsbMoved = 0;
    float smallstep = 0.0005f;
    float colorMult = 1;


    private void doDrawing(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        for (Point i : points) {

            g2.fillOval( i.x - (pointRadius / 2) , i.y - (pointRadius / 2) , pointRadius , pointRadius);

        }

        for (int i = 0 ; i < lines.size(); i ++) {

            float curColor = hsbMoved + i*hsbStep*colorMult;
            System.out.println(colorMult);

            if (curColor > 1) {
                curColor =  curColor -1;
            }

            g2.setColor( Color.getHSBColor( curColor , 1 , 1) );
            g2.setStroke( new BasicStroke(1));
            g2.drawLine(lines.get(i).x , lines.get(i).y , lines.get(i).x2 , lines.get(i).y2);

        }



        if (hsbMoved + smallstep >= 1) {
            hsbMoved = hsbMoved - 1 ;
        } else {
            hsbMoved = hsbMoved + smallstep;
        }

    }

    @Override
    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        paintComponents(g);
        doDrawing(g);
        repaint();


    }

    public void drawPoints ( int a) {

        double angle  = Math.PI * 2  / a;
        int hei , wid;

        points.clear();

        for (int i = 0 ; i < a ; i++) {

            hei = (int)Math.round(Math.sin(angle*i) * radius);
            wid = (int)Math.round(Math.cos(angle*i) * radius);

            points.add( new Point(center.x - wid , center.y - hei));

        }



    }

    public void drawLines ( ) {

        drawPoints(Strings.pointAmount);
        int tempMult;
        lines.clear();

        for (int i = 0; i < Strings.pointAmount ; i++) {
            tempMult = i * (Strings.multiplier);

            if ( Strings.multiplier % 10 == 0) {
                tempMult = tempMult / 10;
            } else {
                tempMult = (int) (tempMult / 10);
            }

            if ( tempMult < Strings.pointAmount) {

                lines.add(new Point( points.get(i).x , points.get(i).y , points.get(tempMult).x , points.get(tempMult).y ));
            } else {
                tempMult = tempMult % Strings.pointAmount;

                lines.add(new Point( points.get(i).x , points.get(i).y , points.get(tempMult).x , points.get(tempMult).y ));
            }



        }

        hsbStep = (float)1 / Strings.pointAmount;

    }



}

class Point {
    int x , y , x2 , y2;

    public Point (int x , int y ) {
        this.x = x;
        this.y = y;
    }

    public Point (int x , int y , int x2 , int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }
}


