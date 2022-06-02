package br.com.tqi.vss.resources.payload;

public class VehicleServiceRequest {

    private String color;
    private String model;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "VehicleServiceRequest{" +
                "color='" + color + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
