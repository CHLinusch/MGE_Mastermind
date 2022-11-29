package mastermind

import java.util.*

class Peg {
    private val pColor: Int
    var isActive: Boolean

    constructor(pegcolor: Int) {
        pColor = pegcolor
        isActive = true
    }

    constructor(pegcolor: Int, pickable: Boolean) {
        pColor = pegcolor
        isActive = pickable
    }

    fun getpColor(): Int {
        return pColor
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val peg = other as Peg
        return pColor == peg.pColor
    }

    override fun hashCode(): Int {
        return Objects.hash(pColor)
    }
}