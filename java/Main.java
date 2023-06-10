import java.util.Collections;
import java.util.ArrayList;
public class Main 
{
    public static void main(String[] args)
    {
        int k= Integer.parseInt(args[0]);
        int shuffle = Integer.parseInt(args[1]);

        Cube initialCube = new Cube(); 
        initialCube.shuffle(shuffle);

        System.out.println("\nCube to solve:");
        initialCube.print();

        CubeSolver solver = new CubeSolver();

        Cube terminalCube = solver.Astar_ClosedSet(initialCube, k);

        if(terminalCube == null)
        {
            System.out.println("Could not find a solution.");
        }
        else
        {
            Cube temp = terminalCube;
            ArrayList<Cube> path = new ArrayList<>();
			path.add(terminalCube);
            while(temp.get_father() != null) 
            {
                path.add(temp.get_father());
                temp = temp.get_father();
            }
            Collections.reverse(path);

            System.out.println("\n\nPath to solved cube (" + Integer.toString(path.size()-1) + " rotations):");
            for(Cube item: path)
            {
                item.print();
            }
            System.out.println();
        }
    }
}
