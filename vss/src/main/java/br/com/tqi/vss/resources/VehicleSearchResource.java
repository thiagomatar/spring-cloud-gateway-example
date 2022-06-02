package br.com.tqi.vss.resources;


import br.com.tqi.vss.resources.payload.VehicleSearchResponse;
import br.com.tqi.vss.resources.payload.VehicleServiceRequest;
import br.com.tqi.vss.service.VehicleSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/vehicle")
@RestController
public class VehicleSearchResource {

    private static final Logger log = LoggerFactory.getLogger(VehicleSearchResource.class);
    private final VehicleSearchService service;

    public VehicleSearchResource(VehicleSearchService service) {
        this.service = service;
    }

    @GetMapping
    public VehicleSearchResponse search(VehicleServiceRequest request){
        log.info(request.toString());
        return service.search(request);
    }

}
