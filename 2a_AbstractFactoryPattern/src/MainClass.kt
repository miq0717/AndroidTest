fun main(args: Array<String>) {
    val car = Car()
    val plane = Plane()

    println("Number of Wheels in a Car: " + car.set_num_of_wheels())
    println("No of Passengers in a Car: " + car.set_num_of_passengers())
    println("Car has gas: " + car.has_gas())

    println()

    println("Number of Wheels in a Plane: " + plane.set_num_of_wheels())
    println("No of Passengers in a Plane: " + plane.set_num_of_passengers())
    println("Plane has gas: " + plane.has_gas())
}