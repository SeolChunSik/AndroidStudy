package android.study.chunsik.androidstudy.study.objectStudy.Part1.step4;

/**
 * Created by admin on 2015-04-21.
 */
public enum Wood {
    INDIAN_ROSEWOOD, BRAZILLIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO, CEDAR, ADIRONDACK, ALDER, SITKA;

    @Override
    public String toString() {
        switch (this) {
            case INDIAN_ROSEWOOD: return "Indian Rosewood";
            case BRAZILLIAN_ROSEWOOD: return "Brazillian RoseWood";
            case MAHOGANY: return "Mahogany";
            case MAPLE: return "Maple";
            case COCOBOLO: return "Cocobolo";
            case CEDAR: return "Cedar";
            case ADIRONDACK: return "Adirondack";
            case ALDER: return "Alder";
            case SITKA: return "Sitcka";
            default: return "";
        }
    }
}
