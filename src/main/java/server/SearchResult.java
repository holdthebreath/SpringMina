package server;

import entities.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchResult implements Serializable {
//    private String searchCommand;
    private ArrayList<Message> results;
    public SearchResult(String searchCommand, ArrayList<Message> results){
//        this.searchCommand = searchCommand;
        this.results = results;
    }
//    public String getSearchCommand(){
//        return searchCommand;
//    }
    public ArrayList<Message> getResults() {
        return results;
    }
}
