package Forms;

import javax.swing.*;
import java.awt.*;

public class Warrior extends Characters{

    static ImageIcon iconWarrior;

    public Warrior(int lives, int speed, int money, String name) {
        super(lives, speed, money, name);
    }


    protected static ImageIcon warriorUp(){
        iconWarrior = new ImageIcon("src/Images/warrior/warrior_up.gif");

        return iconWarrior;
    }

    protected static ImageIcon warriorDown(){
        iconWarrior = new ImageIcon("src/Images/warrior/warrior_down.gif");

        return iconWarrior;
    }

    protected static ImageIcon warriorRight(){
        iconWarrior = new ImageIcon("src/Images/warrior/warrior_right.gif");

        return iconWarrior;
    }

    protected static ImageIcon warriorLeft(){
        iconWarrior = new ImageIcon("src/Images/warrior/warrior_left.gif");

        return iconWarrior;
    }
}
