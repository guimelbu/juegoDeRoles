package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Principal {
    private JPanel panelMain;
    private JPanel panelMenu;
    private JPanel panelCenter;
    private JPanel panelHeart;
    private JLabel labelCharacter;
    private JLabel labelAmount;
    private JLabel labelMitraMenu;
    private JLabel labelPotionMenu;
    private JLabel labelSwordMenu;
    private JLabel labelTime;
    private JLabel labelGold;
    private JLabel labelMitra;
    private JLabel labelPotion;
    private JLabel labelSword;
    private JLabel labelHeartExtra;
    private JLabel[] labelEnemies, labelHeart;
    private String name;
    private int seconds, minutes, money = 0, chosenCharacter, died = 0, lifes, puntuacion, endChoose;
    Warrior wr;
    Wizard wz;
    Priest p;
    ImageIcon iconUp, iconDown, iconRight, iconLeft, skeletonUp, skeletonDown, skeletonRight, skeletonLeft;
    Timer skeletonTimer, timerPrincipal;

    public Principal() {
        panelMain.setPreferredSize(new Dimension(960, 640));
        panelMain.setSize(new Dimension(960, 640));
        panelMain.setLayout(null);
        panelMain.setFocusable(true);

        showPanelMenu();
        showPanelCenter();

        timerPrincipal = new Timer(1000, new TimerActionListener());
        timerPrincipal.start();

        panelMain.addKeyListener(new PanelMainListener());
    }

    /**
     * Panel situado en la parte superior del jugo donde aparece información adicional (tiempo de juego, vidas, etc...) referente
     * al jugador.
     */
    private void showPanelMenu() {
        panelMenu = new JPanel();
        panelMenu.setLocation(0, 0);
        panelMenu.setSize(panelMain.getWidth(), 64);
        panelMenu.setBackground(Color.BLACK);
        panelMenu.setLayout(null);
        panelMain.add(panelMenu);

        showMessage();

        chosenCharacter = chooseCharacter();
        name = chooseName();

        showMenuCharacter();

        showLabelTime();

        showPanelHeart();

        showPanelMoney();

        showPanelObject();

    }

    /**
     * Mensaje de inicio de juego donde se explican las reglas básicas del juego.
     */
    private void showMessage() {
        JOptionPane.showMessageDialog(null, "Bienvenido/a a 'JUEGO DE ROLES'! \nDonde tendrás que conseguir recolectar 50 monedas y salir de la mazmorra para ganar. \nEsquiva a los esqueletos para no perder vidas! \nY, por último, escoge sabiamente a tu personaje. \nTen en cuenta que si un enemigo toca a: \n- El que guerrero, lo puede eliminar con la espada" +
                "\n- El mago toma la poción, vuelve al inicio y gana una vida \n- El sacerdote no pierde una vida y vuelve al inicio \nBuena suerte!");
    }

    /**
     * JOptionPane donde se da a escoger el personaje con el qu el usuario jugará.
     *
     * @return Int según el personaje escogido (0 = Guerrero, 1 = Mago, 2 = Sacerdote)
     */
    private int chooseCharacter() {
        String[] choose = {"Guerrero", "Mago", "Sacerdote"};

        chosenCharacter = JOptionPane.showOptionDialog(null, "Escoge a tu personaje", "JUEGO DE ROLES", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, choose[0]);

        return chosenCharacter;
    }

    /**
     * JOptionPane donde se da a escoger un nombre para el personaje escogido por el usuario.
     *
     * @return String con el nombre del personaje escogido.
     */
    private String chooseName() {

        return JOptionPane.showInputDialog(null, "Escoge un nombre para tu personaje", "User");
    }

    /**
     * Panel, situado en el menú, donde se añaden los objetos, pero, no enseñan todavía.
     */
    private void showPanelObject() {
        JPanel panelObject = new JPanel();
        panelObject.setSize(111, panelMenu.getHeight());
        panelObject.setLocation(panelMenu.getWidth() / 6 * 5 - panelObject.getWidth() / 2, labelTime.getHeight() / 2);
        panelObject.setBackground(Color.BLACK);
        panelMenu.add(panelObject);

        //Mitra
        labelMitraMenu = new JLabel();
        labelMitraMenu.setSize(32, 32);
        ImageIcon iconMitra = new ImageIcon("src/Images/dungeon/mitra.png");
        Icon mitra = new ImageIcon(
                iconMitra.getImage().getScaledInstance(labelMitraMenu.getWidth(), labelMitraMenu.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMitraMenu.setIcon(mitra);
        panelObject.add(labelMitraMenu);

        //Potion
        labelPotionMenu = new JLabel();
        labelPotionMenu.setSize(32, 32);
        ImageIcon iconPotion = new ImageIcon("src/Images/dungeon/potion.png");
        Icon potion = new ImageIcon(
                iconPotion.getImage().getScaledInstance(labelPotionMenu.getWidth(), labelPotionMenu.getHeight(), Image.SCALE_DEFAULT)
        );
        labelPotionMenu.setIcon(potion);
        panelObject.add(labelPotionMenu);

        //Sword
        labelSwordMenu = new JLabel();
        labelSwordMenu.setSize(32, 32);
        ImageIcon iconSword = new ImageIcon("src/Images/dungeon/sword.png");
        Icon sword = new ImageIcon(
                iconSword.getImage().getScaledInstance(labelSwordMenu.getWidth(), labelSwordMenu.getHeight(), Image.SCALE_DEFAULT)
        );
        labelSwordMenu.setIcon(sword);
        panelObject.add(labelSwordMenu);

        labelMitraMenu.setVisible(false);
        labelPotionMenu.setVisible(false);
        labelSwordMenu.setVisible(false);
    }

    /**
     * Panel, situado en el menú, donde se añaden 2 labels. Una para añadir la imagen de las monedas y la otra para enseñar la cantidad
     * de monedas recogida por el jugador.
     */
    private void showPanelMoney() {
        JPanel panelMoney = new JPanel();
        panelMoney.setSize(100, 32);
        panelMoney.setBackground(Color.BLACK);
        panelMoney.setLocation(panelMenu.getWidth() / 6 * 4 - panelMoney.getWidth() / 2, labelTime.getHeight() / 2);
        panelMenu.add(panelMoney);

        JLabel labelMoney = new JLabel();
        labelMoney.setSize(32, 32);
        ImageIcon iconDollar = new ImageIcon("src/Images/dungeon/dollar.png");
        Icon dollar = new ImageIcon(
                iconDollar.getImage().getScaledInstance(labelMoney.getWidth(), labelMoney.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMoney.setIcon(dollar);

        labelAmount = new JLabel();
        labelAmount.setForeground(Color.WHITE);
        labelAmount.setText(money + " $");

        panelMoney.add(labelMoney);
        panelMoney.add(labelAmount);

    }

    /**
     * Panel, situado en el menú, donde se enseñan tantos JLabel, con una imagen de corazon, como vidas tena el personaje escogido.
     */
    private void showPanelHeart() {
        panelHeart = new JPanel();
        panelHeart.setSize(130, 64);
        panelHeart.setBackground(Color.BLACK);
        panelHeart.setLocation(panelMenu.getWidth() / 2 - panelHeart.getWidth() / 2, panelMenu.getHeight() / 2 - 16);
        panelMenu.add(panelHeart);

        if (chosenCharacter == 0) {
            lifes = wr.getLifes();
        } else if (chosenCharacter == 1) {
            lifes = wz.getLifes();
        } else {
            lifes = p.getLifes();
        }

        labelHeart = new JLabel[lifes];

        for (int i = 0; i < lifes; i++) {
            labelHeart[i] = new JLabel();
            labelHeart[i].setSize(20, 20);
            ImageIcon iconLife = new ImageIcon("src/Images/dungeon/heart.png");
            Icon life = new ImageIcon(
                    iconLife.getImage().getScaledInstance(labelHeart[i].getWidth(), labelHeart[i].getHeight(), Image.SCALE_DEFAULT)
            );
            labelHeart[i].setIcon(life);

            panelHeart.add(labelHeart[i]);
        }
    }

    /**
     * Timer principal donde se temporiza la duración del juego mientras el jugador está viva, con un texto inicial.
     */
    private class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            labelTime.setText("Time " + minutes + ":" + seconds);

            if (seconds == 59) {
                minutes++;
                seconds = 0;
            }
        }
    }

    /**
     * JLabel, situado en el menú, donde se enseña el timepo de jugo al jugador.
     */
    private void showLabelTime() {
        labelTime = new JLabel();
        labelTime.setSize(100, 32);
        labelTime.setLocation(panelMenu.getWidth() / 6 * 2 - labelTime.getWidth() / 2, panelMenu.getHeight() / 4);
        labelTime.setForeground(Color.WHITE);
        labelTime.setText("Time 0:0");
        panelMenu.add(labelTime);
    }

    /**
     * JPanel, situado en el menú, donde se enseña en icono del personaje escogido junto al nombre, también escogido, previamente.
     */
    private void showMenuCharacter() {

        JPanel panelMenuCharacter = new JPanel();
        panelMenuCharacter.setSize(130, 64);
        panelMenuCharacter.setLocation(panelMenu.getWidth() / 6 - panelMenuCharacter.getWidth() / 2, panelMenu.getHeight() / 2 - 22);
        panelMenuCharacter.setBackground(Color.BLACK);

        JLabel labelNameCharacter = new JLabel();
        labelNameCharacter.setSize(60, 60);
        labelNameCharacter.setForeground(Color.WHITE);
        labelNameCharacter.setText(name);
        panelMenuCharacter.add(labelNameCharacter);

        JLabel labelMenuCharacter = new JLabel();
        labelMenuCharacter.setSize(36, 36);

        chooseIconCharacter();

        Icon character = new ImageIcon(
                iconDown.getImage().getScaledInstance(labelMenuCharacter.getWidth(), labelMenuCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMenuCharacter.setIcon(character);
        panelMenuCharacter.add(labelMenuCharacter);

        panelMenu.add(panelMenuCharacter);
    }

    /**
     * Se escogen los iconos principales, según el personaje escogido, y se separan en diferentes iconos, según la dirección del personaje.
     */
    private void chooseIconCharacter() {
        switch (chosenCharacter) {
            case 0:
                wr = new Warrior(5, 3, money, name);
                iconUp = Warrior.warriorUp();
                iconDown = Warrior.warriorDown();
                iconRight = Warrior.warriorRight();
                iconLeft = Warrior.warriorLeft();
                break;
            case 1:
                wz = new Wizard(3, 7, money, name);
                iconUp = Wizard.wizardUp();
                iconDown = Wizard.wizardDown();
                iconRight = Wizard.wizardRight();
                iconLeft = Wizard.wizardLeft();
                break;
            case 2:
                p = new Priest(4, 5, money, name);
                iconUp = Priest.priestUp();
                iconDown = Priest.priestDown();
                iconRight = Priest.priestRight();
                iconLeft = Priest.priestLeft();
                break;
            default:
                break;
        }
    }

    /**
     * Se escogen los iconos para los enemigos según la dirección en la que vayan.
     *
     * @param i         int con la posición del enemigo en el array de enemigos.
     * @param direction int con las 4 direcciones posibles.
     */
    private void chooseIconEnemy(int i, int direction) {
        switch (direction) {
            case 0:
                skeletonUp = Skeleton.skeletonUp();
                Icon icon1 = new ImageIcon(
                        skeletonUp.getImage().getScaledInstance(labelEnemies[i].getWidth(), labelEnemies[i].getHeight(), Image.SCALE_DEFAULT)
                );
                labelEnemies[i].setIcon(icon1);
                break;
            case 1:
                skeletonDown = Skeleton.skeletonDown();
                Icon icon2 = new ImageIcon(
                        skeletonDown.getImage().getScaledInstance(labelEnemies[i].getWidth(), labelEnemies[i].getHeight(), Image.SCALE_DEFAULT)
                );
                labelEnemies[i].setIcon(icon2);
                break;
            case 2:
                skeletonLeft = Skeleton.skeletonLeft();
                Icon icon3 = new ImageIcon(
                        skeletonLeft.getImage().getScaledInstance(labelEnemies[i].getWidth(), labelEnemies[i].getHeight(), Image.SCALE_DEFAULT)
                );
                labelEnemies[i].setIcon(icon3);
                break;
            case 3:
                skeletonRight = Skeleton.skeletonRight();
                Icon icon4 = new ImageIcon(
                        skeletonRight.getImage().getScaledInstance(labelEnemies[i].getWidth(), labelEnemies[i].getHeight(), Image.SCALE_DEFAULT)
                );
                labelEnemies[i].setIcon(icon4);
                break;
            default:
                break;
        }
    }

    /**
     * Panel central donde se desarrollará el juego.
     */
    private void showPanelCenter() {
        panelCenter = new JPanel();
        panelCenter.setLayout(null);
        panelCenter.setLocation(0, panelMenu.getHeight());
        panelCenter.setSize(panelMain.getWidth(), panelMain.getHeight() - panelMenu.getHeight());
        panelMain.add(panelCenter);

        showEnemies();

        showLabelCharacter();

        showLabelObjects();

        Walls.showWall(panelCenter);
    }

    /**
     * Creación de los JLabel de los enemigos y se les asigna una posición aleatoria.
     */
    private void showEnemies() {

        labelEnemies = new JLabel[6];
        for (int i = 0; i < 6; i++) {
            labelEnemies[i] = new JLabel();
            labelEnemies[i].setSize(32, 51);
            int randomX = (int) (Math.random() * (panelCenter.getWidth() - 96) + 32);
            int randomY = (int) (Math.random() * (panelCenter.getHeight() - 96) + 32);
            labelEnemies[i].setLocation(randomX, randomY);
            panelCenter.add(labelEnemies[i]);
        }

        skeletonTimer = new Timer(50, new SkeletonTimer(labelEnemies));
        skeletonTimer.start();
    }

    /**
     * Clase donde se asignará una dirección aleatoria y se controlará el movimiento de los enemigos.
     */
    private class SkeletonTimer implements ActionListener {
        JLabel[] enemyLabels;
        private final int[] directions;

        public SkeletonTimer(JLabel[] enemyLabels) {
            this.enemyLabels = enemyLabels;
            this.directions = new int[6];

            for (int i = 0; i < 6; i++) {
                int randomDirection = (int) (Math.random() * 4);
                this.directions[i] = randomDirection;

                chooseIconEnemy(i, directions[i]);
            }
        }

        /**
         * Función para restringir el campo de movimiento de los enemigos.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 6; i++) {
                int x = enemyLabels[i].getX(), y = enemyLabels[i].getY(), speed = 5;

                switch (directions[i]) {
                    case 0: // Up
                        y -= speed;
                        if (y < Walls.labelWall.getHeight()) {
                            directions[i] = 1;
                            chooseIconEnemy(i, directions[i]);
                        }
                        enemyLabels[i].setLocation(x, y);
                        break;
                    case 1: // Down
                        y += speed;
                        if (y > panelCenter.getHeight() - Walls.labelWall.getHeight() - enemyLabels[i].getHeight()) {
                            y = panelCenter.getHeight() - Walls.labelWall.getHeight() - enemyLabels[i].getHeight();
                            directions[i] = 0;
                            chooseIconEnemy(i, directions[i]);
                        }
                        enemyLabels[i].setLocation(x, y);
                        break;
                    case 2: // Left
                        x -= speed;
                        if (x < Walls.labelWall.getWidth()) {
                            x = Walls.labelWall.getWidth();
                            directions[i] = 3;
                            chooseIconEnemy(i, directions[i]);
                        }
                        enemyLabels[i].setLocation(x, y);
                        break;
                    case 3: // Right
                        x += speed;
                        if (x > panelCenter.getWidth() - Walls.labelWall.getWidth() - enemyLabels[i].getWidth()) {
                            directions[i] = 2;
                            chooseIconEnemy(i, directions[i]);
                        }
                        enemyLabels[i].setLocation(x, y);
                }
            }
            comprobarColisiones();
        }
    }

    /**
     * JLabel para enseñar al personaje en la entrada del juego.
     */
    private void showLabelCharacter() {
        labelCharacter = new JLabel();
        labelCharacter.setSize(44, 44);
        labelCharacter.setLocation(0, 40);

        chooseIconCharacter();

        Icon character = new ImageIcon(
                iconRight.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
        labelCharacter.setIcon(character);
        panelCenter.add(labelCharacter);
    }

    /**
     * Clase para que el usuario pueda controlar al personaje según las teclas pulsadas y se cambia el icono según la dirección utilizada.
     */
    private class PanelMainListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int speed;

            int x = labelCharacter.getX(), y = labelCharacter.getY();

            if (chosenCharacter == 0) {
                speed = wr.getSpeed();
            } else if (chosenCharacter == 1) {
                speed = wz.getSpeed();
            } else {
                speed = p.getSpeed();
            }

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    x += speed;
                    Icon icon1 = new ImageIcon(
                            iconRight.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
                    );
                    labelCharacter.setIcon(icon1);
                    break;
                case KeyEvent.VK_LEFT:
                    x -= speed;
                    Icon icon2 = new ImageIcon(
                            iconLeft.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
                    );
                    labelCharacter.setIcon(icon2);
                    break;
                case KeyEvent.VK_UP:
                    y -= speed;
                    Icon icon3 = new ImageIcon(
                            iconUp.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
                    );
                    labelCharacter.setIcon(icon3);
                    break;
                case KeyEvent.VK_DOWN:
                    y += speed;
                    Icon icon4 = new ImageIcon(
                            iconDown.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
                    );
                    labelCharacter.setIcon(icon4);
                    break;
            }
            if ((x >= 0 && x < 32 && y >= 32 && y <= 64) || (x >= 32 && x < (panelCenter.getWidth() - Walls.labelWall.getWidth() - labelCharacter.getWidth()) && y >= Walls.labelWall.getHeight() && y <= (panelCenter.getHeight() - Walls.labelWall.getHeight() - labelCharacter.getHeight())) || (x >= (panelCenter.getWidth() - Walls.labelWall.getWidth() - labelCharacter.getWidth()) && x <= (panelCenter.getWidth() - labelCharacter.getWidth()) && y >= panelCenter.getHeight() - (Walls.labelWall.getHeight() * 3) && y <= (panelCenter.getHeight() - Walls.labelWall.getHeight() - labelCharacter.getHeight()))) {
                labelCharacter.setLocation(x, y);
            }
        }
    }

    /**
     * Función para enseñar los objetos en el JPanel central en posiciones aleatorias.
     */
    private void showLabelObjects() {
        int[] x = new int[4], y = new int[4];

        for (int i = 0; i < 4; i++) {

            int randomX = (int) (Math.random() * (panelCenter.getWidth() - 96) + 32);
            int randomY = (int) (Math.random() * (panelCenter.getHeight() - 96) + 32);

            x[i] = randomX;
            y[i] = randomY;
        }

        labelGold = new JLabel();
        labelGold.setSize(32, 32);
        labelGold.setLocation(x[0], y[0]);
        ImageIcon iconGold = new ImageIcon("src/Images/dungeon/dollar.png");
        Icon gold = new ImageIcon(
                iconGold.getImage().getScaledInstance(labelGold.getWidth(), labelGold.getHeight(), Image.SCALE_DEFAULT)
        );
        labelGold.setIcon(gold);
        panelCenter.add(labelGold);

        //Object 0
        labelMitra = new JLabel();
        labelMitra.setSize(32, 32);
        labelMitra.setLocation(x[1], y[1]);
        ImageIcon iconMitra = new ImageIcon("src/Images/dungeon/mitra.png");
        Icon mitra = new ImageIcon(
                iconMitra.getImage().getScaledInstance(labelMitra.getWidth(), labelMitra.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMitra.setIcon(mitra);
        panelCenter.add(labelMitra);

        //Object 1
        labelPotion = new JLabel();
        labelPotion.setSize(32, 32);
        labelPotion.setLocation(x[2], y[2]);
        ImageIcon iconPotion = new ImageIcon("src/Images/dungeon/potion.png");
        Icon potion = new ImageIcon(
                iconPotion.getImage().getScaledInstance(labelPotion.getWidth(), labelPotion.getHeight(), Image.SCALE_DEFAULT)
        );
        labelPotion.setIcon(potion);
        panelCenter.add(labelPotion);

        //Object 2
        labelSword = new JLabel();
        labelSword.setSize(32, 32);
        labelSword.setLocation(x[3], y[3]);
        ImageIcon iconSword = new ImageIcon("src/Images/dungeon/sword.png");
        Icon sword = new ImageIcon(
                iconSword.getImage().getScaledInstance(labelSword.getWidth(), labelSword.getHeight(), Image.SCALE_DEFAULT)
        );
        labelSword.setIcon(sword);
        panelCenter.add(labelSword);
    }

    /**
     * Función para realizar diferentes acciones dependiendo de las colisiones con el personaje y los objetos o los enemigos.
     */
    private void comprobarColisiones() {
        Rectangle characterBounds = labelCharacter.getBounds();
        boolean potionToken = false;

        if (characterBounds.intersects(labelGold.getBounds())) {
            int x = (int) (Math.random() * (panelCenter.getWidth() - 96) + 32);
            int y = (int) (Math.random() * (panelCenter.getHeight() - 96) + 32);
            labelGold.setLocation(x, y);
            money += 10;
            labelAmount.setText(money + " $");
        }

        if (characterBounds.intersects(labelMitra.getBounds())) {
            labelMitra.setVisible(false);
            labelMitra.setLocation(0, 0);
            labelMitraMenu.setVisible(true);

            if (chosenCharacter == 0) {
                wr.objects[0] = true;
                wr.setObjects(wr.objects);
            } else if (chosenCharacter == 1) {
                wz.objects[0] = true;
                wz.setObjects(wz.objects);
            } else {
                p.objects[0] = true;
                p.setObjects(p.objects);
            }
        }

        if (characterBounds.intersects(labelPotion.getBounds())) {
            labelPotion.setVisible(false);
            labelPotion.setLocation(0, 0);
            labelPotionMenu.setVisible(true);

            if (chosenCharacter == 0) {
                wr.objects[1] = true;
                wr.setObjects(wr.objects);

            } else if (chosenCharacter == 1) {
                wz.objects[1] = true;
                wz.setObjects(wz.objects);
            } else {
                p.objects[1] = true;
                p.setObjects(p.objects);
            }
        }

        if (characterBounds.intersects(labelSword.getBounds())) {
            labelSword.setVisible(false);
            labelSword.setLocation(0, 0);
            labelSwordMenu.setVisible(true);

            if (chosenCharacter == 0) {
                wr.objects[2] = true;
                wr.setObjects(wr.objects);
            } else if (chosenCharacter == 1) {
                wz.objects[2] = true;
                wz.setObjects(wz.objects);
            } else {
                p.objects[2] = true;
                p.setObjects(p.objects);
            }
        }

        for (int i = 0; i < 6; i++) {
            if (characterBounds.intersects(labelEnemies[i].getBounds())) {
                if (chosenCharacter == 0 && wr.objects[2]) {
                    //warrior
                    labelEnemies[i].setVisible(false);
                    labelEnemies[i].setLocation(0, 0);
                    labelEnemies[i].setSize(30, 30);
                    labelSwordMenu.setVisible(false);

                    wr.objects[2] = false;
                    wr.setObjects(wr.objects);

                } else if (chosenCharacter == 1 && wz.objects[1]) {
                    //wizard
                    labelHeartExtra = new JLabel();
                    labelHeartExtra.setSize(20, 20);
                    ImageIcon iconLife = new ImageIcon("src/Images/dungeon/heart.png");
                    Icon life = new ImageIcon(
                            iconLife.getImage().getScaledInstance(labelHeartExtra.getWidth(), labelHeartExtra.getHeight(), Image.SCALE_DEFAULT)
                    );
                    labelHeartExtra.setIcon(life);
                    panelHeart.add(labelHeartExtra);

                    potionToken = true;

                    labelCharacter.setLocation(0, 48);

                    wz.objects[1] = false;
                    wz.setObjects(wz.objects);

                    labelPotionMenu.setVisible(false);

                } else if (chosenCharacter == 2 && p.objects[0]) {
                    //priest
                    labelMitraMenu.setVisible(false);
                    labelCharacter.setLocation(0, 48);

                    p.objects[0] = false;
                    p.setObjects(p.objects);
                } else {
                    if (potionToken) {
                        labelHeartExtra.setVisible(false);
                        potionToken = false;
                    } else {
                        labelCharacter.setLocation(0, 48);
                        labelHeart[died].setVisible(false);
                        died++;
                    }
                }
            }
        }
        endGame();
    }

    /**
     * Condiciones que se tienen que cumplir para poder terminar con el juego.
     */
    private void endGame() {

        if (money >= 50 && labelCharacter.getX() >= (panelCenter.getWidth() - Walls.labelWall.getWidth() - labelCharacter.getWidth())) {
            timerPrincipal.stop();
            puntuacion = 50 + (15 * (lifes - died)) + (money - 50);
            if (labelMitra.isVisible()) {
                puntuacion += 10;
            }
            if (labelSword.isVisible()) {
                puntuacion += 10;
            }
            if (labelPotion.isVisible()) {
                puntuacion += 10;
            }

            String[] choose = {"Salir", "Ver Rankings"};

            endChoose = JOptionPane.showOptionDialog(null, "¡Felicidades! Has conseguido recolectar las 50 monedas en un tiempo de " + minutes + " minutos con " + seconds + " segundos \nPuntuación: " + puntuacion, "GAME OVER", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, choose[0]);

            ficheroPartida();

        }

        if (died == lifes) {

            if (minutes >= 1) {
                puntuacion += 30;
            }
            if (labelMitra.isVisible()) {
                puntuacion += 10;
            }
            if (labelSword.isVisible()) {
                puntuacion += 10;
            }
            if (labelPotion.isVisible()) {
                puntuacion += 10;
            }
            if (money >= 50) {
                puntuacion += 20;
            }

            timerPrincipal.stop();

            String[] choose = {"Salir", "Ver Rankings"};

            endChoose = JOptionPane.showOptionDialog(null, "No has sido lo suficientemente ágil para derribar a los enemigos. \nPuntuación: " + puntuacion, "GAME OVER", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, choose[0]);

            ficheroPartida();
        }
    }

    /**
     * Escritura sobre el fichero write.txt con la información del usuario referente a la partida.
     */
    private void ficheroPartida() {
        ArrayList<Usuarios> rankingGeneral = new ArrayList<>();
        ArrayList<Usuarios> rankingPersonaje = new ArrayList<>();


        String tipo;
        if (chosenCharacter == 0) {
            tipo = "Guerrero";
        } else if (chosenCharacter == 1) {
            tipo = "Mago";
        } else {
            tipo = "Sacerdote";
        }

        String infoResultats = name + ";" + tipo + ";" + minutes + ":" + seconds + ";" + (lifes - died) + ";" + money + ";" + puntuacion + ":";
        String rutaResultats = "src/resources/write.txt";

        BufferedWriter bw = null;


        try {
            FileWriter fw = new FileWriter(rutaResultats, true);
            bw = new BufferedWriter(fw);
            bw.write(infoResultats);
            bw.newLine();

            BufferedReader br = new BufferedReader(new FileReader(rutaResultats));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String tipoPersonaje = partes[1];

                Usuarios usuario = new Usuarios(partes[0], partes[1], partes[2], partes[3], partes[4], Integer.parseInt(partes[5].split(":")[0]));

                rankingGeneral.add(usuario);

                if (chosenCharacter == 0 && tipoPersonaje.equals("Guerrero")) {
                    rankingPersonaje.add(usuario);
                } else if (chosenCharacter == 1 && tipoPersonaje.equals("Mago")) {
                    rankingPersonaje.add(usuario);
                } else if (chosenCharacter == 2 && tipoPersonaje.equals("Sacerdote")) {
                    rankingPersonaje.add(usuario);
                }
            }

        } catch (IOException e) {
            System.out.println("Error en la escritura del fichero!");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error cerrando el fichero!");
            }
            ensenarRankings(rankingGeneral, rankingPersonaje);
        }
    }

    /**
     * Ordena los arrayList de personajes (general/tipo) según el número de puntuaciones que tienen, para después enseñar los 5 mejores o,
     * en caso de tener menos de 5, todos los elementos que tenga.
     *
     * @param rankingGeneral Ranking sacado del fichero write.txt con la información de todos los usuarios que han jugado al juego.
     * @param rankingPersonaje Ranking sacado del fichero write.txt con la información según el tipo de personaje escogido por el usuario.
     */
    private void ensenarRankings(ArrayList<Usuarios> rankingGeneral, ArrayList<Usuarios> rankingPersonaje) {
        ArrayList<Integer> puntuacionesGenerales = new ArrayList<>();
        ArrayList<Usuarios> rankingGeneralOrdenado = new ArrayList<>();
        ArrayList<Integer> puntuacionesPersonaje = new ArrayList<>();
        ArrayList<Usuarios> rankingPersonajeOrdenado = new ArrayList<>();
        StringBuilder rankingGeneralMensaje, rankingPersonajeMensaje;

        //Ordenar ranking general
        for (Usuarios usuario : rankingGeneral) {
            puntuacionesGenerales.add(usuario.getPuntos());
        }

        Comparator<Integer> comparador = Collections.reverseOrder();
        puntuacionesGenerales.sort(comparador);

        for (Integer puntuacion : puntuacionesGenerales) {
            for (Usuarios usuario : rankingGeneral) {
                if (usuario.getPuntos() == puntuacion) {
                    rankingGeneralOrdenado.add(usuario);
                }
            }
        }

        //Ordenar ranking de personaje
        for (Usuarios usuario : rankingPersonaje) {
            puntuacionesPersonaje.add(usuario.getPuntos());
        }

        puntuacionesPersonaje.sort(comparador);

        for (Integer puntuacion : puntuacionesPersonaje) {
            for (Usuarios usuario : rankingPersonaje) {
                if (usuario.getPuntos() == puntuacion) {
                    rankingPersonajeOrdenado.add(usuario);
                }
            }
        }

        switch (endChoose) {
            case 0:
                System.exit(0);
                break;
            case 1:
                if (rankingGeneralOrdenado.size() < 5) {
                    rankingGeneralMensaje = new StringBuilder("******* RANKING GENERAL *******\n");
                    for (int i = 0; i < rankingGeneralOrdenado.size(); i++) {
                        rankingGeneralMensaje.append(i + 1).append(".- ").append(rankingGeneralOrdenado.get(i).toString());
                    }

                }else {
                    rankingGeneralMensaje = new StringBuilder("******* RANKING GENERAL *******\n").append(("1.- ")).append(rankingGeneralOrdenado.get(0).toString()).append("2.- ").append(rankingGeneralOrdenado.get(1).toString()).append("3.- ").append(rankingGeneralOrdenado.get(2).toString()).append("4.- ").append(rankingGeneralOrdenado.get(3).toString()).append("5.- ").append(rankingGeneralOrdenado.get(4).toString());
                }

                if (rankingPersonajeOrdenado.size() < 5){
                    rankingPersonajeMensaje = new StringBuilder("******* RANKING PERSONAJE *******\n");
                    for (int i = 0; i < rankingPersonajeOrdenado.size(); i++) {
                        rankingPersonajeMensaje.append(i + 1).append(".- ").append(rankingPersonajeOrdenado.get(i).toString());
                    }
                }else {
                    rankingPersonajeMensaje = new StringBuilder("******* RANKING PERSONAJE *******\n").append(("1.- ")).append(rankingPersonajeOrdenado.get(0).toString()).append("2.- ").append(rankingPersonajeOrdenado.get(1).toString()).append("3.- ").append(rankingPersonajeOrdenado.get(2).toString()).append("4.- ").append(rankingPersonajeOrdenado.get(3).toString()).append("5.- ").append(rankingPersonajeOrdenado.get(4).toString());
                }

                JOptionPane.showMessageDialog(null, rankingGeneralMensaje);
                JOptionPane.showMessageDialog(null, rankingPersonajeMensaje);

                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(new Principal().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Image icon = pantalla.getImage("src/Images/politecnics.png");
        frame.setIconImage(icon);
    }
}
