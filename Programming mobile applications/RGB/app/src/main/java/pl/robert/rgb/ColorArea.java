package pl.robert.rgb;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;

class ColorArea {

    private ConstraintLayout layout;
    private int red;
    private int green;
    private int blue;

    ColorArea(ConstraintLayout layout) {
        this.layout = layout;
    }

    void setRed(int red) {
        this.red = red;
    }

    void setGreen(int green) {
        this.green = green;
    }

    void setBlue(int blue) {
        this.blue = blue;
    }

    void changeAreaColor() {
        layout.setBackgroundColor(Color.rgb(red, green, blue));
    }
}
