package cs340.tickettohyrule;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cs340.tickettohyrule.Fragments.CreateGameFragment;
import cs340.tickettohyrule.Fragments.GameLobbyFragment;
import cs340.tickettohyrule.Fragments.SignInFragment;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Fragment fragment = new SignInFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //move to the game lobby fragment
    public void moveToLobby() {
        Fragment fragment = new GameLobbyFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    //move to the create game fragment
    public void moveToCreate() {
        Fragment fragment = new CreateGameFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
