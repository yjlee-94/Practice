import java.util.Scanner;
import java.util.Random;
//Author - Lee Yong Jian (Jaidon)
public class tictactoe { // TicTacToe 
    public static void main(String[]args)
    {       
        // 2D array rows and columns
        char [][] board = 
        {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '}
        };  

        char [] record = new char[9];
        // 012,345,678,036,158,258,048,246
        // 

        
        Scanner kb = new Scanner(System.in);
        displayBoard(board); // display the board
        
        while(true)
        {
            // User turn
            System.out.println("\nEnter a position: (1-9) "); // prompting user
            int pos = kb.nextInt()-1;  // 
            if(pos < 0 || pos >8) // Check if user given invalid option.
            {
                System.out.println("Invalid pos, pls try again");
                continue;
            }
            else
            {
                if(record[pos] == 0) // check if position is already taken
                {
                    record[pos] = 'X';
                    playTurn(pos+1,board,'X');
                }
                else
                {
                    System.out.println("Position taken!");
                    continue;
                }
            }
            displayBoard(board);
            
            
            //Check if there's a winner
            if(checkWin(record,'X'))
            {
                System.out.println("\nCongratulations you win!");
                break;
            }
            else
            {
                if(checkFull(record))// end game if array is full 
                {
                    System.out.println("Game Drawn! ");
                    break;
                }
            }
            

            // Computer turn
            Random cpu = new Random();
            int cpuPos = cpu.nextInt(9); // Generate a random number (0-8)+1 .
            while(record[cpuPos] != 0)
            {
                cpuPos = cpu.nextInt(9);
            }
            record[cpuPos] = 'O';
            playTurn(cpuPos+1,board,'O');
            System.out.println("\nCPU play: ");
            displayBoard(board);
            if(checkWin(record,'O'))
            {
                System.out.println("\nCPU has won! Game Over.");
                break;
            }

        }
              
    }    
    
    // Function check if CPU or user has won the game.
    static boolean checkWin(char[] record,char sym)
    {
        boolean win = false;
        // 012,345,678,036,147,258,048,246
        if (record[0] == sym && record[1] == sym && record[2] == sym)
        {
            win = true;
        }
        else if (record[3] == sym && record[4] == sym && record[5] == sym)
        {
            win = true;
        }
        else if (record[6] == sym && record[7] == sym && record[8] == sym)
        {
            win = true;
        }
        else if (record[0] == sym && record[3] == sym && record[6] == sym)
        {
            win = true;
        }
        else if (record[1] == sym && record[4] == sym && record[7] == sym)
        {
            win = true;
        }
        else if (record[2] == sym && record[5] == sym && record[8] == sym)
        {
            win = true;
        }
        else if (record[0] == sym && record[4] == sym && record[8] == sym)
        {
            win = true;
        }
        else if (record[2] == sym && record[4] == sym && record[6] == sym)
        {
            win = true;
        }

        return win;
    }

    // Function checks if all positions are taken up or not.
    static boolean checkFull(char[] record)
    {
        boolean full = true;
        for(int i =0;i<record.length;i++)
            {
                if(record[i] == 0)
                {
                    full = false;
                }
            }
        
        return full;
            
    }

    //This function will update the board with the appropriate symbol.
    static void playTurn(int pos,char[][] board,char symbol)
    {
            switch(pos)
            {
                case 1:
                    board[0][0] = symbol;
                    break;
                case 2:
                    board[0][2] = symbol;
                    break;
                case 3:
                    board[0][4] = symbol;
                    break;
                case 4:
                    board[2][0] = symbol;
                    break;
                case 5:
                    board[2][2] = symbol;
                    break;
                case 6:
                    board[2][4] = symbol;
                break;
                case 7:
                    board[4][0] = symbol;
                    break;
                case 8:
                    board[4][2] = symbol;     
                    break;
                case 9:
                    board[4][4] = symbol;
                    break;
                default: System.out.println("Invalid position");
            }
    }

    // This function will display the board to the user.
    static void displayBoard(char[][] board)
    {
        for(char[] r:board)
        {
            for(char c:r)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    
    
}
