package com.example.koolguy.realshitanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by koolguy on 25.02.2018.
 */

public class Game_view extends View {

    private static int view_height,view_width;
    private Sprite sprite;
    int track;
    Timer t ;

    public Game_view(Context context)
    {
        super(context);
        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.ttt);
       sprite = new Sprite(b,35,35);
        t = new Timer(Long.MAX_VALUE,285);
        track=0;

        new Timer(Long.MAX_VALUE,285);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        view_height=h;
        view_width=w;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.RED );
        p.setTextSize(55);
        canvas.drawText(view_height+" "+view_width,200,200,p);
        canvas.drawRect(view_width/3*2,view_height/3 * 2,100+(view_width/3*2),(view_height/3*2)+120,p);//up
        canvas.drawRect(view_width/3*2,view_height/4 * 3,100+(view_width/3*2),(view_height/4*3)+120,p);// down
        canvas.drawRect(view_width/4*3+30,view_height/3 * 2 +50,100+(view_width/4*3)+30,(view_height/3*2)+120+50,p);//right
        canvas.drawRect(view_width/4*3 -210,view_height/3 * 2 +50,100+(view_width/4*3)-210,(view_height/3*2)+120+50,p);//left
        sprite.Draw(canvas);
    }
    public static int getView_height(){return view_height;}
    public static int getView_width(){return view_width;}
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                sprite.touchListener(x,y,true);
                t.start();invalidate();
                break;
            case MotionEvent.ACTION_UP:
                sprite.touchListener(x,y,false);t.cancel();
                break;
            default:
                sprite.touchListener(x,y,false);t.cancel();
                break;

        }

        return true;
    }
    class Timer extends CountDownTimer {
        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        @Override
        public void onTick(long l) {
            sprite.update();invalidate();
            track++;

        }

        @Override
        public void onFinish() {

        }
    }
}
