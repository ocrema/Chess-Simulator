package com.chess;

import java.awt.event.*;
import java.util.BitSet;
import java.util.LinkedList;


public final class KeyManager implements MouseListener, KeyListener, MouseMotionListener {
    //controls keyboard input

    public static final short mouse1 = 256;
    public static final short mouse2 = 257;
    public static final short mouse3 = 258;
    public static final short mouse4 = 259;
    public static final short mouse5 = 260;

    private static int mouseX;
    private static int mouseY;


    private static BitSet keysDown = new BitSet(264);
    private static BitSet keysClicked = new BitSet(264);
    private static BitSet keysReleased = new BitSet(264);

    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }

    public boolean getKeyDown(int i){
        return keysDown.get(i);
    }
    public boolean getKeyClicked(int i){
        return keysClicked.get(i);
    }
    public boolean getKeyReleased(int i){
        return keysReleased.get(i);
    }



    //KeyListener

    //clears keys clicked and released, should be ran every tick
    public void cycleKeys(){
        keysClicked.clear();
        keysReleased.clear();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        keysDown.set(e.getKeyCode());
        keysClicked.set(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {

        keysDown.clear(e.getKeyCode());
        keysReleased.set(e.getKeyCode());

    }


    //MouseListener

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() != 0 && e.getButton() <= 5) {
            keysDown.set(e.getButton() + 255);
            keysClicked.set(e.getButton() + 255);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() != 0 && e.getButton() <= 8) {
            keysDown.clear(e.getButton() + 255);
            keysReleased.set(e.getButton() + 255);

        }
    }



    //MouseMotionListener

    private void updateMousePos(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateMousePos(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateMousePos(e);
    }


    //unused methods that i have to have for some reason

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }



}

