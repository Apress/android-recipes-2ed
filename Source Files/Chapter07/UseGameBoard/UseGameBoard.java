package ca.tutortutor.usegameboard;

import android.app.Activity;

import android.graphics.Color;

import android.os.Bundle;

import ca.tutortutor.gameboard.GameBoard;

public class UseGameBoard extends Activity 
{
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      GameBoard gb = new GameBoard(this, 8, Color.BLUE, Color.WHITE);
      setContentView(gb);
   }
}