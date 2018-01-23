//This class will manage all the things a player can do on their turn, as well as the player data
import java.util.Scanner;

public class Player extends Board
{
    String player;
    int[] hand;
    int[] stock;
    int[][] disPile;

    public Player(String pNum)
    {
        player = pNum;
        hand = genHand();
        stock = genStock();
        disPile = genDisPile();
    } //Player

    public void doTurn (Board board)
    {
        String line;
        char entry1;
        Scanner keyboard = new Scanner(System.in);
        boolean flag = true;

        hand = genHand(hand);

        do {
            stock = sortStock(stock);

            for (int i = 0; i < 10; i ++)
            {
                System.out.print(stock[i] + " ");
            } //for

            System.out.println("");

            if (countCards(hand) == 0) {hand = genHand();} //if
            Graphics.displayBoard(this, board);

            System.out.print("Enter move here: ");
            line = keyboard.nextLine();
            entry1 = line.charAt(0);

            if (String.valueOf(entry1).equals("p"))
            {
                play(line, board);
                board.cleanBPiles();
            } //if

            else if (String.valueOf(entry1).equals("d"))
            {
                discard(line, board);
                flag = false;
            } //else

            else
            {
                System.out.println("Sorry, your entry is invalid. Please use items from this list:");
                System.out.println("Letter  Word");
                System.out.println("p       play");
                System.out.println("d       discard");
                System.out.println("s       stock");
                System.out.println("h       hand");
                System.out.println("dp      discard pile");
            } //else

            System.out.println("");
        } while(flag);
    } //doTurn

    public void cDoTurn (Board board)
    {
        hand = genHand(hand);
        boolean flag = true, played;

        do {
            stock = sortStock(stock);
            Graphics.displayBoard(this, board);
            played = false;

            for (int i = 0; i < 4; i ++)
            {
                if ((stock[0] == (board.bPiles[0][i] + 1)) || stock[0] == 13)
                {
                    board.bPiles[0][i] ++;
                    board.cleanBPiles();
                    stock[0] = 0;
                    played = true;
                    break;
                } //if
            } //for

            if (!played)
            {
                for (int i = 0; i < 5; i ++)
                {
                    for (int n = 0; n < 4; n ++)
                    {
                        if ((hand[i] == (bPiles[0][n] + 1)) || hand[i] == 13)
                        {
                            board.bPiles[0][n] ++;
                            board.cleanBPiles();
                            hand[i] = 0;
                            played = true;
                            break;
                        } //if
                    } //for

                    if (played)
                    {
                        break;
                    } //if
                } // for
            } //if

            if (!played)
            {
                for (int i = 0; i < 4; i ++)
                {
                    for (int n = 0; n < 4; n ++)
                    {
                        if ((disPile[0][i] == (board.bPiles[0][n] + 1)) || disPile[0][i] == 13)
                        {
                            board.bPiles[0][n] ++;
                            board.cleanBPiles();
                            disPile[0][i] = 0;
                            disPile = sortDisPile(disPile);
                            played = true;
                            break;
                        } // if
                    } //for

                    if (played)
                    {
                        break;
                    } //if
                } //for
            } //if

            if (!played)
            {
                disPile = addDisPile(disPile, hand[1], 0);
                hand[1] = 0;
                flag = false;
            } //if

            System.out.println("");
        } while(flag);
    } //cDoTurn

