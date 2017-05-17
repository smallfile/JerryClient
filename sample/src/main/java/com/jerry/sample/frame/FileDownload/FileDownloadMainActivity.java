package com.jerry.sample.frame.FileDownload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jerry.sample.R;
import com.jerry.sample.frame.FileDownload.performance.PerformanceTestActivity;
import com.liulishuo.filedownloader.FileDownloadMonitor;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by Jacksgong on 12/17/15.
 */
public class FileDownloadMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_download_main);

        // 这是只是为了全局监控。如果你有需求需要全局监控（比如用于打点/统计）可以使用这个方式，如果没有类似需求就不需要
        // 如果你有这个需求，实现FileDownloadMonitor.IMonitor接口，也使用FileDownloadMonitor.setGlobalMonitor
        // 注册进去即可
        // You do not have to add below code to your project only if you need monitor the global
        // FileDownloader Engine for statistic or others
        // If you have such requirement, just implement FileDownloadMonitor.IMonitor, and register it
        // use FileDownloadDownloader.setGlobalMonitor the same as below code.
        FileDownloadMonitor.setGlobalMonitor(GlobalMonitor.getImpl());
    }

    public void onClickMultitask(final View view) {
        startActivity(new Intent(this, MultiTaskActivity.class));
    }

    public void onClickSingle(final View view) {
        startActivity(new Intent(this, SingleTaskActivity.class));
    }

    public void onClickHybridTest(final View view) {
        startActivity(new Intent(this, HybridActivity.class));
    }

    public void onClickTasksManager(final View view) {
        startActivity(new Intent(this, TasksManagerActivity.class));
    }

    public void onClickPerformance(final View view) {
        startActivity(new Intent(this, PerformanceTestActivity.class));
    }

    public void onClickNotification(final View view){
        startActivity(new Intent(this, NotificationEntranceActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unbind and stop service manually if idle
        FileDownloader.getImpl().unBindServiceIfIdle();

        FileDownloadMonitor.releaseGlobalMonitor();
    }
}
