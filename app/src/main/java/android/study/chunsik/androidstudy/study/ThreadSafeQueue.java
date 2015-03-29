package android.study.chunsik.androidstudy.study;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by admin on 2015-03-30.
 * 큐, 동기화, 락 (소비자/생산자 패턴)
 *  java.util.concurrent패키지에는 동시성 맵 클래스뿐 아니라 다양한 대체 큐 및 클래스가 들어있음 참고
 *
 * 사용예제 구현 예정
 */
public class ThreadSafeQueue {
    private LinkedList<String> mList = new LinkedList<>();
    private final Object mLock = new Object();

    public void offer(String value){
        synchronized (mLock){
            mList.offer(value);
            mLock.notifyAll();
        }
    }

    public synchronized String poll(){
        synchronized (mLock){
            while (mList.isEmpty()){
                try {
                    mLock.wait();
                }catch (InterruptedException e){

                }
            }
            return mList.poll();
        }
    }

    //위에 구현한 패턴을 대신해서 사용할 코드
    LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
}
