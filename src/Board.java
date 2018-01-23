//This class will manage the game board and everything a player may interact with
import sun.nio.cs.ext.MacArabic;

import java.lang.Math;

public class Board
{
    int[][] bPiles = new int[12][4];
    double numCards = 162;
    int[] numbers = {12,12,12,12,12,12,12,12,12,12,12,12,18};


    public Board ()
    {
    }

    public int genCard ()
    {
        /*
        double value = Math.random();
        int card = 0;

        if (value < 0.08){card = 1;} //if
        else if (value < 0.16){card = 2;} //else if
        else if (value < 0.24){card = 3;} //else if
        else if (value < 0.32){card = 4;} //else if
        else if (value < 0.40){card = 5;} //else if
        else if (value < 0.48){card = 6;} //else if
        else if (value < 0.56){card = 7;} //else if
        else if (value < 0.64){card = 8;} //else if
        else if (value < 0.72){card = 9;} //else if
        else if (value < 0.80){card = 10;} //else if
        else if (value < 0.88){card = 11;} //else if
        else if (value < 0.96){card = 12;} //else if
        else if (value < 1){card = 13;} //else if

        return card;
        */

        double chance = 100 / numCards;

        double[] chances = new double[13];
        chances[0] = chance * numbers[0];

        for (int i = 1; i < 13; i ++)
        {
            chances[i] = (chance * numbers[i]) + chances[i - 1];
        } //for

        double value = Math.random() * 100;
        int card = 0;

        for (int i = 0; i < 13; i ++)
        {
            if (value < chances[i]){card = i + 1; break;} //if
            else { card = 13;} //else
        }

        numCards -= 1;
        return card;
    } //genCard

    public  int[] genHand ()
    {
        int[] cards = new int[5];

        for (int i = 0; i < 5; i ++)
        {
            cards[i] = genCard();
        } //for

        return cards;
    } //genHand

    public  int[] genHand (int[] cards)
    {
        for (int i = 0; i < 5; i ++)
        {
            if (cards[i] == 0)
            {
                cards[i] = genCard();
            } //if
        } //for

        return cards;
    } //genHand

    public int[] genStock ()
    {
        int[] stock = new int[10];

        for (int i = 0; i < 10; i ++)
        {
            stock[i] = genCard();
        } //for

        return stock;
    } //genStock

    public int[] sortStock (int[] stock)
    {
        boolean swapFlag;

        do {
            swapFlag = false;

            for (int i = 0; i < 9; i ++)
            {
                if (stock[i] == 0)
                {
                    stock[i] = stock[(i + 1)];
                    stock[i + 1] = 0;
                    if (stock[i] == 0)
                    {
                        swapFlag = false;
                    } //if

                    else
                    {
                        swapFlag = true;
                    } //else
                }// if
            } //for
        } while (swapFlag);

        return stock;
    } //sortStock

    public int countStock (int[] stock)
    {
        int count = 0;

        for (int i = 0; i < 10; i ++)
        {
            if (stock[i] != 0)
            {
                count ++;
            } //if
        } //if

        return count;
    } //countStock
    public int[][] genDisPile ()
    {
        int[][] disPile = new int[100][4];

        for (int i = 0; i < 4; i ++)
        {
            for (int n = 0; n < 100; n ++)
            {
                disPile[n][i] = 0;
            } //for
        } //for

        return disPile;
    } //genDisPile

    public int[][] sortDisPile(int[][] disPile)
    {
        boolean swapFlag;

        do {
            swapFlag = false;

            for (int i = 0; i < 4; i ++)
            {
                for (int n = 0; n < 99; n++)
                {
                    if (disPile[n][i] == 0)
                    {
                        disPile[n][i] = disPile[(n + 1)][i];
                        if (disPile[n][i] == 0)
                        {
                            swapFlag = false;
                        } //if

                        else
                        {
                            swapFlag = true;
                        } //else
                    }// if
                } // for
            } //for
        } while (swapFlag);

        return disPile;
    } //sortDisPile

    public int[][] addDisPile(int[][] disPile, int num1, int num2)
    {
        for (int n = 0; n < 99; n++)
        {
            disPile[(n + 1)][num2] = disPile[n][num2];
            if ((disPile[n][num2] == 0) && (n > 0))
            {
                break;
            } //if
        } // for

        disPile[0][num2] = num1;

        return disPile;
    } //addDisPile

    public void cleanBPiles()
    {
        for (int i = 0; i  < 4; i ++)
        {
            if (bPiles[0][i] == 12)
            {
                for (int n = 0; n <12; n ++)
                {
                    if (bPiles[n][i] == n)
                    {
                        numbers[n] ++;
                    } //if

                    else
                    {
                        numbers[12] ++;
                    } //else
                } //for

                for (int n = 0; n < 12; n ++)
                {
                    bPiles[n][i] = 0;
                }
            } //if
        } //for

        boolean swapFlag;

        do {
            swapFlag = false;

            for (int i = 0; i < 4; i ++)
            {
                for (int n = 0; n < 11; n++)
                {
                    if (bPiles[n][i] == 0)
                    {
                        bPiles[n][i] = bPiles[(n + 1)][i];
                        if (bPiles[n][i] == 0)
                        {
                            swapFlag = false;
                        } //if

                        else
                        {
                            swapFlag = true;
                        } //else
                    }// if
                } // for
            } //for
        } while (swapFlag);
    } //cleanBPiles
} //Board
