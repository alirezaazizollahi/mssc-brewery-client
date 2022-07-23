package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@Data
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BeerClient {

    private String BEER_PATH_V1 = "/api/v1/beer/";
    private String apihost;

    private RestTemplate restTemplate;



    public BeerClient(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = new RestTemplate();
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId){
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + beerId, BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    /*public String getApihost() {
        return apihost;
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }*/
}
