package com.cogniaid.cogniaid.Model;

/**
 * Created by alcanzer on 12/5/18.
 */

public class Conversation {
    private QueryInput queryInput;
    private String session;
    private QueryParams queryParams;

    public QueryInput getQueryInput() {
        return queryInput;
    }

    public void setQueryInput(QueryInput queryInput) {
        this.queryInput = queryInput;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public QueryParams getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
    }
}
