package mastermind

class GameLogic {
    val solution = arrayOfNulls<Peg>(PEGS_PER_ROW)

    constructor(repeate: Boolean) {
        var i = 0
        while (i < PEGS_PER_ROW) {
            var skip = false
            val nextPegNr = (Math.random() * PEG_NR).toInt()
            if (!repeate) {
                for (j in 0 until i) {
                    if (solution[j]!!.getpColor() == nextPegNr) {
                        i--
                        skip = true
                        break
                    }
                }
            }
            if (skip) {
                i++
                continue
            }
            solution[i] = Peg(nextPegNr)
            i++
        }
    }

    constructor(sol: IntArray) {
        for (i in sol.indices) {
            solution[i] = Peg(sol[i])
        }
    }

    fun evaluateBW(pegs: Array<Peg?>): IntArray {
        val blackWhite = intArrayOf(0, 0)
        val used = BooleanArray(pegs.size)
        for (i in pegs.indices) {
            if (solution[i]!!.isActive && solution[i] == pegs[i]) {
                blackWhite[0] += 1
                solution[i]!!.isActive = false
                used[i] = true
            }
        }
        for (i in pegs.indices) {
            if (!used[i]) {
                for (j in pegs.indices) {
                    if (solution[j]!!.isActive && solution[j] == pegs[i]) {
                        blackWhite[1] += 1
                        solution[j]!!.isActive = false
                        used[i] = true
                    }
                }
            }
        }
        for (i in 0 until PEGS_PER_ROW) {
            solution[i]!!.isActive = true
        }
        return blackWhite
    }

    companion object {
        const val PEG_NR = 6
        const val PEGS_PER_ROW = 4
    }
}