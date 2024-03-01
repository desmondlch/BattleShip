abstract class Map {
    char[][] battle_map=new char[10][10];

    public Map()
    {
        for (int y=0;y<10;y++)
        {
            for (int x=0;x<10;x++)
            {
                this.battle_map[x][y]=' ';
            }
        }
    }

    public void print_map()
    {
        //Header
        System.out.print("  ");
        for (int k=0;k<10;k++)
        {
            System.out.print(k);
        }
        System.out.print("\n");

        //Body
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                if (j==0)
                {
                    System.out.print(i +"|");
                }
                else
                {

                }
                System.out.print(this.battle_map[i][j]);
            }
            System.out.print("|"+i+"\n");
        }

        //Footer
        System.out.print("  ");
        for (int k=0;k<10;k++)
        {
            System.out.print(k);
        }
        System.out.print("\n");
}

    public boolean check_deploy_ship(int x, int y)
    {
        if(this.battle_map[x][y]=='@')
        {
            System.out.println("\nDuplicated Entry");
            return false;
        }
        else
        {
            return true;
        }
    }

    public void deploy_ship(Player target_player,int x, int y,char z)
    {
        target_player.battle_map[x][y]=z;
    }

}
