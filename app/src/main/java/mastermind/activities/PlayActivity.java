package mastermind.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mastermind.R;

import java.util.Arrays;

import mastermind.GameLogic;
import mastermind.Gamestate;
import mastermind.Mastermind;
import mastermind.Peg;
import mastermind.services.SettingsSaveStateService;

public class PlayActivity extends Activity {
    int active_row = 0;
    GameLogic gameLogic;
    Gamestate gamestate;

    Button basepeg0, basepeg1, basepeg2, basepeg3, basepeg4, basepeg5;
    Button btn_peg11, btn_peg12, btn_peg13, btn_peg14 ;
    Button btn_peg21, btn_peg22, btn_peg23, btn_peg24 ;
    Button btn_peg31, btn_peg32, btn_peg33, btn_peg34 ;
    Button btn_peg41, btn_peg42, btn_peg43, btn_peg44 ;
    Button btn_peg51, btn_peg52, btn_peg53, btn_peg54 ;
    Button btn_peg61, btn_peg62, btn_peg63, btn_peg64 ;
    Button btn_peg71, btn_peg72, btn_peg73, btn_peg74 ;
    Button btn_peg81, btn_peg82, btn_peg83, btn_peg84 ;

    ImageView eval11, eval12, eval13, eval14;
    ImageView eval21, eval22, eval23, eval24;
    ImageView eval31, eval32, eval33, eval34;
    ImageView eval41, eval42, eval43, eval44;
    ImageView eval51, eval52, eval53, eval54;
    ImageView eval61, eval62, eval63, eval64;
    ImageView eval71, eval72, eval73, eval74;
    ImageView eval81, eval82, eval83, eval84;

    ImageView[][] evals = new ImageView[][]{{eval11, eval12, eval13, eval14},
            {eval21, eval22, eval23, eval24},
            {eval31, eval32, eval33, eval34},
            {eval41, eval42, eval43, eval44},
            {eval51, eval52, eval53, eval54},
            {eval61, eval62, eval63, eval64},
            {eval71, eval72, eval73, eval74},
            {eval81, eval82, eval83, eval84}
    };
    ImageView solp1, solp2, solp3, solp4;
    ImageView[] solution_view = new ImageView[]{solp1, solp2, solp3, solp4};


    Button[][] pegbuttons = new Button[][]{{btn_peg11, btn_peg12, btn_peg13, btn_peg14},
            {btn_peg21, btn_peg22, btn_peg23, btn_peg24},
            {btn_peg31, btn_peg32, btn_peg33, btn_peg34},
            {btn_peg41, btn_peg42, btn_peg43, btn_peg44},
            {btn_peg51, btn_peg52, btn_peg53, btn_peg54},
            {btn_peg61, btn_peg62, btn_peg63, btn_peg64},
            {btn_peg71, btn_peg72, btn_peg73, btn_peg74},
            {btn_peg81, btn_peg82, btn_peg83, btn_peg84}
    };
    Button[] basebuttons = new Button[]{basepeg0, basepeg1, basepeg2, basepeg3, basepeg4, basepeg5};

    Peg[][] pegs = new Peg[9][4];
    Peg[] basepegs = new Peg[6];





// TODO IMPLEMENT PEGS[][]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_8);
        Bundle extras = getIntent().getExtras();
        String savestate_string = extras.getString("Savestate");
        String repeatable_string = extras.getString("Repeateable");
        gamestate = new Gamestate(savestate_string, repeatable_string);

