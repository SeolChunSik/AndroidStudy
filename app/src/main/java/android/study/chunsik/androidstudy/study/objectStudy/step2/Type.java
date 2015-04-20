package android.study.chunsik.androidstudy.study.objectStudy.step2;

/**
 * Created by admin on 2015-04-20.
 */
public enum Type {
    ACOUSTIC, ELECTRIC;

    @Override
    public String toString() {
        switch (this){
            case ACOUSTIC: return "acoustic";
            case ELECTRIC: return "electric";
            default: return "";
        }
    }
}
