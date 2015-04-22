package android.study.chunsik.androidstudy.study.objectStudy.Part1.step3;

/**
 * Created by admin on 2015-04-22.
 */
public class GuitarSpec {
    Builder builder;
    String model;
    Type type;
    Wood backWood;
    Wood topWood;

    public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood) {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public Builder getBuilder() {
        return builder;
    }

    public String getModel() {
        return model;
    }

    public Type getType() {
        return type;
    }

    public Wood getBackWood() {
        return backWood;
    }

    public Wood getTopWood() {
        return topWood;
    }
}
