package rusha.x.cats

class Nurse(
    private val cats: List<GluttonyCat>
) {
    fun giveFood() {
        cats.forEach(fun(gluttonyCat: GluttonyCat) {
            gluttonyCat.eat()
        })
        println("GluttonyCat")
    }
}

interface GluttonyCat {
    fun eat()
}