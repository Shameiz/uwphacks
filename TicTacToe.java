import java.util.Scanner;
import java.lang.Math;

public class TicTacToe
{
    public static char player = '.';
    public static char [][] game = new char [3][3];
    public static int moveNumber = 0;
    public static char computer = '.';
    public static boolean gameOver = false;
    public static boolean moveMade = false;
    public static void main (String[] args)
    {
        Scanner scanner = new Scanner (System.in);

        System.out.print ("Choose either x or o: ");
        player = scanner.nextLine().charAt(0);
        if (player == 'x')
        {
            computer = 'o';
        }
        else
        {
            computer = 'x';
        }

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                game[i][j] = '.';
            }
        }

        int counter = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                counter++;
                System.out.print(counter);
            }
            System.out.println();
        }

        do
        {
            fullHorizontal();
            fullVertical();
            fullDiagonal();
            if (moveNumber%2 == 0)
            {
                System.out.print ("Enter the number you would like to place the " + player + ".");
                int number = scanner.nextInt();  
                placeLetter (number);
                System.out.println();
            }
            else 
            {
                System.out.println ("Computer's move");
                int count=0;
                if(check()==1 || check()==2)
                {
                    do
                    {
                        int i=(int) Math.random()*2;
                        int j= (int)Math.random()*2;
                        if(game[i][j]=='.')
                        {
                            game[i][j]=computer;
                            count++;
                        }
                    }
                    while(count==0);
                    print();
                }
                else
                {

                    checkHorizontal();
                    if (!moveMade)
                    {
                        checkVertical();
                    }
                    if (!moveMade)
                    {
                        checkDiagonal1();
                    }
                    if (!moveMade)
                    {
                        checkDiagonal2();
                    }
                    print();
                }

            } 
            moveNumber++;
        }
        while (moveNumber < 9 && !gameOver);
    }

    public static void fullHorizontal()
    {
        int pcounter = 0;
        int ccounter = 0;
        for (int i = 0; i < 3; i++)
        {
            pcounter=0;
            ccounter=0;
            for (int j = 0; j < 3; j++)
            {
                if(game[i][j]==player)
                {
                    pcounter++;          
                } 
                else if(game[i][j]==computer)
                {
                    ccounter++;
                }
            }
        }

        if (ccounter == 3)
        {
            System.out.print("The computer wins.");
            gameOver = true;
        }
        else if (pcounter == 3)
        {
            System.out.print("You won!");
            gameOver = true;
        }

    } 

    public static void fullVertical ()
    {
        int pcounter = 0;
        int ccounter = 0;
        for (int i = 0; i < 3; i++)
        {
            pcounter=0;
            ccounter=0;
            for (int j = 0; j < 3; j++)
            {
                if(game[j][i]==player)
                {
                    pcounter++;          
                } 
                else if(game[j][i]==computer)
                {
                    ccounter++;
                }

            }

        } 
        if (ccounter == 3)
        {
            System.out.print("The computer wins.");
            gameOver = true;
        }
        else if (pcounter == 3)
        {
            System.out.print("You won!");
            gameOver = true;
        }

    }

    public static void fullDiagonal()
    {
        int pcounter = 0;
        int ccounter = 0;
        for (int i = 0; i < 3; i++)
        {

            if(game[i][i]==player)
            {
                pcounter++;          
            } 
            else if(game[i][i]==computer)
            {
                ccounter++;
            }

        }

        if (ccounter == 3)
        {
            System.out.print("The computer wins.");
            gameOver = true;
        }
        else if (pcounter == 3)
        {
            System.out.print("You won!");
            gameOver = true;
        }

        pcounter = 0;
        ccounter = 0;
        for (int i = 2; i >= 0; i--)
        {
            
                if(game[i][2-i]==player)
                {
                    pcounter++;          
                } 
                else if(game[i][2-i]==computer)
                {
                    ccounter++;
                }
            }
        

        if (ccounter == 3)
        {
            System.out.print("The computer wins.");
            gameOver = true;
        }
        else if (pcounter == 3)
        {
            System.out.print("You won!");
            gameOver = true;
        }
    }

    public static int check()
    {
        int count=0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if(game[i][j]!='.')
                    count++;
            }

        } 
        return count;
    }

    public static void print()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print (game[i][j]);
            }
            System.out.println();
        } 
    }

    public static void placeLetter(int a)
    {

        int row = (int)Math.ceil(a/3.0);
        int column = 2-((3*(row))%a);
        if (a == 1)
        {
            column = 0;
        }
        game[row-1][column] = player;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print (game[i][j]);
            }
            System.out.println();
        }
    }

    public static void checkHorizontal()
    {
        int counter = 0;
        boolean found = false;
        int x = -1;
        int y=-1;
        for (int i = 0; i < 3 && !found; i++)
        {
            counter = 0;
            for (int j = 0; j < 3 && !found; j++)
            {  
                if (game[i][j] == player)
                {
                    counter++;
                }
                if (counter == 2)
                {
                    found = true;
                    x = i;

                }
            }
        }

        if (found)
        {
            for (int i = 0; i < 3; i++)
            {  
                if (game[x][i] == '.')
                {
                    game[x][i] = computer;
                    moveMade = true;
                }
            }

        }
    }

    public static void checkDiagonal1()
    {
        int counter=0;
        boolean found = false;
        int x = 0;
        for (int i = 0; i < 3 && !found; i++)
        {
            if (game[i][i] == player)
            {
                counter++;
            }
            if (counter == 2)
            {
                found = true;   
                x=i;
            }
        }
        if (found)
        {
            System.out.print("zaeem");
            for(int i = 0; i < 3; i++)
            {
                if (game[i][i] == '.')
                {
                    game[i][i] = computer;
                    moveMade=true;
                }
            }
        }
    }

    public static void checkDiagonal2()
    {
        int counter=0;
        boolean found=false;
        int x =0;
        int y = 0;
        for (int i = 2; i >= 0 && !found ; i--)
        {
            for (int j = 0; j < 3 && !found; j++)
            {
                if (game[i][j] == player)
                {
                    counter++;
                }
                if (counter == 2)
                {
                    found = true; 

                }
            }
        }
        if (found)
        {

            for (int i = 2; i >= 0; i--)
            {

                if (game[i][2-i] == '.')
                {
                    game[i][2-i] = computer;
                    moveMade = true;
                }

            }

        }
    }

    public static void checkVertical()
    {
        boolean found = false;
        int x = -1;
        int y=-1;
        int counter = 0;
        for (int i = 0; i < 3 && !found; i++)
        {
            counter = 0;
            for (int j = 0; j < 3 && !found; j++)
            {  
                if (game[j][i] == player)
                {
                    counter++;
                }
                if (counter == 2)
                {
                    found = true;
                    x = i;
                }
            }
        }

        if (found)
        {
            for (int i = 0; i < 3; i++)
            {  
                if (game[i][x] == '.')
                {
                    game[i][x] = computer;
                }
            }

        }
    }
}

