// Author: Vaughn Rowse
// Last Updated: March 9, 2019
package com.example.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Create a new game and variables
    Rochambo game = new Rochambo();
    TextView results;
    ImageView playerPick;
    ImageView computerPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = findViewById(R.id.text_results);
        playerPick = findViewById(R.id.image_player);
        computerPick = findViewById(R.id.image_computer);
    }

    public void onRockClick(View view) {
        game.playerMakesMove(Rochambo.ROCK);
        update();
    }

    public void onPaperClick(View view) {
        game.playerMakesMove(Rochambo.PAPER);
        update();
    }

    public void onScissorsClick(View view) {
        game.playerMakesMove(Rochambo.SCISSOR);
        update();
    }

    /**
     * Updates the game after the player has made a choice
     * Shows an animation between the 2 choices, displays the 2 choices
     * Shows the winner at the bottom after
     */
    private void update() {
        Drawable playerImage = getDrawable(getImage(game.getPlayerMove()));
        Drawable computerImage = getDrawable(getImage(game.getGameMove()));
        playerPick.setImageDrawable(playerImage);
        computerPick.setImageDrawable(computerImage);
        results.setText(game.winLoseOrDraw());

        // you can animate any prop that there is a setter for
        // player.setRotationX(0f);

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(playerPick,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(computerPick,
                "rotationY", 0f, 360f)
                .setDuration(500);


        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }

    /**
     * Gets the image based off the id passed in
     * @param image the image ID
     * @return the image that has been chosen
     */
    private int getImage(int image) {
        switch(image) {
            case Rochambo.ROCK:
                return R.drawable.rock;
            case Rochambo.PAPER:
                return R.drawable.paper;
            case Rochambo.SCISSOR:
                return R.drawable.scissors;
            default:
                return R.drawable.none;
        }
    }
}
