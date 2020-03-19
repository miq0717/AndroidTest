class Car : Vehicle() {
    override fun set_num_of_wheels(): Int {
        return 4//Usual cases for most cars
    }

    override fun set_num_of_passengers(): Int {
        return 4//If it's a normal 4 seated private cars
    }

    override fun has_gas(): Boolean {
        return true //Car runs on Octane gas
    }
}