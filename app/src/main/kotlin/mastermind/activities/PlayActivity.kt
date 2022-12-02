package mastermind.activities

import androidx.appcompat.app.AppCompatActivity
import mastermind.GameLogic
import mastermind.Gamestate
import mastermind.Peg
import android.os.Bundle
import com.lf.mastermind.R
import android.annotation.SuppressLint
import mastermind.services.SettingsSaveStateService
import mastermind.Colormapping
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import java.lang.StringBuilder

class PlayActivity : AppCompatActivity() {
    private var activeRow = 0
    private var gameLogic: GameLogic? = null
    private var gamestate: Gamestate? = null
    
    private var basepeg0: Button? = null
    private var basepeg1: Button? = null
    private var basepeg2: Button? = null
    private var basepeg3: Button? = null
    private var basepeg4: Button? = null
    private var basepeg5: Button? = null
    
    private var btnPeg11: Button? = null
    private var btnPeg12: Button? = null
    private var btnPeg13: Button? = null
    private var btnPeg14: Button? = null
    
    private var btnPeg21: Button? = null
    private var btnPeg22: Button? = null
    private var btnPeg23: Button? = null
    private var btnPeg24: Button? = null
    
    private var btnPeg31: Button? = null
    private var btnPeg32: Button? = null
    private var btnPeg33: Button? = null
    private var btnPeg34: Button? = null
    
    private var btnPeg41: Button? = null
    private var btnPeg42: Button? = null
    private var btnPeg43: Button? = null
    private var btnPeg44: Button? = null
    
    private var btnPeg51: Button? = null
    private var btnPeg52: Button? = null
    private var btnPeg53: Button? = null
    private var btnPeg54: Button? = null
    
    private var btnPeg61: Button? = null
    private var btnPeg62: Button? = null
    private var btnPeg63: Button? = null
    private var btnPeg64: Button? = null
    
    private var btnPeg71: Button? = null
    private var btnPeg72: Button? = null
    private var btnPeg73: Button? = null
    private var btnPeg74: Button? = null
    
    private var btnPeg81: Button? = null
    private var btnPeg82: Button? = null
    private var btnPeg83: Button? = null
    private var btnPeg84: Button? = null
    
    private var eval11: ImageView? = null
    private var eval12: ImageView? = null
    private var eval13: ImageView? = null
    private var eval14: ImageView? = null
    
    private var eval21: ImageView? = null
    private var eval22: ImageView? = null
    private var eval23: ImageView? = null
    private var eval24: ImageView? = null
    
    private var eval31: ImageView? = null
    private var eval32: ImageView? = null
    private var eval33: ImageView? = null
    private var eval34: ImageView? = null
    
    private var eval41: ImageView? = null
    private var eval42: ImageView? = null
    private var eval43: ImageView? = null
    private var eval44: ImageView? = null
    
    private var eval51: ImageView? = null
    private var eval52: ImageView? = null
    private var eval53: ImageView? = null
    private var eval54: ImageView? = null
    
    private var eval61: ImageView? = null
    private var eval62: ImageView? = null
    private var eval63: ImageView? = null
    private var eval64: ImageView? = null
    
    private var eval71: ImageView? = null
    private var eval72: ImageView? = null
    private var eval73: ImageView? = null
    private var eval74: ImageView? = null
    
    private var eval81: ImageView? = null
    private var eval82: ImageView? = null
    private var eval83: ImageView? = null
    private var eval84: ImageView? = null
    
    private lateinit var evals: Array<Array<ImageView?>>

    private var solp1: ImageView? = null
    private var solp2: ImageView? = null
    private var solp3: ImageView? = null
    private var solp4: ImageView? = null

    private lateinit var solutionView: Array<ImageView?>
    private lateinit var pegbuttons: Array<Array<Button?>>
    private lateinit var basebuttons: Array<Button?>

    private var pegs = Array(9) { arrayOfNulls<Peg>(4) }
    private var basepegs = arrayOfNulls<Peg>(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_8)

        val extras = intent.extras
        val savestateString = extras?.getString("Savestate") ?:""
        val repeatableString = extras?.getString("Repeateable")?:""

        gamestate = Gamestate(savestateString, repeatableString)


        val solution = gamestate!!.placedPegs[8]

        gameLogic = if (solution.contentEquals(intArrayOf(6, 6, 6, 6))) {
            GameLogic(gamestate!!.isRepeatable)
        } else {
            GameLogic(solution)
        }
        activeRow = 0
        val pegsInts: Array<IntArray> = gamestate!!.placedPegs
        for (i in 0 until pegsInts.size - 1) if (pegsInts[i].contentEquals(intArrayOf(6, 6, 6, 6))) {
            activeRow = i
            break
        }

