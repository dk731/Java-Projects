package Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Strings {
    static JFrame myFrame;
    static PanelToDraw myPanel;
    static int pointAmount = 300;
    static int multiplier = 20;
    static JSlider jPointsAmount , jMultiplier , jSpeed , jColors;
    static JTextField jPointsAmountT , jMultiplierT;

    public static void main(String[] args) {

        myFrame = new JFrame("123");


        myPanel = new PanelToDraw();

        myFrame.setContentPane( myPanel);

        jPointsAmount = new JSlider(JSlider.VERTICAL);
        jMultiplier = new JSlider(JSlider.VERTICAL);
        jSpeed = new JSlider(JSlider.HORIZONTAL);
        jColors = new JSlider(JSlider.HORIZONTAL);
        jPointsAmountT = new JTextField(5);
        jMultiplierT = new JTextField(5);

        jMultiplierT.setText(String.format ("%.1f", ((float)multiplier/(float)10)));
        jPointsAmountT.setText(String.valueOf(pointAmount));

        jColors.setMaximum(1000);
        jColors.setMinimum(1);
        jColors.setValue(500);

        jSpeed.setMaximum(999);
        jSpeed.setMinimum(0);
        jSpeed.setValue(500);

        jPointsAmount.setMaximum(10000);
        jPointsAmount.setMinimum(3);

        jMultiplier.setMaximum(0);
        jMultiplier.setMaximum(1000);

        myPanel.add(jPointsAmount);
        myPanel.add(jMultiplier);
        myPanel.add(jPointsAmountT);
        myPanel.add(jMultiplierT);
        myPanel.add(jSpeed);
        myPanel.add(jColors);

        jPointsAmount.setValue(pointAmount);
        jMultiplier.setValue(multiplier);



        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.setBounds( 0 , 0 , 600 , 600);


        jPointsAmount.setBounds(10 , 10 , 20 , myFrame.getHeight() -35);
        jMultiplier.setBounds( myFrame.getWidth()-30 , 10 , 20 , myFrame.getHeight() -35);
        jPointsAmountT.setBounds(40 , myFrame.getHeight() - 80, 50,20);
        jMultiplierT.setBounds(myFrame.getWidth()-90 , myFrame.getHeight() - 80 , 50,20);
        jSpeed.setBounds(100,15,myFrame.getWidth()-200,20);
        jColors.setBounds(100,myFrame.getHeight() - 45,myFrame.getWidth()-200,20);

        myFrame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - myFrame.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - myFrame.getHeight()) / 2);

        myPanel.center.x = ( myFrame.getWidth() / 2 );
        myPanel.center.y = ( myFrame.getHeight() / 2 );
        myPanel.radius = 200;

        myPanel.drawPoints(pointAmount);

        myFrame.setLayout(null);
        myFrame.setVisible(true);


        addListeners();
        myPanel.drawLines();


    }

    public static void addListeners () {

        jPointsAmount.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                pointAmount = jPointsAmount.getValue();
                myPanel.drawLines();
                jPointsAmountT.setText(String.valueOf(pointAmount));
            }
        });

        jMultiplier.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                multiplier = jMultiplier.getValue();
                myPanel.drawPoints(pointAmount);
                myPanel.drawLines();
                jMultiplierT.setText(String.format ("%.1f", ((float)multiplier/(float)10)));
            }
        });

        jMultiplierT.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        multiplier = (int)(Float.valueOf(jMultiplierT.getText()) * 10);
                        if (multiplier > 1000) {
                            multiplier = 1000;
                        }
                        jMultiplier.setValue(multiplier);
                        myPanel.drawLines();
                    }
                }
        );

        jPointsAmountT.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        pointAmount = Integer.valueOf(jPointsAmountT.getText());
                        if (pointAmount > 10000) {
                            pointAmount = 10000;
                        }
                        jPointsAmount.setValue(pointAmount);
                        myPanel.drawLines();
                    }
                }
        );

        jSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                myPanel.smallstep = (float)jSpeed.getValue() / 100000;
                myPanel.drawLines();
            }
        });

        jColors.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                myPanel.colorMult = (float)jColors.getValue() / 100;
                myPanel.drawLines();
            }
        });

        myPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                myFrame.setBounds( myFrame.getX() , myFrame.getY() , myFrame.getHeight() , myFrame.getHeight());

                myPanel.center.x = myFrame.getWidth()/2;
                myPanel.center.y = myFrame.getHeight()/2;
                myPanel.radius = (myFrame.getHeight() - 100) / 2;




                jPointsAmount.setBounds(10 , 10 , 20 , myFrame.getHeight() -35);
                jMultiplier.setBounds( myFrame.getWidth()-30 , 10 , 20 , myFrame.getHeight() -35);
                jPointsAmountT.setBounds(40 , myFrame.getHeight() - 80, 50,20);
                jMultiplierT.setBounds(myFrame.getWidth()-90 , myFrame.getHeight() - 80 , 50,20);
                jSpeed.setBounds(50,15,myFrame.getWidth()-100,20);
                jColors.setBounds(50,myFrame.getHeight() - 45,myFrame.getWidth()-100,20);
                myPanel.drawLines();
            }
        });


    }




}

