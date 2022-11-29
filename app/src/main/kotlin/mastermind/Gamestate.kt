package mastermind

class Gamestate(saveState: String, rep: String) {
    val placedPegs: Array<IntArray>
    val isRepeatable: Boolean

    init {
        val boxStates = saveState.split(" ").toTypedArray()
        placedPegs = arrayOf(intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6), intArrayOf(6, 6, 6, 6))
        if (boxStates.size == 9) {
            for (i in boxStates.indices) {
                for (j in 0 until boxStates[i].length) {
                    placedPegs[i][j] = Character.getNumericValue(boxStates[i][j])
                }
            }
        }
        isRepeatable = "true" == rep
    }
}