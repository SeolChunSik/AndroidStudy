package android.study.chunsik.androidstudy.study.objectStudy.Part1.step3;

/**
 * Created by admin on 2015-04-20.
 */
public class Guitar {

    String serialNumber;
    double price;
    GuitarSpec spec;

    public Guitar(String serialNumber, double price, GuitarSpec spec) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = spec;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public GuitarSpec getSpec() {
        return spec;
    }

}

