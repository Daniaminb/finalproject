import java.util.HashMap;
import java.util.Random;

/**
 * make map of game
 */
public class Map {
    //number of health of big enemy
    public int healhOfBigEnemy;
    //number of health of small enemy
    public int healhOfSmallEnemy;
    //number of kheng enemy
    public int numberOfKhengEnemy ;
    //number of small enemy
    public  int numberOfSmallEnemy;
    //number of big enemy
    public int numberOfBigEnemy;


    //numberForHeight --->     8
    // numberForWidth ---->   16
    static int showingIndex=1;
    public int saveIndex;
    //mood of game
    String mood;


    //all elemnts of map
    HashMap<Integer,String> staff;

    public Map()
    {
        staff=new HashMap<>();
    }

    /**
     * set element of ap
     * @param mood mood of game
     */
    public void setMap(String mood)
    {
        if (mood.equals("easy") == true) {
            healhOfBigEnemy = 3;
            healhOfSmallEnemy = 2;
            int numberOfMine = 10;
            numberOfBigEnemy = 1;
            numberOfSmallEnemy = 2;
            numberOfKhengEnemy = 3;
            int numberOfCannon = 10;
            int numberOfMashine =5;
            int i = 1;
            for (i = 1; i <= 16 * 8 * 20; i += 8) {
                staff.put(i, "box");
            }
            for (i = 1; i <= 16 * 8 * 20; i += 40) {
                Random random=new Random();
                int p = random.nextInt(500);
                //System.out.println(" j=" + p);
                while ( staff.get(p + i) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p+i, "box");
            }
            for (i = 1; i <= 16 * 8 * 20; i += 40) {
                Random random=new Random();
                int p = random.nextInt(100);
                //System.out.println(" j=" + p);
                while ( staff.get(p + i) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p+i, "softwall1");
            }
            for (i = 8; i <= 16 * 8 * 20; i += 8) {
                staff.put(i, "box");
            }
            for (i=39 ; i<= 16 * 8 * 20 ; i+=31)
            {
                Random random=new Random();
                int p = random.nextInt(6);
                while (staff.get(i+p) != null && staff.get(i+p) != "box"  )
                {
                   p++;
                }
                staff.put(i + p, "repairfood");
            }

            int j = 0;
            while ( j!= numberOfMine)
            {
                Random random=new Random();
                int p = random.nextInt(300);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p, "mine");
                j++;
            }
            j = 0;
            while ( j!= numberOfCannon)
            {
                Random random=new Random();
                int p = random.nextInt(600);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                System.out.println("cannnnnnnnnnnnnon");
                staff.put(p, "cannon");
                j++;
            }
            j = 0;
            while ( j!= numberOfMashine)
            {
                Random random=new Random();
                int p = random.nextInt(600);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p, "mashin");
                j++;
            }
            j = 0;
            while ( j != numberOfKhengEnemy)
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(400);
                while ( staff.get(p) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy1");
                j++;
            }
            j = 0;
            while ( (j!=numberOfSmallEnemy))
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(700);
                while ( staff.get(p) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy2");
                j++;
            }

            j = 0;
            while ( (j!=numberOfBigEnemy))
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(700);
                while ( staff.get(p) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy3");
                j++;
            }

            //staff.put(86, "box");
            //staff.put(87, "box");
            //staff.put(77, "box");
            //staff.put(68, "box");
            //staff.put(68, "box");
            //staff.put(60, "box");
            //staff.put(51, "box");
            //staff.put(93, "box");
            //staff.put(86, "box");
            staff.put(116, "enemy2");
            staff.put(35, "mine");
            //staff.put(36, "softwall1");
            //staff.put(37, "softwall1");
            staff.put(38, "softwall1");
            //staff.put(129,"soil");

