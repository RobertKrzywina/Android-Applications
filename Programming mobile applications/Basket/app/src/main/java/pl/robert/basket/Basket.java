package pl.robert.basket;

import android.widget.ImageView;

import static pl.robert.basket.MainActivity.frameWidth;
import static pl.robert.basket.MainActivity.hasUserTapped;

class Basket extends MovableElement {

    Basket(ImageView element) {
        super(element);
    }

    @Override
    void animate(int speed) {
        if (hasUserTapped) {
            this.moveToLeft(speed);
        } else {
            this.moveToRight(speed);
        }
        /* Don't let basket to be outside the screen area */
        if (this.getX() < 0) {
            this.setX(0);
        }
        /* Don't let basket to be outside the screen area */
        if (this.getX() > frameWidth - this.getWidth()) {
            this.setX(frameWidth - this.getWidth());
        }
        this.getElement().setX(this.getX());
    }
}
