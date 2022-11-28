package mastermind;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

public class GameLogic {
    static final int PEG_NR = 6;
    static final int PEGS_PER_ROW = 4;
    private final Peg[] solution = new Peg[PEGS_PER_ROW];
    private final boolean repeatable;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public GameLogic(boolean repeate) {
        repeatable = repeate;
        int[] usedPegs = new int[0];
        for (int i = 0; i < PEGS_PER_ROW; i++) {
            boolean skip = false;
            int next_peg_nr = (int) (Math.random() * PEG_NR);
            if (!repeate) {
                for (int j = 0; j<i; j++) {
                    if (solution[j].getpColor() == next_peg_nr) {
                        i--;
                        skip = true;
                        break;
                    }
                }
            }
            if (skip) continue;
            solution[i] = new Peg(next_peg_nr);
        }

    }
    public GameLogic(int[] sol, boolean repeat){
        repeatable = repeat;
        for (int i = 0; i < sol.length; i++){
            solution[i]=new Peg(sol[i]);
        }
    }

    public int[] evaluateBW(Peg[] pegs) {
        int[] black_white = {0, 0};
        boolean[] used = new boolean[pegs.length];
        for (int i = 0; i < pegs.length; i++) {
            if (solution[i].isActive() &&  solution[i].equals(pegs[i])) {
                black_white[0] += 1;
                solution[i].setActive(false);
                used[i]=true;
            }
        }
        for (int i = 0; i < pegs.length; i++) {
            if (used[i] == false) {
                for (int j = 0; j < pegs.length; j++) {
                    if (solution[j].isActive() &&solution[j].equals(pegs[i])) {
                        black_white[1] += 1;
                        solution[j].setActive(false);
                        used[i] = true;
                    }
                }
            }
        }
        for (int i = 0; i < PEGS_PER_ROW; i++) {
            solution[i].setActive(true);
        }
        return black_white;
    }

    public Peg[] getSolution(){
        return solution;
    }
}
