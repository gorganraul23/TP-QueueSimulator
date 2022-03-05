package Model;

import Model.Strategy.ConcreteStrategyQueue;
import Model.Strategy.ConcreteStrategyTime;
import Model.Strategy.SelectionPolicy;
import Model.Strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private int noServers;
    private Strategy strategy;

    public Scheduler(int noServers){
        servers = new ArrayList<>();
        for(int i = 0; i < noServers; i++){
            Server server = new Server();
            servers.add(server);
            Thread t = new Thread(server);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public List<Server> getServers(){
        return servers;
    }
}