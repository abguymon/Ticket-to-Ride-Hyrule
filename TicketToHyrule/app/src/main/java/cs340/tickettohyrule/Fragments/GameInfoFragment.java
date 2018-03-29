package cs340.tickettohyrule.Fragments;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.gameParts.Player;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.PhaseOnePresenters.GameLobbyPresenter;
import cs340.tickettohyrule.PhaseTwoPresenters.GameInfoPresenter;
import cs340.tickettohyrule.R;
import cs340.tickettohyrule.SignInActivity;

/**
 * Created by eholm on 2/25/2018.
 */

/**
 * This fragment is responsible for displaying information about the game being played including
 * the players data such as cards and points and the data of the other players
 * */
public class GameInfoFragment extends Fragment {
    private GameInfoPresenter gameInfoPresenter = new GameInfoPresenter();
    private ImageButton trainCardDeck;
    private ImageButton destinationCardDeck;
    private ImageButton tCardOne;
    private ImageButton tCardTwo;
    private ImageButton tCardThree;
    private ImageButton tCardFour;
    private ImageButton tCardFive;
    private TextView numTDeck;
    private TextView numDDeck;
    private TextView numLoco;
    private TextView numRed;
    private TextView numGreen;
    private TextView numOrange;
    private TextView numBlue;
    private TextView numBlack;
    private TextView numYellow;
    private TextView numPink;
    private TextView numWhite;
    private RecyclerView playerRecycler;
    private RecyclerView dCardRecycler;
    private Adapter dCardAdapter;
    private HorizontalAdapter playerAdapter;
    private Typeface zeldaFont;

    /**
     * This method creates the view to be displayed to the user. Variables for the view are also initialized
     * in this method.
     * @param inflater - inflates the view to be displayed
     * @param container - contains the view group if it exists
     * @param savedInstanceState - the instance saved if the instance exists
     * @return
     * pre- a view exists that needs to be created
     * post- a view is inflated and displayed to the user with its ui initiated
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_info, container, false);

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        gameInfoPresenter.setView(this);
        ClientFacade.getInstance().addObserver(gameInfoPresenter);

        trainCardDeck = (ImageButton) view.findViewById(R.id.train_deck);
        trainCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameInfoPresenter.drawTrainCard();
            }
        });
        destinationCardDeck = (ImageButton) view.findViewById(R.id.destination_deck);
        destinationCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameInfoPresenter.drawDestinationCards();
            }
        });

        tCardOne = (ImageButton) view.findViewById(R.id.tCardOne);
        tCardOne.setOnClickListener(new FaceUpTrainCardListner());
        tCardTwo = (ImageButton) view.findViewById(R.id.tCardTwo);
        tCardTwo.setOnClickListener(new FaceUpTrainCardListner());
        tCardThree = (ImageButton) view.findViewById(R.id.tCardThree);
        tCardThree.setOnClickListener(new FaceUpTrainCardListner());
        tCardFour = (ImageButton) view.findViewById(R.id.tCardFour);
        tCardFour.setOnClickListener(new FaceUpTrainCardListner());
        tCardFive = (ImageButton) view.findViewById(R.id.tCardFive);
        tCardFive.setOnClickListener(new FaceUpTrainCardListner());

        numTDeck = (TextView) view.findViewById(R.id.num_t_deck);
        numTDeck.setTypeface(zeldaFont);
        numDDeck = (TextView) view.findViewById(R.id.num_d_deck);
        numDDeck.setTypeface(zeldaFont);
        numLoco = (TextView) view.findViewById(R.id.locoTCtext);
        numLoco.setTypeface(zeldaFont);
        numRed = (TextView) view.findViewById(R.id.redTCtext);
        numRed.setTypeface(zeldaFont);
        numGreen = (TextView) view.findViewById(R.id.greenTCtext);
        numGreen.setTypeface(zeldaFont);
        numOrange = (TextView) view.findViewById(R.id.orangeTCtext);
        numOrange.setTypeface(zeldaFont);
        numBlue = (TextView) view.findViewById(R.id.blueTCtext);
        numBlue.setTypeface(zeldaFont);
        numBlack = (TextView) view.findViewById(R.id.blackTCtext);
        numBlack.setTypeface(zeldaFont);
        numYellow = (TextView) view.findViewById(R.id.yellowTCtext);
        numYellow.setTypeface(zeldaFont);
        numPink = (TextView) view.findViewById(R.id.pinkTCtext);
        numPink.setTypeface(zeldaFont);
        numWhite = (TextView) view.findViewById(R.id.whiteTCtext);
        numWhite.setTypeface(zeldaFont);

        dCardRecycler = (RecyclerView) view.findViewById(R.id.destination_card_recycler);
        dCardRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        playerRecycler = (RecyclerView) view.findViewById(R.id.player_info_recycler);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        playerRecycler.setLayoutManager(horizontalLayoutManager);

        updateUI();

        return view;
    }

    /**
     * Method to properly destroy the view on exit. Ensures duplicate observers are not generated.
     * Pre- A view exist to be destroyed
     * Post- The view is destroyed and the observer as well
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        ClientFacade.getInstance().deleteObserver(gameInfoPresenter);
    }

    public class FaceUpTrainCardListner implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            gameInfoPresenter.drawFaceUpTrainCard(v.getId());
        }
    }



    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Updates the screen information depending on input from the user or other users which the main user needs to see.
     * pre- none
     * post- the ui in the view is updated for the user
     */
    //update list ui information
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void updateUI()
    {
        setFaceUpTrainCards();
        gameInfoPresenter.setPlayerTrainCards();
        numDDeck.setText("num destination cards: " + gameInfoPresenter.getnumCardsInDDeck());
        numTDeck.setText("num train cards: " + gameInfoPresenter.getNumCardsInTDeck());

        List<DestinationCard> dCardList = getDCards();
        List<Player> players = getPlayers();
        playerAdapter = new HorizontalAdapter(players);
        dCardAdapter = new Adapter(dCardList);
        playerRecycler.setAdapter(playerAdapter);
        dCardRecycler.setAdapter(dCardAdapter);
    }