        val restartBtn = findViewById<Button>(R.id.play_restart)
        restartBtn.setOnClickListener { restart() }

        val backBtn = findViewById<Button>(R.id.play_back)
        backBtn.setOnClickListener { finish() }

        basepeg0 = findViewById(R.id.play_peg_base_1)
        basepeg1 = findViewById(R.id.play_peg_base_2)
        basepeg2 = findViewById(R.id.play_peg_base_3)
        basepeg3 = findViewById(R.id.play_peg_base_4)
        basepeg4 = findViewById(R.id.play_peg_base_5)
        basepeg5 = findViewById(R.id.play_peg_base_6)
        basebuttons = arrayOf(basepeg0, basepeg1, basepeg2, basepeg3, basepeg4, basepeg5)
        solp1 = findViewById(R.id.play_peg_sol_1)
        solp2 = findViewById(R.id.play_peg_sol_2)
        solp3 = findViewById(R.id.play_peg_sol_3)
        solp4 = findViewById(R.id.play_peg_sol_4)
        solutionView = arrayOf(solp1, solp2, solp3, solp4)
        for (i in basebuttons.indices) {
            basebuttons[i]!!.setOnClickListener(onclicklistenerBase)
            basepegs[i] = Peg(i, true)
        }
        eval11 = findViewById(R.id.play_peg_eval1_1)
        eval12 = findViewById(R.id.play_peg_eval1_2)
        eval13 = findViewById(R.id.play_peg_eval1_3)
        eval14 = findViewById(R.id.play_peg_eval1_4)
        eval21 = findViewById(R.id.play_peg_eval2_1)
        eval22 = findViewById(R.id.play_peg_eval2_2)
        eval23 = findViewById(R.id.play_peg_eval2_3)
        eval24 = findViewById(R.id.play_peg_eval2_4)
        eval31 = findViewById(R.id.play_peg_eval3_1)
        eval32 = findViewById(R.id.play_peg_eval3_2)
        eval33 = findViewById(R.id.play_peg_eval3_3)
        eval34 = findViewById(R.id.play_peg_eval3_4)
        eval41 = findViewById(R.id.play_peg_eval4_1)
        eval42 = findViewById(R.id.play_peg_eval4_2)
        eval43 = findViewById(R.id.play_peg_eval4_3)
        eval44 = findViewById(R.id.play_peg_eval4_4)
        eval51 = findViewById(R.id.play_peg_eval5_1)
        eval52 = findViewById(R.id.play_peg_eval5_2)
        eval53 = findViewById(R.id.play_peg_eval5_3)
        eval54 = findViewById(R.id.play_peg_eval5_4)
        eval61 = findViewById(R.id.play_peg_eval6_1)
        eval62 = findViewById(R.id.play_peg_eval6_2)
        eval63 = findViewById(R.id.play_peg_eval6_3)
        eval64 = findViewById(R.id.play_peg_eval6_4)
        eval71 = findViewById(R.id.play_peg_eval7_1)
        eval72 = findViewById(R.id.play_peg_eval7_2)
        eval73 = findViewById(R.id.play_peg_eval7_3)
        eval74 = findViewById(R.id.play_peg_eval7_4)
        eval81 = findViewById(R.id.play_peg_eval8_1)
        eval82 = findViewById(R.id.play_peg_eval8_2)
        eval83 = findViewById(R.id.play_peg_eval8_3)
        eval84 = findViewById(R.id.play_peg_eval8_4)
        evals = arrayOf(arrayOf(eval11, eval12, eval13, eval14), arrayOf(eval21, eval22, eval23, eval24), arrayOf(eval31, eval32, eval33, eval34), arrayOf(eval41, eval42, eval43, eval44), arrayOf(eval51, eval52, eval53, eval54), arrayOf(eval61, eval62, eval63, eval64), arrayOf(eval71, eval72, eval73, eval74), arrayOf(eval81, eval82, eval83, eval84))
        btnPeg11 = findViewById(R.id.play_peg_box1_1)
        btnPeg12 = findViewById(R.id.play_peg_box1_2)
        btnPeg13 = findViewById(R.id.play_peg_box1_3)
        btnPeg14 = findViewById(R.id.play_peg_box1_4)
        btnPeg21 = findViewById(R.id.play_peg_box2_1)
        btnPeg22 = findViewById(R.id.play_peg_box2_2)
        btnPeg23 = findViewById(R.id.play_peg_box2_3)
        btnPeg24 = findViewById(R.id.play_peg_box2_4)
        btnPeg31 = findViewById(R.id.play_peg_box3_1)
        btnPeg32 = findViewById(R.id.play_peg_box3_2)
        btnPeg33 = findViewById(R.id.play_peg_box3_3)
        btnPeg34 = findViewById(R.id.play_peg_box3_4)
        btnPeg41 = findViewById(R.id.play_peg_box4_1)
        btnPeg42 = findViewById(R.id.play_peg_box4_2)
        btnPeg43 = findViewById(R.id.play_peg_box4_3)
        btnPeg44 = findViewById(R.id.play_peg_box4_4)
        btnPeg51 = findViewById(R.id.play_peg_box5_1)
        btnPeg52 = findViewById(R.id.play_peg_box5_2)
        btnPeg53 = findViewById(R.id.play_peg_box5_3)
        btnPeg54 = findViewById(R.id.play_peg_box5_4)
        btnPeg61 = findViewById(R.id.play_peg_box6_1)
        btnPeg62 = findViewById(R.id.play_peg_box6_2)
        btnPeg63 = findViewById(R.id.play_peg_box6_3)
        btnPeg64 = findViewById(R.id.play_peg_box6_4)
        btnPeg71 = findViewById(R.id.play_peg_box7_1)
        btnPeg72 = findViewById(R.id.play_peg_box7_2)
        btnPeg73 = findViewById(R.id.play_peg_box7_3)
        btnPeg74 = findViewById(R.id.play_peg_box7_4)
        btnPeg81 = findViewById(R.id.play_peg_box8_1)
        btnPeg82 = findViewById(R.id.play_peg_box8_2)
        btnPeg83 = findViewById(R.id.play_peg_box8_3)
        btnPeg84 = findViewById(R.id.play_peg_box8_4)

