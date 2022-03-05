package Model.Strategy;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task){
        int i = 0, minim = 10000, indiceMinim = 0;
        for(Server server : servers){
            if(server.getTasks().size() < minim){
                indiceMinim = i;
            }
            i++;
        }
        servers.get(indiceMinim).addTask(task);
    }
}
