package net.bestofcode.MovingPoint.logic;

import net.bestofcode.MovingPoint.SHA1Encrypt;

import java.security.KeyStore.Entry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GameObjectQuery {

    private LinkedHashMap<SHA1Encrypt, GameObject> gameObjectHashMap;

    public GameObjectQuery() {
        this.gameObjectHashMap = new LinkedHashMap<SHA1Encrypt, GameObject>();
    }

    public GameObject loadGameObject(SHA1Encrypt customGameObjectHash) {
        
        return gameObjectHashMap.get(customGameObjectHash);

    }

    public void storeCollectionOfGameObjects(Collection<GameObject> collectionOfGameObjects) {

        for(GameObject gameObjectToStore : collectionOfGameObjects) {
            this.storeGameObject(gameObjectToStore);
        }
    }

    public void storeGameObject(GameObject gameObjectToStore) {

        gameObjectHashMap.put(new SHA1Encrypt("" + gameObjectToStore.hashCode()), gameObjectToStore);

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
        
        if( number == -1 ) {

            printDebugHeader();

            gameObjectHashMap.entrySet().stream().forEach( (entry) -> {
                System.out.println(entry.getKey().getEncryptedInput() + "\t" + entry.getValue());
            });

        } else {

            printDebugHeader();

            Set<Map.Entry<SHA1Encrypt, GameObject>> gameObjectSet = gameObjectHashMap.entrySet();

            ArrayList<Entry> entrySetAsArrayList = new ArrayList<Entry>();

            for(Map.Entry<SHA1Encrypt, GameObject> entry : gameObjectSet) {
                //entrySetAsArrayList.add(entry);
            }
            while(number > 0) {
                System.out.println(gameObjectSet.toArray()[number]);
            }

        }
    }
}