    /**
     * Sets the number of cards in the view which are currently in the players hand. This includes the players
     * red, green, blue, black, white, orange, yellow and pink cards as well as the number of the players locomotives.
     * pre- valid ui exists for the player cards
     * post- the ui for the player cards is updated for the user
     */
    public void setPlayerTrainCards(int loco, int red, int green, int orange, int blue, int black, int yellow, int pink, int white){
        numLoco.setText("number of locomotives: " + loco);
        numRed.setText("number of red cards: " + red);
        numGreen.setText("number of green cards: " + green);
        numOrange.setText("number of orange cards: " + orange);
        numBlue.setText("number of blue cards: " + blue);
        numBlack.setText("number of black cards: " + black);
        numYellow.setText("number of yellow cards: " + yellow);
        numPink.setText("number of pink cards: " + pink);
        numWhite.setText("number of white cards: " + white);
    }

    /**
     * Sets the face up cards in the view to be displayed to the user. These are the same for every player in the game.
     * pre- valid ui exists for the face up train cards in the view
     * post- the face up train cards are updated in the ui for the user
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setFaceUpTrainCards()
    {
        TrainCard[] faceUpCards = gameInfoPresenter.getFaceUpTrainCards();
        for (int i = 0; i < 5; i++)
        {
            ImageView currentTrainCard;
            switch (i)
            {
                case 0:
                    currentTrainCard = tCardOne;
                    break;
                case 1:
                    currentTrainCard = tCardTwo;
                    break;
                case 2:
                    currentTrainCard = tCardThree;
                    break;
                case 3:
                    currentTrainCard = tCardFour;
                    break;
                case 4:
                    currentTrainCard = tCardFive;
                    break;
                default:
                    currentTrainCard = tCardOne;
                    break;
            }
            switch (faceUpCards[i].getColor().toString())
            {
                case "GREEN":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.greentc));
                    break;
                case "RED":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.redtc));
                    break;
                case "BLACK":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.blacktc));
                    break;
                case "YELLOW":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.yellowtc));
                    break;
                case "PINK":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.pinktc));
                    break;
                case "ORANGE":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.orangetc));
                    break;
                case "WHITE":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.whitetc));
                    break;
                case "BLUE":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.bluetc));
                    break;
                case "WILD":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.locotc));
                    break;
                default:
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.ttr));
                    break;
            }

        }
    }

    /**
     * getter for the destination cards within the presenter
     * @return the number of destination cards
     * pre- a list of destination cards exists in the presenter and is valid.
     * post- returns that ist of destination cards
     */
    private List<DestinationCard> getDCards() {
        return gameInfoPresenter.getDestinationCards();
    }

    /**
     * getter for the players from the presenter
     * @return the players in the current game
     * pre- a list of players exists in the presenter and is valid.
     * post- returns that ist of players
     */
    private List<Player> getPlayers() {return gameInfoPresenter.getPlayers();}

    /**
     * Holder class for the destination card recycler
     */
    private class Holder extends RecyclerView.ViewHolder {

