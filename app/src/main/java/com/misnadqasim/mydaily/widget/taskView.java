package com.misnadqasim.mydaily.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class taskView extends View {

    public boolean taskDone;
    public int color;
    public String taskName;
    public String[] subs;

    private int height;

    public taskView(Context context) {
        super(context);
        color = Color.RED;
        taskName = "Go Jogging";
        taskDone = false;
    }

    public taskView(Context context, String task, int color, boolean isDone) {
        super(context);
        this.taskName = task;
        this.color = color;
        this.taskDone = isDone;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = (int) this.getHeight();
        int width = (int) this.getWidth();

        canvas.drawCircle(height/2, height/2, height/2 - (int) (0.15*height), getPaint());
        canvas.drawText(taskName, (int) (1.1*height), (int)(0.65*height), getPaint());
        canvas.drawCircle((int) (0.93*width), (int) (0.4*height), (int) (height*0.03), getPaint());
        canvas.drawCircle((int) (0.93*width), (int) (0.5*height), (int) (height*0.03), getPaint());
        canvas.drawCircle((int) (0.93*width), (int) (0.6*height), (int) (height*0.03), getPaint());
        this.setBackgroundColor(Color.parseColor("#dddddd"));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int x = (int) getX();
        int y = (int) getY();


        // case 1 : if touch on circle
        {
            int c = getHeight()/2;
            int r = getHeight()/2 - (int) (0.15*getHeight());
            if (Math.pow(x-c, 2) + Math.pow(y-c, 2) < r) {

                return true;
            }
        }

        // case 2 : if touch on menu
        {
            int mx = (int) (0.93*getWidth());
            int my = (int) (0.5*getHeight());
            int r = (int) (getWidth() - mx);
            if (Math.pow(x-mx, 2) + Math.pow(y-my, 2) < r) {
                showMenu();
                return true;
            }
        }

        // case 3 : anywhere else
        {
            showSubTask();
        }


        return true;
    }

    private void showMenu() {
    }

    private void showSubTask() {
        this.setMinimumHeight(height*subs.length);
    }

    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setTextSize((int)(getHeight()*0.40));
        paint.setColor(color);
        return paint;
    }
}
