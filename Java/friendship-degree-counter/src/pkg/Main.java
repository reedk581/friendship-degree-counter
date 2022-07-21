package pkg;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args){

        JSONParser parser = new JSONParser();

        Graph graph = new Graph();

        try{

            Object parsed = parser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) parsed;
            JSONArray array = (JSONArray) jsonObject.get("inputs");

            for (Object obj: array) {
                JSONObject person = (JSONObject) obj;
                String name1 = (String) person.get("name");
                String name2 = (String) person.get("friend");
                graph.addName(name1);
                graph.addName(name2);
                graph.addFriendship(name1, name2);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.print("Enter Name A: ");
            System.out.println();
            String nameA = sc.nextLine();
            if(nameA.equals("q")){
                break;
            }
            System.out.print("Enter Name B: ");
            System.out.println();
            String nameB = sc.nextLine();
            if(nameB.equals("q")){
                break;
            }
            System.out.println(graph.findFriendshipDegree(nameA, nameB));
        }

//        Graph example1 = new Graph();
//
//        example1.addName("Tommy");
//        example1.addName("Jimmy");
//        example1.addFriendship("Tommy", "Jimmy");
//
//        example1.addName("Jimmy");
//        example1.addName("Steve");
//        example1.addFriendship("Jimmy", "Steve");
//
//        example1.addName("Jimmy");
//        example1.addName("Lizz");
//        example1.addFriendship("Jimmy", "Lizz");
//
//        example1.addName("Steve");
//        example1.addName("George");
//        example1.addFriendship("Steve", "George");
//
//        example1.addName("George");
//        example1.addName("El");
//        example1.addFriendship("George", "El");
//
//        example1.addName("Lizz");
//        example1.addName("El");
//        example1.addFriendship("Lizz", "El");
//
//        System.out.println(example1.findFriendshipDegree("Tommy", "El"));
//        System.out.println(example1.findFriendshipDegree("Jimmy", "El"));

    }

}
