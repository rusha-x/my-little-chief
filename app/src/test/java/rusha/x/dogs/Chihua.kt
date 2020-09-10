package rusha.x.dogs

class Chihua : Dog {
    var angryLvl = 0
    override fun givePaw() {
        if (angryLvl == 0) println(message = "GivsPaw")
        else println("kus`")
    }

    // [this, load] -> []
    override fun makeSound(loud: Boolean) {
        when {
            angryLvl == 0 -> println(message = if (!loud) "rrr" else "RRR")
            angryLvl == 1 -> println(message = if (!loud) "grrr" else "GRRR")
            angryLvl == 2 -> println(message = if (!loud) " GAV!" else "killingGav!")
            else -> println(message = " AllahAkbar")
        }
    }

    override fun pat() {
        println("Fawn")
    }
}