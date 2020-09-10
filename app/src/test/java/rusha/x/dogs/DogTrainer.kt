package rusha.x.dogs

class DogTrainer(
    private val sportDogs: List<SportDog>
) {
    fun train() {
        startTrain()

        // 1
        fun sportDogMakeSound(sportDog: SportDog) {
            sportDog.makeSound(loud = false)
        }
        sportDogs.forEach(::sportDogMakeSound)

        // 2 опускаем название
        sportDogs.forEach(fun(sportDog: SportDog) {
            sportDog.makeSound(loud = false)
        })

        // 3 опускаем fun
        sportDogs.forEach({ sportDog: SportDog ->
            sportDog.makeSound(loud = false)
        })

        // 4 выносим функцию за скобки потому что она - последний аргумент
        sportDogs.forEach() { sportDog ->
            sportDog.makeSound(loud = false)
        }

        // 5 опускаем скобки потому что функция - единственный аргумент
        sportDogs.forEach { sportDog ->
            sportDog.makeSound(loud = false)
        }

        // 6 опускаем имя аргумента (тогда ему автоматически присвоится имя it)
        //   такое возможно когда у функции 1 аргумент
        sportDogs.forEach {
            it.makeSound(loud = false)
        }
    }

    private fun startTrain() {
        println("Voice!")
    }
}

interface SportDog {
    fun makeSound(loud: Boolean)
}