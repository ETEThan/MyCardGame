public class Graphics
{
    public static void displayBuildPiles(int[][] b)
    {
        System.out.println("Build Piles: " + b[0][0] + " " + b[0][1] + " " + b[0][2] + " " + b[0][3]);
    } // displayBuildPiles

    public static void displayHand(int[] hand)
    {
        System.out.print("Your Cards: ");
        for (int i = 0; i < 5; i ++)
        {
            if (hand[i] != 0)
            {
                System.out.print(hand[i] + " ");
            } //if
        } //for

        System.out.println("");
    } //displayHand

    public static void displayStock(int[] stock)
    {
        System.out.println("Your Stock Pile: " + stock[0]);
    } //displayStock

    public static void displayDisPile(int[][] disPile)
    {
        System.out.println("Your Discard Pile: " + disPile[0][0] + " " + disPile[0][1] + " "  + disPile[0][2] + " "  + disPile[0][3]);
    } //displayDisPile

    public static void displayBoard(Player p, Board board)
    {
        displayBuildPiles(board.bPiles);
        displayStock(p.stock);
        displayDisPile(p.disPile);
        displayHand(p.hand);
    } //displayBoard
} //Graphics
