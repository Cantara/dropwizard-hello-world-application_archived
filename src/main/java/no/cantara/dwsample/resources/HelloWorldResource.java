package no.cantara.dwsample.resources;

import com.codahale.metrics.annotation.Timed;
import no.cantara.dwsample.api.Planet;
import no.cantara.dwsample.api.Saying;
import no.cantara.dwsample.api.SpecificPlanet;
import no.cantara.dwsample.domain.counter.CounterService;
import org.constretto.annotation.Configuration;
import org.constretto.annotation.Configure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.HttpMethod;
import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.*;

@Service
public class HelloWorldResource implements no.cantara.dwsample.api.HelloWorldResource {
    private final static Logger log = LoggerFactory.getLogger(HelloWorldResource.class);

    private final String template;
    private final String defaultName;

    private final CounterService counterService;

    @Configure
    public HelloWorldResource(@Configuration String template, @Configuration String defaultName, CounterService counterService) {
        this.template = template;
        this.defaultName = defaultName;
        this.counterService = counterService;
        log.debug("{} Initialized with {}, {}", HelloWorldResource.class.getSimpleName(), kv("template", template), kv("defaultName", defaultName));
    }

    @Timed
    @Override
    public Saying hello(Optional<String> name) {
        log.trace("{} {} {}", v("method", HttpMethod.GET), v("path", HelloWorldResource.PATH), kv("name", name.orElse("null")));
        final String value = String.format(template, name.orElse(defaultName));
        log.trace("{}", kv("value", value));
        Saying saying = new Saying(counterService.next(), value);
        log.trace("{}", fields(saying));
        return saying;
    }

    @Timed
    @Override
    public Saying hello(Planet planet) {
        log.trace("{} {} {}", v("method", HttpMethod.POST), v("path", HelloWorldResource.PATH), fields(planet));
        final String value = "Hello " + planet.getYourName() + " on planet " + planet.getPlanetName();
        log.trace("{}", kv("value", value));
        Saying saying = new Saying(counterService.next(), value);
        log.trace("{}", fields(saying));
        return saying;
    }

    @Override
    public SpecificPlanet helloSpecificPlanet(String id) {
        log.trace("{} {} {}", v("method", HttpMethod.GET), v("path", HelloWorldResource.PATH_SPECIFIC_PLANET), fields(id));
        String planetName = "Earth";
        switch(id) {
            case "1":
                planetName = "Mercury";
                break;
            case "2":
                planetName = "Jupiter";
                break;
        }
        return new SpecificPlanetView(new Planet(planetName, "John"));
    }
}