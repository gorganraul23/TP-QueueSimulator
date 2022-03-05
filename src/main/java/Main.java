import Controller.Controller;
import Controller.SimulationManager;
import View.View;

public class Main {

    public static void main(String[] args){

        View view = new View();
        Controller controller = new Controller(view);
        //SimulationManager sim = new SimulationManager();
        view.setVisible(true);
        //System.out.println("Hello world!");
    }
}
