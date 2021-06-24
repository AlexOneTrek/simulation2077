package me.anelfer.simulation.actions.spawn;

import me.anelfer.simulation.actions.AbstractAction;
import me.anelfer.simulation.entities.SimulationEntity;
import me.anelfer.simulation.entities.object.EmptyEntity;
import me.anelfer.simulation.entities.сreature.HerbivoreCreature;
import me.anelfer.simulation.map.MapLocation;
import me.anelfer.simulation.map.MapSimulation;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractSpawnAction extends AbstractAction {

    private final MapSimulation map;
    private final int max;
    private final int X;
    private final int Y;

    public AbstractSpawnAction(int max, MapSimulation map) {
        this.max = max;
        this.map = map;
        this.X = map.getX();
        this.Y = map.getY();
    }

    private void spawn() {
        int maxOnMap = (int) (((double) max * (X * Y)) / 100);
        int counter = map.getEntityCount(this.getEntityClass());

        if (this.getEntityClass() == HerbivoreCreature.class) {
            System.out.println(counter);
            System.out.println(maxOnMap);
        }

        while (counter < maxOnMap) {
            seeder();
            counter = map.getEntityCount(this.getEntityClass());
        }
    }

    private void seeder() {
        int randomX = ThreadLocalRandom.current().nextInt(0, X);
        int randomY = ThreadLocalRandom.current().nextInt(0, Y);

        if (map.getSimulationEntity(randomX, randomY) == null || map.getSimulationEntity(randomX, randomY).getType() == EmptyEntity.class) {
            if (!map.isCellEmpty(randomX, randomY)) {
                map.remove(new MapLocation(randomX, randomY));
            }

            map.putEntity(randomX, randomY, createEntity(new MapLocation(randomX, randomY)));
        }
    }

    @Override
    public void perform() {
        spawn();
    }

    public abstract SimulationEntity createEntity(MapLocation location);

    public abstract Class<?> getEntityClass();

}
