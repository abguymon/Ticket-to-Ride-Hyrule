package cs340.tickettohyrule.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.gameParts.Player;
import cs340.tickettohyrule.PhaseTwoPresenters.DrawDestinationCardsPresenter;
import cs340.tickettohyrule.PhaseTwoPresenters.PreStartPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class PreStartFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{
    private PreStartPresenter preStartPresenter = new PreStartPresenter();
    private DrawDestinationCardsPresenter drawDestinationCardsPresenter = new DrawDestinationCardsPresenter();
    private ImageView playerImage;
    private TextView playerColor;
    private CheckBox checkBoxOne;
    private CheckBox checkBoxTwo;
    private CheckBox checkBoxThree;
    private ImageButton submitButton;
    private TextView cardOne;
    private TextView cardTwo;
    private TextView cardThree;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestart, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            drawDestinationCardsPresenter.setView(this);
            ClientFacade.getInstance().addObserver(drawDestinationCardsPresenter);
        }
        else {
            preStartPresenter.setView(this);
            ClientFacade.getInstance().addObserver(preStartPresenter);
        }

        Player currentPlayer = preStartPresenter.getPlayer();

        playerImage = (ImageView) view.findViewById(R.id.player_image);
        switch(currentPlayer.getPlayerNum())
        {
            case 1:
                playerImage.setImageDrawable(getActivity().getDrawable(R.drawable.link));
                break;
            case 2:
                playerImage.setImageDrawable(getActivity().getDrawable(R.drawable.goron));
                break;
            case 3:
                playerImage.setImageDrawable(getActivity().getDrawable(R.drawable.zelda));
                break;
            case 4:
                playerImage.setImageDrawable(getActivity().getDrawable(R.drawable.bird_person));
                break;
            case 5:
                playerImage.setImageDrawable(getActivity().getDrawable(R.drawable.zora));
                break;
            default:
                playerImage.setImageDrawable(getActivity().getDrawable(R.drawable.gerudo));
        }

        playerColor = (TextView) view.findViewById(R.id.player_color);
        playerColor.setText(currentPlayer.getColor().toString());

        checkBoxOne = (CheckBox) view.findViewById(R.id.checkbox_one);
        checkBoxOne.setOnCheckedChangeListener(this);
        checkBoxTwo = (CheckBox) view.findViewById(R.id.checkbox_two);
        checkBoxTwo.setOnCheckedChangeListener(this);
        checkBoxThree = (CheckBox) view.findViewById(R.id.checkbox_three);
        checkBoxThree.setOnCheckedChangeListener(this);
        cardOne = (TextView) view.findViewById(R.id.d_card_one);
        cardTwo = (TextView) view.findViewById(R.id.d_card_two);
        cardThree = (TextView) view.findViewById(R.id.d_card_three);
        submitButton = (ImageButton) view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
        setText();
        return view;
    }
    public void setText(){
        cardOne.setText(preStartPresenter.getDestinationCards().get(0).toString());
        cardTwo.setText(preStartPresenter.getDestinationCards().get(1).toString());
        cardThree.setText(preStartPresenter.getDestinationCards().get(2).toString());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ClientFacade.getInstance().deleteObserver(preStartPresenter);
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked){
        switch (button.getId()){
            case R.id.checkbox_one:
                if(!isChecked) {
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(0));
                }
                else {
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(0));
                }
                break;
            case R.id.checkbox_two:
                if(!isChecked) {
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(1));
                }
                else {
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(1));
                }
                break;
            case R.id.checkbox_three:
                if(!isChecked) {
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(2));
                }
                else {
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(2));
                }
                break;
        }
    }
    @Override
    public void onClick(View v){
        preStartPresenter.submit();
    }

    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
