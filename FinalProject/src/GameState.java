/*** In The Name of Allah ***/


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {
    static double degreeOfRotate=0;
    public boolean shift = false;
    public boolean tab = false;
    public int locX, locY, diam ;
    public int locXhead,locYhead;
    public int health;
    public int positionOfHealth;
    public boolean gameOver;
    static  int shieldRemain = 0;

    public boolean starting = true ;
    public boolean starting2 = false;

    public String gameMood = null;
    public boolean firstGun = true;
    public int numberOfFirstGun = 100;
    public int numberOfSecondGun = 10;

    public int lastLocX,lastLocY;
    public int lastLocXhead,lastLockYhead;
    public boolean toBox=false;
    public boolean boxRight = true, boxUp = true, boxLeft = true, boxDown = true;


    public Music music = new Music();


    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    public boolean mousePress;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public ArrayList<Shoot> shoots;
    public Map map;

    public GameState() {
        locX = 200;
        locY = 200;
        health=4;
        positionOfHealth= health * - (health+1);
        locYhead=locY+4;
        locXhead=locX+5;

        diam = 32;
        gameOver = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        //
        shoots=new ArrayList<>();
    }

    /**
     * The method which updates the game state.
     */
    public void update(Map map) throws FileNotFoundException {
        if (mousePress) {

            if (firstGun == true)
            {
                if (numberOfFirstGun > 0) {
                   Shoot shoot = new Shoot(locXhead, locYhead - 20, (int) GameState.degreeOfRotate + 90);
                    music.playMusic("cannon.wav");
                    numberOfFirstGun --;
                    shoot.secondGun = false;
                    shoots.add(shoot);
                }
                else
                {
                    music.playMusic("emptyGun.wav");
                }
            }
            else
            {
                if (numberOfSecondGun > 0) {
                   Shoot shoot = new Shoot(locXhead, locYhead - 20, (int) GameState.degreeOfRotate + 90);
                    music.playMusic("cannon.wav");
                    numberOfSecondGun --;
                    shoot.secondGun = true;
                    shoots.add(shoot);
                }
                else
                {
                    music.playMusic("emptyGun.wav");
                }

            }

            //shoot.degree=(int) GameState.degreeOfRotate + 90;
            //System.out.println(shoot.degree);
        }


        if (keyUP) {
            /*if (toBox == false)
            {
                lastLocY = locY;
            }*/
            if (boxUp == true)
            {
                if (shift == false)
                {
                    locY -= 8;
                    lastLocY = locY;
                }
                else
                {
                    locY -= 32;
                    lastLocY = locY;
                }
            }
        }
        if (keyDOWN) {
            /*if (toBox == false)
            {
                lastLocY = locY;
            }*/
            if (boxDown == true)
            {
                if (shift == false)
                {
                    locY += 8;
                    lastLocY = locY;
                }
                else
                {
                    locY += 32;
                    lastLocY = locY;
                }

            }
        }
        if (keyLEFT)
        {
            /*if (toBox == false)
            {
                lastLocX = locX;
            }*/
            if (boxLeft == true)
            {
                if (tab == false) {
                    locX -= 8;
                    lastLocX = locX;
                }
                else
                {
                    locX -= 32;
                    lastLocX = locX;
                }
            }

            if (locX<240)
            {
                if (Map.showingIndex>2)
                {
                    Map.showingIndex -= 2;
                    locX = 840-360;
                }
            }

        }
        if (keyRIGHT)
        {
            /*if (toBox == false)
            {
                lastLocX = locX;
            }*/
            if ( boxRight == true)
            {
                if (tab == false) {
                    locX += 8;
                    lastLocX = locX;
                }
                else
                {
                    locX += 32;
                    lastLocX = locX;
                }
            }
        }


        locX = Math.max(locX, 0);
        //locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);


        if (locX>=1080)
        {
            locX = 840;
            Map.showingIndex += 2;
        }


        locY = Math.max(locY, 0);
        locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);


        Iterator<Shoot> iterator=shoots.iterator();
        while(iterator.hasNext())
        {
            iterator.next().update();
        }


        lastLockYhead=lastLocY;
        lastLocXhead=lastLocX;
        locYhead=locY+4;
        locXhead=locX+5;

    }


    public KeyListener getKeyListener() {
        return keyHandler;
    }
    public MouseListener getMouseListener() {
        return mouseHandler;
    }
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }



    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gameOver = true;
                    break;
                case KeyEvent.VK_SHIFT:
                    if (shift == true)
                    {
                        shift = false;
                    }
                    else
                    {
                        shift = true;
                    }
                    break;
                case KeyEvent.VK_TAB:
                    if ( tab== true)
                    {
                        tab = false;
                    }
                    else
                    {
                        tab = true;
                    }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;
            }
        }

    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
            {
                if (firstGun == true)
                {
                    firstGun = false;
                }
                else
                {
                    firstGun = true;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (starting == true)
            {
                if ( e.getX()>= 352 && e.getX()<= 480)
                {
                    if (e.getY()>=440 && e.getY() <= 465)
                    {
                        gameMood = "easy";
                        map.numberOfKhengEnemy = 10;
                        starting = false;
                        starting2 = true;
                    }
                }
                if ( e.getX()>= 352 && e.getX()<= 564)
                {
                    if (e.getY()>=503 && e.getY() <= 530)
                    {
                        gameMood = "normal";
                        map.removeAllElement();
                        map.mood = gameMood;
                        map.numberOfKhengEnemy = 15;
                        map.setMap(gameMood);
                        starting = false;
                        starting2 = true;
                    }
                }
                if ( e.getX()>= 352 && e.getX()<= 490)
                {
                    if (e.getY()>=563 && e.getY() <= 587)
                    {
                        gameMood = "hard";
                        map.removeAllElement();
                        map.mood = gameMood;
                        map.numberOfKhengEnemy = 20;
                        map.setMap(gameMood);
                        starting = false;
                        starting2 = true;
                    }

                }
            }
            else if (starting2 == true)
            {
                starting2 =false;
            }
            else {
                if (e.getModifiers() != MouseEvent.BUTTON3_MASK)
                {
                    mousePress = true;
                    double angle = Math.atan2(e.getY() - locY, e.getX() - locX);
                    angle -= (Math.PI / 2);
                    if (angle < 0) angle += 2 * Math.PI;
                    GameState.degreeOfRotate = Math.toDegrees(angle);
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            //mouseX = e.getX();
            //mouseY = e.getY();
        }
        @Override
        public void mouseMoved(MouseEvent e)
        {
            double angle = Math.atan2(e.getY() - locY, e.getX() - locX);
            angle-=(Math.PI/2);
            if (angle < 0) angle += 2 * Math.PI;
            GameState.degreeOfRotate=Math.toDegrees(angle);

        }

    }


}
