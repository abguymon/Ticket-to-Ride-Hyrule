package cs340.tickettohyrule;

import android.support.v7.app.AppCompatActivity;

import cs340.tickettohyrule.PhaseTwoPresenters.GamePresenter;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameActivity extends AppCompatActivity {
    private GamePresenter gamePresenter = new GamePresenter();
//    gamePresenter.setView(this);  <-- links presenter and view, do this on create
//    ClientFacade.getInstance().addObserver(gamePresenter);  <--- links presenter as observer do this in on create as well
}
