package android.study.chunsik.androidstudy.study.thrad_example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.study.chunsik.androidstudy.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 2015-04-07.
 * <p/>
 * handler를 활용할 때는 주로 Message 객체를 전송해 작업을 수행한다.
 * Message 객체는 단순하고 가벼우며 재사용할 수 있는 개체로스 백그라운드 스레드로 데이터 및 인자를 전달하는 데 사용
 * <p/>
 * <p/>
 * activity 실행하자마자 1분마다 서버로 핑을 보내는 기능을 구현
 * handler를 이용하여 progress구현 핑기능과 별도로 구현 이기 때문에 참고만 하자 (메인 기능이 ping 구현임)
 * TODO prgress는 참고만하고 다른 곳에 구현
 *
 */
public class Step3_Handler extends Activity implements Handler.Callback {

    private Handler mHandler;
    private static final int SYNC_DATA = 10;
    private static final int PING_SERVER = 20;
    private static final int PROGRESS = 30;

    private static final int RECIPIENT_ID = 1;
    private static final long DELAY_TIME = 3000;

    private static final String PING_URL = "http://~~";
    private static final int ONE_MINUATE = 60 * 1000;
    private boolean mPingServer = false;
    private int mFailedPings = 0;
    private int mProgress = 0;
    private static final int MAX = 10;


    /*
    * 메시지 전송 방식
    * 예약 방식, 일반 전송 방식
    *
    * handler 와 message 사용 예제 sample임 전체적인 기능에 영향이 없음으로 사용 방식을 참고만 하자
    * */
    public void sendMessageDemo(Object data) {
        //data를 파라미터로 사용해 새 Message 생성
        //핸들러에서 바로 실행하게끔 전송
        Message.obtain(mHandler, SYNC_DATA, data).sendToTarget();

        //핸들로 빈 메시지를 바로 전송한다.
        mHandler.sendEmptyMessage(SYNC_DATA);

        //지금부터 30초 후 실행되게끔 빈 메시지를 전송
        mHandler.sendEmptyMessageAtTime(SYNC_DATA, DELAY_TIME);

        //30 초 후 두 인자 필드와 obj 객체가 들어있는 메시지를 실행하게끔 전송한다.
        int recipient = RECIPIENT_ID;
        int priority = 5;
        Message msg = mHandler.obtainMessage(SYNC_DATA, recipient, priority, data);
        mHandler.sendMessageDelayed(msg, DELAY_TIME);
    }

    /**
     * 핸들러를 이용하여 서버 핑체크 기능을 데모 형식으로 구현함
     */
    public void pingServer() {
        HttpURLConnection urlConnection = null;
        try {
            URL pingUrl = new URL(PING_URL);
            urlConnection = (HttpURLConnection) pingUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                mFailedPings = 0;
            }
        } catch (IOException e) {
            //네트워크 오류 이곳에서 처리
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        if (mPingServer) {
            mHandler.sendEmptyMessageDelayed(PING_SERVER, ONE_MINUATE);
        }
    }

    public void setProgress(){
        if(mProgress<=MAX){
            Message.obtain(mHandler,PROGRESS, mProgress, MAX);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_step3_handler);

        //Looper를 활용해 새 스레드를 시작
        HandlerThread handlerThread = new HandlerThread("BackgroundThread");
        handlerThread.start();

        //새 handler 생성
        mHandler = new Handler(handlerThread.getLooper(), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPingServer = false;
        mHandler.sendEmptyMessage(PING_SERVER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(PING_SERVER);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //looper 스레드 종료
        mHandler.getLooper().quit();
    }

    @Override
    public boolean handleMessage(Message msg) {
        //이곳에서 들어온 메시지를 처리
        switch (msg.what) {
            case PING_SERVER:
                mProgress ++;
                pingServer();
                setProgress();
                break;

            case SYNC_DATA:
                //오래 걸리는 네트워크 I/O 작업을 수행
                break;

            case PROGRESS:
                //TODO progress xml에 추가 후 구현
//                ProgressBar progressBar = (ProgressBar) findViewById(R.id.pro);
//                progressBar.setProgress(msg.arg1);
//                progressBar.setMax(msg.arg2);
                break;

        }


        //메시지 객체를 재사용
        msg.recycle();
        return true;
    }

}
