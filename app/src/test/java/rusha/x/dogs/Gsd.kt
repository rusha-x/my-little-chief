package rusha.x.dogs

data class Gsd(
    val isJob: Boolean = true,
    val goodBoy: Boolean = true
) : Dog {

    override fun makeSound(loud: Boolean) {
        if (isJob == true) println(if (!loud) "Gav" else "GAV")
        else println(if (!loud) "mrrrr" else "MRRR")
    }

    override fun givePaw() {
        println("WhoTheGoodBoyThere?Yes.ItsMe")
    }

    override fun pat() {
        if (goodBoy == true) println("Fawn")
        else println("!Fawn")
    }
}