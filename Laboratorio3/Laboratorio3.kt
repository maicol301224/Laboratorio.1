import kotlin.system.measureTimeMillis

fun main() {
    do {
        println("\nElige una opción del menú:")
        println("1. Ordenar números con Bubble Sort")
        println("2. Ordenar números con Quick Sort")
        println("3. Calcular el factorial de un número")
        println("4. Resolver las Torres de Hanói")
        println("5. Salir")

        val opcion = readLine()?.toIntOrNull()
        when (opcion) {
            1 -> ejecutarBubbleSort()
            2 -> ejecutarQuickSort()
            3 -> ejecutarFactorial()
            4 -> ejecutarTorresDeHanoi()
            5 -> println("Gracias por usar el programa. Adiós!")
            else -> println("Opción no válida. Inténtalo de nuevo.")
        }
    } while (opcion != 5)
}

fun ejecutarBubbleSort() {
    println("Introduce una lista de números separados por comas (ej: 7,2,5,3):")
    val lista = leerListaDeNumeros()
    if (lista.isEmpty()) {
        println("Lista vacía o inválida.")
        return
    }
    
    val tiempo = measureTimeMillis {
        val resultado = bubbleSort(lista.toMutableList())
        println("Lista ordenada (Bubble Sort): $resultado")
    }
    println("Tiempo de ejecución: ${tiempo}ms")
}

fun bubbleSort(numeros: MutableList<Int>): List<Int> {
    for (i in numeros.indices) {
        for (j in 0 until numeros.size - 1 - i) {
            if (numeros[j] > numeros[j + 1]) {
                numeros[j] = numeros[j + 1].also { numeros[j + 1] = numeros[j] }
            }
        }
    }
    return numeros
}

fun ejecutarQuickSort() {
    println("Introduce una lista de números separados por comas (ej: 10,3,8,2):")
    val lista = leerListaDeNumeros()
    if (lista.isEmpty()) {
        println("Lista vacía o inválida.")
        return
    }

    val tiempo = measureTimeMillis {
        val resultado = quickSort(lista)
        println("Lista ordenada (Quick Sort): $resultado")
    }
    println("Tiempo de ejecución: ${tiempo}ms")
}

fun quickSort(numeros: List<Int>): List<Int> {
    if (numeros.size < 2) return numeros
    val pivote = numeros[numeros.size / 2]
    val menores = numeros.filter { it < pivote }
    val iguales = numeros.filter { it == pivote }
    val mayores = numeros.filter { it > pivote }
    return quickSort(menores) + iguales + quickSort(mayores)
}

fun ejecutarFactorial() {
    println("Introduce un número entero positivo:")
    val numero = readLine()?.toIntOrNull()
    if (numero == null || numero < 0) {
        println("Número inválido. Inténtalo de nuevo.")
        return
    }

    val resultado = calcularFactorial(numero)
    println("El factorial de $numero es $resultado.")
}

fun calcularFactorial(n: Int): Long {
    return (1..n).fold(1L) { acc, i -> acc * i }
}

fun ejecutarTorresDeHanoi() {
    println("Introduce el número de discos para las Torres de Hanói:")
    val discos = readLine()?.toIntOrNull()
    if (discos == null || discos <= 0) {
        println("Número inválido. Inténtalo de nuevo.")
        return
    }

    println("Resolviendo Torres de Hanói para $discos discos:")
    resolverTorresDeHanoi(discos, "A", "C", "B")
}

fun resolverTorresDeHanoi(n: Int, origen: String, destino: String, auxiliar: String) {
    if (n == 1) {
        println("Mover disco 1 de $origen a $destino")
    } else {
        resolverTorresDeHanoi(n - 1, origen, auxiliar, destino)
        println("Mover disco $n de $origen a $destino")
        resolverTorresDeHanoi(n - 1, auxiliar, destino, origen)
    }
}

fun leerListaDeNumeros(): List<Int> {
    return readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: emptyList()
}
