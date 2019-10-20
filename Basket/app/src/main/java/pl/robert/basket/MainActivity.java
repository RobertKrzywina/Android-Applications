package pl.robert.basket;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView score;
    private TextView tapToStart;

    static int frameWidth;
    static int screenHeight;

    private int currentScore;

    static boolean hasUserTapped = false;
    private boolean hasUserStarted = false;

    private MovableElement basket;
    private MovableElement square;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score);
        tapToStart = findViewById(R.id.tapToStart);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (this.hasUserStarted) {
            checkIfUserTapped(event);
        } else {
            this.hasUserStarted = true;
            initializeMovableElements();
            setScreenHeight();
            setFrameWidth();
            setVisibilityOfTextViews();
            runThread();
        }
        return true;
    }

    private void checkIfUserTapped(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            hasUserTapped = true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            hasUserTapped = false;
        }
    }

    private void initializeMovableElements() {
        this.basket = new Basket((ImageView) findViewById(R.id.basket));
        this.square = new Fruit((ImageView) findViewById(R.id.square));
    }

    private void setScreenHeight() {
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        Point p = new Point();
        d.getSize(p);
        screenHeight = p.y;
    }

    private void setFrameWidth() {
        frameWidth = findViewById(R.id.main).getWidth();
    }

    private void setVisibilityOfTextViews() {
        this.tapToStart.setVisibility(View.GONE);
        this.score.setVisibility(View.VISIBLE);
    }

    private void runThread() {
        final Handler handler = new Handler();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        basket.animate(20);
                        square.animate(12);
                        checkHit();
                    }
                });
            }
        }, 0, 20);
    }

    /**
     * Check if element has fallen into basket.
     * If yes then increase current score and move element outside of screen.
     */
    private void checkHit() {
        int difference = square.getX() - basket.getX();
        boolean hasElementFallenIntoBasket =
                square.getY() >= basket.getY() && difference > 0 && difference <= basket.getWidth();
        if (hasElementFallenIntoBasket) {
            score.setText(String.valueOf(++currentScore));
            square.moveToBottom(screenHeight + 1);
        }
    }
}
