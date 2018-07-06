package com.piendop.connect3kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    /*override fun onClick(v: View?) {
        when(v?.id){
            R.id.playAgainButton ->{
                playAgain(v)
            }
            else ->{
                dropIn(v as View)
            }
        }
    }*/

    //variables
    var activePlayer: Int = 0
    var gameIsActive : Boolean = true

    //-1 means unplayed

    var gameState  = intArrayOf(2,2,2,2,2,2,2,2,2)

    var winningPositions = arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8), intArrayOf(0,3,6),
            intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(0,4,8), intArrayOf(2,4,6))

    //view variables



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*imageView.setOnClickListener(this)
        imageView2.setOnClickListener(this)
        imageView3.setOnClickListener(this)
        imageView4.setOnClickListener(this)
        imageView5.setOnClickListener(this)
        imageView6.setOnClickListener(this)
        imageView7.setOnClickListener(this)
        imageView8.setOnClickListener(this)
        imageView9.setOnClickListener(this)
        playAgainButton.setOnClickListener(this)*/

    }

    fun dropIn(view: View) {

        //0 = yellow, 1=red
        //cast view to imageView
        var counter = view as ImageView

        //find the tag of the box
        var tappedCounter =  counter.tag.toString().toInt()

        if(gameState[tappedCounter] == 2 && gameIsActive){

            gameState[tappedCounter] = activePlayer
            counter.translationY= -1000f
            //fill box with appropriate coin
            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow)
                //change to red player
                activePlayer=1
            }else{
                counter.setImageResource(R.drawable.red)
                activePlayer=0
            }

            counter.animate().translationYBy(1000f).rotation(360f).duration=300

            for(winningPostion in winningPositions){

                //check if we have a winner
                if(gameState[winningPostion[0]]==gameState[winningPostion[1]] &&
                        gameState[winningPostion[1]]==gameState[winningPostion[2]] &&
                        gameState[winningPostion[0]]!=2){
                    gameIsActive =false

                    var winner = "Red"

                    if(gameState[winningPostion[0]]==0){
                        winner="Yellow"
                    }

                    winnerMessage.text= winner +" has won!"

                    playAgainLayout.visibility = View.VISIBLE

                }else{
                    var gameIsOver = true
                    for(counterState in gameState){
                        if(counterState==2) gameIsOver=false
                    }

                    if(gameIsOver){
                        winnerMessage.text="It's a draw"

                        playAgainLayout.visibility=View.VISIBLE

                    }
                }
            }
        }
    }

    fun playAgain (view: View){
        gameIsActive=true

        playAgainLayout.visibility = View.INVISIBLE

        activePlayer = 0

        for (i in  gameState.indices){
            gameState[i]=2
        }
        for ( j in 0 until boardConstraintLayout.childCount) {
            var temp = boardConstraintLayout.getChildAt(j) as ImageView
            temp.setImageResource(0)
        }
    }
}
