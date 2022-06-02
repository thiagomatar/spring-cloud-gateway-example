package br.com.tqi.vss.service;

import br.com.tqi.vss.resources.payload.Vehicle;
import br.com.tqi.vss.resources.payload.VehicleSearchResponse;
import br.com.tqi.vss.resources.payload.VehicleServiceRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleSearchService {

    public VehicleSearchResponse search(VehicleServiceRequest request) {

        List<Vehicle> filtered = new ArrayList<>();
        List<Vehicle> allVehicles = getVehicleList();


        if(request.getColor() != null && request.getModel() != null){
            filtered.addAll(
                    allVehicles.stream()
                            .filter(vehicle -> isFilteredByColorAndModel(request, vehicle))
                            .collect(Collectors.toList()));
        } else if (request.getColor() == null && request.getModel() != null){
            filtered.addAll(
                    allVehicles.stream()
                            .filter(vehicle -> isFilteredByModel(request, vehicle))
                            .collect(Collectors.toList()));
        }else if (request.getColor() != null && request.getModel() == null) {
            filtered.addAll(
                    allVehicles.stream()
                            .filter(vehicle -> isFilteredByColor(request, vehicle))
                            .collect(Collectors.toList()));
        }

        if (filtered.isEmpty()) {
            return new VehicleSearchResponse(allVehicles, false);
        } else {
            return new VehicleSearchResponse(filtered, true);
        }
    }

    private boolean isFilteredByModel(VehicleServiceRequest request, Vehicle vehicle) {
        return vehicle.getModel().equalsIgnoreCase(request.getModel());
    }


    private boolean isFilteredByColor(VehicleServiceRequest request, Vehicle vehicle) {
        return vehicle.getColor().equalsIgnoreCase(request.getColor());
    }

    private boolean isFilteredByColorAndModel(VehicleServiceRequest request, Vehicle vehicle) {
        return isFilteredByColor(request, vehicle)
                && isFilteredByModel(request, vehicle);
    }

    private List<Vehicle> getVehicleList() {
        return List.of(
                new Vehicle("Blue", "Sedan", LocalDate.now().plusMonths(1), BigDecimal.valueOf(243.23)),
                new Vehicle("Black", "Sedan", LocalDate.now().plusMonths(1).plusDays(2), BigDecimal.valueOf(212.32)),
                new Vehicle("Grey", "Sedan", LocalDate.now().plusMonths(2).plusDays(1), BigDecimal.valueOf(322.42)),
                new Vehicle("Blue", "SUV", LocalDate.now(), BigDecimal.valueOf(294.29)),
                new Vehicle("Black", "SUV", LocalDate.now().plusMonths(1).plusDays(2), BigDecimal.valueOf(210.82)),
                new Vehicle("Grey", "SUV", LocalDate.now().plusMonths(2).plusDays(1), BigDecimal.valueOf(335.02)),
                new Vehicle("Blue", "Pickup", LocalDate.now().plusMonths(3), BigDecimal.valueOf(443.92)),
                new Vehicle("Black", "Pickup", LocalDate.now().plusMonths(1).plusDays(2), BigDecimal.valueOf(359.82)),
                new Vehicle("Grey", "Pickup", LocalDate.now().plusDays(5), BigDecimal.valueOf(322.72))
        );
    }

}
