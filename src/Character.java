import java.awt.*;

public class Character {

    //declare variables

    public String name;
    public Image pic;
    public int xpos;
    public int ypos;
    public int dx = 1;
    public int dy = 1;
    public int width =100;
    public int height = 100;
    public boolean isAlive;
    public Rectangle hitbox;


    public Character(){
        hitbox = new Rectangle(xpos, ypos, width, height);

    }
    public Character(int paramXpos, int paramYpos, int paramDx, int paramDy, int paramWidth, int paramHeight){
        xpos = paramXpos;
        ypos = paramYpos;
        dx = paramDx;
        dy = paramDy;
        width = paramWidth;
        height = paramHeight;
        hitbox = new Rectangle(xpos, ypos, width, height);

    }


    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos >= 600 - height || ypos <= 0) {
            dy = -dy;
        }

        if (xpos >= 1000 - width || xpos <= 0) {
            dx = -dx;


        }

        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void wrap(){
        xpos = xpos + dx;
        ypos = ypos + dy;


        if (xpos >= 1000){
            xpos  = -width;

        }

        if (ypos >= 800){
            ypos = -height;

        }

        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void printInfo(){
        System.out.println(name + " is at (" + xpos + "," + ypos + ")");


    }


}