            staff.put(43, "update");
            staff.put(44, "shield");
            staff.put(45, "shield");
        }
        if (mood.equals("normal") == true) {
            healhOfBigEnemy = 4;
            healhOfSmallEnemy = 3;
            int numberOfMine = 15;
            numberOfBigEnemy = 1;
            numberOfSmallEnemy = 2;
            numberOfKhengEnemy = 3;
            int numberOfCannon = 15;
            int numberOfMashine =10;

            int i = 1;
            for (i = 1; i <= 16 * 8 * 20; i += 8) {
                staff.put(i, "box");
            }
            for (i = 1; i <= 16 * 8 * 20; i += 40) {
                Random random=new Random();
                int p = random.nextInt(500);
                //System.out.println(" j=" + p);
                while ( staff.get(p + i) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p+i, "box");
            }
            for (i = 1; i <= 16 * 8 * 20; i += 40) {
                Random random=new Random();
                int p = random.nextInt(100);
                //System.out.println(" j=" + p);
                while ( staff.get(p + i) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p+i, "softwall1");
            }
            for (i = 8; i <= 16 * 8 * 20; i += 8) {
                staff.put(i, "box");
            }
            for (i=39 ; i<= 16 * 8 * 20 ; i+=31)
            {
                Random random=new Random();
                int p = random.nextInt(6);
                while (staff.get(i+p) != null && staff.get(i+p) != "box"  )
                {
                    p++;
                }
                staff.put(i + p, "repairfood");
            }
            int j = 0;
            while ( j!= numberOfMine)
            {
                Random random=new Random();
                int p = random.nextInt(300);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p, "mine");
                j++;
            }
            j = 0;
            while ( j!= numberOfCannon)
            {
                Random random=new Random();
                int p = random.nextInt(600);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                System.out.println("cannnnnnnnnnnnnon");
                staff.put(p, "cannon");
                j++;
            }
            j = 0;
            while ( j!= numberOfCannon)
            {
                Random random=new Random();
                int p = random.nextInt(1000);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                System.out.println("cannnnnnnnnnnnnon");
                staff.put(p, "update");
                j++;
            }
            j = 0;
            while ( j!= numberOfMashine)
            {
                Random random=new Random();
                int p = random.nextInt(600);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p, "mashin");
                j++;
            }
            j = 0;
            while ( j != numberOfKhengEnemy)
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(400);
                while ( staff.get(p) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy1");
                j++;
            }
            j = 0;
            while ( (j!=numberOfSmallEnemy))
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(700);
                while ( staff.get(p) != null  )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy2");
                j++;
            }

            j = 0;
            while ( (j!=numberOfBigEnemy))
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(700);
                while ( staff.get(p) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy3");
                j++;
            }

            //staff.put(86, "box");
            //staff.put(87, "box");
            //staff.put(77, "box");
            //staff.put(68, "box");
            //staff.put(68, "box");
            //staff.put(60, "box");
            //staff.put(51, "box");
            //staff.put(93, "box");
            //staff.put(86, "box");
            staff.put(116, "enemy2");
            staff.put(35, "mine");
            //staff.put(36, "softwall1");
            //staff.put(37, "softwall1");
            staff.put(38, "softwall1");
            //staff.put(129,"soil");
        }
        if (mood.equals("hard") == true) {
            healhOfBigEnemy = 5;
            healhOfSmallEnemy = 4;
            int numberOfMine = 20;
            numberOfBigEnemy = 1;
            numberOfSmallEnemy = 2;
            numberOfKhengEnemy = 3;
            int numberOfCannon = 5;
            int numberOfMashine =5;

            int i = 1;
            for (i = 1; i <= 16 * 8 * 20; i += 8) {
                staff.put(i, "box");
            }
            for (i = 1; i <= 16 * 8 * 20; i += 40) {
                Random random=new Random();
                int p = random.nextInt(500);
                //System.out.println(" j=" + p);
                while ( staff.get(p + i) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p+i, "box");
            }
            for (i = 1; i <= 16 * 8 * 20; i += 40) {
                Random random=new Random();
                int p = random.nextInt(100);
                //System.out.println(" j=" + p);
                while ( staff.get(p + i) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p+i, "softwall1");
            }
            for (i = 8; i <= 16 * 8 * 20; i += 8) {
                staff.put(i, "box");
            }
            for (i=39 ; i<= 16 * 8 * 20 ; i+=31)
            {
                Random random=new Random();
                int p = random.nextInt(6);
                while (staff.get(i+p) != null && staff.get(i+p) != "box"  )
                {
                    p++;
                }
                staff.put(i + p, "repairfood");
            }
            int j = 0;
            while ( j!= numberOfMine)
            {
                Random random=new Random();
                int p = random.nextInt(300);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p, "mine");
                j++;
            }
            j = 0;
            while ( j!= numberOfCannon)
            {
                Random random=new Random();
                int p = random.nextInt(600);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                System.out.println("cannnnnnnnnnnnnon");
                staff.put(p, "cannon");
                j++;
            }
            j = 0;
            while ( j!= numberOfMashine)
            {
                Random random=new Random();
                int p = random.nextInt(600);
                //System.out.println(" j=" + p);
                while ( staff.get(p) != null && staff.get(p)!="box" )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put(p, "mashin");
                j++;
            }
            j = 0;
            while ( j != numberOfKhengEnemy)
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(400);
                while ( staff.get(p) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy1");
                j++;
            }
            j = 0;
            while ( (j!=numberOfSmallEnemy))
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(700);
                while ( staff.get(p) != null  )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy2");
                j++;
            }

            j = 0;
            while ( (j!=numberOfBigEnemy))
            {
                //System.out.println("hereerer");
                Random random=new Random();
                int p = random.nextInt(700);
                while ( staff.get(p) != null )
                {
                    //System.out.println(" j=" + p);
                    p++;
                }
                staff.put( p, "enemy3");
                j++;
            }

            //staff.put(86, "box");
            //staff.put(87, "box");
            //staff.put(77, "box");
            //staff.put(68, "box");
            //staff.put(68, "box");
            //staff.put(60, "box");
            //staff.put(51, "box");
            //staff.put(93, "box");
            //staff.put(86, "box");
            staff.put(116, "enemy2");
            staff.put(35, "mine");
            //staff.put(36, "softwall1");
            //staff.put(37, "softwall1");
            staff.put(38, "softwall1");
            //staff.put(129,"soil");
            System.out.println("Harrd");
        }
    }

    /**
     * get elent of map
     * @param index index of map
     * @return name of index
     */
    public String getMap(int index)
    {
        return staff.get(index);
    }

    /**
     * set element of map
     * @param index index of map
     * @param name name of index
     */
    public void setElementMap(int index,String name)
    {
        staff.put(index,name);
    }

    /**
     * remove elemnts of map
     * @param index index of map
     */
    public void removeElementMap(int index)
    {
        staff.remove(index);
    }

    /**
     * remove all elemnts
     */
    public void removeAllElement()
    {
        staff.clear();
    }
}
