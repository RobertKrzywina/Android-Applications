package pl.robert.basket;

import android.widget.ImageView;

class MovableElement {

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

    void increaseX(int value) {
        this.x += value;
    }

    void decreaseX(int value) {
        this.x -= value;
    }

    void increaseY(int value) {
        this.y += value;
    }

    void decreaseY(int value) {
        this.y -= value;
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

    void setY(int y) {
        this.y = y;
    }

    int getWidth() {
        return width;
    }
}
