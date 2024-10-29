package com.assignment.tictactoe.service;

//this is handle the board display and updates
public interface BoardUI {
     void update(int row,int col, Piece piece);             //update the UI when a player makes a move
     void notifyWinner(Piece winner);                       // display the winner msg
}
