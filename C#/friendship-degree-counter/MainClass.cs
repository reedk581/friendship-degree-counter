using Newtonsoft;

class MainClass{
    static void Main(string[] args){

        string jsonFile = File.ReadAllText("input.json");
        Model model = Newtonsoft.Json.JsonConvert.DeserializeObject<Model>(jsonFile);

        Graph graph = new Graph();

        foreach (var item in model.inputs){
            graph.addName(item.name);
            graph.addName(item.friend);
            graph.addFriendship(item.name, item.friend);
        }

        while(true){
            Console.WriteLine("Enter Name A: ");
            string nameA = Console.ReadLine();
            if(nameA.Equals("q")){
                break;
            }
            Console.WriteLine("Enter Name B: ");
            string nameB = Console.ReadLine();
            if(nameB.Equals("q")){
                break;
            }
            Console.WriteLine(graph.findFriendshipDegree(nameA,nameB));
        }


        // Graph example1 = new Graph();

        // example1.addName("Tommy");
        // example1.addName("Jimmy");
        // example1.addFriendship("Tommy", "Jimmy");

        // example1.addName("Jimmy");
        // example1.addName("Steve");
        // example1.addFriendship("Jimmy", "Steve");

        // example1.addName("Jimmy");
        // example1.addName("Lizz");
        // example1.addFriendship("Jimmy", "Lizz");

        // example1.addName("Steve");
        // example1.addName("George");
        // example1.addFriendship("Steve", "George");

        // example1.addName("George");
        // example1.addName("El");
        // example1.addFriendship("George", "El");

        // example1.addName("Lizz");
        // example1.addName("El");
        // example1.addFriendship("Lizz", "El");

        // Console.WriteLine(example1.findFriendshipDegree("Tommy", "El"));
        // Console.WriteLine(example1.findFriendshipDegree("Jimmy", "El"));

    }
}
