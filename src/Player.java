public class Player extends Map{
    int total_attack;
    
    public Player()
    {
        this.total_attack=0;
    }

    public boolean launch_attack(Player target_player, int x, int y)
    {

        if (target_player.battle_map[x][y]=='@')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void increase_total_count()
    {
        this.total_attack+=1;
    }

    public int get_succ_count()
    {
        return this.total_attack;
    }
}
