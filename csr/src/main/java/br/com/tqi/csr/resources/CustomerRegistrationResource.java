package br.com.tqi.csr.resources;

import br.com.tqi.csr.resources.payload.CustomerRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerRegistrationResource {

    @PostMapping
    public String register(@RequestBody CustomerRequest request){

        return "Customer " + request.getName() + " created successfully";
    }


}
