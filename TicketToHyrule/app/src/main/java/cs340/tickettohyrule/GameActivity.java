package cs340.tickettohyrule;

import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.Fragments.GameChatFragment;
import cs340.tickettohyrule.Fragments.GameHistoryFragment;
import cs340.tickettohyrule.Fragments.GameInfoFragment;
import cs340.tickettohyrule.Fragments.MapFragment;
import cs340.tickettohyrule.Fragments.PreStartFragment;
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
        moveToPreStart();
    }

    //MOVE TO MAP SCREEN
    public void moveToMap() {
        Fragment fragment = new MapFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //MOVE TO PRESTART SCREEN
    public void moveToPreStart() {
        Fragment fragment = new PreStartFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //MOVE TO INFO SCREEN
    public void moveToInfo() {
        Fragment fragment = new GameInfoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //MOVE TO CHAT SCREEN
    public void moveToChat() {
        Fragment fragment = new GameChatFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //MOVE TO HISTORY SCREEN
    public void moveToHistory() {
        Fragment fragment = new GameHistoryFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
    public void moveToDrawDestinationCards(){
        Fragment fragment = new PreStartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("draw", "cards");
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
