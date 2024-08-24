package com.chess;

import java.awt.*;

public class Effect {
    // visuals that are not connected directly to the board

    private int x;
    private int y;
    private int width;
    private int height;
    private long deleteTime;
    private String imageStr;

    public Effect(int x, int y, int width, int height, long deleteTime, String imageStr){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.deleteTime = System.currentTimeMillis() + deleteTime;
        this.imageStr = imageStr;
    }

    //if an effect's delete time has come it will return true
    public boolean checkForDeletion(){
        return (System.currentTimeMillis() >= deleteTime);
    }

    public void draw(Graphics g){
        g.drawImage(Main.images.get(imageStr), x - width/2, y - height/2, width, height, null);
    }

}
