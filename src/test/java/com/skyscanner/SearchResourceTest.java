package com.skyscanner;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.function.Function;

import static jakarta.ws.rs.client.Entity.json;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(DropwizardExtensionsSupport.class)
public class SearchResourceTest {

    private static final Function<Search, List<SearchResult>> handler = mock(Function.class);
    private static final ResourceExtension extension = ResourceExtension.builder()
            .addResource(new SearchResource(handler))
            .build();

    @Test
    void search() {
        Search request = new Search("rustburg");
        when(handler.apply(request))
                .thenReturn(List.of(new SearchResult("rustburg", "clank's clunks", null)));
        final Response response = extension.target("/search").request().post(json(request));

        assertThat(response.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.OK.getStatusCode());
        assertEquals("[{\"title\":\"clank's clunks\",\"kind\":null}]", response.readEntity(String.class));
    }

}