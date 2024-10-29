package com.assignment.tictactoe.service;

//act as a base class for different players
public abstract class Player {
    protected  BoardImpl board;         // a reference to the board where moves made
    public  Player(BoardImpl board){
        this.board = board;

    }

    public abstract void move(int row, int col);
}

