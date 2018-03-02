package cs340.tickettohyrule.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.PhaseTwoPresenters.PreStartPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class PreStartFragment extends Fragment {
    private PreStartPresenter preStartPresenter = new PreStartPresenter();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestart, container, false);

        preStartPresenter.setView(this);
        ClientFacade.getInstance().addObserver(preStartPresenter);


        return view;
    }
}
