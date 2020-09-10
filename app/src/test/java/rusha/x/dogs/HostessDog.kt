package rusha.x.dogs

class HostessDog(
    private val dogs: List<PrettyDog>
) {
    fun helloDog() {
        startHello()
        dogs.forEach(fun(prettyDog: PrettyDog) {
            prettyDog.givePaw()
            prettyDog.pat()
        })
    }

    private fun startHello() {
        println("Hi,pretty dog")
    }
}

interface PrettyDog {
    fun givePaw()
    fun pat()
}
