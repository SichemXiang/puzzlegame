package com.itheima.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseJFrame extends JFrame implements MouseListener {
    JButton jtb1 = new JButton("click me");

    public MouseJFrame(){
        this.setSize(403,680);
        this.setTitle("MouseJFrame");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        jtb1.setBounds(0,0,100,50);
        jtb1.addMouseListener(this);


        this.getContentPane().add(jtb1);



        this.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("press");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("release");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exit");
    }
}
