package com.example.ljy.keeplive.asynctask;

import android.os.AsyncTask;

/**
 * params 执行子线程所需要传入的参数
 * Progress 进度指示所需要的类型
 * Result运行后的结果类型
 */
public class MyTask extends AsyncTask<Void,Integer,Void> {

    /**
     * 运行在子线程的方法
     * 运行在后台
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(Void... params) {
        //传递值
        publishProgress(1);
        return null;
    }

    /**
     * 运行在主线程，可以直接更新控件属性
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * 运行在主线程，当后台运行结束，自动调用。
     * 运行结束
     * @param aVoid
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
