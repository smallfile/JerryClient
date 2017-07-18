package com.jerry.sample.frame;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jerry.sample.R;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxAndroidActivity extends Activity {

    private Button btn1;
    private Button btn2;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid);

        btn1 = (Button)findViewById(R.id.btn1);
        View.OnClickListener btn1Listener = new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onRunSchedulerExampleButtonClicked();

            }
        };
        btn1.setOnClickListener(btn1Listener);

        btn2 = (Button)findViewById(R.id.btn2);
        View.OnClickListener btn2Listener = new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

            }
        };
        btn2.setOnClickListener(btn2Listener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }


    private void onRunSchedulerExampleButtonClicked() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onComplete() {
                        System.out.println("onComplete()");
                    }

                    @Override public void onError(Throwable e) {
                        System.out.println("onError()");
                    }

                    @Override public void onNext(String string) {
                        System.out.println("onNext(" + string + ")");
                    }
                }));
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
                SystemClock.sleep(5000);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

}
