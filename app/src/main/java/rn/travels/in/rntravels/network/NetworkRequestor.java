package rn.travels.in.rntravels.network;

/**
 * Created by demo on 16/02/18.
 */

public class NetworkRequestor {
    private static NetworkRequestor INSTANCE;

    private NetworkRequestor(){}

    public static NetworkRequestor getInstance(){

        if(INSTANCE == null){
            INSTANCE = new NetworkRequestor();
        }
        return INSTANCE;
    }

    private void makeRequest(String url , int requestType , NetworkListemer listener ){

    }

    private void cancelRequest(int requestTag){

    }

    private void cancelAllRequest(){

    }


}
