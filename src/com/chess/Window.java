package com.chess;


import javax.swing.*;
import java.awt.*;


public final class Window extends JFrame {

    private int width;
    private int height;

    private int screenWidth;
    private int screenHeight;


    public Window(Display display, KeyManager keyManager, int width, int height){
        pack();
        Insets insets = getInsets();
        int addedWidth = insets.left + insets.right;
        int addedHeight = insets.top + insets.bottom;
        this.width = width + addedWidth;
        this.height = height + addedHeight;

        screenWidth = width;
        screenHeight = height;

        setTitle("chess");
        setIconImage(Main.images.get("white_king").getScaledInstance(450, 450, Image.SCALE_DEFAULT));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(display);
        addKeyListener(keyManager);
        getContentPane().addMouseListener(keyManager);
        getContentPane().addMouseMotionListener(keyManager);

        setSize(new Dimension(this.width, this.height));

        setLocationRelativeTo(null);

        setVisible(true);

        setResizable(false);


    }


    public int getWidth(){ return width; }

    public int getHeight(){ return height; }


}

