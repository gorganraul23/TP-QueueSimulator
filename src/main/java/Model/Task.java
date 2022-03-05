package Model;

public class Task implements Comparable<Task>{

    private int id;
    private int arrivalTime;
    private int processingTime;

    public Task(int id, int arrivalTime, int processingTime){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public int getId(){
        return this.id;
    }
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    public int getProcessingTime(){
        return this.processingTime;
    }

    public void setId(int newId){
        this.id = newId;
    }
    public void setArrivalTime(int newArrivalTime){
        this.arrivalTime = newArrivalTime;
    }
    public void setProcessingTime(int newProcessingTime){
        this.processingTime = newProcessingTime;
    }

    @Override
    public int compareTo(Task o) {

        if (this.arrivalTime < o.arrivalTime) {
            return -1;
        } else if (this.arrivalTime > o.arrivalTime) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "(" + id + "," + arrivalTime + "," + processingTime + ")";
    }
}