        pegbuttons = arrayOf(arrayOf(btnPeg11, btnPeg12, btnPeg13, btnPeg14), arrayOf(btnPeg21, btnPeg22, btnPeg23, btnPeg24), arrayOf(btnPeg31, btnPeg32, btnPeg33, btnPeg34), arrayOf(btnPeg41, btnPeg42, btnPeg43, btnPeg44), arrayOf(btnPeg51, btnPeg52, btnPeg53, btnPeg54), arrayOf(btnPeg61, btnPeg62, btnPeg63, btnPeg64), arrayOf(btnPeg71, btnPeg72, btnPeg73, btnPeg74), arrayOf(btnPeg81, btnPeg82, btnPeg83, btnPeg84))

        for (pegbutton in pegbuttons) {
            for (button in pegbutton) {
                button!!.setOnClickListener(onclicklistenerPpeg)
            }
        }

        //set pegs from gamestate
        for (i in 0 until pegs.size - 1) {
            for (j in pegs[i].indices) {
                val color = pegsInts[i][j]
                if (i == activeRow) {
                    if (color != 6) {
                        pegs[i][j] = Peg(color, true)
                    } else {
                        pegs[i][j] = Peg(color, false)
                    }
                } else {
                    pegs[i][j] = Peg(color, false)
                }
                pegElement(i, j, color)
            }
        }
        pegs[8] = gameLogic!!.solution

