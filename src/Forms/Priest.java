package Forms;

import javax.swing.*;

public class Priest extends Characters{
    static ImageIcon iconPriest;

    public Priest(int lives, int speed, int money, String name) {
        super(lives, speed, money, name);
    }

    protected static ImageIcon priestUp() {
        iconPriest = new ImageIcon("src/Images/priest/priest_up.gif");

        return iconPriest;
    }
    protected static ImageIcon priestDown() {
        iconPriest = new ImageIcon("src/Images/priest/priest_down.gif");

        return iconPriest;
    }
    protected static ImageIcon priestRight() {
        iconPriest = new ImageIcon("src/Images/priest/priest_right.gif");

        return iconPriest;
    }
    protected static ImageIcon priestLeft() {
        iconPriest = new ImageIcon("src/Images/priest/priest_left.gif");

        return iconPriest;
    }

}
