public class PitStop {
    int position;
    int time;

    public PitStop(int position, int time) {
        this.position = position;
        this.time = time;
    }
    
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "PitStop [position=" + position + ", time=" + time + "]";
    }
}
