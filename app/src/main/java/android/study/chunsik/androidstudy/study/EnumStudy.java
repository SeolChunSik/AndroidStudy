package android.study.chunsik.androidstudy.study;

/**
 * Created by admin on 2015-03-23.
 * -타입 안전한 열거형
 * - 열거형은 공통 그룹에 속하는 선택 옵션을 나타낼 때 사용
 * 과거 자바 버전에서는 이문제를 해결할 때 여러개의 정수 상수를 사용했다.
 * <p/>
 * -개선전 방식 (MachineBefore.class)
 */
public class EnumStudy {

    /**
     * MachineBefore형식으로 작업을 하게 될 경우 setState()메서드가 다른 값을 받지 못하게 막을 수 있
     * 는 방법이 없음, 예상하지 못하는 값을 받게 될 경우 예외처리를 해야하는 불편함도 있음
     */
    private static class MachineBefore {
        public static final int STOPPED = 10;
        public static final int INITIALIZING = 20;
        public static final int STARTING = 30;
        public static final int RUNNING = 40;
        public static final int STOPPING = 50;
        public static final int CRASHED = 60;

        private int mState;

        public MachineBefore() {
            mState = STOPPED;
        }

        public int getState() {
            return mState;
        }

        public void setState(int state) {
            mState = state;
        }

    }


    /**
     * 정수 상수를 이용한 방식의 단점을 보완은 가능함
     * 이전엔 enum도입을 권장하지 않음 (메모리 및 성능 부담이 정수 상수를 사용할 때보다 큼)
     * 하지만 요즘은 jit컴파일러가 강력해졌고, 달빅 vm도 계쏙해서 개선되고 있으므로 크게 걱정하지
     * 않아도 됨 요즘은 권장함
     * <p/>
     * 적절하게 사용할 필요가 있음
     */
    private static class MachineAfter {

        public enum State {
            STOPPED, INITIALIZING, STARTING, RUNNING, STOPPING, CRASHED
        }

        private State mState;

        public MachineAfter() {
            mState = State.STOPPED;
        }

        public State getState() {
            return mState;
        }

        public void setState(State state) {
            mState = state;
        }
    }


//    용어정리 (jit의 개념 검색을 하면서 참고)
//
//    ART, Android Runtime from Kitkat( 4.4 )
//
//    기존의 안드로이드에서는 DEX file 을 Dalvik machine 위에 올리는 방식이었는데,
//    Kitkat ( 4.4 ) 부터는 ART machine 위에서 OAT file 을 돌리는 것이 가능해졌다.
//    기존의 dex 파일을 dex2oat 라는 converter 를 이용해서 converting 하여 생성하고 run 한다.
//
//    Dalvik 은 앱 코드를 네이티브 기계어 코드로 변환한다.
//    사용자가 앱을 실행할 때마다 이 변환 절차가 필요한데 이를 JIT( just-in-time ) 컴파일이라고 한다.
//    이 방식은 다양한 하드웨어나 아키텍쳐에서 실행할 수 있는 장점이 있지만,
//    이 과정이 성능과 배터리에 나쁜 영향을 준다.
//
//    ART 는 Android RunTime 의 약자이며,
//    앱을 설치할 때 완전히 네이티브 앱으로 변환하여 설치한다. 이 과정을 Ahead-Of-Time( AOT ) 컴파일이라 한다.
//    ART 를 사용하면, 새로운 가상머신(Dalvik)을 매번 생성하고, 인터프리트 된 코드 실행하는 시간을 제거하여 performance 가 엄청 향상될 수 있다.
//    다시 말해 VM 자체가 필요없어, iOS 와 비슷한 성능의 이점을 얻을 수 있다는 것이다.
//
//
//
//    ART 를 사용함으로서 얻는 성능상 이점은 앱의 종류에 따라 다르다.
//    일부 앱은 약 2배의 속도 향상이 있었고( 보통 GPU 를 많이 사용하는 것 ), 일부 앱은 속도가 약간 더 느려지기도 한다. 하지만 전반적으로는 속도향상이 있었다.
//
//
//    현재는 Dalvik -> ART 를 할 경우,
//    반드시 재부팅을 해야 하고, OAT file 이 생성되지 않은 경우 이 파일들을 생성하는데 오랜 시간을 기다려야 할 수도 있다.
//
//    게다가 아직 호환성이 완벽히 보장되지 않기 때문에
//    Dalvik 에서는 제대로 작동하던 앱들이 ART 를 사용하면 죽는 현상이 자주 발생하기도 한다.
//
//
//
//    자바와 그 컴파일러에 대한 이해.
//
//    Java 언어의 철학 중 하나는 한번 프로그래밍을 하면, Platform 에 상관없이 실행 가능하게 하는 것.
//    Write Once, Run Everywhere 이라고도 부른다.
//
//    이것을 위해서 Java Virtual Machine( JVM ) 이란 장치를 만들어 놓는다.
//    Java 로 작성된 프로그램을 컴파일하면 JVM 이 읽을 수 있는 중간언어로 번역되고,
//    JVM 이 각 플랫폼 ( 소위 OS 라고도 하는 윈도우, 유닉스, 리눅스 등 )이 알아 들을 수 있는 언어로 번역해서 프로그램을 실행시킨다.
//
//    이런 번역의 과정을 실행할 때마다 거치기 때문에
//    태생적으로 Native 언어들에 비해 속도가 느리다는 단점이 있다.
//    그리고 언어가 발전하면서 이런 단점을 만회하기 위해 JIT ( Just-In-Time ) 컴파일러라는 장치가 추가된다.
//
//
//
//    JIT Compiler.
//
//    JVM 은 중간 언어를 읽어 들일 때 한줄 한줄 읽어들인다.
//    이런 방식의 컴파일러를 interpreter 라고 하는데,
//    JIT 는 인터프리터 형식으로 중간 언어를 읽지 않고, 프로그램이 실행될 때 한꺼번에 읽어서
//    한번에 Platform 이 알아들을 수 있는 언어로 번역을 한다.
//    한줄 한줄 읽는 Interpreter 방식에 비해 당연히 속도가 빠르긴 하지만, native 방식에 비해서는 여전히 속도가 느리다. ( 그래도 Interpreter 방식에 비해 약 10~20배 정도의 성능향상이 있다고 한다. )
//
//
//
//
//
//
//
//    Dalvik VM
//
//    안드로이드도 Java 언어를 사용하기 때문에 VM 을 이용해야 한다.
//    다만 라이선스 문제가 있어 일반 JVM 대신 Dalvik VM 이라는 것을 만들어 사용한다.
//    초기의 안드로이드는 Dalvik VM 에 JIT 가 포함되어 있지 않았지만,
//    진저브레드 ( 2.3 ) 부터는 JIT 가 포함되어 성능 향상이 있었다.
//    다만 JIT 컴파일러는 작동할 때 당연히 Interpreter 에 비해 CPU 와 RAM 등의 resource 를 많이 사용하기 때문에 배터리를 많이 소모하게 된다.
//
//    화면 전환이 많을수록 배터리 소모는 더 급격해지고,
//    한번에 읽기 때문에 화면 전환시 속도도 느리다는 단점이 있다고 한다.


}
