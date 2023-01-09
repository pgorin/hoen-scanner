package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {

    private final String city;
    private final String title;
    private final String kind;

    @JsonCreator
    public SearchResult(@JsonProperty("city") String city,
                        @JsonProperty("title") String title,
                        @JsonProperty("kind") String kind) {
        this.city = city;
        this.title = title;
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public String getKind() {
        return kind;
    }

    public boolean appliesTo(Search request) {
        return request.getCity().equals(this.city);
    }

}
