package cs340.tickettohyrule.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);


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
                int testRun = mapPresenter.runTest();
                Toast.makeText(getActivity(), "test " + testRun + " called", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
