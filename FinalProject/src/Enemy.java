import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Enemy {

    public int locX, locY, diam ;
    public int locXhead,locYhead;

    public int indexOfMap = 1;

    //health of enemy
    public int health;
    //positon of health
    public int positionOfHealth;

    //difference x between enemy and alliense tank
    public int differenceLocX;
    //difference y between enemy and alliense tanl
    public int differenceLocy;
    //type of enemy
    boolean enemy1 = false;
    //go up or down
    boolean goUp = false;
    //go up or down
    boolean goDown = false;
    //y for shoot
    public int yForShoot;
    //x for shoot
    public int xForShoot;
    public boolean xShoot = false;
    public boolean shooting = false;
    //stop for shooting
    public  boolean stop = false;
    //go right for good position
    public boolean goRight = false;
    //name of enemy
    public String name;
    //remain pervious x and y
    public int lastLocX,lastLocY;
    public boolean boxRight = true, boxUp = true, boxLeft = true, boxDown = true;

    //music player
    public Music music = new Music();
    public ArrayList<Shoot> shoots;

    /**
     * make new enemy
     * @param name name of enemy
     */
    public Enemy(String name) {
        //locX = 1660;
        this.name = name;
        //locY = 650;
        health=5;
        positionOfHealth= health * - health;

        diam = 32;
        //
        shoots=new ArrayList<>();
    }

    /**
     * The method which updates the game state.
     */
    public void update(Map map) throws FileNotFoundException {
        //System.out.println(indexOfMap);
        if (indexOfMap != map.showingIndex) {
            if (indexOfMap < map.showingIndex) {
                lastLocX = locX;
                locX -= 240;
            } else {
                lastLocX = locX;
                locX += 240;
            }
        }
            indexOfMap = map.showingIndex;



            if (boxLeft == true && stop == false && enemy1 == false) {
                locX -= 8;
                lastLocX = locX;
            }
            if (boxLeft == false && stop == false && enemy1 == false) {
                System.out.println("0912");
                System.out.println(boxUp);
                System.out.println("@" + boxDown);
                if (boxUp == true) {
                    System.out.println("123");
                    //locX += 8;
                    locY -= 8;
                    lastLocY = locY;
                }
                else if (boxDown == true) {
                    System.out.println("1234");
                    //locX += 8;
                    locY += 8;
                    lastLocY = locY;
                }
                else if (boxRight == true) {

                }

                locY = Math.max(locY, 0);
                locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);
                locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);
                locX = Math.max(locX, 0);
            }
            /*if (goRight == true && enemy1 == false) {
                if (boxRight == true) {
                    locX += 8;
                    lastLocX = locX;
                } else {
                    if (boxUp == true) {
                        locY -= 8;
                        lastLocY = locY;
                    } else if (boxDown == true) {
                        locY += 8;
                        lastLocY = locY;
                    }
                }
                locY = Math.max(locY, 0);
                locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);
                locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);
                locX = Math.max(locX, 0);
            }*/
            if (enemy1 == true)
            {
                if (differenceLocy > 0)
                {
                    locY -= 10;
                }
                else
                {
                    locY += 10;
                }
                if (differenceLocX>0)
                {
                    locX -= 10;
                }
                else
                {
                    locX += 10;
                }
            }
            else
            {
                //System.out.println("godown  " + goDown);
                if (goDown == true && goUp != true && stop == true)
                {
                    //System.out.println("danial");
                    if (boxDown == true)
                    {
                        locY += 8;
                        lastLocY = locY;
                    }
                    else
                    {
                        System.out.println("#" +boxLeft);
                        if (boxLeft==true)
                        {
                            //System.out.println("here");
                            locX -= 8;
                            //System.out.println("locX  " + locX);
                            lastLocX = locX;
                            //lastLocY = locY;
                        }
                        else
                        {
                            goUp = true;
                        }
                    }
                    if (Math.min(locY, GameFrame.GAME_HEIGHT -180) == GameFrame.GAME_HEIGHT -180)
                    {

                        goUp = true;
                    }

                }
                if (goUp == true &&  stop == true)
                {
                    if (boxUp == true)
                    {
                        locY -= 8;
                        lastLocY = locY;
                    }
                    else
                    {
                        System.out.println("Left"+boxLeft);
                        if (boxLeft==true)
                        {
                            locX -= 8;
                            //locY += 500;
                            lastLocX = locX;
                            //System.out.println("123locY" + locY);
                        }
                        else
                        {
                            goUp = false;
                            goUp = true;
                        }
                    }
                    if (Math.max(locY, 130) == 130)
                    {
                        //System.out.println("HERE1234");
                        goUp = false;
                        goDown = true;
                    }
                }
                goRight = false;
                //System.out.println(stop);
                enemy1 = false;
            }


            Iterator<Shoot> iterator = shoots.iterator();
            while (iterator.hasNext()) {
                iterator.next().update();
            }

        }

    /**
     * update shoot of enemy
     * @param width width of shoot
     * @param height height of shoot
     * @param x x of shoot
     * @param y y of shoot
     * @return changing y
     */
        public int updateShoot(int width,int height,int x,int y)
        {
            xForShoot -= 10;
            return 2;
        }
}
