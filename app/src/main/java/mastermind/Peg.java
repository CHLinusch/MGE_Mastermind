package mastermind;

import java.util.Objects;

public class Peg {

    private final int pColor;
    private boolean active;

    public Peg(int pegcolor){
        pColor = pegcolor;
        active = true;
    }

    public Peg(int pegcolor, boolean pickable){
        pColor = pegcolor;
        this.active = pickable;
    }

    public int getpColor() {
        return pColor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peg peg = (Peg) o;
        return pColor == peg.pColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pColor);
    }
}
