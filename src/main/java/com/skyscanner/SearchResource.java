package com.skyscanner;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;
import java.util.function.Function;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/search")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class SearchResource {

    private final Function<Search, List<SearchResult>> handler;

    public SearchResource(Function<Search, List<SearchResult>> handler) {
        this.handler = handler;
    }

    @POST
    public List<SearchResult> search(@NotNull @Valid Search request) {
        return handler.apply(request);
    }
}
