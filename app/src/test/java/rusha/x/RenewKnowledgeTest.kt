package rusha.x

import org.junit.Test
import rusha.x.cats.Maykun
import rusha.x.cats.MistressKat
import rusha.x.cats.Nurse
import rusha.x.cats.Sphinx
import rusha.x.cats.UndergroundKitty
import rusha.x.dogs.Chihua
import rusha.x.dogs.DogTrainer
import rusha.x.dogs.Gsd
import rusha.x.dogs.HostessDog
import rusha.x.dogs.Pug

class RenewKnowledgeTest {

    @Test
    fun dogs() {

        // invoke Chihua$new [] -> [alisa]
        val alisa = Chihua()

        // store alisa [alisa] -> []
        // load alisa [] -> [alisa]
        // load false [alisa] -> [alisa, false]
        // invoke Chihua$makeSound [alisa, false] -> []
        alisa.makeSound(loud = false)

        // load alisa [] -> [alisa]
        // invoke Chihua$givePaw [alisa] -> []
        alisa.givePaw()

        // invoke Chihua$new [] -> [gosha]
        // store gosha [gosha] -> []
        val gosha = Chihua()

        // load gosha [] -> [gosha]
        // load 2 [gosha] -> [gosha, 2]
        // invoke Chihua$setAngryLvl [gosha, 2] -> []
        gosha.angryLvl = 2

        // load gosha [] -> [gosha]
        // invoke Chihua$givePaw [gosha] -> []
        gosha.givePaw()

        // load gosha [] -> [gosha]
        // load true [gosha] -> [gosha, true]
        // invoke Chihua$makeSound [gosha, true] -> []
        gosha.makeSound(loud = true)

        // load gosha [] -> [gosha]
        // invoke Chihua$getAngryLvl [gosha] -> [angryLvl]
        // invoke println [angryLvl] -> []
        println(gosha.angryLvl)


        val mark = Pug()
        mark.makeSound(loud = true)
        mark.givePaw()

        var jeck = Gsd()

        fun goWork() {
            jeck = jeck.copy(goodBoy = false)
        }

        goWork()
        jeck.makeSound(loud = true)
        jeck.givePaw()
        jeck.pat()
    }

    @Test
    fun people() {

        val viktor = DogTrainer(sportDogs = listOf(Gsd(), Gsd(), Gsd(), Pug(), Chihua()))
        viktor.train()

        val marta = HostessDog(dogs = listOf(Gsd(), Gsd(), Gsd(), Pug(), Chihua()))
        marta.helloDog()
    }

    @Test
    fun cats() {
        val barbara = MistressKat(cats = listOf(Maykun(), UndergroundKitty()))
        barbara.observeTheRegime()

        val lola = Nurse(cats = listOf(Maykun(), UndergroundKitty()))
        lola.giveFood()

        val kleo = Sphinx()
        kleo.pat()
        kleo.clean()
        kleo.pat()

        val vaska = UndergroundKitty()
        vaska.clean()
        vaska.eat()
        vaska.pat()

        val king = Maykun()
        king.clean()
        king.eat()
        king.pat()
    }
}


//Хозяин может иметь несколько кошек.Утром кормит кошек. Днем он их кормит и гладит, вечером - кормит.
//Когда наступает пятница 13е - он их моет.

//Кормилица может кормить всех кошек, которые у нее есть.

//мейкун
//помыть, тогда он приходит в ярость и поет дориме,
// его можно покормить тогда он успокаивается
// можно погладить если он в этот момент в ярости, то он царапает, если спокоен мурлыкает

//Сфинкс
// Можно погладить. Каждый раз когда его гладят уровень счастья увеличивается на 1(<7)
//Кормить, уровень счастья на 2(<7)
// Купать, уровень счастья на 3( не ниже 0)

//ДВОРОВЫЙ
//Можно покормить ест всегда.
//Можно помыть. Он промолчит,но запомнит
//Погладить. Если его только что покормили, то мурлычет. Если не кормили давно то нейтрален.
// Если помыли то шипит