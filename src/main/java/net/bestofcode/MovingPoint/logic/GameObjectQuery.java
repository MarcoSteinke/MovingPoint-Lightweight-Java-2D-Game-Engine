package net.bestofcode.MovingPoint.logic;

import java.security.KeyStore.Entry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GameObjectQuery {

    private final LinkedHashMap<SHA1HashGenerator, GameObject> gameObjectHashMap;

    public GameObjectQuery() {
        this.gameObjectHashMap = new LinkedHashMap<SHA1HashGenerator, GameObject>();
    }

    public GameObject loadGameObject(SHA1HashGenerator customGameObjectHash) {

        return gameObjectHashMap.get(customGameObjectHash);

    }

    public List<GameObject> loadAllGameObjects() {

        if(this.gameObjectHashMap.size() > 0) {
            return gameObjectHashMap.entrySet().stream().map((entry) -> (entry.getValue())).collect(Collectors.toList());
        } else {
            return List.of();
        }

    }

    public boolean hasGameObject() {
        return this.gameObjectHashMap.size() > 0;
    }

    public void storeCollectionOfGameObjects(Collection<GameObject> collectionOfGameObjects) {

        for (GameObject gameObjectToStore : collectionOfGameObjects) {
            this.storeGameObject(gameObjectToStore);
        }
    }

    public void storeGameObject(GameObject gameObjectToStore) {

        gameObjectHashMap.put(new SHA1HashGenerator("" + gameObjectToStore.hashCode()), gameObjectToStore);

    }

    private void printDebugHeader() {

        System.out.println("net.bestofcode.MovingPointGameEngine.MovingPointGameEngine: DEBUG INFORMATION for net.bestofcode.MovingPointGameEngine.GameObjectQuery");
        System.out.println("key" + "                                          " + "value");

    }

    private void printDateAndTime() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println(dateTimeFormatter.format(currentDateTime));

    }

    public void debug(int number) {

        if (number == -1) {

            printDebugHeader();

            gameObjectHashMap.entrySet().stream().forEach((entry) -> {
                System.out.println(entry.getKey().getEncryptedInput() + "\t" + entry.getValue());
            });

        } else {

            printDebugHeader();

            Set<Map.Entry<SHA1HashGenerator, GameObject>> gameObjectSet = gameObjectHashMap.entrySet();

            ArrayList<Entry> entrySetAsArrayList = new ArrayList<Entry>();

            for (Map.Entry<SHA1HashGenerator, GameObject> entry : gameObjectSet) {
                //entrySetAsArrayList.add(entry);
            }
            while (number > 0) {
                System.out.println(gameObjectSet.toArray()[number]);
            }

        }
    }
}