public class Plane implements Vehicle {

    @Override
    public int set_num_of_wheels() {
        return 18; //A normal plane has 18 wheels
    }

    @Override
    public int set_num_of_passengers() {
        return 366; //An Airbus A350 can cover up to 366 passengers
    }

    @Override
    public boolean has_gas() {
        return true; //Plane runs on gasoline gas
    }

}
