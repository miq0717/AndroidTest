class Plane:Vehicle() {
    override fun set_num_of_wheels(): Int {
        return 18//A normal plane has 18 wheels
    }

    override fun set_num_of_passengers(): Int {
        return 366//An Airbus A350 can cover up to 366 passengers
    }

    override fun has_gas(): Boolean {
        return true//Plane runs on gasoline gas
    }
}