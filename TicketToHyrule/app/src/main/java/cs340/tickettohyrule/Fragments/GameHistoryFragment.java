package cs340.tickettohyrule.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs240.lib.common.results.GameHistoryResult;
import cs340.tickettohyrule.PhaseTwoPresenters.GameHistoryPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameHistoryFragment extends Fragment {
    private RecyclerView historyRecycler;
    private Adapter historyAdapter;
    private Typeface zeldaFont;
    private TextView titleText;
    private GameHistoryPresenter gameHistoryPresenter = new GameHistoryPresenter();
//    gameHistoryPresenter.setView(this);  <-- links presenter and view, do this on create
//    ClientFacade.getInstance().addObserver(gameHistoryPresenter);  <--- links presenter as observer do this in on create as well
@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_game_history, container, false);

    gameHistoryPresenter.setView(this);
    ClientFacade.getInstance().addObserver(gameHistoryPresenter);


    zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

    titleText = (TextView) view.findViewById(R.id.game_history_title);
    titleText.setTypeface(zeldaFont);

    historyRecycler = (RecyclerView) view.findViewById(R.id.game_history_recycler);
    historyRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

    updateUI();

    return view;
}
    @Override
    public void onDestroy() {
        super.onDestroy();
        ClientFacade.getInstance().deleteObserver(gameHistoryPresenter);
    }


    //update list ui information
    private void updateUI()
    {
        List<String> historyList = getHistory();
        historyAdapter = new Adapter(historyList);
        historyRecycler.setAdapter(historyAdapter);
    }

    private List<String> getHistory() {
        return gameHistoryPresenter.getHistory();
    }

    private class Holder extends RecyclerView.ViewHolder {

        private TextView historyText;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.log_text_list,parent,false));

            historyText = (TextView) itemView.findViewById(R.id.log_text);
        }
        //bind object to recycler
        public void bind(String historyText)
        {
            this.historyText.setText(historyText);
            this.historyText.setTypeface(zeldaFont);
        }
    }
    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    //adapter for recycler
    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<String> mHistory;

        public Adapter(List<String> history){
            mHistory = history;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bind(mHistory.get(position));
        }

        @Override
        public int getItemCount() {
            return mHistory.size();
        }
    }
}
