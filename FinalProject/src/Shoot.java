import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * add new shoot for alliens tank
 */
public class Shoot {
    //position of x
    public int x;
    //show the shoot or not
    public boolean show = true ;
    //position of y
    public int y;
    //change the directon of shoot or not
    public int moodOfShoot = 1;
    //degree of shooting
    public int degree;
    //add difference
    int adding;
    boolean done = false;
    //use secondgun or firstgun
    public boolean secondGun = false;
    //
    static int power = 0;


    /**
     * make a new shoot
     * @param x x of shootg
     * @param y y of shooting
     * @param degree degree of shooting
     */
    public Shoot(int x,int y,int degree )
    {
        this.degree=degree;
        //System.out.println(degree);
        /*int addingy=0;
        int addingx=0;
        /*if (degree >=270 && degree<360)
        {
            degree -=90;
            addingy += 400;
        }
        if (degree>=360)
        {
            degree -= 180;
            addingy += 25;
        }
        int diffrence = Math.abs(degree-270) / 2;
        System.out.println(diffrence);
        int totaldifference= 90-(180 - ((degree-270) /2));
        //System.out.println(diffrence);
        //int adding = ((degree - 270)/ 6) * 10 - 65 ;
        int adding = (int)(Math.sin( diffrence) * 50 *2);
        //int addingy=(int) Math.asin((double) diffrence) * 78 *2;
        //System.out.println(Math.abs((Math.cos (totaldifference) * adding)));
        addingx += Math.abs((int) (Math.sin( totaldifference) * adding)) ;
        addingy += Math.abs((int) (Math.cos( totaldifference) * adding)) ;
        System.out.println(addingy);*/

        adding = 0 ;
        //System.out.println(degree);
        if (degree >90 && degree <180)
        {
            degree -= 90;
        }
        if ( degree >0 && degree <=90)
        {
            degree = 360 - degree ;
            //System.out.println("degree" + degree);
            adding += 100;
            //System.out.println(adding);
        }
        if (degree>=180 && degree<270)
        {
            degree = 360 - (degree -180) ;
        }
        if (degree >=270 && degree<315)
        {
            adding= degree - 253 + 2;
            //System.out.println("hi");
        }
        if (degree>=360 && degree<405)
        {
            adding= degree - 343 + 2;
            adding +=120;
            //System.out.println("hiiiiiiii");
            moodOfShoot = -1;
        }
        if (degree>=405 && degree<450)
        {
            moodOfShoot = -1;
            if (degree < 416)
                adding= degree - 373 + 13;
            else
                adding= degree - 373 + 13 + degree - 336;
        }
        if (degree>=315 && degree <=360)
        {
            if (degree < 336)
            adding= degree - 283 + 13;
            else
                adding= degree - 283 + 13 + degree - 336;
        }
        if (adding < 0 )
        {
            adding = 0 ;
        }
        /*if (degree>=0 && degree <=90)
        {
            System.out.println("here");
            addingx += 5;
            addingy += 45;
        }*/
        //System.out.println("addingy" + addingy);
        this.y= y  + ( adding )   ;
        if (moodOfShoot == -1)
        this.x= x + ( adding ) -150 ;
        else
            this.x= x + ( adding );

    }

