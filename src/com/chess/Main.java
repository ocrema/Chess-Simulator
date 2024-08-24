package com.chess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static Display display;

    public static KeyManager keyManager;

    public static Window window;

    public static String imageFolderDirectory = System.getProperty("user.dir") + "\\images\\";

    public static HashMap<String, Image> images = new HashMap<>();

    public static final int displayWidth = 720;
    public static final int displayHeight = 780;
    public static final int tileSize = 90;
    public static final int boardWidth = 8;
    public static final int boardHeight = 8;
    public static final int boardXOffset = 0;
    public static final int boardYOffset = 60;

    public static final Font font = new Font("Helvetica", Font.BOLD, 40);;

    public static boolean gameLoop = true;

    public static Board board;
    public static DataAnalyzer dataAnalyzer;

    public static String header1;
    public static String header2;





    public static void main(String[] args) {

        loadImages();

        display = new Display();
        keyManager = new KeyManager();
        window = new Window(display, keyManager, displayWidth, displayHeight);


        dataAnalyzer = new DataAnalyzer();
        board = new Board(boardXOffset, boardYOffset);

        header1 = "";
        header2 = "";


        while (gameLoop && board.getWinner() == null){



            if (keyManager.getKeyClicked(KeyManager.mouse1)){

                board.clicked(keyManager.getMouseX(), keyManager.getMouseY());
            }

            if (keyManager.getKeyReleased(KeyManager.mouse1)){

                board.released(keyManager.getMouseX(), keyManager.getMouseY());
            }

            updateHeaders();

            keyManager.cycleKeys();

            if (window.isFocused()){
                window.repaint();
            }

            if (keyManager.getKeyDown(KeyEvent.VK_ESCAPE)) {
                gameLoop = false;
            }

            try {
                Thread.sleep(30);
            }catch (Exception e){}
        }

        //game over state

        if (board.getWinner() != null){
            board.clearEffects();
            window.repaint();
        }

        boolean gameLoop2 = true;
        while (gameLoop2){

            if (keyManager.getKeyDown(KeyEvent.VK_ESCAPE)) {
                gameLoop2 = false;
            }

            try {
                Thread.sleep(30);
            }catch (Exception e){}
        }

        window.dispose();
    }

    private static void updateHeaders(){

        String[] result = board.getHeaderText();
        header1 = result[0];
        header2 = result[1];
    }

    private static void loadImages(){
        String[] imageNames = {"white_pawn", "black_pawn", "white_rook", "black_rook", "white_knight", "black_knight",
                "white_bishop", "black_bishop", "white_queen", "black_queen", "white_king", "black_king", "x"};

        for (String name : imageNames){
            try{
                images.put(name, ImageIO.read(Main.class.getClassLoader().getResource("chess_" + name + ".png")));
            }
            catch (IOException e){
                System.out.println("File \"chess_" + name + ".png\" not found");
            }
        }
    }
}
