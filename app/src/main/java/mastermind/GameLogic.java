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
            int next_peg_nr = (int) Math.random() * PEG_NR;
            if (!repeate) {
                if (Arrays.stream(usedPegs).anyMatch(k -> k == next_peg_nr)) {
                    i--;
                    continue;
                }
            }
            solution[i] = new Peg(Mastermind.sPegs[next_peg_nr]);
        }

    }

    public int[] evaluateBW(int[] guess) {
        int[] black_white = {0, 0};
        for (int i = 0; i < PEGS_PER_ROW; i++) {
            if (solution[i].isPickable() && guess[i] == solution[i].getpColor()) {
                black_white[0] += 1;
                solution[i].setPickable(false);
                guess[i] = -1;

            }
        }
        for (int i = 0; i < PEGS_PER_ROW; i++) {
            if (guess[i] != -1) {
                for (int j = 0; j < PEGS_PER_ROW; j++) {
                    if (solution[j].isPickable() && guess[i] == solution[j].getpColor()) {
                        black_white[1] += 1;
                        solution[j].setPickable(false);
                        guess[i] = -1;
                    }
                }
            }
        }
        for (int i = 0; i < PEGS_PER_ROW; i++) {
            solution[i].setPickable(true);
        }
        return black_white;
    }
}
