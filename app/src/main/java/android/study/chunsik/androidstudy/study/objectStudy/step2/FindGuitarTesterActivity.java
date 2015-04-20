package android.study.chunsik.androidstudy.study.objectStudy.step2;

import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2015-04-20.
 * 추후 액티비로 변경 예정
 */
public class FindGuitarTesterActivity {

    public void startFunction() {
        //재고 목록 셋업(초기화)
        Inventory inventory = new Inventory();
        inintializeInventory(inventory);

        Guitar whatCostomerLikes = new Guitar("",0, Builder.ANY, "model", Type.ELECTRIC, Wood.BRAZILLIAN_ROSEWOOD, Wood.BRAZILLIAN_ROSEWOOD); //sample

        Guitar guitar = inventory.search(whatCostomerLikes);

        if(guitar !=null){
            //제고에 있는 기타임
        }else{
            //재고에 없는 기타임
        }
    }

    //여러개의 리스트를 볼 수 있는 기능
    public void startFunction_two() {
        //재고 목록 셋업(초기화)
        Inventory inventory = new Inventory();
        inintializeInventory(inventory);

        Guitar whatCostomerLikes = new Guitar("",0, Builder.ANY, "model", Type.ELECTRIC, Wood.BRAZILLIAN_ROSEWOOD, Wood.BRAZILLIAN_ROSEWOOD); //sample

        List matchingGuitars = inventory.searchList(whatCostomerLikes);

        if(!matchingGuitars.isEmpty()){
            //제고에 있는 기타임
            for(Iterator i = matchingGuitars.iterator(); i.hasNext();){
                Guitar guitar = (Guitar)i.next();
                //재고에 있는 기타 표현
            }
        }else{
            //재고에 없는 기타임
        }
    }

    public void inintializeInventory(Inventory inventory) {
        //기타들을 재고 목록에 등록
        inventory.addGuitar("1", 300, Builder.ANY, "model", Type.ACOUSTIC, Wood.ADIRONDACK, Wood.BRAZILLIAN_ROSEWOOD); //sample
    }
}