    /**
     * update x and y of shooting
     */
    public void update()
    {
       /* if (degree>=360 && degree<450)
        {
            System.out.println((degree-360));
            //System.out.println(10 *Math.sin((double) (degree - 360)));
            adding+=adding/2;
            x = (int)(adding *Math.cos((double) (degree - 360)));
            y = (int)(adding *Math.sin((double) (degree - 360)));
        }*/
        /*if (degree>=270 && degree <360)
        {
            System.out.println((degree-270));
            //System.out.println(10 *Math.sin((double) (degree - 360)));
            //adding+=addForUpdate;
            x = (int)(adding *Math.cos((double) (degree - 270)));
            y = (int)(adding *Math.sin((double) (degree - 270)));
        }*/
        //x += 10 *Math.sin((double) degree);
        //y += 10 *Math.cos((double) degree);
        //System.out.println(y);
        //y = y + (int)((int) Math.tan((double) degree) * 1) + 5 ;
        //System.out.println(y);
        System.out.println(degree);
        if (degree==90)
        {
            y += 20;
        }
        if (degree==180)
        {
            x -= 40;
        }
        if (degree==270)
        {
            y -= 20;
        }
        if (degree==0 || degree==360)
        {
            x += 40;
        }
        if (degree>90 && degree<=135 )
        {
            x -= Math.tan((double) degree) *2 +11.5;
            y += Math.tan((double) degree) *2 +9;
        }
        if (degree > 135 && degree < 180)
        {

            x -= Math.tan((double) degree) *2 +23;
            y += Math.tan((double) degree) *2 +4.5;
        }
        //
        if (degree>180 && degree<=225 )
        {
            if (done==true)
            {
                y+=10;
                x -= 55;
            }
            if (degree > 180 && degree <=190)
            {
                x -= 20;
                y -= 2;
            }
            if (degree > 190 && degree <=200)
            {
                x -= 22;
                y -= 3;
            }
            if (degree > 200 && degree <=210)
            {
                x -= 24;
                y -= 4;
            }
            if (degree > 210 && degree <=220)
            {
                x -= 26;
                y -= 5;
            }
            if (degree > 220 && degree <225)
            {
                x -= 27;
                y -= 6;
            }
        }
        //
        if (degree > 225 && degree < 270)
        {
            if (degree > 225 && degree <=235)
            {
                if (done==false)
                {
                    x -= 21;
                    done = true;
                }
                x -= 13;
                y -= 11;
            }
            if (degree > 235 && degree <=245)
            {
                if (done==false)
                {
                    x -= 17;
                    done = true;
                }
                x -= 7;
                y -= 10;
            }
            if (degree > 245 && degree <=255)
            {
                if (done==false)
                {
                    x -= 15;
                    done = true;
                }
                x -= 5;
                y -= 9;
            }
            if (degree > 255 && degree <=265)
            {
                if (done==false)
                {
                    x -= 10;
                    done = true;
                }
                x -= 3;
                y -= 8;
            }
            if (degree > 265 && degree <270)
            {
                if (done==false)
                {
                    x -= 5;
                    done = true;
                }
                x -= 1;
                y -= 7;
            }
        }
        //

        if (degree>270 && degree<=315 )
        {
            if (done == false)
            {
                x+=20;
                y -= 5;
                done = true;
            }

            if (degree > 270 && degree <=280)
            {
                x += 2;
                y -= 11;
            }
            if (degree > 280 && degree <=290)
            {
                x += 4;
                y -= 10;
            }
            if (degree > 290 && degree <=300)
            {
                x += 6;
                y -= 9;
            }
            if (degree > 300 && degree <=310)
            {
                x += 8;
                y -= 8;
            }
            if (degree > 310 && degree <315)
            {
                x += 10;
                y -= 7;
            }
            //System.out.println("heeeeeeeeeeeeee");
        }
        if (degree > 315 && degree < 360)
        {
            if (done == false)
            {
                if (degree > 355 && degree <360)
                {
                    y -= 20;
                }
                if (degree >= 315 && degree <=320)
                {
                    y += 10;
                }
                if (degree > 320 && degree <=325)
                {
                    y -= 5;
                }

                if (degree >= 347 && degree <=350)
                {
                    y -= 5;
                }
                if (degree > 350 && degree <=355)
                {
                    y -= 15;
                }
                y += 12;
                done = true;
            }
            if (degree > 315 && degree <=325)
            {
                x += 12;
                y -= 5;
            }
            if (degree > 325 && degree <=335)
            {
                x += 14;
                y -= 4;
            }
            if (degree > 335 && degree <=345)
            {
                x += 16;
                y -= 3;
            }
            if (degree > 345 && degree <=355)
            {
                x += 18;
                y -= 2;
            }
            if (degree > 355 && degree <360)
            {
                x += 20;
                y -= 1;
            }
        }
        //
        if (degree>360 && degree<=405 )
        {
            if (done == false)
            {
                x += 50;
                y -= 30;
                done = true;
            }

            if (degree > 360 && degree <=370)
            {

                x += 20;
                y += 1;
            }
            if (degree > 370 && degree <=380)
            {
                x += 18;
                y += 3;
            }
            if (degree > 380 && degree <= 390)
            {
                x += 16;
                y += 5;
            }
            if (degree > 390 && degree <=400)
            {
                x += 14;
                y += 7;
            }
            if (degree > 400 && degree < 405)
            {
                x += 12;
                y += 10;
            }
            if (degree == 405)
            {

            }
            //System.out.println("heeeeeeeeeeeeee");
        }
        if (degree > 405 && degree <450 )
        {
            if (done == false)
            {
                x += 25;
                if (degree > 415 && degree <=425)
                {
                    x += 50;
                }
                if (degree > 405 && degree <=415)
                {
                    y += 100;
                    x += 160;
                }
                if (degree > 425 && degree <=430)
                {
                    x += 30;
                }
                if (degree > 430 && degree<435)
                {
                    x+=20;
                }
                if (degree > 445 && degree <450)
                {
                    System.out.println("192");
                    x -= 40;
                    y -= 100;
                }
                if (degree > 435 && degree <=440)
                {
                    x -= 10;
                    y -= 30;
                }
                if (degree >440 && degree <= 445)
                {
                    x -= 30;
                    y -= 30;
                }
                y += 40;
                done = true;
            }
            if (degree > 405 && degree <=415)
            {
                x += 10;
                y += 12;
            }
            if (degree > 415 && degree <=425)
            {
                x += 8;
                y += 14;
            }
            if (degree > 425 && degree <=435)
            {
                x += 6;
                y += 16;
            }
            if (degree > 435 && degree <=445)
            {
                x += 4;
                y += 18;
            }
            if (degree > 445 && degree <450)
            {
                x += 2;
                y += 20;
            }
        }
    }

}
