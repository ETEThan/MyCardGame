//This class will manage the player turns and running the core game

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class TheGame extends Graphics
{
    public static void main (String[] args)
    {
        Player p1 = new Player("One");
        Player p2 = new Player("Two");
        Board board = new Board();
        boolean win = false;
        String pTurn = "One";

        do {
            if (pTurn.equals("One"))
            {
                System.out.println("---------- PLAYER ONE ----------");
                p1.doTurn(board);

                if (board.countStock(p1.stock) == 0)
                {
                    win = true;
                } //if

                else
                {
                    pTurn = "Two";
                } //else

                System.out.println("--------------------------------");
            } //if

            else
            {
                System.out.println("---------- PLAYER TWO ----------");
                p2.cDoTurn(board);

                if (board.countStock(p2.stock) == 0)
                {
                    win = true;
                } //if

                else
                {
                    pTurn = "One";
                } //else
                System.out.println("--------------------------------");
            } //else

            System.out.println("");
        }while(!win);
    } //main
} //TheGame
