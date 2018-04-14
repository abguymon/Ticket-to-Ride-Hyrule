package cs340.tickettohyrule;

import android.content.Intent;
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

        Intent intent = getIntent();

        if (intent.getExtras() == null)
        {
            moveToSignIn();
        }
        else
        {
            moveToLobby();
        }

    }

    public void moveToSignIn()
    {
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
    public void moveToPreGame(){
        //PROBABLY CHANGE THIS TO PASS AN INTENT ON WHETHER IT STARTS THE PREGAME OR JUST JUMPS INTO THE GAME
        Intent intent = new Intent(SignInActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
