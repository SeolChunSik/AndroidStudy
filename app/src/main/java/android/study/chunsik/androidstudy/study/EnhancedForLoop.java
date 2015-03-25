package android.study.chunsik.androidstudy.study;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by admin on 2015-03-25.
 * 향상된 for 순환문 예제
 *
 * loopOne, loopTwo의 방식은 성능이 동일
 * (Collection) loopThree, loopFour 방식은 성능이 동일
 *
 * 각 항목의 값뿐 아니라 위치도 필요한 경우라면 Collection클래스는 성능이 훨씬 느리므로 ArrayList사용
 *
 * 일반적으로 거의 변하지 않는 데이터셋을 읽을 때 성능을 높이려면 일반 배열을 사용 (데이터 추가시 성능에 영향이 있음)
 *
 * 모든 요소를 신중히 고려해야 함
 */
public class EnhancedForLoop {

    void loopOne(String[] names) {
        int size = names.length;
        for (int i = 0; i < size; i++) {
            printName(names[i]);
        }
    }

    void loopTwo(String[] names) {
        for (String name : names) {
            printName(name);
        }
    }

    void loopThree(Collection<String> names) {
        for (String name : names) {
            printName(name);
        }
    }

    void loopFour(Collection<String> names) {
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            printName(iterator.next());
        }
    }

    //ArrayList 에서는 개선된 for 순환문의 사용을 삼가해야함
    void loopFive(ArrayList<String> names) {
        int size = names.size();
        for (int i = 0; i < size; i++) {
            printName(names.get(i));
        }
    }


    public void printName(String name) {
        System.out.print(name);
    }
}
