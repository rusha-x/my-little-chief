package rusha.x

// определяем что питомник для питомцев, являющихся питомцами
class Nursery<TPet : Pet> { //

    // определяем что питомецВКлетке (являющийся необязательным питомцем)
    // изначально значит то же, что и "ничего"
    private var petInCell: TPet? = null

    // определяем что "отдать питомца"
    fun givePet(
        // принимая "питомца", являющегося питомцем
        pet: TPet
    )
    // это
    {
        // сначала напечатать текст (Теперь <имя питомца> у нас)
        println("Теперь ${pet.name} у нас")
        // после запомнить что "питомец в клетке" теперь означает то же, что и "питомец"
        petInCell = pet
    }

    // определяем что действие "получить питомца", результат
    // которого является необязательным питомцем, это
    fun getPet(): TPet? {
        // сначала определяем что "питомец в руках" - это то же, что и "питомец в клетке"
        val petInHands = petInCell
        // потом определяем что "питомец в клетке" - это то же, что и "ничего"
        petInCell = null
        // потом отдаем "питомца в клетке"
        return petInHands
    }
}

// определяем про каждую собаку
class Dog(
    // принимающую имя, являющееся строкой
    name: String
) : Pet( // являющуюся питомцем
    // у которого имя - это то же, что и "имя"
    name = name
) { // и про неё
    // определяем что "издать звук" - это
    fun makeSound() {
        // напечатать "Voof"
        println("Voof")
    }
}

// определяем про каждую кошку
class Cat(
    // принимающую имя, являющееся строкой
    val catName: String
) : Pet( // являющуюся питомцем, при создании
    // предающую имя в качестве имени [питомца]
    name = catName
) { // и про неё
    // определяем что "погладить" - это
    fun pat() {
        // напечатать "Murrr"
        println("Murrr")
    }
}

// определяем про каждого питомца
abstract class Pet(
    // принимает и запоминает "имя", являющееся строкой
    val name: String
)

// запомни, "собачий приют" мы получим когда создадим приют для собак
val dogsNursery = Nursery<Dog>()

// запомни, "кошачий приют" мы получим когда создадим приют для кошек
val catsNursery = Nursery<Cat>()

// запомни, "главное" - это
fun main() {
    // сначала дать собаку
    giveDog()

    // потом получить собаку
    getDog()

    build()
}

// запомни, "дать собаку" - это
fun giveDog() {
    // сначала запомнить что "Василия" мы получим когда создадим собаку,
    // у которой имя - это то же самое, что строка "Василий"
    val vasily = Dog(name = "Василий")

    // потом дать кошачему приюту Василия
    dogsNursery.givePet(vasily)
}



// запомни, "получить собаку" - это
fun getDog() {
    // сначала запомнить, что "собаку" мы получим когда получим у
    val dog = dogsNursery.getPet()

    // потом если "собака" - это не то же, что и "ничего"
    if (dog != null) {
        // издать звук у собаки
        dog.makeSound()
    }
}

// запомни, "дать кошку"
fun giveCat() {
    // 1. запомнить что "мурку" мы получим когда создадим кошку,
    // у которой имя - это то же самое, что строка "Мурка"
    val murka: Cat = Cat(catName = "Мурка")

    // 2. дать питомца мурку кошачему приюту
    catsNursery.givePet(murka)
}

class House<TDoor : House.Door>(
    val mainDoor: TDoor
) {
    abstract class Door

    class NumberDoor(
        val number: Int
    ) : Door()

    class TextDoor(
        val text: String
    ) : Door()
}

fun build() {

    val lenina13 = House<House.NumberDoor>(
        mainDoor = House.NumberDoor(
            number = 13
        )
    )
    printDoorNumber(house = lenina13)

    val tsvilinga16 = House<House.TextDoor>(
        mainDoor = House.TextDoor(
            text = "12"
        )
    )
    printDoorText(house = tsvilinga16)
}

fun printDoorNumber(house: House<House.NumberDoor>) {
    println(house.mainDoor.number)
}

fun printDoorText(house: House<House.TextDoor>) {
    println(house.mainDoor.text)
}