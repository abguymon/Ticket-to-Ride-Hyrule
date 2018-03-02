package cs340.tickettohyrule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.Game;
import cs340.tickettohyrule.Fragments.GameChatFragment;
import cs340.tickettohyrule.Fragments.GameHistoryFragment;
import cs340.tickettohyrule.Fragments.GameInfoFragment;
import cs340.tickettohyrule.Fragments.PreStartFragment;
import cs340.tickettohyrule.Fragments.SignInFragment;
import cs340.tickettohyrule.PhaseTwoPresenters.GamePresenter;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameActivity extends AppCompatActivity {
    private GamePresenter gamePresenter = new GamePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gamePresenter.setView(this);
        ClientFacade.getInstance().addObserver(gamePresenter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Fragment fragment = new PreStartFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //MOVE TO INFO SCREEN
    public void moveToInfro() {
        Fragment fragment = new GameInfoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //MOVE TO CHAT SCREEN
    public void moveToChat() {
        Fragment fragment = new GameChatFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //MOVE TO HISTORY SCREEN
    public void moveToHistory() {
        Fragment fragment = new GameHistoryFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
