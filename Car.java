public class Car { //application
    private int speed; //current frequency
    private int maximum_racing_distance; // WCEP, worst case execution path
    private int minimum_racing_distance; // BCEP, best-case execution path
    private int maximum_timing_constraint; // Application deadline
    private int remainingDistance; //RWCEC, remaining worst-case execution cycles
    
    //construtors
    public Car(int speed, int maximum_racing_distance, int minimum_racing_distance, 
            int maximum_timing_constraint){ //construtor mainCar
        this.speed = speed;
        this.maximum_racing_distance = maximum_racing_distance;
        this.minimum_racing_distance = minimum_racing_distance;
        this.maximum_timing_constraint = maximum_timing_constraint;
        this.remainingDistance = maximum_racing_distance;
    }

    public Car(int speed, int maximum_racing_distance, int minimum_racing_distance, 
            int maximum_timing_constraint, int position){ //construtor Car
        this.speed = speed;
        this.maximum_racing_distance = maximum_racing_distance;
        this.minimum_racing_distance = minimum_racing_distance;
        this.maximum_timing_constraint = maximum_timing_constraint;
        this.remainingDistance = Math.abs(position - maximum_racing_distance);
    }

    //getters and settlers
    public int getRemainingDistance() {
        return remainingDistance;
    }
    public void setRemainingDistance(int remainingDistance) {
        this.remainingDistance = remainingDistance;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getMaximum_racing_distance() {
        return maximum_racing_distance;
    }
    public void setMaximum_racing_distance(int maximum_racing_distance) {
        this.maximum_racing_distance = maximum_racing_distance;
    }
    public int getMinimum_racing_distance() {
        return minimum_racing_distance;
    }
    public void setMinimum_racing_distance(int minimum_racing_distance) {
        this.minimum_racing_distance = minimum_racing_distance;
    }
    public int getMaximum_timing_constraint() {
        return maximum_timing_constraint;
    }
    public void setMaximum_timing_constraint(int maximum_timing_constraint) {
        this.maximum_timing_constraint = maximum_timing_constraint;
    }

    //methods
    public int initialPosition(){ //
        int currentPosition = this.maximum_racing_distance - (this.speed * this.maximum_timing_constraint); //Si = Smax−(Vi∗Tmax)
        this.remainingDistance = this.maximum_racing_distance - currentPosition; //
        return this.remainingDistance;
    }

    public int getPosition(){ // return the current distance of the car
        return maximum_racing_distance - remainingDistance;
    }

    @Override
    public String toString() {
        return "Car [maximum_racing_distance=" + maximum_racing_distance + ", maximum_timing_constraint="
                + maximum_timing_constraint + ", minimum_racing_distance=" + minimum_racing_distance
                + ", remainingDistance=" + remainingDistance + ", speed=" + speed + "]\n";
    }

}
