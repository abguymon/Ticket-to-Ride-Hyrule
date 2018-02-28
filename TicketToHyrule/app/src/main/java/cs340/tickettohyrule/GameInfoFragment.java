package cs340.tickettohyrule;

import android.support.v4.app.Fragment;

import cs340.tickettohyrule.PhaseTwoPresenters.GameInfoPresenter;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameInfoFragment extends Fragment {
    private GameInfoPresenter gameInfoPresenter = new GameInfoPresenter();
//    gameInfoPresenter.setView(this);  <-- links presenter and view, do this on create
}
