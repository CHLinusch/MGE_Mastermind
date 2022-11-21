package mastermind;

public class Peg {

    private final int pColor;
    private boolean pickable;

    public Peg(int pegcolor){
        pColor = pegcolor;
        pickable = true;
    }

    public int getpColor() {
        return pColor;
    }

    public boolean isPickable() {
        return pickable;
    }

    public void setPickable(boolean pickable) {
        this.pickable = pickable;
    }
}
