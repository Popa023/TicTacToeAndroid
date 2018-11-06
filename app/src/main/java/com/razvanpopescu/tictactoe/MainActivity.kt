package com.razvanpopescu.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var board = Array(3) { IntArray(3) }
    var turn = true
    var winner = false
    var wild = 1
    var owins = 0
    var xwins = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reset.isEnabled = false
    }
    fun buttonTapped(v: View?) {
        when(v!!.id){
            button1.id -> buttonpressed(0, 0,button1)
            button2.id -> buttonpressed(0,1,button2)
            button3.id -> buttonpressed(0,2,button3)
            button4.id -> buttonpressed(1,0,button4)
            button5.id -> buttonpressed(1,1,button5)
            button6.id -> buttonpressed(1,2,button6)
            button7.id -> buttonpressed(2,0,button7)
            button8.id -> buttonpressed(2,1,button8)
            button9.id -> buttonpressed(2,2,button9)
            reset.id -> reset()
        }
    }

    fun checkWin(){
        if(turn){
            wild = 2
        }
        else{
            wild = 1
        }
        for ((i, value) in board.withIndex()) {
            for((j, current) in value.withIndex()){
                if(i == 0){
                   if(current == wild && current == board[i+1][j] && current == board[i+2][j]){
                       winner = true
                   }
                    if(j == 0){
                        if(current == wild && current == board[i+1][j+1] && current == board[i+2][j+2]){
                            winner = true
                        }
                    }
                    if(j == 2){
                        if(current == wild && current == board[i+1][j-1] && current == board[i+2][j-2]){
                            winner = true
                        }
                    }
                }
                if(j == 0){
                    if(current == wild && current == board[i][j+1] && current == board[i][j+2]){
                        winner = true
                    }
                }
            }
        }
    }

    fun buttonpressed(x: Int, y: Int, button: Button){
        Toast.makeText(applicationContext, "$x  $y", Toast.LENGTH_LONG).show()
        button.isEnabled = false
        if(turn){
            turn = false
            button.text = "X"
            board[x][y] = 1
        }
        else {
            turn = true
            button.text = "0"
            board[x][y] = 2
        }
        checkWin()
        if(winner){
            winner()
            if(turn) {
                Toast.makeText(applicationContext, "0 Castiga", Toast.LENGTH_LONG).show()
                owins++
                owin.text = "0 wins : $owins"
            }
            else{
                Toast.makeText(applicationContext,"X Castiga", Toast.LENGTH_LONG).show()
                xwins++
                xwin.text = "X wins : $xwins"
            }
            reset.isEnabled = true
        }
    }

    fun winner() {
        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        button5.isEnabled = false
        button6.isEnabled = false
        button7.isEnabled = false
        button8.isEnabled = false
        button9.isEnabled = false
    }

    fun reset(){
        button1.isEnabled = true
        button1.text = ""
        button2.isEnabled = true
        button2.text = ""
        button3.isEnabled = true
        button3.text = ""
        button4.isEnabled = true
        button4.text = ""
        button5.isEnabled = true
        button5.text = ""
        button6.isEnabled = true
        button6.text = ""
        button7.isEnabled = true
        button7.text = ""
        button8.isEnabled = true
        button8.text = ""
        button9.isEnabled = true
        button9.text = ""
        for(i in 0..2){
            for(j in 0..2){
                board[i][j] = 0
            }
        }
        winner = false
        turn = true
    }

}
