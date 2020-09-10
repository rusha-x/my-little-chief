package rusha.x.dogs

import rusha.x.dogs.Dog

class Pug : Dog {

    override fun makeSound(loud: Boolean) {
        println(message = if (!loud) "khsss" else "KHSSSandSlaver")
    }

    override fun givePaw() {
        println("givePaw")
    }

    override fun pat() {
        println("Fffffffhh")
    }
}