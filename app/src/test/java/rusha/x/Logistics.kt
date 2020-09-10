package rusha.x

class Logistics {

    // 1/1
    fun efficiency(): Int {
        return calcCof()
    }
}

class Account {

    // 1/1
    fun salary(): Int {
        return calcCof()
    }
}

/**
 * Если меняется коэффициент [Logistics.efficiency],
 * но так же не менянтся [Account.salary], то всем пизда
 */
fun calcCof(): Int {
    // 100 loc
    return (123 * 3132 / 878 / 123 + 1231 + 4213)
}