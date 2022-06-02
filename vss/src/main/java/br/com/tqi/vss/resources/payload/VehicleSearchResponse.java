package br.com.tqi.vss.resources.payload;

import java.util.List;

public class VehicleSearchResponse {

    private final List<Vehicle> vehicle;
    private final boolean match;

    public VehicleSearchResponse(List<Vehicle> vehicle, boolean match) {
        this.vehicle = vehicle;
        this.match = match;
    }

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public boolean isMatch() {
        return match;
    }
}