package cs240.lib.client;

import cs240.lib.Model.ModelFacade;

/**
 * Created by chees on 2/9/2018.
 */


public class Poller implements Runnable {
    public int commandIndex = 0;
    public static Poller instance;
    public static Poller getInstance(){
        if(instance == null){
            instance = new Poller();
        }
        return instance;
    }
    public int getCommandIndex(){return commandIndex;}
    public void incrementCommandIndex(){commandIndex++;}
    public void setCommandIndex(int newIndex){commandIndex = newIndex;}
    //different thread
    public void run(){ //throws InterruptedException {
        try {
            while (true) {
                fullPoll();
                Thread.sleep(1000);
                System.out.println("i looped");
            }
        }
        catch (InterruptedException myException){
            System.out.println("not able to sleep");
        }
    }

    public void fullPoll() { //throws InterruptedException {
        ModelFacade.getInstance().pollerCheckServer();
    }
}


//PollerResults myResult =
//currentFacade.saveResults(myResult);
//Statement st = dbConnection.createStatement();
//ResultSet rs = st.executeQuery("select * from msg_new_to_bde where ACTION =  804 and SEQ >" + lastSeq + "order by SEQ DESC")
/*function doPoll(){
        $.post('ajax/test.html', function(data) {
            alert(data);  // process results here
            setTimeout(doPoll,5000);
        });
    }
while(true){
    fullPoll();
    Thread.sleep(10000);
    }
*/