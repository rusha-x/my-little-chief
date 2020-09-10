package rusha.x.cats

class Maykun : Cat {
    var inLite: Boolean = true
    override fun clean() {
        inLite = false
        println("hsss")
    }

    override fun eat() {
        inLite = true
        println("nya")
    }

    override fun pat() {
        println(if (!inLite) "czarap" else "mrrr")
    }
}