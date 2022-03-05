package Controller;

import Exception.NoIntervalException;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    public int simTime = 0;
    public int minProc = 0;
    public int maxProc = 0;
    public int minArrival = 0;
    public int maxArrival = 0;
    public int nrCozi = 0;
    public int nrClienti = 0;

    private View view;

    public Controller(View view) {
        this.view = view;

        view.addStartListener(new StartListener());
        view.addExitListener(new ExitListener());
    }

    public void readDataInput() throws NoIntervalException {

        simTime = Integer.parseInt(view.getSimulationTime());
        minProc = Integer.parseInt(view.getMinProcessingTime());
        maxProc = Integer.parseInt(view.getMaxProcessingTime());
        if (minProc > maxProc)
            throw new NoIntervalException("Wrong interval. Please retry.");
        minArrival = Integer.parseInt(view.getMinArrivalTime());
        maxArrival = Integer.parseInt(view.getMaxArrivalTime());
        if (minArrival > maxArrival)
            throw new NoIntervalException("Wrong interval. Please retry.");
        nrCozi = Integer.parseInt(view.getNumberOfQueues());
        nrClienti = Integer.parseInt(view.getNumberOfClients());
        if (simTime < 0 || minProc <= 0 || maxProc < 0 || minArrival <= 0 || maxArrival < 0 || nrCozi <= 0 || nrClienti <= 0)
            throw new NumberFormatException();
    }

    class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                readDataInput();

                SimulationManager sim = new SimulationManager(nrClienti, nrCozi, simTime, minArrival, maxArrival, minProc, maxProc, view);

                Thread t = new Thread(sim);
                t.start();

            } catch (NumberFormatException nfe) {
                view.showError("Wrong input. Please retry.");
            } catch (NoIntervalException nie) {
                view.showError(nie.getMessage());
            } catch (Exception ex) {
                view.showError("Some error. Please retry.");
            }
        }
    }

    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
