import java.util.*;

public class main {
    public static void main(final String[] args){ 
        LinkedList<Car> referencesCars = new LinkedList<Car>();
        LinkedList<Integer> controlPoints = new LinkedList<Integer>();
        LinkedList<PitStop> pitStops = new LinkedList<PitStop>(); 
        final int overtakingFee = 1;

        //initial car dataset
        referencesCars.addFirst(new Car(20, 4000, 5000, 200, 6000));
        referencesCars.addFirst(new Car(40, 8000, 5000, 200, 2000));
        referencesCars.addFirst(new Car(60, 12000, 5000, 200, -2000));
        referencesCars.addFirst(new Car(100, 20000, 5000, 200, -10000));

        int aux = -10000;
        while(aux < 9500){ //control points
            aux = aux + 500;
            controlPoints.addLast(aux);
        }

        pitStops.addFirst(new PitStop(6000, 20));

        //sorting all lists
        Collections.sort(controlPoints);

        //initialzing main car and race
        Car mainCar;
        mainCar = new Car(60, 10000, 5000, 200);
        Race race = new Race(referencesCars, mainCar, controlPoints, pitStops, overtakingFee);
        //sorting all lists
        Collections.sort(controlPoints);
        sortCarsInLinkedList(race.getReferencesCars()); //sorting cars in the rece
        sortPitStopInLinkedList(race.getPitStops()); //sorting cars in the rece

        //main loop
        while(race.getMainCar().getPosition() < race.getMainCar().getMaximum_racing_distance()){
            race.nextCicle();
            //break;
        }
        System.out.println(race.getMainCar().getPosition());
        System.out.println(race.getMainCar().getSpeed());
    }

    public static void sortCarsInLinkedList(LinkedList<Car> referencesCars){ //sort the cars
        Collections.sort(referencesCars, new Comparator<Car>() {
            @Override
            public int compare(Car a, Car b) {
                return a.getPosition() - b.getPosition();
            }
        }); 
    }

    public static void sortPitStopInLinkedList(LinkedList<PitStop> pitStops){ //sort the cars
        Collections.sort(pitStops, new Comparator<PitStop>() {
            @Override
            public int compare(PitStop a, PitStop b) {
                return a.getPosition() - b.getPosition();
            }
        }); 
    }

}
