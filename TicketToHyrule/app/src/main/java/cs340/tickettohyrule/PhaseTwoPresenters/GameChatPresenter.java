package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ChatEntry;
import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.GameChatFragment;
import cs340.tickettohyrule.PhaseOnePresenters.SignInPresenter;
import cs340.tickettohyrule.SignInActivity;

/**
 * Created by adam on 2/28/18.
 */

public class GameChatPresenter implements Observer {
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private GameChatFragment view = null;

    public void sendMessage(String message){
        SendMessageAsync sendMessageAsync = new SendMessageAsync();
        sendMessageAsync.execute(message);
    }

    @Override
    public void update (Observable observable, Object o){
        CurrentUserSingleton.getInstance().getModelFacade().setGameData(ClientFacade.getInstance().getGameData()); //<---- DOES THIS WORK CAN WE JUST SET THE WHOLE GAME DATA AND CALL IT GOOD? IDK
        view.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                view.updateUI();
            }
        });
    }

    public List<ChatEntry> getChat(){
        return modelFacade.getGameData().getChatHistory();
    }

    public void setView(GameChatFragment view){
        this.view = view;
    }


    private class SendMessageAsync extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... message){
            String result = modelFacade.sendMessage(message[0], modelFacade.getGameData().getGameName());
            return result;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                //DO WE NEED TO DO ANYTHING HERE??????
                view.toast("message sent!");
            }
            else{
                view.toast(message);
            }
        }
    }
}
