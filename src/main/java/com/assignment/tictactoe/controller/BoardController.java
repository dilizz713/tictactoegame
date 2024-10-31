package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class BoardController implements BoardUI {

    @FXML
    private Button btn00 , btn01 , btn02 , btn10 , btn11 , btn12 , btn20 ,btn21 ,  btn22 ;

    @FXML
    private Button btnStart;

    @FXML
    private GridPane gridPane;

    private BoardImpl board;
    private HumanPlayer humanPlayer;
    private AIPlayer aiPlayer;

    private boolean gameStarted = false;

    public BoardController(){
        board = new BoardImpl();
        aiPlayer = new AIPlayer(board);
        humanPlayer = new HumanPlayer(board);
    }


    @FXML
    void clickOnAction(ActionEvent event) {
        if(!gameStarted){
            showError("Please click the start button to begin the game.");
            return;
        }
        Button clickedButton = (Button) event.getSource();
        String id = clickedButton.getId();


        //Extract row and column from the button ID,( btn00 -> row = 0, col = 0)
        int row = Character.getNumericValue(id.charAt(3));
        int col = Character.getNumericValue(id.charAt(4));

        if (!board.isLegalMove(row, col)) {

            showError("This position is already taken. Please try another one.");
            return;
        }

        humanPlayer.move(row , col);
        board.printBoard();
        updateUI();

        if(board.checkWinner() != null){
            notifyWinner(board.checkWinner().getWinningPiece());
        }else if(board.isFull()){
            showAlert("Tie");
        }else{
            aiPlayer.findBestMove();
            updateUI();

            if(board.checkWinner() != null){
                notifyWinner(board.checkWinner().getWinningPiece());
            }else if(board.isFull()){
                showAlert("Tie");
            }
        }

    }

    @FXML
    void startGame(ActionEvent event) {
        btnStart.setStyle("-fx-background-color: #c30f70; -fx-text-fill: white;");
        gameStarted = true;

        board = new BoardImpl();
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AIPlayer(board);
    }

    private void updateUI(){
        for (int i = 0; i < board.getPieces().length; i++) {
            for (int j = 0; j < board.getPieces()[i].length; j++) {
                update(i,j,board.getPieces()[i][j]);
            }
        }
    }



    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!");
        alert.setContentText(msg);

        ButtonType playAgain = new ButtonType("Play Again");
        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(playAgain,close);

        alert.showAndWait().ifPresent(response ->{
            if(response == playAgain){
                resetGame();
            }else if(response == close){
                Platform.exit();
            }
        });
    }

    private void resetGame(){
        board.initializeBoard();
        gameStarted = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = (Button) gridPane.lookup("#btn" + i+j);
                button.setText("");
            }
        }

        btnStart.setStyle("-fx-background-color:  #e8aade; -fx-text-fill: black;");

        System.out.println("Game reset. Starting a  new game!");
        board.printBoard();
    }

    private void showError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setContentText(msg);

        ButtonType ok = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(ok);

        alert.showAndWait();
    }

    @Override
    public void update(int row, int col, Piece piece) {
        String buttonId = "#btn" + row + col;
        Button button = (Button) gridPane.lookup(buttonId);
        if(button != null){
            if(piece == Piece.X){
                button.setText("X");
            }else if(piece == Piece.O){
                button.setText("O");
            }else{
                button.setText("");
            }
        }
    }

    @Override
    public void notifyWinner(Piece winner) {
        if(winner != Piece.EMPTY){
            String winnerMsg = ((winner == Piece.X) ? "Congratulations! You have won the game" : "Oops! AI has won the game");
            showAlert(winnerMsg);
        }

    }
}
