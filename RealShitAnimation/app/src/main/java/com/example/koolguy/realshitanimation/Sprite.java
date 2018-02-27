package com.example.koolguy.realshitanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;

import java.util.ArrayList;

/**
 * Created by koolguy on 25.02.2018.
 */

public class Sprite {
    private ArrayList<Rect> UP;
    private ArrayList<Rect> DOWN;
    private ArrayList<Rect> LEFT;
    private ArrayList<Rect> RIGHT;
    private Bitmap bitmap;
    private Rect destination;
    private int x,y;
    private int x_frame,y_frame;
    private int currentframe,direction;
    public Sprite(Bitmap bitmap,int x,int y)
    {
        direction = 0;
        this.bitmap=bitmap;
        UP = new ArrayList<Rect>();
        DOWN = new ArrayList<Rect>();
        LEFT = new ArrayList<Rect>();
        RIGHT = new ArrayList<Rect>();
        ReadBitmap();
        currentframe = 0;
        this.x = x;
        this.y = y;
        currentframe=0;


    }
    public void ReadBitmap()
    {
         x_frame = bitmap.getWidth()/4;
         y_frame =bitmap.getHeight()/4;
        for (int i = 0;i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                switch (i)
                {
                    case 0:DOWN.add(new Rect(x_frame*j,y_frame*i,x_frame*j+x_frame,y_frame*i+y_frame));break;
                    case 1:UP.add(new Rect(x_frame*j,y_frame*i,x_frame*j+x_frame,y_frame*i+y_frame));break;
                    case 2:LEFT.add(new Rect(x_frame*j,y_frame*i,x_frame*j+x_frame,y_frame*i+y_frame));break;
                    case 3:RIGHT.add(new Rect(x_frame*j,y_frame*i,x_frame*j+x_frame,y_frame*i+y_frame));break;

                }


            }

        }


    }
    public void Draw(Canvas canvas)
    {
        Paint p =new Paint();
        p.setColor(Color.BLACK);
        destination= new Rect(x,y,x+x_frame,y+y_frame);
        switch (direction) {
            case 4:
                canvas.drawBitmap(bitmap, RIGHT.get(currentframe), destination, p);break;
            case 3:
                canvas.drawBitmap(bitmap,LEFT.get(currentframe),destination,p);break;
            case 2:
                canvas.drawBitmap(bitmap,DOWN.get(currentframe),destination,p);break;
            case 1:
                canvas.drawBitmap(bitmap,UP.get(currentframe),destination,p);break;
        }

        p.setTextSize(65);
        canvas.drawText(y_frame+" "+x_frame,55,100,p);
    }

    public void touchListener(int x,int y,boolean touch)                  //direction = 0(stop),1(up),2(down),3(left),4(right)
    { int view_width = Game_view.getView_width();
      int view_height = Game_view.getView_height();
      if(x>=view_width/4*3+30&&x<=100+(view_width/4*3)+30&&y>=view_height/3 * 2 +50&&y<=(view_height/3*2)+120+50)
      {
          direction = 4;
          ChangeDirection();
      }
      if (x>=view_width/4*3 -210&&x<=100+(view_width/4*3)-210&&y>=view_height/3 * 2 +50&&y<=(view_height/3*2)+120+50)
      {

          direction =3 ;
          ChangeDirection();
      }
      if (x>=view_width/3*2&&x<=100+(view_width/3*2)&&y>=view_height/3 * 2&&y<=(view_height/3*2)+120)
      {

          direction = 1;
          ChangeDirection();
      }
      if (x>=view_width/3*2&&x<=100+(view_width/3*2)&&y>=view_height/4 * 3&&y<=(view_height/4*3)+120)
      {

          direction = 2;
          ChangeDirection();
      }



    }

    public void update()
    {


      if(direction == 4 ) x+=30;
      if (direction==3) x-=30;
      if (direction==1)y-=30;
      if(direction==2)y+=30;

      currentframe = (currentframe+1)%4;
    }
    public void ChangeDirection()
    {
        currentframe=0;

    }


}
