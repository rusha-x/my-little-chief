package rusha.x.cats

class Sphinx : Cat {
    var happyLvl = 3
    override fun pat() {
        if (happyLvl <= 6) {
            happyLvl = happyLvl + 1
        }
        println("mrrr")
    }

    override fun eat() {
        if (happyLvl <= 5) {
            happyLvl = happyLvl + 2
        } else if (happyLvl <= 6) {
            happyLvl = happyLvl + 1
        }
        println(" nya")
    }

    override fun clean() {
        when {
            happyLvl >= 2 -> {
                happyLvl = happyLvl - 3
            }
            happyLvl > 1 -> {
                happyLvl = happyLvl - 2
            }
            happyLvl == 0 -> {
                happyLvl = happyLvl - 1
            }
        }
    }
}