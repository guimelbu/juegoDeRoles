package Forms;

public abstract class Characters {
    protected int type, lifes, speed, money;
    protected boolean[] objects = {false, false, false};
    String name;

    public Characters(int lives, int speed, int money, String name) {
        this.lifes = lives;
        this.speed = speed;
        this.money = money;
        this.name = name;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean[] getObjects() {
        return objects;
    }
    public void setObjects(boolean[] objects) {
        this.objects = objects;
    }
}
