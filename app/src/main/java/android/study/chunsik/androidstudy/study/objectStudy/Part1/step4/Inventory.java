package android.study.chunsik.androidstudy.study.objectStudy.Part1.step4;

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
     * 기능을 분리 함으로 유지보수가 편해짐
     *
     * @param searchSpec
     * @return
     */
    public List searchList(GuitarSpec searchSpec) {
        List matchingGuitars = new LinkedList();
        for (Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar) i.next();

            if(guitar.getSpec().matches(searchSpec)){
                matchingGuitars.add(guitar);
            }
        }
        return matchingGuitars;
    }
}
