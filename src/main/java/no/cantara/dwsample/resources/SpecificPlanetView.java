package no.cantara.dwsample.resources;

import io.dropwizard.views.View;
import no.cantara.dwsample.api.Planet;
import no.cantara.dwsample.api.SpecificPlanet;

public class SpecificPlanetView extends View implements SpecificPlanet {
    private final Planet planet;

    public SpecificPlanetView(Planet planet) {
        super("specific-planet.ftl");
        this.planet = planet;
    }

    @Override
    public Planet getPlanet() {
        return planet;
    }
}
