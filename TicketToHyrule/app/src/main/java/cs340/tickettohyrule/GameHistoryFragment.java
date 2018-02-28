package cs340.tickettohyrule;

import android.support.v4.app.Fragment;

import cs340.tickettohyrule.PhaseTwoPresenters.GameHistoryPresenter;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameHistoryFragment extends Fragment {
    private GameHistoryPresenter gameHistoryPresenter = new GameHistoryPresenter();
//    gameHistoryPresenter.setView(this);  <-- links presenter and view, do this on create
//    ClientFacade.getInstance().addObserver(gameHistoryPresenter);  <--- links presenter as observer do this in on create as well
}
