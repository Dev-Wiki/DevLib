package net.devwiki.log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用于打印Json
 * Created by Asia on 2015/11/27 0027.
 */
class JsonLog {

    private static final String LEFT_BIG = "{";
    private static final String LEFT_MIDDLE = "{";

    private static int logLevel = DevLog.LogLevel.DEBUG;

    static void setLogLevel(int level) {
        JsonLog.logLevel = level;
    }

    static void print(String tag, String location, String content) {
        String msg;
        try {
            if (content.startsWith(LEFT_BIG)) {
                JSONObject jsonObject = new JSONObject(content);
                msg = jsonObject.toString(4);
            } else if (content.startsWith(LEFT_MIDDLE)) {
                JSONArray jsonArray = new JSONArray(content);
                msg = jsonArray.toString(4);
            } else {
                msg = content;
            }
        } catch (JSONException e) {
            msg = content;
        }
        BaseLog.print(logLevel, tag, DevLog.HEAD_LINE);
        BaseLog.print(logLevel, tag, DevLog.START_LINE + "\r" + location);
        String[] msgArray = msg.split("\\n");
        for (String str : msgArray) {
            BaseLog.print(logLevel, tag, DevLog.START_LINE + "\r" + str);
        }
        BaseLog.print(logLevel, tag, DevLog.FOOT_LINE);
    }

}
