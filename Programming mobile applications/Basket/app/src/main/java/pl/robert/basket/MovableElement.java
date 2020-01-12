package pl.robert.basket;

import android.widget.ImageView;

abstract class MovableElement {

    private ImageView element;
    private int x;
    private int y;
    private int width;

    MovableElement(ImageView element) {
        this.element = element;
        setCoOrdinates();
        setWidth();
    }

    private void setCoOrdinates() {
        this.x = (int) element.getX();
        this.y = (int) element.getY();
    }

    private void setWidth() {
        this.width = element.getWidth();
    }

    abstract void animate(int speed);

    void moveToRight(int speed) {
        this.x += speed;
    }

    void moveToLeft(int speed) {
        this.x -= speed;
    }

    void moveToBottom(int speed) {
        this.y += speed;
    }

    ImageView getElement() {
        return element;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setYToZero() {
        this.y = 0;
    }

    int getWidth() {
        return width;
    }
}
