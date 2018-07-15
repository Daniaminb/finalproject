/*** In The Name of Allah ***/

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;
import  sun.audio.*;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering, 
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 960;                  // 960p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 8;  // wide aspect ratio

    //uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
    /*private BufferedImage image;*/

    private long lastRender;
    private ArrayList<Float> fpsHistory;

    private BufferStrategy bufferStrategy;


    //Image Icon
    BufferedImage tankBody;
    BufferedImage tankHead;
    BufferedImage tankHead2;
    BufferedImage arena;
    BufferedImage plant;
    BufferedImage shootImage;
    BufferedImage healthImage;
    BufferedImage box2;
    BufferedImage repairFood;
    BufferedImage softWall1;
    BufferedImage softWall2;
    BufferedImage softWall3;
    BufferedImage softWall4;
    BufferedImage start1;
    BufferedImage start2;
    BufferedImage enemyPic;
    BufferedImage khengenemyPic;
    BufferedImage smallEnemyPic;
    BufferedImage firstGunNumber;
    BufferedImage secondGunNumber;
    BufferedImage mine;
    BufferedImage cannoonUpdate;
    BufferedImage mashinGunUpdate;
    BufferedImage shield;
    BufferedImage updateWeapon;


    public GameFrame(String title) {
        super(title);
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);

	 try{
	     // read Image Icon
			tankBody = ImageIO.read(new File("152873650272747434.png"));
			tankHead = ImageIO.read(new File("152873657198289315.png"));
            tankHead2 = ImageIO.read(new File("tankGun02.png"));
			//arena = ImageIO.read(new File("Area.png"));
            arena = new ImgUtils().scaleImage(120,120,"Area.png");
			//plant = ImageIO.read(new File("plant.png"));
            shootImage = ImageIO.read(new File("HeavyBullet.png"));
            healthImage = ImageIO.read(new File("health.png"));
            repairFood= ImageIO.read(new File("RepairFood.png"));
            softWall1 = new ImgUtils().scaleImage(arena.getWidth(),arena.getHeight(),"softWall.png");
            softWall2 = new ImgUtils().scaleImage(arena.getWidth(),arena.getHeight(),"softWall1.png");
            softWall3 = new ImgUtils().scaleImage(arena.getWidth(),arena.getHeight(),"softWall2.png");
            softWall4 = new ImgUtils().scaleImage(arena.getWidth(),arena.getHeight(),"softWall3.png");
            box2 = new ImgUtils().scaleImage(arena.getWidth(),arena.getHeight(),"wicket2.png");
            //System.out.println(tankHead.getWidth());
            //shootImage=new ImgUtils().scaleImage(50,20,"HeavyBullet.png");
            plant = new ImgUtils().scaleImage(arena.getWidth(),arena.getHeight(),"plant.png");
            start1 = new ImgUtils().scaleImage(GAME_WIDTH,GAME_HEIGHT,"Startup.png");
            start2 = new ImgUtils().scaleImage(GAME_WIDTH,GAME_HEIGHT,"Startup2.png");
            shield =new ImgUtils().scaleImage(repairFood.getWidth(),repairFood.getHeight(),"shield_PNG1268.png");
            updateWeapon = new ImgUtils().scaleImage(repairFood.getWidth(),repairFood.getHeight(),"imageedit_4_8740616574.png");
            enemyPic = ImageIO.read(new File("BigEnemy.png"));
            khengenemyPic = ImageIO.read(new File("Enemy1.png"));
            smallEnemyPic = ImageIO.read(new File("SmallEnemy.png"));
            firstGunNumber = ImageIO.read(new File("NumberOfHeavyBullet2.png"));
            secondGunNumber = ImageIO.read(new File("NumberOfMachinGun2.png"));
            mine = ImageIO.read(new File("mine.png"));
            mashinGunUpdate = ImageIO.read(new File("MashinGunFood.png"));
            cannoonUpdate = ImageIO.read(new File("CannonFood.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}

    }

    /**
     * This must be called once after the JFrame is shown:
     *    frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state,Map map,ArrayList<Enemy> enemy) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state, map,enemy);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state ,Map map, ArrayList<Enemy> enemy) throws IOException {
        // Draw background
        int i=0;
        int j=0;
        int width=arena.getWidth();
        int height=arena.getHeight();
        while(i<=GAME_WIDTH-width)
        {
            while(j<=GAME_HEIGHT-height)
            {
                //i+=width;
                g2d.drawImage(arena,i,j,null);
                j+=height;
                if (j>GAME_HEIGHT-height)
                {
                    g2d.drawImage(arena,i,j,null);
                }
            }
            j=0;
            i+=width;
        }

        //Draw Elements In BackGround
        int loopInMap=((Map.showingIndex-1)*8)+1;
        int coordinateOfMap =1;
        while(loopInMap <= (16+Map.showingIndex-1)*8)
        {
            //System.out.println(m);
            if (map.getMap(loopInMap)=="plant")
            {
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(plant,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(plant,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }
            if (map.getMap(loopInMap)=="box")
            {
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(box2,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(box2,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }
            if (map.getMap(loopInMap)=="shield")
            {
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(shield,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(shield,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }

            if (map.getMap(loopInMap)=="update")
            {
                System.out.println("update");
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(updateWeapon,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(updateWeapon,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }
            if (map.getMap(loopInMap)=="repairfood")
            {
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(repairFood,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(repairFood,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }
            if (map.getMap(loopInMap)=="cannon")
            {
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(cannoonUpdate,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(cannoonUpdate,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }
            if (map.getMap(loopInMap)=="mashin")
            {
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(mashinGunUpdate,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(mashinGunUpdate,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }
            if (map.getMap(loopInMap)=="mine")
            {
                if (coordinateOfMap%8==0)
                {
                    g2d.drawImage(mine,((coordinateOfMap / 8)-1) * 120,7*120 ,null);
                }
                else
                {
                    g2d.drawImage(mine,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
                }
            }
            if (map.getMap(loopInMap)=="softwall1")
            {
                g2d.drawImage(softWall1,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
            }
            // add enemy to map
            if (map.getMap(loopInMap)=="enemy1")
            {
                Enemy enemy1 = new Enemy("enemy1");
                enemy1.locX = (coordinateOfMap / 8) * 120;
                enemy1.locY = ((coordinateOfMap%8)-1)*120 ;
                if (enemy1.name == "enemy1")
                {
                    enemy1.health =1;
                }
                //enemy1.lastLocX = 600;
                //enemy1.lastLocY = 600;
                //System.out.println(enemy1.locX);
                enemy.add(enemy1);
                map.removeElementMap(loopInMap);
                //g2d.drawImage(softWall1,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
            }
            if (map.getMap(loopInMap)=="enemy2")
            {
                Enemy enemy1 = new Enemy("enemy2");
                enemy1.locX = (coordinateOfMap / 8) * 120;
                enemy1.locY = ((coordinateOfMap%8)-1)*120 ;
                //enemy1.lastLocX = 600;
                //enemy1.lastLocY = 600;
                //System.out.println(enemy1.locX);
                if (enemy1.name == "enemy2")
                {
                    if (map.mood == "easy")
                    {
                        enemy1.health =2;
                    }
                    else if ( map.mood == "normal")
                    {
                        enemy1.health =3;
                    }
                    else
                    {
                        enemy1.health = 4;
                    }
                }
                enemy.add(enemy1);
                map.removeElementMap(loopInMap);
                //g2d.drawImage(softWall1,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
            }
            if (map.getMap(loopInMap)=="enemy3")
            {
                Enemy enemy1 = new Enemy("enemy3");
                enemy1.locX = (coordinateOfMap / 8) * 120;
                enemy1.locY = ((coordinateOfMap%8)-1)*120 ;
                if (enemy1.name == "enemy3")
                {
                    if (map.mood == "easy")
                    {
                        enemy1.health =3;
                    }
                    else if ( map.mood == "normal")
                    {
                        enemy1.health =4;
                    }
                    else
                    {
                        enemy1.health = 5;
                    }
                }
                enemy.add(enemy1);
                map.removeElementMap(loopInMap);
            }
            if (map.getMap(loopInMap)=="softwall2")
            {
                g2d.drawImage(softWall2,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
            }
            if (map.getMap(loopInMap)=="softwall3")
            {
                g2d.drawImage(softWall3,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
            }
            if (map.getMap(loopInMap)=="softwall4")
            {
                g2d.drawImage(softWall4,(coordinateOfMap / 8) * 120,((coordinateOfMap%8)-1)*120 ,null);
            }

            loopInMap++;
            coordinateOfMap++;
        }


        //Update and show shoot of main tank
        //all shoots
        Iterator<Shoot> iterator=state.shoots.iterator();
        while(iterator.hasNext())
        {
            int indexEnemy = 0;
            Shoot sh=iterator.next();
            Iterator<Enemy> iterator1= enemy.iterator();
            AffineTransform at = AffineTransform.getTranslateInstance(sh.x,sh.y);
            loopInMap=((Map.showingIndex-1)*8)+1;
            coordinateOfMap =1;
            //shoot destroy enemy
            while (iterator1.hasNext())
            {
                Enemy enemy1= iterator1.next();
                if (sh.x  >=  enemy1.locX && sh.x  <= enemy1.locX + enemyPic.getWidth() && sh.show==true)
                {
                    if (sh.y >= enemy1.locY && sh.y <= (enemy1.locY + enemyPic.getHeight() ))
                    {
                        if (sh.secondGun == false)
                        {
                            enemy1.health -= 1 + sh.power;
                        }
                        else
                        {
                            enemy1.health -= 2 +sh.power;
                        }
                        sh.show = false;
                        if (enemy1.health <=0)
                        {
                            iterator1.remove();
                            Music music =new Music();
                            music.playMusic("enemydestroyed.wav");
                        }
                    }
                }
                indexEnemy ++;
            }
            //shoot barkhord to box or softwall
            while(loopInMap <= (16+Map.showingIndex-1)*8 && sh.show == true)
            {
                if (map.getMap(loopInMap)=="softwall1")
                {
                    //System.out.println(sh.y);
                    //System.out.println("softwl" +(((coordinateOfMap%8)-1)*120 ) + "   " + (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ) );
                    if (sh.x  >= ((coordinateOfMap / 8) * 120 ) && sh.x  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth()) )
                    {
                        if (sh.y >= (((coordinateOfMap%8)-1)*120 ) && sh.y <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ))
                        {
                            iterator.remove();
                            //sh.show = false;
                            map.removeElementMap(loopInMap);
                            map.setElementMap(loopInMap, "softwall2");
                        }
                    }
                }
                else if (map.getMap(loopInMap)=="box")
                {
                    //System.out.println(sh.y);
                    //System.out.println("softwl" +(((coordinateOfMap%8)-1)*120 ) + "   " + (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ) );
                    if (sh.x  >= ((coordinateOfMap / 8) * 120 ) && sh.x  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth()) )
                    {
                        if (sh.y >= (((coordinateOfMap%8)-1)*120 ) && sh.y <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ))
                        {
                            Music music =new Music();
                            music.playMusic("MineBoom.wav");
                            iterator.remove();
                            //sh.show = false;
                        }
                    }
                }
                else if (map.getMap(loopInMap)=="softwall2")
                {
                    if (sh.x  >= ((coordinateOfMap / 8) * 120 ) && sh.x  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth() ) )
                    {
                        if (sh.y >= (((coordinateOfMap%8)-1)*120 - 152) && sh.y <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ))
                        {
                            iterator.remove();
                            //sh.show = false;
                            map.setElementMap(loopInMap, "softwall3");
                        }
                    }
                }
                else if (map.getMap(loopInMap)=="softwall3")
                {
                    if (sh.x  >= ((coordinateOfMap / 8) * 120 ) && sh.x  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth() ) )
                    {
                        if (sh.y >= (((coordinateOfMap%8)-1)*120 ) && sh.y <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() ))
                        {
                            iterator.remove();
                            //sh.show = false;
                            map.setElementMap(loopInMap, "softwall4");
                        }
                    }
                }
                else if (map.getMap(loopInMap)=="softwall4")
                {
                    if (sh.x  >= ((coordinateOfMap / 8) * 120) && sh.x  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() ) )
                    {
                        if (sh.y >= (((coordinateOfMap%8)-1)*120 ) && sh.y <= (((coordinateOfMap%8)-1)*120 + box2.getHeight()))
                        {
                            iterator.remove();
                            //sh.show = false;
                            map.removeElementMap(loopInMap);
                            Random random = new Random();
                            int a = random.nextInt(3);
                            if ( a==1)
                            {
                                map.setElementMap(loopInMap, "mine");
                            }
                            if ( a==2)
                            {
                                System.out.println("cannon");
                                map.setElementMap(loopInMap, "cannon");
                            }
                            if ( a==3)
                            {
                                System.out.println("mashin");
                                map.setElementMap(loopInMap, "mashin");
                            }
                            //map.setElementMap(loopInMap, "softwall4");
                            Music music =new Music();
                            music.playMusic("softwall.wav");
                        }
                    }
                }

                loopInMap++;
                coordinateOfMap++;
            }
            //show the shoot
            at.rotate( Math.toRadians(sh.degree), shootImage.getWidth()/2, shootImage.getHeight()/2);
            if (sh.show == true)
            {
                g2d.drawImage( shootImage, at, null);
            }
            if (sh.x > 2000)
            {
                iterator.remove();
            }
            if (sh.y > 2000)
            {
                iterator.remove();
            }
            if (sh.x < -10)
            {
                iterator.remove();
            }
            if (sh.y < -10)
            {
                iterator.remove();
            }
        }

        // when we earn new element in the map
        loopInMap=((Map.showingIndex-1)*8)+1;
        coordinateOfMap =1;
        //Repair Foood
        while(loopInMap <= (16+Map.showingIndex-1)*8)
        {
            if (map.getMap(loopInMap)=="repairfood")
            {
                if (loopInMap%8==0)
                {
                    if (state.locX  >= (((coordinateOfMap / 8)-1) * 120 - 88) && state.locX  <= (((coordinateOfMap / 8)-1) * 120 + box2.getWidth() -8) )
                    {
                        if (state.locY >= (7*120 - 152) && state.locY <= (7*120 + box2.getHeight() - 8))
                        {
                            map.removeElementMap(loopInMap);
                            state.health =5 ;
                        }
                    }
                }
                else if (state.locX  >= ((coordinateOfMap / 8) * 120 - 88) && state.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() -8) )
                {
                    if (state.locY >= (((coordinateOfMap%8)-1)*120 - 152) && state.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                    {
                        map.removeElementMap(loopInMap);
                        state.health =5 ;
                    }
                }
            }
            if (map.getMap(loopInMap)=="mine")
            {
                if (loopInMap%8==0)
                {
                    if (state.locX  >= (((coordinateOfMap / 8)-1) * 120 - 88) && state.locX  <= (((coordinateOfMap / 8)-1) * 120 + box2.getWidth() -8) )
                    {
                        if (state.locY >= (7*120 - 152) && state.locY <= (7*120 + box2.getHeight() - 8))
                        {
                            map.removeElementMap(loopInMap);
                            if (GameState.shieldRemain ==0)
                            {
                                state.health -- ;
                            }
                            else
                            {
                                GameState.shieldRemain --;
                            }
                        }
                    }
                }
                else if (state.locX  >= ((coordinateOfMap / 8) * 120 - 88) && state.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() -8) )
                {
                    if (state.locY >= (((coordinateOfMap%8)-1)*120 - 152) && state.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                    {
                        map.removeElementMap(loopInMap);
                        if (GameState.shieldRemain ==0)
                        {
                            state.health -- ;
                        }
                        else
                        {
                            GameState.shieldRemain --;
                        }
                    }
                }
            }
            if (map.getMap(loopInMap)=="mashin")
            {
                if (loopInMap%8==0)
                {
                    if (state.locX  >= (((coordinateOfMap / 8)-1) * 120 - 88) && state.locX  <= (((coordinateOfMap / 8)-1) * 120 + box2.getWidth() -8) )
                    {
                        if (state.locY >= (7*120 - 152) && state.locY <= (7*120 + box2.getHeight() - 8))
                        {
                            map.removeElementMap(loopInMap);
                            state.numberOfSecondGun = 10;
                        }
                    }
                }
                else if (state.locX  >= ((coordinateOfMap / 8) * 120 - 88) && state.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() -8) )
                {
                    if (state.locY >= (((coordinateOfMap%8)-1)*120 - 152) && state.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                    {
                        map.removeElementMap(loopInMap);
                        state.numberOfSecondGun = 10;
                    }
                }
            }

            if (map.getMap(loopInMap)=="update")
            {
                if (loopInMap%8==0)
                {
                    if (state.locX  >= (((coordinateOfMap / 8)-1) * 120 - 88) && state.locX  <= (((coordinateOfMap / 8)-1) * 120 + box2.getWidth() -8) )
                    {
                        if (state.locY >= (7*120 - 152) && state.locY <= (7*120 + box2.getHeight() - 8))
                        {
                            map.removeElementMap(loopInMap);
                            Shoot.power ++;
                        }
                    }
                }

                else if (state.locX  >= ((coordinateOfMap / 8) * 120 - 88) && state.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() -8) )
                {
                    if (state.locY >= (((coordinateOfMap%8)-1)*120 - 152) && state.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                    {
                        map.removeElementMap(loopInMap);
                        Shoot.power ++;
                    }
                }
            }

            if (map.getMap(loopInMap)=="cannon")
            {
                if (loopInMap%8==0)
                {
                    if (state.locX  >= (((coordinateOfMap / 8)-1) * 120 - 88) && state.locX  <= (((coordinateOfMap / 8)-1) * 120 + box2.getWidth() -8) )
                    {
                        if (state.locY >= (7*120 - 152) && state.locY <= (7*120 + box2.getHeight() - 8))
                        {
                            map.removeElementMap(loopInMap);
                            state.numberOfFirstGun = 100;
                        }
                    }
                }
                else if (state.locX  >= ((coordinateOfMap / 8) * 120 - 88) && state.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() -8) )
                {
                    if (state.locY >= (((coordinateOfMap%8)-1)*120 - 152) && state.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                    {
                        map.removeElementMap(loopInMap);
                        state.numberOfFirstGun = 100;
                    }
                }
            }
            if (map.getMap(loopInMap)=="shield")
            {
                if (loopInMap%8==0)
                {
                    if (state.locX  >= (((coordinateOfMap / 8)-1) * 120 - 88) && state.locX  <= (((coordinateOfMap / 8)-1) * 120 + box2.getWidth() -8) )
                    {
                        if (state.locY >= (7*120 - 152) && state.locY <= (7*120 + box2.getHeight() - 8))
                        {
                            map.removeElementMap(loopInMap);
                            GameState.shieldRemain ++;
                        }
                    }
                }
                else if (state.locX  >= ((coordinateOfMap / 8) * 120 - 88) && state.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() -8) )
                {
                    if (state.locY >= (((coordinateOfMap%8)-1)*120 - 152) && state.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                    {
                        map.removeElementMap(loopInMap);
                        GameState.shieldRemain ++;
                    }
                }
            }

            loopInMap++;
            coordinateOfMap++;
        }





        //when we position next to  the box or sth like that
        //cant move
        loopInMap=((Map.showingIndex-1)*8)+1;
        coordinateOfMap =1;
        boolean print = true ;
        //Draw MyTank
        while(loopInMap <= (16+Map.showingIndex-1)*8)
        {
            if (map.getMap(loopInMap)=="box" || map.getMap(loopInMap)=="softwall1" || map.getMap(loopInMap)=="softwall2" || map.getMap(loopInMap)=="softwall3" || map.getMap(loopInMap)=="softwall4" )
            {
                //System.out.println("Locx=" + (state.locX ));
                //System.out.println("Locy=" + (state.locY ));
                //System.out.println("box = " + (((coordinateOfMap%8)-1)*120 -152) + "  " + (((coordinateOfMap%8)-1)*120 + box2.getHeight() -8) );
                if (coordinateOfMap%8==0)
                {
                    if (state.locX  >= (((coordinateOfMap / 8)-1) * 120 - 88) && state.locX  <= (((coordinateOfMap / 8)-1) * 120 + box2.getWidth() -8) )
                    {
                        if (state.locY >= (7*120 - 152) && state.locY <= (7*120 + box2.getHeight() - 8))
                        {
                            if (state.locX  == (((coordinateOfMap / 8)-1) * 120 - 88))
                            {
                                state.boxRight = false;
                            }
                            if (state.locX == (((coordinateOfMap / 8)-1) * 120 + box2.getWidth()-8))
                            {
                                state.boxLeft = false ;
                            }
                            if (state.locY == (7*120 - 152))
                            {
                                state.boxDown = false;

                            }
                            if (state.locY == (7*120 + box2.getHeight() - 8))
                            {
                                state.boxUp = false ;
                            }
                            print=false;
                            state.toBox=true;
                            //System.out.println("FInd");
                        }
                    }
                }
                else if (state.locX  >= ((coordinateOfMap / 8) * 120 - 88) && state.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() -8) )
                {
                    if (state.locY >= (((coordinateOfMap%8)-1)*120 - 152) && state.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                    {
                        if (state.locX  == ((coordinateOfMap / 8) * 120 - 88))
                        {
                            state.boxRight = false;
                        }
                        if (state.locX == ((coordinateOfMap / 8) * 120 + box2.getWidth()-8))
                        {
                            state.boxLeft = false ;
                        }
                        if (state.locY == (((coordinateOfMap%8)-1)*120 - 152))
                        {
                            state.boxDown = false;

                        }
                        if (state.locY == (((coordinateOfMap%8)-1)*120 + box2.getHeight() - 8))
                        {
                            state.boxUp = false ;
                        }
                        print=false;
                        state.toBox=true;
                        //System.out.println("FInd");
                    }
                }
            }

            loopInMap++;
            coordinateOfMap++;
        }
        if (print == true)
        {
            state.toBox=false;
            state.boxDown = true;
            state.boxUp = true;
            state.boxLeft = true;
            state.boxRight = true;
            g2d.drawImage(tankBody,state.locX,state.locY,null);
        }
        else
        {
            g2d.drawImage(tankBody,state.lastLocX +5,state.lastLocY ,null);
        }





        //
        //Enemy show and Artificial of enemy
        Iterator<Enemy> iterator1=enemy.iterator();
        while (iterator1.hasNext())
        {
            Enemy enemy1=iterator1.next();
            if (enemy1.shooting == true)
            {
                if (enemy1.name == "enemy1")
                {

                }
                else if (enemy1.name == "enemy2")
                {
                    if (enemy1.xShoot == false)
                    {
                        enemy1.xShoot = true;
                        enemy1.yForShoot = enemy1.locY;
                        enemy1.xForShoot = enemy1.locX;
                    }
                    g2d.drawImage(shootImage, enemy1.xForShoot , enemy1.yForShoot + smallEnemyPic.getHeight()/2 -10 ,null);
                    enemy1.updateShoot(0,0,0,0);
                    loopInMap=((Map.showingIndex-1)*8)+1;
                    coordinateOfMap =1;
                    /*System.out.println("Enemey Y "+ enemy1.yForShoot);
                    System.out.println((((51%8)-1)*120 ));
                    System.out.println((((51%8)-1)*120 +box2.getHeight()));

                    System.out.println("Enemey x "+ enemy1.xForShoot);
                    System.out.println((51 / 8) * 120);
                    System.out.println((51 / 8) * 120 + box2.getWidth());*/
                    if (enemy1.xForShoot  >=  state.locX && enemy1.xForShoot  <= state.locX + tankBody.getWidth())
                    {
                        //System.out.println("danial");
                        if (enemy1.yForShoot + 40 >= (state.locY ) && enemy1.yForShoot + 40 <= (state.locY + tankBody.getHeight() ))
                        {
                            //System.out.println("here");
                            state.health --;
                            enemy1.xForShoot = -100;
                            if (enemy1.health <=0)
                            {
                                Music music =new Music();
                                music.playMusic("enemydestroyed.wav");
                            }
                        }
                    }
                    while(loopInMap <= (16+Map.showingIndex-1)*8 )
                    {
                        if (map.getMap(loopInMap)=="softwall1")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth()) )
                            {

                                //System.out.println("a  =" +a);
                                //System.out.println((((coordinateOfMap%8)-1)*120 ));
                                //System.out.println((((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ));
                                if (enemy1.yForShoot >= (((coordinateOfMap%8)-1)*120 +700 ) && enemy1.yForShoot <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() + 650 ))
                                {
                                    enemy1.xForShoot = -100;
                                    map.setElementMap(loopInMap, "softwall2");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="box")
                        {
                            //System.out.println(sh.y);
                            //System.out.println("softwl" +(((coordinateOfMap%8)-1)*120 ) + "   " + (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ) );
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth()) )
                            {
                                //System.out.println("1");
                                if ((enemy1.yForShoot  >= (((coordinateOfMap%8)-1)*120 ) && enemy1.yForShoot  <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight()) || (enemy1.yForShoot - 50 >= (((coordinateOfMap%8)-1)*120 ) && enemy1.yForShoot  <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() ) )))
                                {
                                    //System.out.println("2");
                                    enemy1.xForShoot = -100;
                                    Music music =new Music();
                                    music.playMusic("MineBoom.wav");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="softwall2")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth() ) )
                            {
                                if (enemy1.yForShoot >= (((coordinateOfMap%8)-1)*120 +700) && enemy1.yForShoot <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() + 650 ))
                                {
                                    enemy1.xForShoot = -100;
                                    map.setElementMap(loopInMap, "softwall3");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="softwall3")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth() ) )
                            {
                                if (enemy1.yForShoot >= (((coordinateOfMap%8)-1)*120 + 700 ) && enemy1.yForShoot <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() + 650 ))
                                {
                                    enemy1.xForShoot = -100;
                                    map.setElementMap(loopInMap, "softwall4");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="softwall4")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() ) )
                            {
                                if (enemy1.yForShoot >= (((coordinateOfMap%8)-1)*120 + 700 ) && enemy1.yForShoot <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() + 650))
                                {
                                    enemy1.xForShoot = -100;
                                    map.removeElementMap(loopInMap);
                                    Music music =new Music();
                                    music.playMusic("softwall.wav");
                                }
                            }
                        }

                        loopInMap++;
                        coordinateOfMap++;
                    }

                }
                else
                {
                    if (enemy1.xShoot == false)
                    {
                        enemy1.xShoot = true;
                        enemy1.xForShoot = enemy1.locX;
                        System.out.println(enemy1.locX);
                    }
                    //System.out.println("1" + enemy1.xForShoot);
                    int a = enemy1.updateShoot( enemyPic.getWidth(),  enemyPic.getHeight() , state.locX , state.locY + tankBody.getHeight()/2);
                    //System.out.println(enemy1.xForShoot);
                    loopInMap=((Map.showingIndex-1)*8)+1;
                    coordinateOfMap =1;
                    if (enemy1.xForShoot  >=  state.locX && enemy1.xForShoot  <= state.locX + tankBody.getWidth())
                    {
                        System.out.println("here");
                            if (a >= state.locY && a <= (state.locY + tankBody.getHeight() ))
                            {
                                state.health --;
                                if (enemy1.health <=0)
                                {
                                    iterator1.remove();
                                    Music music =new Music();
                                    music.playMusic("enemydestroyed.wav");
                                }
                            }
                    }
                    while(loopInMap <= (16+Map.showingIndex-1)*8 )
                    {
                        if (map.getMap(loopInMap)=="softwall1")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth()) )
                            {
                                if (a >= (((coordinateOfMap%8)-1)*120 ) && a <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ))
                                {
                                    map.setElementMap(loopInMap, "softwall2");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="box")
                        {
                            //System.out.println(sh.y);
                            //System.out.println("softwl" +(((coordinateOfMap%8)-1)*120 ) + "   " + (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ) );
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth()) )
                            {
                                if (a >= (((coordinateOfMap%8)-1)*120 ) && a <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ))
                                {
                                    Music music =new Music();
                                    music.playMusic("MineBoom.wav");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="softwall2")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth() ) )
                            {
                                if (a >= (((coordinateOfMap%8)-1)*120) && a <= (((coordinateOfMap%8)-1)*120 + softWall1.getHeight() ))
                                {
                                    map.setElementMap(loopInMap, "softwall3");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="softwall3")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120 ) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + softWall1.getWidth() ) )
                            {
                                if (a >= (((coordinateOfMap%8)-1)*120 ) && a <= (((coordinateOfMap%8)-1)*120 + box2.getHeight() ))
                                {
                                    map.setElementMap(loopInMap, "softwall4");
                                }
                            }
                        }
                        else if (map.getMap(loopInMap)=="softwall4")
                        {
                            if (enemy1.xForShoot  >= ((coordinateOfMap / 8) * 120) && enemy1.xForShoot  <= ((coordinateOfMap / 8) * 120 + box2.getWidth() ) )
                            {
                                if (a >= (((coordinateOfMap%8)-1)*120 ) && a <= (((coordinateOfMap%8)-1)*120 + box2.getHeight()))
                                {
                                    map.removeElementMap(loopInMap);
                                    Music music =new Music();
                                    music.playMusic("softwall.wav");
                                }
                            }
                        }

                        loopInMap++;
                        coordinateOfMap++;
                    }
                    g2d.drawImage(shootImage, enemy1.xForShoot , a - enemy1.locY + enemyPic.getHeight() ,null);
                    if (state.locX < enemy1.locX)
                    {
                        enemy1.xForShoot -= 10;
                    }
                    else
                    {
                        enemy1.xForShoot += 10;
                    }
                }
                if (enemy1.xForShoot >= 2000)
                {
                    System.out.println("areeee");
                    enemy1.shooting = false;
                    enemy1.xShoot = false;
                }
                if (enemy1.xForShoot <= (-10))
                {
                    System.out.println("naaaaaaaa");
                    enemy1.shooting = false;
                    enemy1.xShoot = false;
                }
            }
            loopInMap=((Map.showingIndex-1)*8)+1;
            coordinateOfMap =1;
            boolean print1 = true;
            while(loopInMap <= (16+Map.showingIndex-1)*8)
            {

                if (map.getMap(loopInMap)=="box" || map.getMap(loopInMap)=="softwall1" || map.getMap(loopInMap)=="softwall2" || map.getMap(loopInMap)=="softwall3" || map.getMap(loopInMap)=="softwall4" )
                {
                    int widthPic = 0;
                    int heightPic = 0;
                    if (enemy1.name == "enemy1")
                    {
                        widthPic = khengenemyPic.getWidth();
                        heightPic = khengenemyPic.getHeight();
                    }
                    else if (enemy1.name == "enemy2")
                    {
                        widthPic = smallEnemyPic.getWidth();
                        heightPic = smallEnemyPic.getHeight();
                    }
                    else
                    {
                        widthPic = enemyPic.getWidth();
                        heightPic = enemyPic.getHeight();
                    }



                    if (enemy1.locX  >= ((coordinateOfMap / 8) * 120 - widthPic ) && enemy1.locX  <= ((coordinateOfMap / 8) * 120 + box2.getWidth()) )
                    {
                        if (enemy1.locY >= (((coordinateOfMap%8)-1)*120 - heightPic) && enemy1.locY <= (((coordinateOfMap%8)-1)*120 + box2.getHeight()))
                        {
                            System.out.println("enemy locky "+ enemy1.locX);
                            //System.out.println("box " +((coordinateOfMap / 8) * 120 + box2.getWidth()));
                            //System.out.println("@+" + (enemy1.locX - ((coordinateOfMap / 8) * 120 + box2.getWidth())));
                            //System.out.println("BOX WIDT" + box2.getWidth());
                            //System.out.println("BOX WIDT2" + box2.getHeight());
                           // if (enemy1.locX == ((coordinateOfMap / 8) * 120 + box2.getWidth())
                            if (Math.abs(enemy1.locX - ((coordinateOfMap / 8) * 120 + box2.getWidth())) >=0 && Math.abs(enemy1.locX - ((coordinateOfMap / 8) * 120 + box2.getWidth()))<=10 )
                            {
                                //System.out.println("hereee123456");
                                enemy1.boxLeft = false;
                            }
                            if ( enemy1.locX ==((coordinateOfMap / 8) * 120 + (box2.getWidth()) + 120)  )
                            {
                                enemy1.boxRight = false ;
                            }
                            //System.out.println(enemy1.locY + "%");
                            //System.out.println(((((coordinateOfMap%8)-1)*120) - heightPic) + 140);
                            if (Math.abs(enemy1.locY - ((((coordinateOfMap%8)-1)*120) + 120)) >= 0 && Math.abs(enemy1.locY - ((((coordinateOfMap%8)-1)*120) + 120))<=10 )
                            {
                                //System.out.println("hereee3");
                                enemy1.boxUp = false;

                            }
                            if (Math.abs(enemy1.locY  - ((((coordinateOfMap%8)-1)*120 + box2.getHeight() -220 )))>=0 && Math.abs(enemy1.locY  - ((((coordinateOfMap%8)-1)*120 + box2.getHeight() - 220 )))<=10)
                            //if (enemy1.locY == (((coordinateOfMap%8)-1)*120 + box2.getHeight() + 120 ))
                            {
                                //System.out.println("hereee4444444444");
                                enemy1.boxDown = false ;
                            }
                                print1=false;
                            //System.out.println("FInd");
                        }
                    }
                }

                loopInMap++;
                coordinateOfMap++;
            }
            if (print1 == true)
            {
                enemy1.boxDown = true;
                enemy1.boxUp = true;
                enemy1.boxLeft = true;
                enemy1.boxRight = true;
                //System.out.println("1213");
                if (enemy1.name == "enemy1")
                {
                    int numberOfHealth = 0 ;
                    int positionOfHealth = -1;
                    while (numberOfHealth != 1)
                    {
                        //System.out.println("sososos");
                        g2d.drawImage( healthImage, enemy1.locX + positionOfHealth , enemy1.locY-30  , null);
                        numberOfHealth++;
                        positionOfHealth += healthImage.getWidth();

                    }
                    g2d.drawImage(khengenemyPic,enemy1.locX,enemy1.locY,null);
                }
                else
                    if (enemy1.name == "enemy2")
                    {
                        int numberOfHealth = 0 ;
                        int positionOfHealth = enemy1.health * - (enemy1.health);
                        while (numberOfHealth != enemy1.health)
                        {
                            //System.out.println("sososos");
                            g2d.drawImage( healthImage, enemy1.locX + positionOfHealth , enemy1.locY-30  , null);
                            numberOfHealth++;
                            positionOfHealth += healthImage.getWidth();

                        }
                        g2d.drawImage(smallEnemyPic,enemy1.locX,enemy1.locY,null);
                    }
                else
                    {
                        int numberOfHealth = 0 ;
                        int positionOfHealth = enemy1.health * - (enemy1.health);
                        while (numberOfHealth != enemy1.health)
                        {
                            //System.out.println("sososos");
                            g2d.drawImage( healthImage, enemy1.locX + positionOfHealth , enemy1.locY-30  , null);
                            numberOfHealth++;
                            positionOfHealth += healthImage.getWidth();

                        }
                        g2d.drawImage(enemyPic,enemy1.locX,enemy1.locY,null);
                    }
            }
            else
            {
                //System.out.println("4444");
                if (enemy1.name == "enemy1")
                {
                    int numberOfHealth = 0 ;
                    int positionOfHealth = -1;
                    while (numberOfHealth != 1)
                    {
                        //g2d.drawImage( healthImage, enemy1.lastLocX + positionOfHealth + 5 , enemy1.lastLocY-30  , null);
                        numberOfHealth++;
                        positionOfHealth += healthImage.getWidth();

                    }
                    //g2d.drawImage(khengenemyPic,enemy1.lastLocX+5,enemy1.locY,null);
                }
                else
                if (enemy1.name == "enemy2")
                {
                    int numberOfHealth = 0 ;
                    int positionOfHealth = enemy1.health * - (enemy1.health);
                    while (numberOfHealth != enemy1.health)
                    {
                        //System.out.println("sososos");
                        g2d.drawImage( healthImage, enemy1.lastLocX + positionOfHealth +5 , enemy1.locY-30  , null);
                        numberOfHealth++;
                        positionOfHealth += healthImage.getWidth();

                    }
                    g2d.drawImage(smallEnemyPic,enemy1.lastLocX+5,enemy1.locY,null);
                }
                else
                {
                    int numberOfHealth = 0 ;
                    int positionOfHealth = enemy1.health * - (enemy1.health);
                    while (numberOfHealth != enemy1.health )
                    {
                        //System.out.println("sososos");
                        g2d.drawImage( healthImage, enemy1.lastLocX + positionOfHealth +5 , enemy1.locY-30  , null);
                        numberOfHealth++;
                        positionOfHealth += healthImage.getWidth();

                    }
                    g2d.drawImage(enemyPic,enemy1.lastLocX+5,enemy1.locY,null);
                }
            }
            //System.out.println(enemy1.locY);

            //g2d.drawImage(enemyPic,enemy1.locX,enemy1.locY ,null);
            if (enemy1.name != "enemy1")
            {
                enemy1.differenceLocX = enemy1.locX - state.locX;
                if (enemy1.differenceLocX < -300)
                {
                     enemy1.goRight = true;
                    enemy1.goDown = false;
                    //enemy1.goUp = false;
                }
                else
                {
                    enemy1.goRight = false;
                    enemy1.goDown = true;
                    //enemy1.goDown = true;
                }
                if (Math.abs(enemy1.locX-state.locX)<800)
                {
                    enemy1.stop = true;
                    enemy1.shooting = true;
                    enemy1.goDown = true;
                    //enemy1.goDown = true;
                }
                else
                {
                    enemy1.stop = false;
                    enemy1.goDown = false;
                    //enemy1.goUp = false;
                }
            }
            else
            {
                enemy1.differenceLocX = enemy1.locX - state.locX;
                enemy1.differenceLocy = enemy1.locY - state.locY;
                if (enemy1.locX >= state.locX && enemy1.locX <= state.locX + tankBody.getWidth())
                {
                    if (enemy1.locY >= state.locY && enemy1.locY <= state.locY + tankBody.getHeight()) {
                        iterator1.remove();
                        state.health--;
                    }
                }
                enemy1.enemy1 = true;
            }
        }

        //










        //Showing health of tank
        int numberOfHealth = 0 ;
        int positionOfHealth = state.positionOfHealth;
        while (numberOfHealth != state.health)
        {
            if (state.toBox == true)
            g2d.drawImage( healthImage, state.lastLocX + positionOfHealth +5, state.lastLocY-30  , null);
            else
                g2d.drawImage( healthImage, state.locX + positionOfHealth, state.locY-30 , null);
            numberOfHealth++;
            positionOfHealth += healthImage.getWidth();

        }
        AffineTransform at;
        if (print== true)
        {
            at = AffineTransform.getTranslateInstance(state.locXhead,state.locYhead);
        }
        else
        {
            at = AffineTransform.getTranslateInstance(state.lastLocXhead+10,state.lastLockYhead+1);
        }
        //positon the picture of tank head
        /*if (GameState.degreeOfRotate>90 && GameState.degreeOfRotate<180 )
        {
            state.locXhead= state.locXhead - (int)((180-GameState.degreeOfRotate)/2);
            state.locYhead= state.locYhead- (int) ((180-GameState.degreeOfRotate)-10);
            at = AffineTransform.getTranslateInstance(state.locXhead-((180-GameState.degreeOfRotate)/2),state.locYhead-((180-GameState.degreeOfRotate)-10));
        }
        if (GameState.degreeOfRotate>=0 && GameState.degreeOfRotate<=90 )
        {
            state.locXhead = state.locXhead-(int) ((GameState.degreeOfRotate)/2);
            state.locYhead = state.locYhead-(int) ((GameState.degreeOfRotate)/2);
            at = AffineTransform.getTranslateInstance(state.locXhead-((GameState.degreeOfRotate)/2),state.locYhead-((GameState.degreeOfRotate)/2));
        }
        if (GameState.degreeOfRotate>=180 && GameState.degreeOfRotate<=270 )
        {
            if (GameState.degreeOfRotate>=225 && GameState.degreeOfRotate<=270 )
            {
                state.locXhead = state.locXhead+(int) ((GameState.degreeOfRotate-180)/2);
                state.locYhead = state.locYhead-(int) (GameState.degreeOfRotate-180);
                at = AffineTransform.getTranslateInstance(state.locXhead+((GameState.degreeOfRotate-180)/2),state.locYhead-(GameState.degreeOfRotate-180));
            }
            if (GameState.degreeOfRotate>=180 && GameState.degreeOfRotate<225 )
            {
                state.locXhead = state.locXhead+(int) (GameState.degreeOfRotate-180)/2;
                state.locYhead = state.locYhead-(int) ((GameState.degreeOfRotate-180)-40);
                at = AffineTransform.getTranslateInstance(state.locXhead+((GameState.degreeOfRotate-180)/2),state.locYhead-(GameState.degreeOfRotate-180)-40);
            }
        }
        if (GameState.degreeOfRotate>270 && GameState.degreeOfRotate<=360 )
        {
            state.locXhead = state.locXhead+(int) ((360-GameState.degreeOfRotate)/2);
            state.locYhead = state.locYhead-(int) ((360-GameState.degreeOfRotate)/2);
            at = AffineTransform.getTranslateInstance(state.locXhead+((360-GameState.degreeOfRotate)/2),state.locYhead-((360-GameState.degreeOfRotate)/2));
        }*/
        at.rotate(Math.toRadians(GameState.degreeOfRotate),tankHead.getWidth()/2,tankHead.getHeight()/2);
        if (state.firstGun == true)
        {
            g2d.drawImage( tankHead, at,null);
        }
        else
        {
            g2d.drawImage( tankHead2, at,null);
        }

        //Show the number of remaining shoot for each gone
        //
        if (state.firstGun == true)
        {
            g2d.drawImage( firstGunNumber, 10,30,null);
            String str = Integer.toString(state.numberOfFirstGun);
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(20.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, 60, 65);
        }
        else
        {
            g2d.drawImage( secondGunNumber, 10,22,null);
            String str = Integer.toString(state.numberOfSecondGun);
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(20.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, 60, 65);
        }
        if (GameState.shieldRemain >=0)
        {
            //g2d.drawImage( secondGunNumber, 10,22,null);
            String str = Integer.toString(GameState.shieldRemain);
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(20.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, 60, 100);
        }
        //Time of GameOver
        if (state.health <= 0)
        {
            BufferedImage gameOver;
            gameOver = new ImgUtils().scaleImage(GAME_WIDTH,GAME_HEIGHT,"gameOver.png");
            g2d.drawImage(gameOver,0,0 , null);
            //state.gameOver = true;

        }





        // Print FPS info
        long currentRender = System.currentTimeMillis();
        if (lastRender > 0) {
            fpsHistory.add(1000.0f / (currentRender - lastRender));
            if (fpsHistory.size() > 100) {
                fpsHistory.remove(0); // remove oldest
            }
            float avg = 0.0f;
            for (float fps : fpsHistory) {
                avg += fps;
            }
            avg /= fpsHistory.size();
            String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
                    avg, (currentRender - lastRender));
            g2d.setColor(Color.CYAN);
            g2d.setFont(g2d.getFont().deriveFont(18.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            int strHeight = g2d.getFontMetrics().getHeight();
            //g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight+50);
        }
        lastRender = currentRender;
        // Print user guide
        String userGuide
                = "Starting "
                + "Use Your Keyboard.";
        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.drawString(userGuide, 10, GAME_HEIGHT - 10);
        // Draw GAME OVER
        if (state.gameOver) {
            String str = "GAME OVER";
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
        }
        if (state.starting)
        {
            g2d.drawImage(start1,0,0 , null);
        }
        if (state.starting2)
        {
            g2d.drawImage(start2,0,0 , null);
            String str = "Please presse mouse to countinue";
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(32.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, 10, 60);


        }
    }

}