    public void play(String line, Board board)
    {
        String loc = String.valueOf(line.charAt(2));
        int num1, num2, inHandLoc = 0;
        boolean hasCard = true;

        if (loc.equals("d"))
        {
            num1 = readNum1(line) - 1;
            num2 = readNum2(line) - 1;

            if (num1 > 3)
            {
                hasCard = false;
                System.out.println("Sorry, that is not a valid pile number.");
            } //if

            else if ((disPile[0][(num1)] != (board.bPiles[0][num2] + 1)) && disPile[0][num1] != 13)
            {
                hasCard = false;
                System.out.println("Sorry, that card is invalid.");
            } //if
        } //if

        else
        {
            num1 = readNum1(line);
            num2 = readNum2(line) - 1;


            if (loc.equals("s"))
            {
                if (stock[0] != num1)
                {
                    hasCard = false;
                    System.out.println("Sorry, you don't have that card on your stock pile.");
                } //if
            } //if

            else if (loc.equals("h"))
            {
                boolean inHand = false;

                for (int i = 0; i < 5; i ++)
                {
                    if (hand[i] == num1)
                    {
                        inHand = true;
                        inHandLoc = i;
                        break;
                    } //if
                } //for

                if (!inHand)
                {
                    hasCard = false;
                    System.out.println("Sorry, you don't have that card in your hand.");
                } //if
            } //else if
        } //else

        if (hasCard)
        {
            if (loc.equals("d") && (((board.bPiles[0][num2] + 1) == disPile[0][num1]) || disPile[0][num1] == 13) && (num2 < 4))
            {
                board.bPiles[0][num2] ++;

                disPile[0][num1] = 0;
                disPile = sortDisPile(disPile);
            }

            else if ((((board.bPiles[0][num2] + 1) == num1) || num1 == 13) && (num2 < 4))
            {
                board.bPiles[0][num2]++;

                if (loc.equals("s"))
                {
                    stock[0] = 0;
                    stock = sortStock(stock);
                } //else if

                else if (loc.equals("h"))
                {
                    hand[inHandLoc] = 0;
                } //else if
            } //if

            else
            {
                System.out.println("Sorry, that move is invalid.");
            } //else
        } //if
    } //play

    public void discard(String line, Board board)
    {
        int num1 = readNum1(line);
        int num2 = readNum2(line) - 1;

        boolean inHand = false;
        int inHandLoc = 0;

        for (int i = 0; i < 5; i ++)
        {
            if (hand[i] == num1)
            {
                inHand = true;
                inHandLoc = i;
                break;
            } //if
        } //for

        if (!inHand)
        {
            System.out.println("Sorry, you don't have that card in your hand.");
        } //if

        else
        {
            hand[inHandLoc] = 0;
            disPile = addDisPile(disPile, num1, num2);
        } //else
    }

    public static int readNum1 (String line)
    {
        String numbers = "0123456789";
        int num1;

        if (String.valueOf(line.charAt(2)).equals("d"))
        {
            num1 = Character.getNumericValue(line.charAt(5));

            if (numbers.contains(String.valueOf(line.charAt(6))))
            {
                num1 = Character.getNumericValue(((line.charAt(5)) + (line.charAt(6))));
            } //if
        } //if

        else
        {
            num1 = Character.getNumericValue(line.charAt(4));

            if (numbers.contains(String.valueOf(line.charAt(5))))
            {
                num1 = Character.getNumericValue(((line.charAt(4)) + (line.charAt(5))));
            } //if
        } //else

        return num1;
    } //readNum1

    public static int readNum2 (String line)
    {
        String numbers = "0123456789";
        int num2;

        if (String.valueOf(line.charAt(2)).equals("d"))
        {
            if (line.length() > 14)
            {
                num2 = Character.getNumericValue(line.charAt(14));
            } //if

            else
            {
                num2 = Character.getNumericValue(line.charAt(13));
            } //else
        } //if

        else
        {
            if (line.length() > 13)
            {
                num2 = Character.getNumericValue(line.charAt(13));
            } //if

            else
            {
                num2 = Character.getNumericValue(line.charAt(12));
            } //else
        } //else

        return num2;
    } //readNum2

    public static int countCards (int[] hand)
    {
        int count = 0;

        for (int i = 0; i < 5; i ++)
        {
            if (hand[i] != 0){count ++;} //if
        } //for

        return count;
    } //countCard
} //Player
