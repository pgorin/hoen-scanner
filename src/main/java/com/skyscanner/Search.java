package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Search {

    private final String city;

    @JsonCreator
    public Search(@JsonProperty("city") String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Search search = (Search) o;
        return Objects.equals(city, search.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }
}
