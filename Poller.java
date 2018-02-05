package sharedPackage;


/**
 * Created by chees on 2/2/2018.
 */

class Poller implements Runnable {

    //different thread
    public void run(){ //throws InterruptedException {
        //int seqId = 0;
        ClientFacade myFacade = new ClientFacade();
        try {
            while (true) {
                fullPoll(myFacade);
                Thread.sleep(6000);
            }
        }
        catch (InterruptedException myException){
            System.out.println("not able to sleep");
        }
    }

    public void fullPoll(ClientFacade currentFacade) { //throws InterruptedException {
        //Statement st = dbConnection.createStatement();
        //ResultSet rs = st.executeQuery("select * from msg_new_to_bde where ACTION =  804 and SEQ >" + lastSeq + "order by SEQ DESC")
        PollerResults myResult = currentFacade.checkServerModel();
        currentFacade.saveResults(myResult);
    }
}


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