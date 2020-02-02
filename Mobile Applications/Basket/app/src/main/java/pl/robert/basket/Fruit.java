package pl.robert.basket;

import android.widget.ImageView;

import static pl.robert.basket.MainActivity.frameWidth;
import static pl.robert.basket.MainActivity.screenHeight;

class Fruit extends MovableElement {

    Fruit(ImageView element) {
        super(element);
    }

    @Override
    void animate(int speed) {
        this.moveToBottom(speed);
        /* If element is outside of the screen area then reset it to the start position */
        if (this.getY() > screenHeight) {
            this.setYToZero();
            this.setX((int) Math.floor(Math.random() * (frameWidth - this.getWidth())));
        }
        this.getElement().setX(this.getX());
        this.getElement().setY(this.getY());
    }
}
