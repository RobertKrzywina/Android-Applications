package pl.robert.basket;

import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.graphics.Point;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Timer;
import java.util.Arrays;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static int frameWidth;
    static int screenHeight;
    static boolean hasUserTapped;

    private int currentScore;
    private boolean hasUserStarted;

    private TextView score;
    private TextView tapToStart;
    private Basket basket;
    private List<Fruit> fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (this.hasUserStarted) {
            checkIfUserTapped(event);
        } else {
            this.hasUserStarted = true;
            configureBasket();
            configureFruits();
            setScreenHeight();
            setFrameWidth();
            configureTextViews();
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

    private void configureBasket() {
        ImageView basketImg = findViewById(R.id.basket);
        basket = new Basket(basketImg);
        basketImg.setVisibility(View.VISIBLE);
    }

    private void configureFruits() {
        ImageView fruit1Img = findViewById(R.id.fruit1);
        ImageView fruit2Img = findViewById(R.id.fruit2);
        ImageView fruit3Img = findViewById(R.id.fruit3);
        ImageView fruit4Img = findViewById(R.id.fruit4);
        ImageView fruit5Img = findViewById(R.id.fruit5);
        ImageView fruit6Img = findViewById(R.id.fruit6);
        ImageView fruit7Img = findViewById(R.id.fruit7);
        ImageView fruit8Img = findViewById(R.id.fruit8);
        ImageView fruit9Img = findViewById(R.id.fruit9);

        fruits = Arrays.asList(
                new Fruit(fruit1Img), new Fruit(fruit2Img), new Fruit(fruit3Img),
                new Fruit(fruit4Img), new Fruit(fruit5Img), new Fruit(fruit6Img),
                new Fruit(fruit7Img), new Fruit(fruit8Img), new Fruit(fruit9Img)
        );

        makeFruitsImagesVisible(
                Arrays.asList(
                        fruit1Img, fruit2Img, fruit3Img,
                        fruit4Img, fruit5Img, fruit6Img,
                        fruit7Img, fruit8Img, fruit9Img
                )
        );
    }

    private void makeFruitsImagesVisible(List<ImageView> fruitsImages) {
        for (ImageView fruitImg : fruitsImages) {
            fruitImg.setVisibility(View.VISIBLE);
        }
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

    private void configureTextViews() {
        score = findViewById(R.id.score);
        tapToStart = findViewById(R.id.tapToStart);
        setTextViewsVisibility();
    }

    private void setTextViewsVisibility() {
        tapToStart.setVisibility(View.GONE);
        score.setVisibility(View.VISIBLE);
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
                        generateFallenFruits();
                    }
                });
            }
        }, 0, 20);
    }

    private void generateFallenFruits() {
        for (MovableElement fruit : fruits) {
            int randomSpeed = (int) ((Math.random() * 10) + 10);
            fruit.animate(randomSpeed);
            checkHit(fruit);
        }
    }

    /**
     * Check if fruit has fallen into basket.
     * If yes then increase current score and move element outside of screen.
     *
     * @param fruit to check
     */
    private void checkHit(MovableElement fruit) {
        int difference = fruit.getX() - basket.getX();
        boolean hasElementFallenIntoBasket =
                fruit.getY() >= basket.getY() && difference > 0 && difference <= basket.getWidth();
        if (hasElementFallenIntoBasket) {
            score.setText(String.valueOf(++currentScore));
            fruit.moveToBottom(screenHeight + 1);
        }
    }
}
