package android.study.chunsik.androidstudy.study.objectStudy.Part1.step1;

/**
 * Created by admin on 2015-04-20.
 * 추후 액티비로 변경 예정
 */
public class FindGuitarTesterActivity {

    public void startFunction() {
        //재고 목록 셋업(초기화)
        Inventory inventory = new Inventory();
        inintializeInventory(inventory);

        Guitar whatCostomerLikes = new Guitar("",0,"fender","stratocastor","electric","alder","alder");

        Guitar guitar = inventory.search(whatCostomerLikes);

        if(guitar !=null){
            //제고에 있는 기타임
        }else{
            //재고에 없는 기타임
        }

    }

    public void inintializeInventory(Inventory inventory) {
        //기타들을 재고 목록에 등록
        inventory.addGuitar("1", 300, "builder", "model", "type", "backWood", "topWood"); //sample
    }
}
