package com.assignment.tictactoe.service;

//this is for the defining methods for board operations. managing the tictactoe board
public interface Board {
     void initializeBoard();
     boolean isLegalMove(int row, int column);
     void updateMove(int row, int column , Piece piece);
     Winner checkWinner();
     void printBoard();

}
