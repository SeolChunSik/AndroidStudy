package android.study.chunsik.androidstudy.study.thrad_example;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by admin on 2015-04-06.
 * 장점 - 사용하기 편함, 메인 스레드와 쉽게 통신할 수 있어 ui업데이트가 편함
 * 단점 - 이 클래스의 인스턴스를 한 번만 사용할 수 있다는 점 즉 매번 이 작업을 수행할 때마다 new~로 호출해야 한다는 의미
 *
 * 이 클래스는 무겁지 않지만 (실제 ExecutorService에서 내부적으로 관리) 금세 가비지 컬렉션의 대상이 되는 객체가 늘어나고 이로 인해 결국 app의 ui가 느려지게 되므로
 * 자주 수행하는 작업에 부적합하다.
 *
 * - 주의사항
 * AsyncTask 인스턴스는 항상 UI 스레드에서 생성한다.
 * AsyncTask:execute(…) 메소드는 항상 UI 스레드에서 호출한다.
 * AsyncTask:execute(…) 메소드는 생성된 AsyncTask 인스턴스 별로 꼭 한번만 사용 가능하다. 같은 인스턴스가 또 execute(…)를 실행하면 exception이 발생하며,
 *      이는 AsyncTask:cancel(…) 메소드에 의해 작업완료 되기 전 취소된 AsyncTask 인스턴스라도 마찬가지이다. 그럼으로 background 작업이 필요할 때마다
 *      new 연산자를 이용해 해당 작업에 대한 AsyncTask 인스턴스를 새로 생성해야 한다.
 * AsyncTask의 callback 함수 onPreExecute(), doInBackground(…), onProgressUpdate(…), onPostExecute(…)는 직접 호출 하면 안 된다. (꼭 callback으로만 사용)
 *
 *
 *  AsyncTask사용시 Activity 생명주기를 고려하여 사용해야 함
 *  activity가 종료후에도 task가 살아 있어 activity에 대한 암묵적인 참조를 가지게 만들기 때문에 메모리 릭을 만들 수 있음
 *
 * TODO 예제 activity 필요
 * -예제-
 * protected void onPause() {
 *      super.onPause();
 *      if ((task != null) && (isFinishing()))
 *      task.cancel(false);
 * }
 * */
public class Step2_AsyncTask extends AsyncTask<String, Integer, Integer> {
    private Activity mActivity;

    public Step2_AsyncTask(Activity activity){
        mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//      TODO activity생성 후 아래 주석 제거
//      이 부분은 메인 스레드에서 실행된다.
//      mActivity.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
//      ((ProgressBar) mActivity.findViewById(R.id.progressBar)).setProgress(0);
    }

    @Override
    protected Integer doInBackground(String... params) {
//      이 부분은 메인 스레드에서 실행되지 않는다.
        int progress = 0;

        for(String input: params){
//          서버로 입력값을 전송 (sleep을 통해 가짜로 구현)
            SystemClock.sleep(50);
            publishProgress(++progress, input.length());
        }
        return progress;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
//      TODO activity생성 후 아래 주석 제거
//      이 부분은 메인 스레드에서 실행된다.
//      ((ProgressBar) mActivity.findViewById(R.id.progressBar)).setMax(values[1]);
//      ((ProgressBar) mActivity.findViewById(R.id.progressBar)).setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
//      TODO activity생성 후 아래 주석 제거
//      이 부분은 메인 스레드에서 실행된다.
//      mActivity.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
    }
}
