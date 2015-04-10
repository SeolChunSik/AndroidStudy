package android.study.chunsik.androidstudy.study.component_usage;

import android.app.Application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 2015-04-10.
 *
 * 매니페스트 파일(androidmanifest.xml)에는 항상 application엘리먼트가 들어있다.
 * 컴포넌트를 사용하려면 아래 처럼 선언 해야한다.
 * <application ~~
 *              name:"Step1_Application">
 * </>
 *
 */
public class Step1_Application extends Application {
    private ConcurrentHashMap<String,String> mGlobalVariable;
    private Set<AppStateListener> mAppStateListeners;

    @Override
    public void onCreate() {
        super.onCreate();
        //다른 컴포넌트가 생성되기 전에 호출
        mGlobalVariable = new ConcurrentHashMap<String,String>();
        mAppStateListeners = Collections.synchronizedSet(new HashSet<AppStateListener>());
    }

    public String getGlobalVariable(String key){
        return mGlobalVariable.get(key);
    }

    public String removeGlobalVariable(String key){
        String value = mGlobalVariable.remove(key);
        notifyListeners(key,null);
        return value;
    }

    public void putGlobalVariable(String key, String value){
        mGlobalVariable.put(key, value);
        notifyListeners(key, value);
    }

    public void addAppStateListener(AppStateListener appStateListener){
        mAppStateListeners.add(appStateListener);
    }

    public void removeAppStateListener(AppStateListener appStateListener){
        mAppStateListeners.remove(appStateListener);
    }

    private void notifyListeners(String key, String value){
        for (AppStateListener appStateListener : mAppStateListeners){
            appStateListener.onStateChanged(key, value);
        }
    }

    interface AppStateListener{
        void onStateChanged(String key, String value);
    }
}
