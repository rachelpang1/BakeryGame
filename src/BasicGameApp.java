//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    Character  CartoonCupcake;
    Character Cookie;
    Character CookieMonster;
    Character CookieCrumbs;
    boolean cookieVsCookieMonster;
    boolean isCookieWhole;
    boolean cupcakeVsCookieMonster;
    boolean cupcakeVsCookie;
    ArrayList<Desserts> cherriesList = new ArrayList<>();



    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public Image backgroundPic;

    public BufferStrategy bufferStrategy;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();

        backgroundPic = Toolkit.getDefaultToolkit().getImage("candyland.jpg");
        CartoonCupcake = new Character(400, 300, (int)(Math.random()*5), (int)(Math.random()*5), (int)(Math.random()*100+50),(int)(Math.random()*100+50));
        CartoonCupcake.xpos = 400;
        CartoonCupcake.ypos = 300;
        CartoonCupcake.name = "CartoonCupcake";
        CartoonCupcake.pic = Toolkit.getDefaultToolkit().getImage("Cartoon-Cupcake-Transparent-PNG.png");

        Cookie = new Character(200,200,(int)(Math.random()*5),(int)(Math.random()*5),(int)(Math.random()*100+50),(int)(Math.random()*100+50));
        Cookie.xpos = 200;
        Cookie.ypos = 200;
        Cookie.name = "Cookie";
        Cookie.pic = Toolkit.getDefaultToolkit().getImage("cookiee.png");


        CookieMonster = new Character(500,600,(int)(Math.random()*5),(int)(Math.random()*5),(int)(Math.random()*50+10),(int)(Math.random()*50+10));
        CookieMonster.xpos = 300;
        CookieMonster.ypos = 300;
        CookieMonster.name = "CookieMonster";
        CookieMonster.pic = Toolkit.getDefaultToolkit().getImage("cookiemonster.png");

        //variable and objects
        //create (construct) the objects needed for the game

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main threadv
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            collision();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //call the move() code for each object
        Cookie.move();
        Cookie.printInfo();

        CartoonCupcake.wrap();
        CartoonCupcake.printInfo();

        CookieMonster.move();
        CookieMonster.printInfo();

    }

    public void collision(){
        if (Cookie.hitbox.intersects(CookieMonster.hitbox) == true && cookieVsCookieMonster==false){
            cookieVsCookieMonster = true;
            isCookieWhole = !isCookieWhole;
        }
        if (Cookie.hitbox.intersects(CookieMonster.hitbox) == false){
            cookieVsCookieMonster = false;
        }

       if (isCookieWhole ==false){
           Cookie.pic = Toolkit.getDefaultToolkit().getImage("Cookie Crumbs.png");
       }
       else if(isCookieWhole ==true){
           Cookie.pic = Toolkit.getDefaultToolkit().getImage("cookiee.png");
       }

       if (CookieMonster.hitbox.intersects(CartoonCupcake.hitbox)==true){
           cupcakeVsCookieMonster = true;
           CookieMonster.width = CookieMonster.width + 1;
           CookieMonster.height = CookieMonster.height + 1;
       }

       if (Cookie.hitbox.intersects(CartoonCupcake.hitbox)==true){
           cupcakeVsCookie = true;
           Cookie.dx = Cookie.dx+1;
           Cookie.dy = Cookie.dy+1;
       }
        if (Cookie.hitbox.intersects(CartoonCupcake.hitbox) == false){
            cupcakeVsCookie = false;
        }

       if (CookieMonster.dx < 0){
           CookieMonster.pic = Toolkit.getDefaultToolkit().getImage("cookiemonsterflip.png");
//           isCookieMonsterFlipped = !isCookieMonsterFlipped;
       }
       else if (CookieMonster.dx >= 0){
           CookieMonster.pic = Toolkit.getDefaultToolkit().getImage("cookiemonster.png");
       }


    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the images
        g.drawImage(backgroundPic, 0,0, WIDTH, HEIGHT, null);
        g.drawImage(CartoonCupcake.pic, CartoonCupcake.xpos, CartoonCupcake.ypos, CartoonCupcake.width, CartoonCupcake.height, null);
        g.drawImage(Cookie.pic, Cookie.xpos, Cookie.ypos, Cookie.width, Cookie.height, null);
        g.drawImage(CookieMonster.pic, CookieMonster.xpos, CookieMonster.ypos, CookieMonster.width, CookieMonster.height, null);


//        g.drawRect(CartoonCupcake.hitbox.x, CartoonCupcake.hitbox.y, CartoonCupcake.hitbox.width, CartoonCupcake.hitbox.height);
//        g.drawRect(Cookie.hitbox.x, Cookie.hitbox.y, Cookie.hitbox.width, Cookie.hitbox.height);
//        g.drawRect(CookieMonster.hitbox.x, CookieMonster.hitbox.y, CookieMonster.hitbox.width, CookieMonster.hitbox.height);

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}

