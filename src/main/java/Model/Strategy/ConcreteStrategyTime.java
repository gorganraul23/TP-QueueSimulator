package Model.Strategy;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {
        int i = 0, minim = 10000, indiceMinim = 0;
        for(Server server : servers){
            if(server.getWaitingPeriod().intValue() < minim){
                indiceMinim = i;
                minim = server.getWaitingPeriod().intValue();
            }
            i++;
        }
        servers.get(indiceMinim).addTask(task);
    }
}