        private TextView dCardText;
        private DestinationCard mDestinationCard;

        /**
         * Constructor to set up the holder and initialize the destination card text views.
         * @param inflater - inflater to inflate the views the recycler will hold
         * @param parent - the view group of the view to be placed in the recycler
         * pre- valid ui exists for the holder
         * post- ui is set to the correct values
         */
        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.log_text_list,parent,false));

            dCardText = (TextView) itemView.findViewById(R.id.log_text);
            dCardText.setTypeface(zeldaFont);
        }

        /**
         * Binds the destination card object to the recycler view.
         * @param destinationCard - destination card to be displayed
         * pre- destination cards exists and has valid data
         * post- destination card is bound to the holder
         */
        //bind object to recycler
        public void bind(DestinationCard destinationCard)
        {
            this.mDestinationCard = destinationCard;
            dCardText.setText(mDestinationCard.toString());
            dCardText.setTypeface(zeldaFont);
        }
    }

    /**
     * Adapter for the destination card recycler
     */
    //adapter for recycler
    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<DestinationCard> mDCards;

        /**
         * Constructor for the adapter which connects one of the destination cards to be bound to the recycler view.
         * @param dCards - list of destination cards to be bound to recycler
         * pre- the list is not null and contains valid destination cards
         * post- destination card list in adapter is initialized
         */
        public Adapter(List<DestinationCard> dCards){
            mDCards = dCards;
        }

        /**
         * Method called when the view holder is created.
         * @param parent - view group the view in the holder belongs to
         * @param viewType - type of view of the current view
         * @return
         */
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        /**
         * Method for the holder to bind destination cards to the recycler
         * @param holder - the adapters holder
         * @param position - position of the destination card in the list
         * pre- the position of the card is valid as well as the ist of cards
         * post- the destination cards are now bound to the recycler
         */
        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bind(mDCards.get(position));
        }

        /**
         * Getter for number of items in the recycler.
         * @return the number of cards to be placed in the recycler view.
         * pre- the list is not null
         * post- returns the number of destination cards
         */
        @Override
        public int getItemCount() {
            return mDCards.size();
        }
    }

    /**
     * Horizontal adapter for the player list recycler.
     */
    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        private List<Player> playerList;

        /**
         * Class for the view holder of the adapter for the players to be listed
         * also instantiates the text and images for the players
         */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;
            public ImageView imgView;

            /**
             * Constructor to set up the view holder and instantiate player information.
             * @param view - the view which the holder i holding
             * pre- the view is valid and its data is valid and exists
             * post- data in the view is instantiated
             */
            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.playerInfo);
                txtView.setTypeface(zeldaFont);
                imgView = (ImageView) view.findViewById(R.id.player_image);
            }
        }

        /**
         * Constructor for the horizontal adapter of the player list.
         * @param playerList - list of players to be put into recycler
         * pre- the player list is not null and contains valid players
         * post- player list of the adapter is initialized
         */
        public HorizontalAdapter(List<Player> playerList) {
            this.playerList= playerList;
        }

        /**
         * Method called when the view holder is created, sets up the views the recycler will contain.
         * @param parent - view group of the view in the recycler
         * @param viewType - type of view to be held
         * @return
         * pre- all params valid and a layout exists to be created
         * post- view is inflated for the recycler to use
         */
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.player_image_list, parent, false);

            return new MyViewHolder(itemView);
        }

        /**
         * Sets up the information that will be bound to the recycler.
         * @param holder - recyclers holder
         * @param position - position of the player in the list to be bound to the recycler
         * pre- position is valid and holder exists
         * post- players image and info is bound to the recycler
         */
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Player mPlayer = playerList.get(position);
            holder.txtView.setText(mPlayer.getPlayerName() + "\n" + mPlayer.getColor() +
                    "\n" + mPlayer.getTrainsRemaining() + " trains" +
                    "\n" + "points: " + mPlayer.getScore() + "\nnum train cards: " +
                    mPlayer.getTrainCards().size() + "\nnum dest cards: " +
                    mPlayer.getDestinationCards().size());
            switch(playerList.get(position).getPlayerNum())
            {
                case 1:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.link));
                    break;
                case 2:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.goron));
                    break;
                case 3:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.zelda));
                    break;
                case 4:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.bird_person));
                    break;
                case 5:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.zora));
                    break;
                default:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.gerudo));
            }
        }

        /**
         * Gets the number of players to be in the recycler.
         * @return the number of players in the list
         * pre- player list is valid and not null
         * post- returns number of players
         */
        @Override
        public int getItemCount() {
            return playerList.size();
        }
    }

}
