import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class CubeSolver 
{
    private static HashSet<Cube> closedSet;
    private ArrayList<Cube> frontier;

    CubeSolver()
    {
        this.frontier = new ArrayList<>();
        this.closedSet = new HashSet<>();
    }


    Cube Astar_ClosedSet(Cube initialCube, int k)
    {
        if(initialCube.isFinal(k)) return initialCube;
        this.frontier.add(initialCube);
        while(this.frontier.size() > 0)
        {
            Cube currentCube = this.frontier.remove(0);

            if(currentCube.isFinal(k)) return currentCube;

            if(!closedSetContains(currentCube))
            {
                this.closedSet.add(currentCube);
                this.frontier.addAll(currentCube.getChildren(k));
                Collections.sort(this.frontier);
            }
        }
        return null;
    }

    public static boolean closedSetContains(Cube c)
    {
        for(Cube i:closedSet)
        {
            if(i.equals(c))
            {
                return true;
            }
        }

        return false;
    }
}
