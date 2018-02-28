package cs340.tickettohyrule;

import android.support.v4.app.Fragment;

import cs340.tickettohyrule.PhaseTwoPresenters.PreStartPresenter;

/**
 * Created by eholm on 2/25/2018.
 */

public class PreStartFragment extends Fragment {
    private PreStartPresenter preStartPresenter = new PreStartPresenter();
//    preStartPresenter.setView(this);  <-- links presenter and view, do this on create
//    ClientFacade.getInstance().addObserver(preStartPresenter);  <--- links presenter as observer do this in on create as well
}
