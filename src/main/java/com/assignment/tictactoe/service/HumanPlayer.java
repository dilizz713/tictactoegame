package com.assignment.tictactoe.service;

public class HumanPlayer extends Player{

    public HumanPlayer(BoardImpl board) {

        super(board);
    }

    @Override
    public void move(int row, int col) {            // implement move to place X on the board if the cell is empty
        if (board.isLegalMove(row, col)) {
            board.updateMove(row,col,Piece.X);
        }
    }
}