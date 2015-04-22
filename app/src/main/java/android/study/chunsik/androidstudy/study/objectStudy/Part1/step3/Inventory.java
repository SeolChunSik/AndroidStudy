package android.study.chunsik.androidstudy.study.objectStudy.Part1.step3;

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

    public void addGuitar(String serialNumber, double price,GuitarSpec spec) {
        Guitar guitar = new Guitar(serialNumber, price, spec);
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
     * 각 속성과 재고 목록에 있는 각 기타 객체를 비교 (여러개의 기타를 검색)
     * @param searchSpec
     * @return
     */
    public List searchList(GuitarSpec searchSpec) {
        List matchingGuitars = new LinkedList();
        for (Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar) i.next();
            // 일련번호는 유일한 값이니까 무시
            // 가격은 유일한 값이니깐 무시
            GuitarSpec guitarSpec = guitar.getSpec();
            if (searchSpec.getBuilder() != guitarSpec.getBuilder())
                continue;

            //대소문자를 구별해야 하는 코드는 현재 모델만 필요
            String model = searchSpec.getModel().toLowerCase();
            if ((model != null) && (!model.equals("")) && (!model.equals(guitarSpec.getModel().toLowerCase())))
                continue;

            if (searchSpec.getType() != guitarSpec.getType())
                continue;

            if (searchSpec.getBackWood() != guitarSpec.getBackWood())
                continue;

            if (searchSpec.getTopWood() != guitarSpec.getTopWood())
                continue;
            matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }
}
