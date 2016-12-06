package i_introduction._4_Lambdas

import util.JavaCode

internal class JavaCode4 : JavaCode() {
    fun task4(collection: Collection<Int>): Boolean {
        return collection.any { i -> i % 42 == 0 }
    }
}