        int[] solution = gamestate.getPlacedPegs()[8];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if (solution == new int[]{6, 6, 6, 6}){
                gameLogic = new GameLogic( gamestate.isRepeatable());
            }else {
                gameLogic = new GameLogic(solution, gamestate.isRepeatable());
            }
        }

        active_row = 0;
        int[][] pegs_ints = gamestate.getPlacedPegs();
        for (int i = 1; i < pegs_ints.length -1; i++){
            if (Arrays.equals(pegs_ints[i], new int[]{6,6,6,6}))
            {
                active_row = i;
                break;
            }
        }
        //set pegs from gamestate
        for(int i = 0; i < pegs.length-1; i++){
            for (int j = 0; j < 4; j++){
                int color = pegs_ints[i+1][j];
                if (i == active_row){
                    if(color != 6){
                    pegs[i][j] = new Peg(Mastermind.pPegs[color], true);
                    }
                }else{
                    pegs[i][j] = new Peg(Mastermind.pPegs[color], false);
                }
                setPeg_element(i+1, j, color);
            }
        }


        basepeg0 =  findViewById(R.id.play_peg_base_1);
        basepeg1 =  findViewById(R.id.play_peg_base_2);
        basepeg2 =  findViewById(R.id.play_peg_base_3);
        basepeg3 =  findViewById(R.id.play_peg_base_4);
        basepeg4 =  findViewById(R.id.play_peg_base_5);
        basepeg5 =  findViewById(R.id.play_peg_base_6);

        solp1 = findViewById(R.id.play_peg_sol_1);
        solp2 = findViewById(R.id.play_peg_sol_2);
        solp3 = findViewById(R.id.play_peg_sol_3);
        solp4 = findViewById(R.id.play_peg_sol_4);




        for(int i = 0; i < basebuttons.length; i++){
            basebuttons[i].setOnClickListener(onClickListener_base);
            basepegs[i] = new Peg(Mastermind.pPegs[i], true);
        }

        btn_peg11 =  findViewById(R.id.play_peg_box1_1);
        btn_peg12 =  findViewById(R.id.play_peg_box1_2);
        btn_peg13 =  findViewById(R.id.play_peg_box1_3);
        btn_peg14 =  findViewById(R.id.play_peg_box1_4);

        btn_peg21 =  findViewById(R.id.play_peg_box2_1);
        btn_peg22 =  findViewById(R.id.play_peg_box2_2);
        btn_peg23 =  findViewById(R.id.play_peg_box2_3);
        btn_peg24 =  findViewById(R.id.play_peg_box2_4);

        btn_peg31 =  findViewById(R.id.play_peg_box3_1);
        btn_peg32 =  findViewById(R.id.play_peg_box3_2);
        btn_peg33 =  findViewById(R.id.play_peg_box3_3);
        btn_peg34 =  findViewById(R.id.play_peg_box3_4);

        btn_peg41 =  findViewById(R.id.play_peg_box4_1);
        btn_peg42 =  findViewById(R.id.play_peg_box4_2);
        btn_peg43 =  findViewById(R.id.play_peg_box4_3);
        btn_peg44 =  findViewById(R.id.play_peg_box4_4);

        btn_peg51 =  findViewById(R.id.play_peg_box5_1);
        btn_peg52 =  findViewById(R.id.play_peg_box5_2);
        btn_peg53 =  findViewById(R.id.play_peg_box5_3);
        btn_peg54 =  findViewById(R.id.play_peg_box5_4);

        btn_peg61 =  findViewById(R.id.play_peg_box6_1);
        btn_peg62 =  findViewById(R.id.play_peg_box6_2);
        btn_peg63 =  findViewById(R.id.play_peg_box6_3);
        btn_peg64 =  findViewById(R.id.play_peg_box6_4);

        btn_peg71 =  findViewById(R.id.play_peg_box7_1);
        btn_peg72 =  findViewById(R.id.play_peg_box7_2);
        btn_peg73 =  findViewById(R.id.play_peg_box7_3);
        btn_peg74 =  findViewById(R.id.play_peg_box7_4);

        btn_peg81 =  findViewById(R.id.play_peg_box8_1);
        btn_peg82 =  findViewById(R.id.play_peg_box8_2);
        btn_peg83 =  findViewById(R.id.play_peg_box8_3);
        btn_peg84 =  findViewById(R.id.play_peg_box8_4);

        for (Button[] pegbutton : pegbuttons) {
            for (Button button : pegbutton) {
                button.setOnClickListener(onClickListener_ppeg);
            }
        }

    }



    @SuppressLint("NonConstantResourceId")
    private final View.OnClickListener onClickListener_base = v -> {
        if (active_row != 9) {
            switch (v.getId()) {
                case R.id.play_peg_base_1:
                    placePeg(basepegs[0], active_row);
                    break;
                case R.id.play_peg_base_2:
                    placePeg(basepegs[1], active_row);
                    break;
                case R.id.play_peg_base_3:
                    placePeg(basepegs[2], active_row);
                    break;
                case R.id.play_peg_base_4:
                    placePeg(basepegs[3], active_row);
                    break;
                case R.id.play_peg_base_5:
                    placePeg(basepegs[4], active_row);
                    break;
                case R.id.play_peg_base_6:
                    placePeg(basepegs[5], active_row);
                    break;
            }

        }
    };


    private void placePeg(Peg peg, int row) {
        for (int i = 0; i < pegs[row].length; i++){
            if (!pegs[row][i].isActive()){
                pegs[row][i] = new Peg(peg.getpColor(), true);
            }
        }
        checkevalRow(row);
    }

    private void checkevalRow(int row){
        //check if row is full
        for (int i = 0; i < pegs[row].length; i++){
            if (!pegs[row][i].isActive()){
                return;
            }
        }
        int[] bw = gameLogic.evaluateBW(pegs[row]);

        drawEval(bw, row);
        if (bw[0] == pegs[row].length){
            gameWon();
        }
        //set all pegs from full row to not active
        for (int i = 0; i < pegs[row].length; i++){
            pegs[row][i].setActive(false);
        }
        active_row++;
        SettingsSaveStateService.saveGame(pegsToString(), gamestate.isRepeatable()?"true":"false");
        if (active_row == pegs.length){
            gameLost();
        }
    }

    private void gameWon() {
        showSolution();
    }

    private void gameLost() {
        showSolution();
    }

    private void showSolution() {
        for(int i = 0; i<pegs[8].length; i++){
            solution_view[i].setImageDrawable(getResources().getDrawable(Mastermind.pPegs[pegs[8][i].getpColor()], getTheme()));
        }
    }

    private String pegsToString(){
        StringBuilder savestring = new StringBuilder();
        for (Peg[] peg : pegs) {
            for (Peg value : peg) {
                savestring.append(value.getpColor());
            }
            savestring.append(" ");
        }
        return savestring.toString().trim();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void drawEval(int[] bw, int row) {
        int counter = 0;
        for (int i = 0; i < bw[0]; i++){
            evals[row][i].setImageDrawable(getResources().getDrawable(Mastermind.sPegs[0]));
            counter++;
        }
        for (int i = 0; i < bw[1]; i++){
            evals[row][counter+i].setImageDrawable(getResources().getDrawable(Mastermind.sPegs[1]));
        }
    }

    private View.OnClickListener onClickListener_ppeg = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int row = -1;
            int column = -1;
            for (int i = 0; i<pegs.length; i++){
                for (int j = 0; j<pegs[i].length;j++){
                    if (pegbuttons[i][j].getId() == id){
                        row = i;
                        column = j;
                    }
                }
            }
            if(active_row == column && pegs[row][column].isActive()){
                removePeg(row, column);
            }

               }
    };

    private void removePeg(int row, int column) {
        pegs[row][column].setActive(false);
        setPeg_element(row, column, 6);
    }


    private void setPeg_element(int row ,int column, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pegbuttons[row][column].setForeground(getResources().getDrawable(Mastermind.pPegs[color], getTheme()));
        }

    }
}
