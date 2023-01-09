package com.skyscanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) {
        environment.jersey().register(
                new SearchResource(
                        (Search request) -> {
                            Function<String, List<SearchResult>> fetch = (String resource) -> {
                                ObjectMapper mapper = new ObjectMapper();
                                try {
                                    return stream(
                                            mapper.readValue(
                                                    getClass().getClassLoader().getResource(resource),
                                                    SearchResult[].class
                                            )
                                    )
                                            .filter(option -> option.appliesTo(request))
                                            .collect(Collectors.toList());
                                } catch (IOException e) {
                                    return List.of();
                                }
                            };
                            List<SearchResult> response = new ArrayList<>();
                            response.addAll(fetch.apply("rental_cars.json"));
                            response.addAll(fetch.apply("hotels.json"));
                            return response;
                        }

                )
        );
    }

}
