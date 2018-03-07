package cs340.tickettohyrule.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.GameActivity;
import cs340.tickettohyrule.PhaseTwoPresenters.MapPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 3/5/2018.
 */

public class MapFragment extends Fragment implements View.OnClickListener{
    ImageButton chatButton;
    ImageButton infoButton;
    ImageButton historyButton;
    ImageButton testButton;
    ImageButton bKakarikoLonLon;
    ImageButton bHyruleCLonLon;
    ImageButton bGoronVLonLonOne;
    ImageButton bGoronVLonLonTwo;
    ImageButton bLonLonLordJW;
    ImageButton bLonLonLordJG;

    MapPresenter mapPresenter = new MapPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ClientFacade.getInstance().deleteObserver(mapPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapPresenter.setView(this);
        ClientFacade.getInstance().addObserver(mapPresenter);


        bKakarikoLonLon = (ImageButton) view.findViewById(R.id.b_kak_lon);
        bHyruleCLonLon = (ImageButton) view.findViewById(R.id.b_hyr_lon);
        bGoronVLonLonOne = (ImageButton) view.findViewById(R.id.b_gor_lon);
        bGoronVLonLonTwo = (ImageButton) view.findViewById(R.id.b_gor_lon_two);
        bLonLonLordJW = (ImageButton) view.findViewById(R.id.b_lon_lord_white);
        bLonLonLordJG = (ImageButton) view.findViewById(R.id.b_lon_lord_green);

        chatButton = (ImageButton) view.findViewById(R.id.chat_button);
        infoButton = (ImageButton) view.findViewById(R.id.info_button);
        historyButton = (ImageButton) view.findViewById(R.id.history_button);
        testButton = (ImageButton) view.findViewById(R.id.test_button);

        chatButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        testButton.setOnClickListener(this);

        return view;
    }


    public void updateUI(){

        if(mapPresenter.isClaimed()) {
            bKakarikoLonLon.setBackground(getActivity().getDrawable(R.drawable.green_circle));
        }
    }

    //handle the clicking of join,leave, and create buttons
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.info_button:
                Toast.makeText(getActivity(), "info called", Toast.LENGTH_SHORT).show();
                ((GameActivity) getActivity()).moveToInfo();
                break;
            case R.id.chat_button:
                Toast.makeText(getActivity(), "chat called", Toast.LENGTH_SHORT).show();
                ((GameActivity) getActivity()).moveToChat();
                break;
            case R.id.history_button:
                Toast.makeText(getActivity(), "history called", Toast.LENGTH_SHORT).show();
                ((GameActivity) getActivity()).moveToHistory();
                break;
            case R.id.test_button:
                String testRun = mapPresenter.runTest();
                Toast.makeText(getActivity(), testRun, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