        //set the evaluation pins
        for (i in 0 until activeRow) {
            val bw = gameLogic!!.evaluateBW(pegs[i])
            drawEval(bw, i)
        }
    }

    @SuppressLint("NonConstantResourceId")
    private val onclicklistenerBase = View.OnClickListener { v: View ->
        if (activeRow != 9) {
            when (v.id) {
                R.id.play_peg_base_1 -> placePeg(basepegs[0], activeRow)
                R.id.play_peg_base_2 -> placePeg(basepegs[1], activeRow)
                R.id.play_peg_base_3 -> placePeg(basepegs[2], activeRow)
                R.id.play_peg_base_4 -> placePeg(basepegs[3], activeRow)
                R.id.play_peg_base_5 -> placePeg(basepegs[4], activeRow)
                R.id.play_peg_base_6 -> placePeg(basepegs[5], activeRow)
            }
        }
    }

    private fun placePeg(peg: Peg?, row: Int) {
        if ((gamestate!!.isRepeatable || peg!!.isActive) && activeRow < pegs.size - 1) {
            for (i in pegs[row].indices) {
                if (!pegs[row][i]!!.isActive) {
                    pegs[row][i] = Peg(peg!!.getpColor(), true)
                    pegElement(row, i, peg.getpColor())
                    if (!gamestate!!.isRepeatable) {
                        peg.isActive = false
                    }
                    break
                }
            }
            checkevalRow(row)
        }
    }

    private fun checkevalRow(row: Int) {
        //check if row is full
        if (activeRow == pegs.size - 1) {
            return
        }
        for (i in pegs[row].indices) {
            if (!pegs[row][i]!!.isActive) {
                return
            }
        }
        val bw = gameLogic!!.evaluateBW(pegs[row])
        drawEval(bw, row)
        if (bw[0] == pegs[row].size) {
            gameWon()
        }
        //set all pegs from full row to not active
        for (i in pegs[row].indices) {
            pegs[row][i]!!.isActive = false
        }
        activeRow++
        SettingsSaveStateService.saveGame(pegsToString(), if (gamestate!!.isRepeatable) "true" else "false")
        if (activeRow == pegs.size - 1) {
            gameLost()
        }
        for (peg in basepegs) {
            peg!!.isActive = true
        }
    }

    private fun gameWon() {
        endOfGame()
    }

    private fun gameLost() {
        endOfGame()
    }

    private fun endOfGame() {
        for (peg in basepegs) {
            peg!!.isActive = false
        }
        showSolution()
    }

    private fun showSolution() {
        for (i in pegs[8].indices) {
            solutionView[i]!!.setImageDrawable(ResourcesCompat.getDrawable(resources, Colormapping.pPegs[pegs[8][i]!!.getpColor()], theme))
        }
    }

    private fun hideSolution(){
        for (i in pegs[8].indices) {
            solutionView[i]!!.setImageDrawable(ResourcesCompat.getDrawable(resources, Colormapping.pPegs[6], theme))
        }
    }


    private fun pegsToString(): String {
        val savestring = StringBuilder()
        for (peg in pegs) {
            for (value in peg) {
                savestring.append(value!!.getpColor())
            }
            savestring.append(" ")
        }
        return savestring.toString().trim { it <= ' ' }
    }

    private fun drawEval(bw: IntArray, row: Int) {
        var counter = 0
        for (i in 0 until bw[0]) {
            evals[row][i]!!.setImageDrawable(ResourcesCompat.getDrawable(resources, Colormapping.sPegs[0], theme))
            counter++
        }
        for (i in 0 until bw[1]) {
            evals[row][counter + i]!!.setImageDrawable(ResourcesCompat.getDrawable(resources, Colormapping.sPegs[1], theme))
        }
    }

    private val onclicklistenerPpeg = View.OnClickListener { v ->
        val id = v.id
        var row = -1
        var column = -1
        for (i in 0 until pegs.size - 1) {
            for (j in pegs[i].indices) {
                if (pegbuttons[i][j]!!.id == id) {
                    row = i
                    column = j
                }
            }
        }
        if (activeRow == row && pegs[row][column]!!.isActive) {
            removePeg(row, column)
        }
    }

    private fun removePeg(row: Int, column: Int) {
        pegs[row][column]!!.isActive = false
        pegElement(row, column, 6)
        basepegs[pegs[row][column]!!.getpColor()]!!.isActive = true
    }

    private fun pegElement(row: Int, column: Int, color: Int) {
        pegbuttons[row][column]!!.foreground = ResourcesCompat.getDrawable(resources, Colormapping.pPegs[color], theme)
    }

    private fun restart() {
        gamestate = Gamestate("", if (gamestate!!.isRepeatable) "true" else "false")
        gameLogic = GameLogic(gamestate!!.isRepeatable)

        for (peg in basepegs) {
            peg!!.isActive = true
        }

        activeRow = 0
        val pegsInts = gamestate!!.placedPegs
        for (i in 0 until pegsInts.size - 1) {
            if (pegsInts[i].contentEquals(intArrayOf(6, 6, 6, 6))) {
                activeRow = i
                break
            }

        }

        //set pegs from gamestate
        for (i in 0 until pegs.size - 1) {
            for (j in pegs[i].indices) {
                val color = pegsInts[i][j]
                if (i == activeRow) {
                    if (color != 6) {
                        pegs[i][j] = Peg(color, true)
                    } else {
                        pegs[i][j] = Peg(color, false)
                    }
                } else {
                    pegs[i][j] = Peg(color, false)
                }
                pegElement(i, j, color)
            }
        }

        pegs[8] = gameLogic!!.solution

        //reset sol
        hideSolution()

        //reset Eval
        for (evalbox in evals) {
            for (evalpeg in evalbox) {
                evalpeg!!.setImageDrawable(ResourcesCompat.getDrawable(resources, Colormapping.sPegs[2], theme))
            }
        }
    }
}