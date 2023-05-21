package Forms;

import javax.swing.*;

public class Wizard extends Characters{

    static ImageIcon iconWizard;

    public Wizard(int lives, int speed, int money, String name) {
        super(lives, speed, money, name);
    }

    protected static ImageIcon wizardUp() {
        iconWizard = new ImageIcon("src/Images/wizard/wizard_up.gif");

        return iconWizard;
    }

    protected static ImageIcon wizardDown() {
        iconWizard = new ImageIcon("src/Images/wizard/wizard_down.gif");

        return iconWizard;
    }

    protected static ImageIcon wizardRight() {
        iconWizard = new ImageIcon("src/Images/wizard/wizard_right.gif");

        return iconWizard;
    }

    protected static ImageIcon wizardLeft() {
        iconWizard = new ImageIcon("src/Images/wizard/wizard_left.gif");

        return iconWizard;
    }

}
