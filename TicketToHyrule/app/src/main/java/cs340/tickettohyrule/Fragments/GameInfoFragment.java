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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs340.tickettohyrule.PhaseTwoPresenters.GameInfoPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameInfoFragment extends Fragment {
    private GameInfoPresenter gameInfoPresenter = new GameInfoPresenter();
    private ImageButton trainCardDeck;
    private ImageButton destinationCardDeck;
    private RecyclerView playerRecycler;
    private RecyclerView dCardRecycler;
    private Adapter dCardAdapter;
    private Adapter playerAdapter;
    private Typeface zeldaFont;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_info, container, false);

        gameInfoPresenter.setView(this);
        ClientFacade.getInstance().addObserver(gameInfoPresenter);

        trainCardDeck = (ImageButton) view.findViewById(R.id.train_deck);
        destinationCardDeck = (ImageButton) view.findViewById(R.id.destination_deck);

        dCardRecycler = (RecyclerView) view.findViewById(R.id.destination_card_recycler);
        dCardRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    //update list ui information
    private void updateUI()
    {
        List<DestinationCard> dCardList = getDCards();
        dCardAdapter = new Adapter(dCardList);
        dCardRecycler.setAdapter(dCardAdapter);
    }

    private List<DestinationCard> getDCards() {
        return gameInfoPresenter.getDestinationCards();
    }

    private class Holder extends RecyclerView.ViewHolder {

        private TextView dCardText;
        private DestinationCard mDestinationCard;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.log_text_list,parent,false));

            dCardText = (TextView) itemView.findViewById(R.id.log_text);
        }

        //bind object to recycler
        public void bind(DestinationCard destinationCard)
        {
            this.mDestinationCard = destinationCard;
            dCardText.setText(mDestinationCard.toString());
            dCardText.setTypeface(zeldaFont);
        }
    }

    //adapter for recycler
    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<DestinationCard> mDCards;

        public Adapter(List<DestinationCard> dCards){
            mDCards = dCards;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bind(mDCards.get(position));
        }

        @Override
        public int getItemCount() {
            return mDCards.size();
        }
    }
}
