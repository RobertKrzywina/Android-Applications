package pl.robert.basket;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView score;
    private TextView tapToStart;

    private ImageView basket;
    private ImageView square;
    private ImageView ndSquare;

    private int frameWidth;
    private int basketSize;
    private int screenHeight;

    private int basketX;
    private int basketY;
    private int squareX;
    private int squareY;
    private int ndSquareX;
    private int ndSquareY;

    private int currentScore = 0;

    private boolean action_flg = false;
    private boolean start_flg = false;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score);
        tapToStart = findViewById(R.id.tapToStart);
        basket = findViewById(R.id.basket);
        square = findViewById(R.id.square);
        ndSquare = findViewById(R.id.blackSquare);

        setScreenHeight();
    }

    private void setScreenHeight() {

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        this.screenHeight = point.y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!start_flg) {

            start_flg = true;

            FrameLayout frame = findViewById(R.id.main);
            frameWidth = frame.getWidth();

            basketX = (int) basket.getX();
            basketY = (int) basket.getY();

            basketSize = basket.getWidth();

            tapToStart.setVisibility(View.GONE);
            score.setVisibility(View.VISIBLE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }

        return true;
    }

    private void changePos() {

        hitCheck();
        squareY += 12;
        if (squareY > screenHeight) {
            squareY = 0;
            squareX = (int) Math.floor(Math.random() * (frameWidth - square.getWidth()));
        }
        square.setX(squareX);
        square.setY(squareY);


        ndSquareY += 7;
        if (ndSquareY > screenHeight) {
            ndSquareY = 0;
            ndSquareX = (int) Math.floor(Math.random() * (frameWidth - square.getWidth()));
        }
        ndSquare.setX(ndSquareX);
        ndSquare.setY(ndSquareY);

        if (action_flg) {
            basketX -= 20;
        } else {
            basketX += 20;
        }

        if (basketX < 0) {
            basketX = 0;
        }

        if (basketX > frameWidth - basketSize) {
            basketX = frameWidth - basketSize;
        }

        basket.setX(basketX);
    }

    private void hitCheck() {

        int diff = squareX - basketX;

        if (squareY >= basketY && diff > 0 && diff <= basketSize) {
            score.setText(String.valueOf(++currentScore));
            squareY += 1000;
        }
    }
}

