package com.assignment.tictactoe.service;

//this is handle the board display and updates
public interface BoardUI {
     void update(int row,int col, Piece piece);
     void notifyWinner(Piece winner);
}
