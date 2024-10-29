package com.assignment.tictactoe.service;

import lombok.Getter;

//this is  implements board interface and contains the main board structure
@Getter
public class BoardImpl implements Board{
    private Piece[][] pieces = new Piece[3][3];

    public BoardImpl(){
        initializeBoard();
    }

    @Override
    public void initializeBoard() {
        pieces = new Piece[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int column) {
        return pieces[row][column] == Piece.EMPTY;
    }

    @Override
    public void updateMove(int row, int column, Piece piece) {
        pieces[row][column] = piece;
    }

    @Override
    public Winner checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {
                return new Winner(pieces[i][0],i,0,i,1,i,2);
            }
            if(pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]){
                return new Winner(pieces[0][i],0,i,1,i,2,i);
            }

        }
        if(pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]){
            return new Winner(pieces[0][0],0,0,1,1,2,2);
        }
        if(pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]){
            return new Winner(pieces[0][2],0,2,1,1,2,0);
        }
        return null;

    }

    @Override
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(pieces[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isFull(){
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if(pieces[i][j] == Piece.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }


}
