package rn.travels.in.rntravels.models;

/**
 * Created by demo on 22/02/18.
 */

public class DrawerItemVO {
    private String text;
    int drawable;

    public DrawerItemVO(String text, int drawable) {
        this.text = text;
        this.drawable = drawable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
