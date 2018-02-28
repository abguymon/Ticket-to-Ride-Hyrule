package cs340.tickettohyrule;

import android.support.v4.app.Fragment;

import cs340.tickettohyrule.PhaseTwoPresenters.GameChatPresenter;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameChatFragment extends Fragment {
    private GameChatPresenter gameChatPresenter = new GameChatPresenter();
//    gameChatPresenter.setView(this);  <-- links presenter and view, do this in on create
//    ClientFacade.getInstance().addObserver(gameChatPresenter);  <--- links presenter as observer do this in on create as well
}
