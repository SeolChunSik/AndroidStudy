package android.study.chunsik.androidstudy.study.thrad_example;

import android.app.Activity;
import android.os.SystemClock;

/**
 * Created by admin on 2015-04-03.
 * <p/>
 * 안드로이드에서 모든 스레드에서 사용되는 기본 클래스
 * 이 클래스는 자바 SE에 들어있는 클래스와 같은 클래스 이며 동일한 방식으로 동작
 * <p/>
 * 사용방법
 * 1 스레드를 상속하는 새 클래스를 작성
 * 2 스레드의 생성자 파라미터로 전달한 Runnable 인터페이스를 구현
 * <p/>
 * <p/>
 * 2번째 방식으로 구현 ProgressBar 구현 예제
 */
public class Step1_baseThread implements Runnable {
    private Object[] mInput;
    private Activity mActivity;
    private int mProgress = 0;

    /*
    * Object... 설명
    * Java 변수 인자들이 객체의 배열이다. 컴파일러는 인자 메소드에 전달된 목록에 기반하여 배열로 합친다.
    *
    * public void aMethod(String... args) {
    *       for(String s : args) {
    *            System.out.println(s);
    *       }
    * }
    *
    *
    * aMethod 는 다음과 같은 형태로 호출된다 :
    *
    * aMethod("here","we","go"); //3 argument list
    * aMethod("to","be","or","not","to","be"); //6 argument list
    *
    * 컴파일러는 인자 목록을 자동으로 다음과 같이 만든다 :
    *
    * aMethod(new String[] {"here","we","go"});
    * aMethod(new String[] {"to","be","or","not","to","be"});
    * */
    public Step1_baseThread(Activity activity, Object... input) {
        mActivity = activity;
        mInput = input;
    }

    @Override
    public void run() {
        mProgress = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                TODO activity생성 후 아래 주석 제거
//                mActivity.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
//                ((ProgressBar) mActivity.findViewById(R.id.progressBar)).setProgress(0);

            }
        };
        mActivity.runOnUiThread(runnable);

        for (Object input : mInput){
            // 입력값을 서버로 전달(sleep을 통해 가짜로 구현)
            SystemClock.sleep(50);

            runnable = new Runnable() {
                @Override
                public void run() {
//                    TODO activity생성 후 아래 주석 제거
//                    ((ProgressBar) mActivity.findViewById(R.id.progressBar)).setMax(mProgress++);
//                    ((ProgressBar) mActivity.findViewById(R.id.progressBar)).setProgress(mInput.length);
                }
            };
            mActivity.runOnUiThread(runnable);
        }

        runnable = new Runnable() {
            @Override
            public void run() {
//                TODO activity생성 후 아래 주석 제거
//                mActivity.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            }
        };
        mActivity.runOnUiThread(runnable);
    }
}

// 이 예제에서 볼 수 있듯UI를 업데이트할 때는 매번 새 Runnable을 생성해야 한다.
// 이러면 코드가 지저분해지고 불필요한 객체 할당 및 가비지 컬렉션을 초래하므로 바람직 하지않음
// Ttread 인스턴스에서 Start를 한 번만 호출할 수 있으므로 이 작업을 수행할 때는 매번 새 Thread 객체를 생성해야 한다는 점
// 새 thread는 계속해서 생성하기에는 연산 비용이 매우 크므로 이코드에는 개선할 점이 매우 많다
