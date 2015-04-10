package android.study.chunsik.androidstudy.study.component_usage;

import android.os.Bundle;
import android.study.chunsik.androidstudy.R;
import android.support.v7.app.ActionBarActivity;


/**
 * Step1_appication 사용법 샘플
 */
public class Step1_Application_use_Activity extends ActionBarActivity implements Step1_Application.AppStateListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1__application_use_);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Step1_Application application = (Step1_Application) getApplication();
        application.addAppStateListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Step1_Application application = (Step1_Application) getApplication();
        application.removeAppStateListener(this);
    }

    @Override
    public void onStateChanged(String key, String value) {
        //상태 변화 처리
    }
}
