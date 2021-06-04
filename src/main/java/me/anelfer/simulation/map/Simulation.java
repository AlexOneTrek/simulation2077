package me.anelfer.simulation.map;

import me.anelfer.simulation.actions.AbstractAction;
import me.anelfer.simulation.actions.move.PredatorMoveAction;
import me.anelfer.simulation.actions.spawn.creature.HerbivoreSpawnAction;
import me.anelfer.simulation.actions.spawn.creature.PredatorSpawnAction;
import me.anelfer.simulation.actions.spawn.object.GrassSpawnAction;
import me.anelfer.simulation.actions.spawn.object.RockSpawnAction;
import me.anelfer.simulation.actions.spawn.object.TreeSpawnAction;

import java.util.ArrayList;

public class Simulation {

    public final int Y = 25;
    public final int X = 25;
    public final MapSimulation map = new MapSimulation(X, Y);
    private final ArrayList<AbstractAction> actions = new ArrayList<>();
    private int counter = 1;

    public Simulation() {
        actions.add(new RockSpawnAction(10, map));
        actions.add(new GrassSpawnAction(10, map));
        actions.add(new TreeSpawnAction(10, map));

        actions.add(new PredatorSpawnAction(2, map));
        actions.add(new HerbivoreSpawnAction(2, map));

//        actions.add(new PredatorMoveAction(map));
    }

    public void start() {
        for (AbstractAction action : actions) {
            action.perform();
            counter++;
        }
    }

}
