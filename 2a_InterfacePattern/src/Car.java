public class Car implements Vehicle {

    @Override
    public int set_num_of_wheels() {
        return 4; //Usual cases for most cars
    }

    @Override
    public int set_num_of_passengers() {
        return 4; //If it's a normal 4 seated private cars
    }

    @Override
    public boolean has_gas() {
        return true; //Car runs on Octane gas
    }


}
