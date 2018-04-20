package net.devwiki.log;

import android.util.Log;

/**
 * print normal log
 * Created by DevWiki on 2015/11/27 0027.
 */
class BaseLog {

    /**
     * print normal log
     * @param level log level
     * @param tag the tag of log
     * @param msg the content of log
     */
    static void print(int level, String tag, String msg){
        switch (level){
            case DevLog.LogLevel.VERBOSE:
                Log.v(tag, msg);
                break;
            case DevLog.LogLevel.DEBUG:
                Log.d(tag, msg);
                break;
            case DevLog.LogLevel.INFO:
                Log.i(tag, msg);
                break;
            case DevLog.LogLevel.WARN:
                Log.w(tag, msg);
                break;
            case DevLog.LogLevel.ERROR:
                Log.e(tag, msg);
                break;
            default:
                break;
        }
    }
}
