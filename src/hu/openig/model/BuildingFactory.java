package hu.openig.model;

import hu.openig.core.Pair;

import java.util.HashMap;

public class BuildingFactory {

    private static final HashMap<Pair, Building> buildingMap = new HashMap<>();

    public static Building getBuilding(int id, BuildingType type, String race) {
        Pair pair = Pair.of(type, race);
        Building building = buildingMap.get(pair);

        if(building != null) {
            building = building.copy(id);
        }
        else {
            building = new Building(id, type, race);
            buildingMap.put(pair, building);
        }

        return building;
    }
}
