/*** In The Name of Allah ***/

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods 
 * in the while loop (update() and render()) should be 
 * long running! Both must execute very quickly, without 
 * any waiting and blocking!
 *
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameLoop implements Runnable {

    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */

    public static final int FPS = 30;

    private GameFrame canvas;
    private GameState state;
    private ArrayList<Enemy> enemy;
    public Map map;

    public GameLoop(GameFrame frame) {
        canvas = frame;
        map=new Map();
        map.setMap("easy");
    }

    /**
     * This must be called before the game loop starts.
     */
    public void init() {
        state = new GameState();
        state.map = map;
        enemy = new ArrayList<>();
        //enemy.add(new Enemy());
        canvas.addKeyListener(state.getKeyListener());
        canvas.addMouseListener(state.getMouseListener());
        canvas.addMouseMotionListener(state.getMouseMotionListener());
    }

    @Override
    public void run() {
        boolean gameOver = false;
        while (!gameOver) {
            try {
                long start = System.currentTimeMillis();
                //
                state.update(map);
                Iterator<Enemy> iterator=enemy.iterator();
                while (iterator.hasNext())
                {
                    iterator.next().update(map);
                }
                canvas.render(state ,map,enemy);
                gameOver = state.gameOver;
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ex) {
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        boolean starting = true;
        while ( starting)
        {
            try {
            long start = System.currentTimeMillis();
            //
            state.update(map);
            //canvas.render(state ,map,enemy);
            starting = state.starting;
            //
            long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
            if (delay > 0)
                Thread.sleep(delay);
        } catch (InterruptedException ex) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        }
        //canvas.render(state ,map,enemy);
    }
}