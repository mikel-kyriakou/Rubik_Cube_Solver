import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cube implements Comparable<Cube>
{
    private int [][]squares = new int[6][9];
    private int cost;
    private int h_cost;
    private Cube father = null;

    //0==green  1==white  2==red  3==yellow  4==blue  5==orange

    Cube() //constuctor
    {
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<9; j++)
            {
                squares[i][j] = i;
            }
        }

        cost = 0;
    }

    Cube(Cube c) //copy constructor
    {
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<9; j++)
            {
                squares[i][j] = c.squares[i][j];
            }
        }
        this.cost = c.cost;
    }

    void print()
    {
        System.out.println("-----------------------------");

        for(int k=0; k<3; k++)
        {
            for(int i=0; i<3; i++)
            {
                for(int j=0; j<6; j++)
                {
                    for(int z=0; z<3; z++)
                    {
                        if(k==0)
                        {
                            if(j==1)
                            {
                                System.out.print(squares[1][i*3+z]);
                            }
                            else
                            {
                                System.out.print(" ");
                            }
                        }
                        else if(k==1)
                        {
                            switch (j) {
                                case 0:
                                    System.out.print(squares[0][i*3+z]);
                                    break;
                            
                                case 1:
                                    System.out.print(squares[2][i*3+z]);
                                    break;
                                
                                case 2:
                                    System.out.print(squares[4][i*3+z]);
                                    break;

                                case 3:
                                    System.out.print(squares[5][i*3+z]);
                                    break;
                                
                                default:
                                    System.out.print(" ");
                            }

                        }
                        else
                        {
                            if(j==1)
                            {
                                System.out.print(squares[3][i*3+z]);
                            }
                            else
                            {
                                System.out.print(" ");
                            }
                        }

                        System.out.print(' ');
                    }

                    System.out.print("      ");
                    
                }
                System.out.println();
            }

            System.out.println();
        }

        System.out.println("-----------------------------");
    }

    void set_cost(int num)
    {
        cost = num;
    }

    int get_cost()
    {
        return cost;
    }

    void set_h_cost(int num)
    {
        h_cost = num;
    }

    int get_h_cost()
    {
        return h_cost;
    }

    void set_father(Cube c)
    {
        father = c;
    }

    Cube get_father()
    {
        return father;
    }

    void rotate_up()
    {
        cost += 1;
        Cube copy = new Cube(this);

        for(int i=0; i<6; i++)
        {
            for(int j=0; j<3; j++)
            {
                switch (i) {
                    case 0:
                        squares[i][j] = copy.squares[2][j];
                        break;

                    case 2:
                        squares[i][j] = copy.squares[4][j];
                        break;

                    case 4:
                        squares[i][j] = copy.squares[5][j];
                        break;

                    case 5:
                        squares[i][j] = copy.squares[0][j];
                        break;
                
                    default:
                        break;
                }
            }
        }

        squares[1][2] = copy.squares[1][0];
        squares[1][5] = copy.squares[1][1];
        squares[1][8] = copy.squares[1][2];
        squares[1][1] = copy.squares[1][3];
        squares[1][7] = copy.squares[1][5];
        squares[1][0] = copy.squares[1][6];
        squares[1][3] = copy.squares[1][7];
        squares[1][6] = copy.squares[1][8];

    }

    void rotate_up_anti()
    {
        cost -= 2;
        for(int i=0; i<3; i++)
        {
            this.rotate_up();
        }
    }

    void rotate_down()
    {
        cost += 1;
        Cube copy = new Cube(this);

        for(int i=0; i<6; i++)
        {
            for(int j=6; j<9; j++)
            {
                switch (i) {
                    case 0:
                        squares[i][j] = copy.squares[5][j];
                        break;

                    case 2:
                        squares[i][j] = copy.squares[0][j];
                        break;

                    case 4:
                        squares[i][j] = copy.squares[2][j];
                        break;

                    case 5:
                        squares[i][j] = copy.squares[4][j];
                        break;
                
                    default:
                        break;
                }
            }
        }

        squares[3][2] = copy.squares[3][0];
        squares[3][5] = copy.squares[3][1];
        squares[3][8] = copy.squares[3][2];
        squares[3][1] = copy.squares[3][3];
        squares[3][7] = copy.squares[3][5];
        squares[3][0] = copy.squares[3][6];
        squares[3][3] = copy.squares[3][7];
        squares[3][6] = copy.squares[3][8];
    }

    void rotate_down_anti()
    {
        cost -= 2;
        for(int i=0; i<3; i++)
        {
            this.rotate_down();
        }
    }

    void rotate_right()
    {
        cost += 1;
        Cube copy = new Cube(this);

        for(int i=0; i<6; i++)
        {
            for(int j=2; j<9; j+=3)
            {
                switch (i) {
                    case 1:
                        squares[i][j] = copy.squares[2][j];
                        break;

                    case 2:
                        squares[i][j] = copy.squares[3][j];
                        break;

                    // case 3:
                    //     squares[i][j] = copy.squares[5][j];
                    //     break;

                    // case 5:
                    //     squares[i][j] = copy.squares[1][j];
                    //     break;
                
                    default:
                        break;
                }
            }
        }

        squares[5][0] = copy.squares[1][8];
        squares[5][3] = copy.squares[1][5];
        squares[5][6] = copy.squares[1][2];

        squares[3][2] = copy.squares[5][6];
        squares[3][5] = copy.squares[5][3];
        squares[3][8] = copy.squares[5][0];

        squares[4][2] = copy.squares[4][0];
        squares[4][5] = copy.squares[4][1];
        squares[4][8] = copy.squares[4][2];
        squares[4][1] = copy.squares[4][3];
        squares[4][7] = copy.squares[4][5];
        squares[4][0] = copy.squares[4][6];
        squares[4][3] = copy.squares[4][7];
        squares[4][6] = copy.squares[4][8];
    }

    void rotate_right_anti()
    {
        cost -= 2;
        for(int i=0; i<3; i++)
        {
            this.rotate_right();
        }
    }

    void rotate_left()
    {
        cost += 1;
        Cube copy = new Cube(this);

        for(int i=0; i<6; i++)
        {
            for(int j=0; j<9; j+=3)
            {
                switch (i) {
                    // case 1:
                    //     squares[i][j] = copy.squares[5][j];
                    //     break;

                    case 2:
                        squares[i][j] = copy.squares[1][j];
                        break;

                    case 3:
                        squares[i][j] = copy.squares[2][j];
                        break;

                    // case 5:
                    //     squares[i][j] = copy.squares[3][j];
                    //     break;
                
                    default:
                        break;
                }
            }
        }

        squares[5][8] = copy.squares[3][0];
        squares[5][5] = copy.squares[3][3];
        squares[5][2] = copy.squares[3][6];

        squares[1][0] = copy.squares[5][8];
        squares[1][3] = copy.squares[5][5];
        squares[1][6] = copy.squares[5][2];

        squares[0][2] = copy.squares[0][0];
        squares[0][5] = copy.squares[0][1];
        squares[0][8] = copy.squares[0][2];
        squares[0][1] = copy.squares[0][3];
        squares[0][7] = copy.squares[0][5];
        squares[0][0] = copy.squares[0][6];
        squares[0][3] = copy.squares[0][7];
        squares[0][6] = copy.squares[0][8];
    }

    void rotate_left_anti()
    {
        cost -= 2;
        for(int i=0; i<3; i++)
        {
            this.rotate_left();
        }
    }

    void rotate_back()
    {
        cost += 1;
        Cube copy = new Cube(this);

        squares[1][0] = copy.squares[4][2];
        squares[1][1] = copy.squares[4][5];
        squares[1][2] = copy.squares[4][8];
        
        squares[4][2] = copy.squares[3][8];
        squares[4][5] = copy.squares[3][7];
        squares[4][8] = copy.squares[3][6];

        squares[3][6] = copy.squares[0][0];
        squares[3][7] = copy.squares[0][3];
        squares[3][8] = copy.squares[0][6];

        squares[0][0] = copy.squares[1][0];
        squares[0][3] = copy.squares[1][1];
        squares[0][6] = copy.squares[1][2];

        squares[5][2] = copy.squares[5][0];
        squares[5][5] = copy.squares[5][1];
        squares[5][8] = copy.squares[5][2];
        squares[5][1] = copy.squares[5][3];
        squares[5][7] = copy.squares[5][5];
        squares[5][0] = copy.squares[5][6];
        squares[5][3] = copy.squares[5][7];
        squares[5][6] = copy.squares[5][8];

    }

    void rotate_back_anti()
    {
        cost -= 2;
        for(int i=0; i<3; i++)
        {
            this.rotate_back();
        }
    }

    void rotate_front()
    {
        cost += 1;
        Cube copy = new Cube(this);

        squares[0][2] = copy.squares[3][0];
        squares[0][5] = copy.squares[3][1];
        squares[0][8] = copy.squares[3][2];
        
        squares[1][6] = copy.squares[0][8];
        squares[1][7] = copy.squares[0][5];
        squares[1][8] = copy.squares[0][2];

        squares[4][0] = copy.squares[1][6];
        squares[4][3] = copy.squares[1][7];
        squares[4][6] = copy.squares[1][8];

        squares[3][0] = copy.squares[4][6];
        squares[3][1] = copy.squares[4][3];
        squares[3][2] = copy.squares[4][0];

        squares[2][2] = copy.squares[2][0];
        squares[2][5] = copy.squares[2][1];
        squares[2][8] = copy.squares[2][2];
        squares[2][1] = copy.squares[2][3];
        squares[2][7] = copy.squares[2][5];
        squares[2][0] = copy.squares[2][6];
        squares[2][3] = copy.squares[2][7];
        squares[2][6] = copy.squares[2][8];

    }

    void rotate_front_anti()
    {
        cost -= 2;
        for(int i=0; i<3; i++)
        {
            this.rotate_front();
        }
    }

    void rotate_middle_up()
    {
        cost -= 1;
        this.rotate_right_anti();
        this.rotate_left();
    }

    void rotate_middle_down()
    {
        cost -= 1;
        this.rotate_right();
        this.rotate_left_anti();
    }

    void rotate_middle_right()
    {
        cost -= 1;
        this.rotate_up();
        this.rotate_down_anti();
    }

    void rotate_middle_left()
    {
        cost -= 1;
        this.rotate_up_anti();
        this.rotate_down();
    }

    void rotate_middle_clockwise()
    {
        cost -= 1;
        this.rotate_front_anti();
        this.rotate_back();
    }

    void rotate_middle_anti()
    {
        cost -= 1;
        this.rotate_front();
        this.rotate_back_anti();
    }

    void shuffle(int moves) //moves einai o arithmos ton tixeon kiniseon pou tha kanoume gia na anakatepsoume ton kivo. 
    {

        Random rand = new Random();
        int random_number;

        for(int i=0; i<moves; i++)
        {
            //System.out.println(this.countSumRotations());
            random_number = rand.nextInt(18);

            switch (random_number) {
                case 0:
                    this.rotate_back();
                    break;

                case 1:
                    this.rotate_back_anti();
                    break;

                case 2:
                    this.rotate_down();
                    break;

                case 3:
                    this.rotate_down_anti();
                    break;

                case 4:
                    this.rotate_front();
                    break;

                case 5:
                    this.rotate_front_anti();
                    break;

                case 6:
                    this.rotate_left();
                    break;

                case 7:
                    this.rotate_left_anti();
                    break;

                case 8:
                    this.rotate_right();
                    break;

                case 9:
                    this.rotate_right_anti();
                    break;

                case 10:
                    this.rotate_up();
                    break;

                case 11:
                    this.rotate_up_anti();
                    break;

                case 12:
                    this.rotate_middle_up();
                    break;

                case 13:
                    this.rotate_middle_down();
                    break;

                case 14:
                    this.rotate_middle_right();
                    break;

                case 15:
                    this.rotate_middle_left();
                    break;

                case 16:
                    this.rotate_middle_clockwise();
                    break;

                case 17:
                    this.rotate_middle_anti();
                    break;
            
                default:
                    break;
            }

        }

        set_cost(0);

    }

    boolean check_Side(int x)
    {
        for(int j=0; j<9; j++)
        {
            if(this.squares[x][j] != this.squares[x][4])
            {
                return false;
            }
        }

        return true;
    }

    boolean isFinal(int k)
    {
        int counter = 0;
        for(int i=0; i<5; i++)
        {
            if(check_Side(i))
            {
                counter++;
            }
        }

        if(counter>=k)
        {
            return true;
        }

        return false;
    }

    int countRotations(int x, int y)
    {

        if(this.squares[x][y] == this.squares[x][4])
        {
            return 0;
        }

        int count=0;

        if(y!=1 && y!=4 && y!=7)
        {
            count+=1;
        }
        else
        {
            count+=2;
        }

        switch (this.squares[x][y]) {
            case 0:
                if(x==4)
                {
                    count+=1;
                }
                break;

            case 1:
                if(x==3)
                {
                    count+=1;
                }
                break;

            case 2:
                if(x==5)
                {
                    count+=1;
                }
                break;

            case 3:
                if(x==1)
                {
                    count+=1;
                }
                break;

            case 4:
                if(x==0)
                {
                    count+=1;
                }
                break;

            case 5:
                if(x==2)
                {
                    count+=1;
                }
                break;
        
            default:
                break;
            }

        return count;
        
    }

    int countSumRotations()
    {
        int sum=0; 
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<9; j++)
            {
                sum+=this.countRotations(i, j);
                //System.out.println(this.countRotations(i, j));
            }
        }
        //System.out.println(sum/9);
        return sum/9;
    }

    void evaluate(int k)
    {
        h_cost = countByColor(k);
    }

    ArrayList<Cube> getChildren(int k)
    {
        ArrayList<Cube> children = new ArrayList<>();

        Cube child = new Cube(this);

        child.rotate_back();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

 
        child = new Cube(this);

        child.rotate_back_anti();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

 
        child = new Cube(this);

        child.rotate_down();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

 
        child = new Cube(this);

        child.rotate_down_anti();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

 
        child = new Cube(this);

        child.rotate_front();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

 
        child = new Cube(this);

        child.rotate_front_anti();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

 
        child = new Cube(this);

        child.rotate_left();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_left_anti();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_middle_anti();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_middle_clockwise();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

 
        child = new Cube(this);

        child.rotate_middle_down();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_middle_up();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_middle_left();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_middle_right();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_right();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_right_anti();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

        
        child = new Cube(this);

        child.rotate_up();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);


        child = new Cube(this);

        child.rotate_up_anti();
        child.evaluate(k);
        child.set_father(this);
        children.add(child);

        return children;
    }


    @Override
    public int compareTo(Cube c)
    {
        return Double.compare(this.cost + this.h_cost, c.cost + c.h_cost); 
    }

    public boolean equals(Cube c)
    {
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<9; j++)
            {
                if(this.squares[i][j] != c.squares[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] findCombinations(int k)
    {
        int[][] combinationsOf1 = {{0}, {1}, {2}, {3}, {4}, {5}};
        int[][] combinationsOf2 = {{0,1}, {0,2}, {0,3}, {0,4}, {0,5}, {1,2}, {1,3}, {1,4}, {1,5}, {2,3}, {2,4}, {2,5}, {3,4}, {3,5}, {4,5}};
        int[][] combinationsOf3 = {{0,1,2}, {0,1,3}, {0,1,4}, {0,1,5}, {0,2,3}, {0,2,4}, {0,2,5}, {0,3,4}, {0,3,5}, {0,4,5},
                                    {1,2,3}, {1,2,4}, {1,2,5}, {1,3,4}, {1,3,5}, {1,4,5}, {2,3,4}, {2,3,5}, {2,4,5}, {3,4,5}};
        int[][] combinationsOf4 = {{0,1,2,3}, {0,1,2,4}, {0,1,2,5}, {0,1,3,4}, {0,1,3,5}, {0,1,4,5}, {0,2,3,4}, {0,2,3,5}, {0,2,4,5}, {0,3,4,5}, {1,2,3,4}, {1,2,3,5}, {1,2,4,5}, {1,3,4,5},  {2,3,4,5}};

        switch (k) {
            case 1:
                return combinationsOf1;

            case 2:
                return combinationsOf2;

            case 3:
                return combinationsOf3;

            case 4:
                return combinationsOf4;
    
            default:
                return null;
        }
    }
    
    public int countByColor(int k)
    {

        if(k==5 || k==6)
        {
            return countSumRotations();
        }

        int[][] combinations = findCombinations(k);

        int result =0;

        ArrayList<Integer> all_results = new ArrayList<Integer>();

        for(int[] list:combinations)
        {
            for(int color:list)
            {
                for(int i=0; i<6; i++)
                {
                    for(int j=0; j<9; j++)
                    {
                        if(this.squares[i][j] == color)
                        {
                            result += this.countRotations(i, j);
                        }
                    }
                }
                
            }
            all_results.add(result/3);
            result = 0;
        }

        return Collections.min(all_results);

    }
}