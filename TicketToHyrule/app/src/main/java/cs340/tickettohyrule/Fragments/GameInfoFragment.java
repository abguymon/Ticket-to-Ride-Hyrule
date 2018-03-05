package cs340.tickettohyrule.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.PhaseTwoPresenters.GameInfoPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameInfoFragment extends Fragment {
    private GameInfoPresenter gameInfoPresenter = new GameInfoPresenter();
    private ImageButton trainCardDeck;
    private ImageButton destinationCardDeck;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_info, container, false);

        gameInfoPresenter.setView(this);
        ClientFacade.getInstance().addObserver(gameInfoPresenter);

        trainCardDeck = (ImageButton) view.findViewById(R.id.trainDeck);
        destinationCardDeck = (ImageButton) view.findViewById(R.id.destinationDeck);

        return view;
    }
}
