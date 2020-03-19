public class MainClass {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println("Number of Wheels in a Car: " + car.set_num_of_wheels());
        System.out.println("No of Passengers in a Car: " + car.set_num_of_passengers());
        System.out.println("Car has gas: " + car.has_gas());

        System.out.println();

        Plane plane = new Plane();
        System.out.println("Number of Wheels in a Plane: " + plane.set_num_of_wheels());
        System.out.println("No of Passengers in a Plane: " + plane.set_num_of_passengers());
        System.out.println("Plane has gas: " + plane.has_gas());

    }
}
