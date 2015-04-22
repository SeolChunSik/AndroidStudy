package android.study.chunsik.androidstudy.study.objectStudy.Part1.step4;

/**
 * Created by admin on 2015-04-22.
 */
public class GuitarSpec {
    private Builder builder;
    private String model;
    private Type type;
    private Wood backWood;
    private Wood topWood;

    //사용자 요청으로 새로운 요구 조건 추가
    int numStrings;

    public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood,int numStrings) {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
        this.numStrings = numStrings;
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

    public int getNumStrings() {
        return numStrings;
    }

    public boolean matches(GuitarSpec otherSpec){
        if(builder != otherSpec.builder)
            return false;
        if((model != null) && (!model.equals(""))&& (!model.equals(otherSpec.getModel().toLowerCase())))
            return false;
        if(type != otherSpec.type)
            return false;
        if(numStrings != otherSpec.numStrings)
            return false;
        if(backWood != otherSpec.backWood)
            return false;
        if(topWood != otherSpec.topWood)
            return false;
        return true;
    }
}
