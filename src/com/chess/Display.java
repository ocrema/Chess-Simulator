package com.chess;

import javax.swing.*;
import java.awt.*;

public final class Display extends JPanel {


    public Display() {
        setFocusable(false);

    }

    public void paint(Graphics g) {
        if (Main.board != null){
            Main.board.draw(g);
        }

        g.setColor(new Color(210, 210, 210));
        g.fillRect(0, 0, 720, 60);

        g.setFont(Main.font);
        g.setColor(new Color(39, 39, 39));
        if (!Main.header1.isEmpty()) {
            g.drawString(Main.header1, 10, 50);
        }
        if (!Main.header2.isEmpty()) {
            g.drawString(Main.header2, 400, 50);
        }
    }

}
