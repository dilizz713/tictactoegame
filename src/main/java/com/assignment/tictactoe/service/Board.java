package com.assignment.tictactoe.service;

//this is for the defining methods for board operations. managing the tictactoe board
public interface Board {
     void initializeBoard();                                //sets up an empty board
     boolean isLegalMove(int row, int column);              // check if cell is empty before a move
     void updateMove(int row, int column , Piece piece);    //update board with human player moves
     Winner checkWinner();                                  // determine if there is a winner
     void printBoard();

}
