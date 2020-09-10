package rusha.x.cats

class MistressKat(
    private val cats: List<PrettyKitty>
) {
    private var morning = true
    private var day = false
    private var evening = false

    fun setMorning() {
        morning = true
        day = false
        evening = false
    }

    fun setDay() {
        morning = false
        day = true
        evening = false
    }

    fun setEvening() {
        morning = false
        day = false
        evening = true
    }

    private fun giveFood() {
        startGiveFood()
        cats.forEach(fun(prettyKitty: PrettyKitty) {
            prettyKitty.eat()
        })
    }

    private fun startGiveFood() {
        println("ks ks ks ks ska")
    }

    private fun pat() {
        println("prettyKitty")
    }

    private fun clean() {
        cats.forEach(fun(prettyKitty: PrettyKitty) {
            prettyKitty.clean()
        })
    }

    fun observeTheRegime() {
        when {
            morning -> giveFood()
            day -> {
                giveFood()
                pat()
            }
            evening -> {
                giveFood()
                clean()
            }
        }
    }
}

interface PrettyKitty {
    fun pat()
    fun eat()
    fun clean()
}