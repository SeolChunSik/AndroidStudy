package android.study.chunsik.androidstudy.study.thrad_example;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 2015-03-30.
 * 한 스레드만 같은 데이터에 쓸 수 있게 함과 동시에 여러 스레드에서의 읽기 전용 접근을 허용하는
 * ReentrantReadWriteLock예제
 */
public class ReadWriteLockDemo {
    private final ReentrantReadWriteLock mLock;
    private String mName;
    private int mAge;
    private String mAddress;

    public ReadWriteLockDemo(ReentrantReadWriteLock mLock) {
        this.mLock = new ReentrantReadWriteLock();
    }

    public void setPersonData(String name, int age, String address){
        ReentrantReadWriteLock.WriteLock writeLock = mLock.writeLock();
        try {
            writeLock.lock();
            mName = name;
            mAge = age;
            mAddress = address;
        }finally {
            writeLock.unlock();
        }
    }

    public String getName(){
        ReentrantReadWriteLock.ReadLock readLock = mLock.readLock();
        try {
            readLock.lock();
            return mName;
        }finally {
            readLock.unlock();
        }
    }

    //mAge 및 mAddress에 대해 반복
}
