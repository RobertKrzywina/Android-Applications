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

    private int frameWidth;
    private int screenHeight;

    private int currentScore;

    private boolean hasUserTapped = false;
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
            runScheduler();
        }
        return true;
    }

    private void checkIfUserTapped(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.hasUserTapped = true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            this.hasUserTapped = false;
        }
    }

    private void initializeMovableElements() {
        this.basket = new MovableElement((ImageView) findViewById(R.id.basket));
        this.square = new MovableElement((ImageView) findViewById(R.id.square));
    }

    private void setScreenHeight() {
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        Point p = new Point();
        d.getSize(p);
        this.screenHeight = p.y;
    }

    private void setFrameWidth() {
        this.frameWidth = findViewById(R.id.main).getWidth();
    }

    private void setVisibilityOfTextViews() {
        this.tapToStart.setVisibility(View.GONE);
        this.score.setVisibility(View.VISIBLE);
    }

    private void runScheduler() {
        final Handler handler = new Handler();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePosition();
                    }
                });
            }
        }, 0, 20);
    }

    private void changePosition() {
        hitCheck();
        square.increaseY(12);
        if (square.getY() > screenHeight) {
            square.setY(0);
            square.setX((int) Math.floor(Math.random() * (frameWidth - square.getWidth())));
        }
        square.getElement().setX(square.getX());
        square.getElement().setY(square.getY());

        if (hasUserTapped) {
            basket.decreaseX(20);
        } else {
            basket.increaseX(20);
        }

        if (basket.getX() < 0) {
            basket.setX(0);
        }

        if (basket.getX() > frameWidth - basket.getWidth()) {
            basket.setX(frameWidth - basket.getWidth());
        }

        basket.getElement().setX(basket.getX());
    }

    private void hitCheck() {
        int diff = square.getX() - basket.getX();
        if (square.getY() >= basket.getY() && diff > 0 && diff <= basket.getWidth()) {
            score.setText(String.valueOf(++currentScore));
            square.increaseY(1000);
        }
    }
}

