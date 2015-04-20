package android.study.chunsik.androidstudy.study.objectStudy.step1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by admin on 2015-04-20.
 */
public class Inventory {
    List guitars;

    public Inventory() {
        guitars = new LinkedList();
    }

    public void addGuitar(String serialNumber, double price, String builder, String model, String type, String backWood, String topWood) {
        Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        guitars.add(guitar);
    }

    public Guitar getGuitar(String serialNumber) {
        for (Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar) i.next();
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    /**
     * 각 속성과 재고 목록에 있는 각 기타 객체를 비교
     * @param searchGuitar
     * @return
     */
    public Guitar search(Guitar searchGuitar) {
        for (Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar) i.next();
            // 일련번호는 유일한 값이니까 무시
            // 가격은 유일한 값이니깐 무시
            String builder = searchGuitar.getBuilder();
            if ((builder != null) && (!builder.equals("")) && (!builder.equals(guitar.getBuilder())))
                continue;

            String model = searchGuitar.getModel();
            if ((model != null) && (!model.equals("")) && (!model.equals(guitar.getModel())))
                continue;

            String type = searchGuitar.getType();
            if ((type != null) && (!type.equals("")) && (!type.equals(guitar.getType())))
                continue;

            String backWood = searchGuitar.getBackWood();
            if ((backWood != null) && (!backWood.equals("")) && (!backWood.equals(guitar.getBackWood())))
                continue;

            String topWood = searchGuitar.getTopWood();
            if ((topWood != null) && (!topWood.equals("")) && (!topWood.equals(guitar.getTopWood())))
                continue;

            return guitar;
        }
        return null;
    }
}
