using System;
using System.Collections;

class Graph{
   Dictionary<string, ArrayList> adj;
   Dictionary<string, bool> visited;

   public Graph(){
    adj = new Dictionary<string, ArrayList>();
    visited = new Dictionary<string, bool>();
   }

   public void addName(string name){
    adj[name] = new ArrayList();
    visited[name] = false;
   }

   public void addFriendship(string name1, string name2){
    adj[name1].Add(name2);
    adj[name2].Add(name1);
   }

   public string findFriendshipDegree(string name1, string name2){
        if(adj.ContainsKey(name1) && adj.ContainsKey(name2)){
            if(name1.Equals(name2)){
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

   Dictionary<string,string> bfs(string name1){

    Queue<string> q = new Queue<string>();
    q.Enqueue(name1);
    visited[name1] = true;
    Dictionary<string, string> prev = new Dictionary<string, string>();

    string currentName;
    ArrayList friendsList;

    while(q.Count !=0){
        currentName = q.Dequeue();
        if(adj.TryGetValue(currentName, out ArrayList value)){
            friendsList = adj[currentName];
            foreach (string friend in friendsList){
            if(!visited[friend]){
                q.Enqueue(friend);
                visited[friend] = true;
                prev[friend] = currentName;
                }
            }  
        }

        else{
            break;
        }

    }

    visited = visited.ToDictionary(p => p.Key, p => false);
    return prev;

   }

   string findPath(string name1, string name2, Dictionary<string, string> map){

    if(map == null){
        return "NONE";
    }

    ArrayList path = new ArrayList();

    if(!map.TryGetValue(name2, out string v)){
        return "NONE";
    }
    string currentName = map[name2];

    while(true){
        if(currentName == null){
            break;
        }

        path.Add(currentName);
        if(!map.TryGetValue(currentName, out string value)){
            break;
        }
        currentName = map[currentName];
    }

    if(path.Count <= 0){
        return "NONE";
    }

    if(path[path.Count - 1].Equals(name1)){
        return path.Count.ToString();
    }

    return "ERROR";
   }


}