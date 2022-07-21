package pkg;

import java.util.*;

public class Graph {
    public Graph() {
        adj = new HashMap<>();
        visited = new HashMap<>();
    }

    private Map<String, ArrayList<String>> adj;
    private Map<String, Boolean> visited;

    public void addName(String name){
        adj.putIfAbsent(name, new ArrayList<>());
        visited.putIfAbsent(name, false);
    }

    public void addFriendship(String name1, String name2){
        adj.get(name1).add(name2);
        adj.get(name2).add(name1);
    }

    public String findFriendshipDegree(String name1, String name2){
        if(adj.containsKey(name1) && adj.containsKey(name2)){
            if(name1.equals(name2)){
                return "0";
            }
            else{
                return findPath(name1, name2, bfs(name1));
            }
        }
        else{
            return "NONE";
        }
    }

    // Using a breadth-first search algorithm
    private HashMap bfs(String name1){

        // A queue to keep track of which name to visit next
        // Once a name has been visited it will be removed from the queue
        Queue<String> q = new LinkedList<>();
        // Adding the starting name to the queue
        q.add(name1);
        // Marking the starting name as visited in the boolean array
        visited.put(name1, true);
        // A HashMap to keep track of the parent name of the current name
        // Will use this Hashmap to reconstruct path between the two names
        HashMap<String, String> prev = new HashMap<>();

        String currentName;
        ArrayList<String> friendsList;

        // While the queue is not empty...
        while(!q.isEmpty()){
            // Take the top name off of the queue as we are visiting it now
            currentName = q.remove();
            // Get all the friends of current name
            friendsList = adj.get(currentName);

            // Loop for each unvisited friend in the list
            for (String friend: friendsList) {
                // If this friend is unvisited...
                if(!visited.get(friend)){
                    // Add the name of this friend to the queue
                    q.add(friend);
                    // Set this name to visited
                    visited.put(friend, true);
                    // Keep track of the parent name of the current name
                    prev.put(friend, currentName);
                }
            }
        }
        // Reset all values in boolean array to unvisited
        visited.replaceAll((k,v) -> v = false);
        return prev;
    }

    private String findPath(String name1, String name2, HashMap<String, String> map){
        // ArrayList to reconstruct path between names
        ArrayList<String> path = new ArrayList<>();
        // Start by setting the current name to the second name (name2) to determine if there is a path to the first name (name1)
        String currentName = map.get(name2);

        while(true){
            // This is our end case to break the loop. This means the current name has no parent name, thus being null
            if(currentName == null){
                break;
            }
            // Add the current name to the path
            path.add(currentName);
            // Set the current name to its parent name
            currentName = map.get(currentName);
        }
        // If path is empty, this means there is no friendship path linking name1 and name2
        if(path.isEmpty()){
            return "NONE";
        }
        // This path reconstruction algorithm reconstructs the path backwards.
        // This means the last entry in the path should equal the value of name1 if and only if there is a friendship link between name1 and name2
        // Therefore, size of path is value of the friendship degree.
       if(path.get(path.size() - 1).equals(name1)){
            return String.format("%d", path.size());
        }
        return "ERROR";
    }

}
