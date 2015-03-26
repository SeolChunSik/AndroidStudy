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


//Iterator 는 자동으로 Index 를 관리해주기 때문에 사용에 편리함이 있을수 있으나
//Iterator 를 열어보면 객체를 만들어 사용하기 때문에 느릴수 밖에 없다.




//Collection
//        Collection 인터페이스를 상속받아 List와 Set 인터페이스가 된다. List는 순서가 있는 Collection, 그리고 List는 Data 중복을 허락한다.
//        하지만 Set은 순서의 의미가 없으며 Data를 중복해서 포함할 수 없다.
//
//        List 인터페이스의 특징
//        순서가 있는 Collection.(이 순서는 삽입된 순서를 의미한다.)
//        Data를 중복해서 포함할 수 있다.
//        Stack의 특징
//          Data의 삽입과 추출이 후입선출(Last-In First-Out) 구조로 되어 있다.
//          push() method : Data 삽입할 때 사용
//          pop() method : Data 추출할 때 사용
//          peek() method : 추출할 Data를 삭제하지 않고 Data만을 가져 올 때 사용
//          search() method : Stack으로부터 Data를 검색할 때 사용
//        Vector의 특징
//          자동으로 동기화를 보장해준다.
//          ArrayList에 동기화가 보장되도록 최적화한 클래스이다.
//          JAVA 5.0 이 후로는 AutoBoxing/AutoUnBoxing을 지원한다.
//              AutoBoxing이란? 기본 Data 타입을 Wrapper 클래스형의 객체로 자동으로 변환해주는 기능. AutoUnBoxing은 AutoBoxing의 반대 개념
//                  JAVA 1.4까지
//                  Vector v = new Vector();
//                  v.addElement(new Integer(100));
//
//                  JAVA 5.0이후
//                  Vector v = new Vector();
//                  v.addElement(100); // AutoBoxing 발생, 자동으로 Wrapper class인 Integer로 변경
//
//                  addElement() method : Data를 삽입할 때 사용
//                  elementAt() method : Data를 추출할 때 사용, Index에 해당하는 객체를 얻어냄
//                  size() method : Vector 내에 존재하는 객체의 수를 얻어낼 대 사용
//                  insertElementAt() method : Vector 내에 중간 삽입할 때 사용
//                  setElementAt() method : Vector 내에 존재하는 Data를 수정할 때 사용
//                  indexOf() method : Vector 내에 Data를 검색할 때 사용, Index를 반환
//                  contains() method : Data의 존재 유무를 알기 위해 사용.
//           ArrayList의 특징
//              동기화를 보장해주지 않는다.
//              배열에 동적 메모리 증가 기능을 구현한 클래스이다.
//              동기화 지원 방법 : List list = Collections.synchronizeList(new ArrayList(…));
//              add() method : Data 삽입할 때 사용
//              get(0 method : Data 추출할 때 사용
//              toArray() method : ArrayList로부터 배열 얻어낼 때 사용
//              contains() method : Data의 존재 유무를 알기 위해 사용
//              size() method : ArrayList의 요소 개수를 얻어낼 때 사용
//        Set 인터페이스의 특징
//          집합적인 개념의 Collection
//          순서의 의미가 없다.
//          Data를 중복해서 포함할 수 없다.
//          HashSet의 특징
//              add() method : Data 삽입할 때 사용
//              next() method : Data 추출할 때 사용
//                  HashSet의 Data 추출은 Iterator을 이용하면 된다. Iterator는 Collection내의 모든 Data에 접근할 수 있는 특징이 있다. 그리고 Data의 마지막에 상관하지 않고 검색하기 위한 인터페이스이다. Set의 Iterator() method로 Iterator를 얻어 낼 수 있으며, Iterator의 hasNext() method를 이용해서 Data 끝을 만날 때까지 next() method를 호출해서 Data를 추출할 수 있다.
//                      Iterator<String iter = set.iterator();
//                      while(iter.hasNext()) {
//                      String temp = iter.next();
//
//                      System.out.print(temp + ", ");
//                      }
//
//              remove() method : Data를 삭제할 때 사용
//              contains() method : Data의 포함여부를 알기 위해 사용
//              size() method : HashSet의 요소 개수를 얻어낼 때 사용
//
