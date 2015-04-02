package android.study.chunsik.androidstudy.study.memory_manging;

/**
 * Created by admin on 2015-04-02.
 * <p/>
 * 자바에서 GC는 자동으로 관리를 해주지만 자동으로 메모리를 관리하는 기능은 공짜가 아님
 * GC는 계속해서 실행되고 할당된 메모르릴 해제할 수 있는지 계속 검사를 해야만 함
 * 즉 어플리케이션의 스레드가 GC와 CPU를 나눠 써야 한다는 뜻이므로 GC가 시랭되는 동안 GC 호출에 오래 시간이 걸리지 않게 해주는게 중요
 * <p/>
 * 또한 자동 메모리 관리 기능에서는 메모리 누수 가능성을 완전히 없애주지도 못함, 계속해서 필요 없는 객체에 대한 참조를 유지하면 GC에서는
 * 이를 수집하지 않으며, 결국 메모리를 낭비하게 되고 메모리가 해제되지 않으면 결국 OutOfMemory가 발생하여 어플리케이션이 종료됨
 */
public class ObjectControl {
    final class Pair {
        public int a;
        public int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public void set(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    /*
    * pair의 객체가 짝수가 아닐 경우 예외를 던지는 예제
    * 잘못된 이유 - 순환문 내에서 객체를 할당하는 실수
    *             - 이 순환문의 경우 GC는 많은 일을 해야 하고 대부분의 경우 cpu의 과도 사용으로 인해 app이 ui가 느려지게 할 수 있다
    * */
    public void badObjectAllcationExample(int[] pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("Bad array size");
        }

        for (int i = 0; i < pairs.length; i += 2) {
            Pair pair = new Pair(pairs[i], pairs[i + 1]);
        }
    }

    /*
    * 객체를 할당할 때는 가능한 한 순환문 내에서 할당하는 것을 삼가하여야 함
    * 현재 메서드가 실행될 때 한 번의 GC만 호출
    * */
    public void betterObjectAllcationExample(int[] pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("Bad array size");
        }

        Pair thePair = new Pair(0, 0);
        for (int i = 0; i < pairs.length; i += 2) {
            thePair.set(pairs[i], pairs[i + 1]);
        }
    }

    /*
    * 때로는 순환문 내에서 객체를 생성할 수밖에 없는 경우도 있으므로 이런 상황을 대처하는 방법
    * 스태틱 팩터리 메서드를 활용
    * 현재 이 접근 방식은 안드로이드 프레임워크 및 api에서 자주 사용
    * 요청 시점에 채워지는 내부 객체 캐시를 사용할 수 있게 해줌
    * -유일한 단점은 직접 객체를 재활용해야 한다는 점, 이렇게 하지 않으면 캐시가 항상 비어있게 됨
    *
    * 설명 = 스태틱, 비스태틱 필드를 비롯해 여러 필드를 추가함,  이 필드들은 전통적인 링크드 리스트를 구현하기 위해 사용
    * obtain메서드로만 생성가능 생성자는 private로 지정 함으로써 클래스 밖에서 새 객체를 직접 생성할 수 없게 만듬
    *
    * obtain메서드는 먼저 풀에 기존 객체가 들어있는지 검사 후 , 리스트에 들어있는 첫 번째 요소를 제거한 후 이를 반환
    * 풀이 비어있을 때는 항상 새객체를 생성, 객체를 풀에 다시 집어넣을 때는 객체 사용을 마친 후 recycle을 호출하면 됨
    * */
    public static final class BetterPair { //innerClass로 예제를 구현하기 위해서 static을 붙힘
        public int a, b;
        //풀 내 다음 객체에 대한 참조
        private BetterPair next;

        //동기화에 사용할 락
        private static final Object sPoolSync = new Object();

        //풀에서 처음으로 사용 가능한 객체
        private static BetterPair sPool;

        private static int sPoolSize = 0;
        private static final int MAX_POOL_SIZE = 50;

        /*
        * obtain()을 통한 새 객체만을 허용
        * */
        private BetterPair() {
        }

        public static BetterPair obtain() {
            synchronized (sPoolSync) {
                if (sPool != null) {
                    BetterPair m = sPool;
                    sPool = m.next;
                    m.next = null;
                    sPoolSize--;
                    return m;
                }
            }
            return new BetterPair();
        }

        /*
        * 이 객체를 재활용 이메서들을 호출한 다음에는 이 인스턴스에 대한 모든 참조를 릴리즈해야 한다.
        * */
        public void recycle(){
            synchronized (sPoolSync){
                if(sPoolSize < MAX_POOL_SIZE){
                    next = sPool;
                    sPool = this;
                    sPoolSize++;
                }
            }
        }
    }

    /*
    * 이 메서드가 처음 실행될 때는 매번 반복할 때마다 재사용할 새 인스턴스를 생성한다
    * 하지만 다음 번에 이 메서드를 실행하면 객체가 새로 생성되지 않는다. 아울러 obtain 및 rescycle 메서드가 스레드 안전하므로
    * 이메서드는 여러 동시 스레드에서 실행하더라도 안전하다.
    * 유일한 단점 = recycle메서드를 직접 호출해야 한다는 사실임
    *
    * 어플리케이션이 종료될 때까지 이 클래스에 대한 GC호출을 연기할 수 있다
    *
    * 이 설계 패턴은 Message, MotionEvent, Parcel처럼 자주 사요하는 클래스에서는 불필요한 GC호출을 줄이기 위해 모두 이 패턴을 구현
    *
    * recycle을 호출하지 않으면 매번 풀이 비어있게 됨
    *
    * */
    public void bestObjectAllcationExample(int[] pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("Bad array size");
        }

        for (int i = 0; i < pairs.length; i += 2) {
            BetterPair pair = BetterPair.obtain();
            pair.a =pairs[i];
            pair.b =pairs[i+1];
            pair.recycle();
        }
    }

}
