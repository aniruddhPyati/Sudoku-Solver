package models;

public class sudoku {

    private static final int size=9;

    public static void main(String[] args) {

        int[][]board = {

                {4, 0, 0, 0, 0, 0, 9, 0, 0},
                {0, 8, 0, 9, 1, 0, 0, 4, 2},
                {5, 0, 0, 2, 0, 0, 0, 3, 6},
                {0, 3, 9, 0, 0, 1, 0, 7, 0},
                {0, 0, 0, 0, 9, 2, 3, 8, 0},
                {0, 4, 0, 0, 3, 7, 0, 9, 5},
                {3, 0, 7, 1, 0, 0, 4, 5, 9},
                {9, 0, 0, 5, 0, 3, 7, 0, 0},
                {8, 0, 2, 0, 0, 0, 0, 0, 0}

        };

        if (boardsolve(board)){
            System.out.println("This is the solved sudoku puzzle");
        }
        else{
            System.out.println("This board cannot be solved by me ");
        }
        printboard(board);



    }

    // TO CHECK THE ROWS

    private static boolean inrow (int[][] board , int number , int row){
        for (int i=0;i<size;i++){
            if(board[row][i]==number){
                return true;
            }
        }
        return false;
    }

    // TO CHECK THE COLUMN

    private static boolean incolumn (int[][] board , int number , int column){
        for (int i=0;i<size;i++){
            if(board[i][column]==number){
                return true;
            }
        }
        return false;
    }

    //TO CHECK EACH OF THE 3X3 BOXES OF THE BOARD

    private static boolean inbox (int[][] board , int number , int row, int column) {

        // TO ACCESS THE TOP LEFT CORNER OF THE 3X3 SUB BOX OF THE BOARD
        int sub_box_row= row- row%3;
        int sub_box_column = column - column%3;

        for (int i = sub_box_row;i<sub_box_row +3;i++){
            for (int j=sub_box_column;j<sub_box_column+3;j++){
                if (board[i][j]==number){
                    return true;
                }


            }
        }
        return false;
    }

    //METHOD TO RUN ALL OF THE ABOVE HELPER METHODS AT ONCE

    private static boolean validitycheck(int[][]board,int number ,int row , int column){
       return  ! inbox(board, number, row, column) &&  ! incolumn(board, number, column)
               &&  ! inrow(board, number, row);
    }

    private static boolean boardsolve(int[][] board){
        for (int row = 0;row<size;row++){
            for (int column=0;column<size;column++){
                if (board[row][column]==0){
                    for (int trialnum=1;trialnum<=size;trialnum++){
                        if (validitycheck(board,trialnum,row,column)){
                            board[row][column]= trialnum;

                            //  boardsolve()
                           if (boardsolve(board)){  // RECURSION of solveboard method
                               return true;         // if true then move to the next spot to fill it up using the previously filled up spot as a constraint
                           }
                           else {
                               board[row][column]=0; // if the current trial number doesn't satisfy then backtrack to that previous spot and make that number 0 and then try again with a different number
                           }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    private static void printboard(int[][] board){
        for (int row=0;row<size;row++){
            if(row%3==0){
                System.out.println("-----------------------");
            }
            for (int column=0;column<size;column++){
                if (column%3==0 && column!=0){
                    System.out.print(" | ");
                }
                System.out.print(board[row][column] +" ");
            }
            System.out.println();
        }
    }



}
