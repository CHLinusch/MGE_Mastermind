package mastermind;

import com.example.mastermind.R;

public class Mastermind {
    static final int BLUE = 0, GREEN = 1, RED = 2, BROWN = 3, YELLOW = 4, ORANGE = 5,
            MAX_PEGS = 4;

    static final int[] pPegs = { R.drawable.bluepeg, R.drawable.greenpeg, R.drawable.redpeg,
            R.drawable.brownpeg, R.drawable.yellowpeg, R.drawable.orangepeg };
    static final int[] sPegs = {R.drawable.blacksol, R.drawable.whitesol};

    static int rows_nr = 8;
    static int total_pegs = (rows_nr + 1) * MAX_PEGS;


}
