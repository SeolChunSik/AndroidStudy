package android.study.chunsik.androidstudy.study.objectStudy.Part1.step2;

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

    public void addGuitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backWood, Wood topWood) {
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
            Builder builder = searchGuitar.getBuilder();
            if (searchGuitar.getBuilder() != guitar.getBuilder())
                continue;

            //대소문자를 구별해야 하는 코드는 현재 모델만 필요
            String model = searchGuitar.getModel().toLowerCase();
            if ((model != null) && (!model.equals("")) && (!model.equals(guitar.getModel().toLowerCase())))
                continue;

            Type type = searchGuitar.getType();
            if (searchGuitar.getType() != guitar.getType())
                continue;

            Wood backWood = searchGuitar.getBackWood();
            if (searchGuitar.getBackWood() != guitar.getBackWood())
                continue;

            Wood topWood = searchGuitar.getTopWood();
            if (searchGuitar.getTopWood() != guitar.getTopWood())
                continue;

            return guitar;
        }
        return null;
    }

    /**
     * 각 속성과 재고 목록에 있는 각 기타 객체를 비교 (여러개의 기타를 검색)
     * @param searchGuitar
     * @return
     */
    public List searchList(Guitar searchGuitar) {
        List matchingGuitars = new LinkedList();
        for (Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar) i.next();
            // 일련번호는 유일한 값이니까 무시
            // 가격은 유일한 값이니깐 무시
            Builder builder = searchGuitar.getBuilder();
            if (searchGuitar.getBuilder() != guitar.getBuilder())
                continue;

            //대소문자를 구별해야 하는 코드는 현재 모델만 필요
            String model = searchGuitar.getModel().toLowerCase();
            if ((model != null) && (!model.equals("")) && (!model.equals(guitar.getModel().toLowerCase())))
                continue;

            Type type = searchGuitar.getType();
            if (searchGuitar.getType() != guitar.getType())
                continue;

            Wood backWood = searchGuitar.getBackWood();
            if (searchGuitar.getBackWood() != guitar.getBackWood())
                continue;

            Wood topWood = searchGuitar.getTopWood();
            if (searchGuitar.getTopWood() != guitar.getTopWood())
                continue;
            matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }
}
