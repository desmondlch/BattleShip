import java.util.Scanner;
import java.util.Random;
public class App {

    static Scanner user_input=new Scanner(System.in);
    static Random rand_generator= new Random();

    public static void main(String[] args) throws Exception 
    {
        Player player_1=new Player();
        Player player_pc=new Player();
    
        int user_x,user_y,pc_x,pc_y;

        System.out.println("****Welcome to Battle Ships game****\n\n");
        System.out.println("Right now, the sea is empty\n");

        player_1.print_map();

        //Player define ship location
        System.out.println("\nDeploy your ships:\n");
        for (int i=0;i<5;i++)
        {
            do{
                do{
                    System.out.print("\nEnter X Coordinate for your "+ (i+1) +" ship:");
                    user_x=user_input.nextInt();
                    
                }while(!entry_valid(user_x));

                do{
                    System.out.print("Enter Y Coordinate for your "+ (i+1) +" ship:");
                    user_y=user_input.nextInt();

                }while(!entry_valid(user_y));

            }while(!(player_1.check_deploy_ship(user_x, user_y)));

            player_1.deploy_ship(player_1,user_x, user_y,'@');
        }

        System.out.println("\n");
        player_1.print_map();
        System.out.println("----------------------------------------");


        //Computer Define Ship location
        for (int i=0;i<5;i++)
        {
            do{
                pc_x=rand_generator.nextInt(10);
                pc_y=rand_generator.nextInt(10);

            }while(!(player_pc.check_deploy_ship(pc_x, pc_y)));
            
            player_pc.deploy_ship(player_pc,pc_x, pc_y,'@');
            System.out.print("\n"+(i+1) +". Ship DEPLOYED");
        }

        //Battle
        System.out.println("\nLets Battle");
        System.out.println("----------------------------------------");

        do
        {
            player_attack(player_1,player_pc);
            pc_attack(player_1,player_pc);
            System.out.println("\nStatus: Player left: "+(5-player_1.total_attack)+" | PC left: "+(5-player_pc.total_attack));
        }while((5-player_1.total_attack)!=0 && (5-player_pc.total_attack)!=0);

        //Result
        System.out.print("\n"+"Game Over");
        System.out.print("\n"+"Your ships:"+player_1.get_succ_count()+" | Computer Ships:"+player_pc.get_succ_count());
        System.out.print("\nPlayer Map:\n");
        player_1.print_map();
        System.out.print("\nPC Map:\n");
        player_pc.print_map();

    }

    public static boolean entry_valid(int target_value)
    {
        if(target_value<10 && target_value>=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void player_attack(Player player_obj,Player pc_obj)
    {
        int target_x,target_y;

        System.out.println("\nYOUR TURN");

        //Player
        do{
            System.out.print("Enter X Coordinate ship:");
            target_x=user_input.nextInt();
            
        }while(!entry_valid(target_x));

        do{
            System.out.print("Enter Y Coordinate ship:");
            target_y=user_input.nextInt();

        }while(!entry_valid(target_y));

        if (player_obj.launch_attack(pc_obj,target_x,target_y))
        {
            player_obj.deploy_ship(pc_obj,target_x,target_y,'!');
            pc_obj.increase_total_count();
            System.out.println("Boom! You Sunk the ship!");
        }
        else if(player_obj.launch_attack(player_obj,target_x,target_y))
        {
            player_obj.deploy_ship(player_obj,target_x,target_y,'x');
            player_obj.increase_total_count();
            System.out.println("Oh no, you sunk your own ship :(");
        }
        else
        {
            player_obj.deploy_ship(pc_obj,target_x,target_y,'-');
            System.out.println("Sorry, you missed");
        }
    }
    
    public static void pc_attack(Player player_obj,Player pc_obj)
    {
        int target_x,target_y;

        System.out.print("\nCOMPUTER TURN");
        target_x=rand_generator.nextInt(10);
        target_y=rand_generator.nextInt(10);

        //PC
        if (pc_obj.launch_attack(player_obj,target_x,target_y))
        {
            pc_obj.deploy_ship(player_obj,target_x,target_y,'!');
            player_obj.increase_total_count();
            System.out.println("\nBoom! You Sunk the ship!");
        }
        else if(pc_obj.launch_attack(pc_obj,target_x,target_y))
        {
            pc_obj.deploy_ship(pc_obj,target_x,target_y,'x');
            pc_obj.increase_total_count();
            System.out.println("\nOh no, you sunk your own ship :(");
        }
        else
        {
            pc_obj.deploy_ship(player_obj,target_x,target_y,'-');
            System.out.println("\nSorry, you missed");
        }
    }
    
}
