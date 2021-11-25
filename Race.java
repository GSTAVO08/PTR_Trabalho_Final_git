import java.util.*;

public class Race {
    private LinkedList<Car> referencesCars; //car list
    private Car mainCar; //application
    private LinkedList<Integer> controlPoints; //list of control points (WCEC, worst-case execution cycles)
    private LinkedList<PitStop> pitStops; //list of Preemption points
    private int overtakingFee; //DVFS overhead
    private int time;


    public Race(LinkedList<Car> referencesCars, Car mainCar, LinkedList<Integer> controlPoints,
            LinkedList<PitStop> pitStops, int overtakingFee) {
        this.referencesCars = referencesCars;
        this.mainCar = mainCar;
        this.controlPoints = controlPoints;
        this.pitStops = pitStops;
        this.overtakingFee = overtakingFee;
        this.time = 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int nextReferencesCars() { //what is the car ahead
        int index = 0;
        while(this.mainCar.getPosition() >= referencesCars.get(index).getPosition() && (index < referencesCars.size()-1)){ //if the main car position is ahead
            index++;
        }
        //System.out.println("Index of the car ahead..: " + index);
        return index;
    }

    public int previousReferencesCars() { //what is the car ahead
        int index = nextReferencesCars();
        System.out.println(index-1);
        return index - 1;
    }

    public int nextControlPoint() { //what is the car ahead
        int index = 0;
        while(this.mainCar.getPosition() > controlPoints.get(index)){ //if the main car position is ahead
            index++;
        }
        return index;
    }

    public int previousControlPoint() { //what is the car ahead
        int index = nextReferencesCars();
        return index - 1;
    }

    public void setReferencesCars(LinkedList<Car> referencesCars) {
        this.referencesCars = referencesCars;
    }

    public Car getMainCar() {
        return mainCar;
    }

    public void setMainCar(Car mainCar) {
        this.mainCar = mainCar;
    }

    public LinkedList<Integer> getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(LinkedList<Integer> controlPoints) {
        this.controlPoints = controlPoints;
    }

    public LinkedList<PitStop> getPitStops() {
        return pitStops;
    }

    public void setPitStops(LinkedList<PitStop> pitStops) {
        this.pitStops = pitStops;
    }

    public int getOvertakingFee() {
        return overtakingFee;
    }

    public void setOvertakingFee(int overtakingFee) {
        this.overtakingFee = overtakingFee;
    }

    public LinkedList<Car> getReferencesCars() {
        return referencesCars;
    }
    
    //methods
    private int nextOvertakeTime(int carPosition, int carSpeed){ //find out when the main car (Cm) will meet the next car
        //System.out.println("Next carPosition: " + carPosition);
        //System.out.println("Next carSpeed: " + carSpeed);
        //System.out.println("nextOvertakeTime: " + (carPosition - mainCar.getPosition()) / (mainCar.getSpeed() - carSpeed));
        return (carPosition - mainCar.getPosition()) / (mainCar.getSpeed() - carSpeed);
    }

    private int whereNextOvertakeCar (int carPosition, int carSpeed){ //find out where the next Car will meet the next car
        if(carPosition < 10000){
            int whereMainCar =  (mainCar.getSpeed() * nextOvertakeTime(carPosition, carSpeed)); //before overtaking fee
            //System.out.println("Car position at overtaking..: " + whereMainCar);
            int whereOvertakeCar = whereMainCar + (carSpeed * overtakingFee); //after overtaking fee
            //System.out.println("Car position after overtaking..: " + whereOvertakeCar);
            int newTimingMeeting = (whereOvertakeCar - whereMainCar) / (mainCar.getSpeed() - carSpeed); //meeting point after overtaking fee
            whereMainCar = whereMainCar + newTimingMeeting*mainCar.getSpeed();
            //System.out.println("Car position after overtaking fee..: " + whereMainCar); 
            this.time = this.time + nextOvertakeTime(carPosition, carSpeed);
            System.out.println("Time..: " + this.time); 
            return whereMainCar;
        }else{
            this.time = this.time +  nextOvertakeTime(10000, mainCar.getSpeed());
            return 10000;
        }
    }

    private void updateCarPositions(){
        for(int i=0; i<referencesCars.size(); i++){
            referencesCars.get(i).setRemainingDistance(referencesCars.get(i).getPosition() + referencesCars.get(i).getSpeed() * this.time);
        }
    }

    private void isRacingLineAvaliable(){
        //TODO
    }

    public void nextCicle(){
        //System.out.println("\nNext cicle...");
        int indexNextCar = nextReferencesCars();
        //int indexPreviousCar = previousReferencesCars();
        int aux = whereNextOvertakeCar(referencesCars.get(indexNextCar).getPosition(), referencesCars.get(indexNextCar).getSpeed());
        //the main car will reach the car in front
        mainCar.setRemainingDistance(mainCar.getMaximum_racing_distance() - aux); //new main car position
        mainCar.setSpeed(referencesCars.get(indexNextCar).getSpeed());
        //System.out.println("Car position..: " + mainCar.getPosition());
        //System.out.println("Car speed..: " + mainCar.getSpeed());
        updateCarPositions();
    }
}
