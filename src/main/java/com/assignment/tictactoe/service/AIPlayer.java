package com.assignment.tictactoe.service;

import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(BoardImpl board) {

        super(board);
    }

    @Override
    public void move(int row, int col) {            //place an O in the specified cell if it s a legal move
        if(board.isLegalMove(row,col)){
            board.updateMove(row,col,Piece.O);
        }

    }
    public void findBestMove(){                     // use the minimax algo to evaluate potential moves and select the optimal one
        int bestValue = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestColumn = -1;
        Piece[][] pieces = board.getPieces();

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if(pieces[i][j] == Piece.EMPTY){
                    pieces[i][j] = Piece.O;             //make a temporary move
                    int moveValue = minimax(pieces,0,false);
                    pieces[i][j] = Piece.EMPTY;         //undo the move

                    if(moveValue > bestValue){          //select move with the highest score
                        bestRow = i;
                        bestColumn = j;
                        bestValue = moveValue;
                    }
                }
            }
        }

        if(bestRow != -1 && bestColumn != -1){
            board.updateMove(bestRow,bestColumn,Piece.O);
            board.printBoard();
        }
    }

    private int minimax(Piece[][] pieces,int depth , boolean isMaximize){     // recursively evaluate the board to assign values to moves
        Winner winner =board.checkWinner();
        if(winner != null){
            if(winner.getWinningPiece() == Piece.O){        // 10-depth for AI win , depth-10 for human win
                return 10 - depth;
            }else if(winner.getWinningPiece() == Piece.X){
                return depth - 10;
            }
        }
        if(board.isFull()){
            return 0;
        }
        if(isMaximize){
            int bestValue = Integer.MAX_VALUE;

            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if(pieces[i][j] == Piece.EMPTY){
                        pieces[i][j] = Piece.O;
                        bestValue = Math.max(bestValue,minimax(pieces,depth+1,false));
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue;
        }else{
            int bestValue = Integer.MAX_VALUE;

            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if(pieces[i][j] == Piece.EMPTY){
                        pieces[i][j] = Piece.X;
                        bestValue = Math.max(bestValue,minimax(pieces,depth+1,true));
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue;
        }
        // the Ai maximize it s score while the human minimize it

    }

}

