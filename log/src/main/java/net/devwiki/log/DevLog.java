package net.devwiki.log;

import android.content.Context;

/**
 * 日志类<br>
 * 若不调用{@link DevLog#init(Context, int, int, String)},则默认打印所有级别日志并不保存任何日志
 * @author DevWiki
 * Created by DevWiki on 2015/11/27 0027.
 */
public class DevLog {

    private static final String DEFAULT_SAVE_PATH = "";
    static final String HEAD_LINE = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    static final String START_LINE = "┃  ";
    static final String FOOT_LINE = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    /**
     * 日志级别
     */
    public interface LogLevel {
        /*** 日志级别:输出VERBOSE,DEBUG,INFO,WARN,ERROR级别日志 */
        int VERBOSE = 1;
        /*** 日志级别:输出DEBUG,INFO,WARN,ERROR级别日志 */
        int DEBUG = 2;
        /*** 日志级别:输出INFO,WARN,ERROR级别日志 */
        int INFO = 3;
        /*** 日志级别:输出WARN,ERROR级别日志 */
        int WARN = 4;
        /*** 日志级别:输出ERROR级别日志 */
        int ERROR = 5;
        /*** 日志级别:不输出日志 */
        int NONE = 6;
    }

    interface LogType {
        int BASE = 1;
        int JSON = 2;
    }
    /**
     * 输出日志的级别
     */
    private static int logLevel = LogLevel.VERBOSE;
    /**
     * 保存日志的级别
     */
    private static int saveLevel = LogLevel.NONE;
    /**
     * 保存文件的路径
     */
    private static String savePath = DEFAULT_SAVE_PATH;

    private DevLog() {
        //禁止实例化
    }

    /**
     * 初始化日志类
     *
     * @param context App的上下文
     * @param logLevel  日志在Logcat输出的级别,参见{@link DevLog#setLogLevel(int)}
     * @param saveLevel 保存到文件的日志级别,参见{@link DevLog#setSaveLevel(int)}
     * @param savePath  日志文件保存路径,设置为绝对路径,默认保存在{@link DevLog#DEFAULT_SAVE_PATH}
     */
    public static void init(Context context, int logLevel, int saveLevel, String savePath) {
        DevLog.logLevel = logLevel;
        DevLog.saveLevel = saveLevel;
        if (saveLevel != LogLevel.NONE) {
            LogStore.init(context, savePath);
        }
    }

    /**
     * 设置Logcat输出日志的级别
     *
     * @param logLevel 日志级别,可设置为:{@link LogLevel#VERBOSE}, {@link LogLevel#DEBUG}, {@link LogLevel#INFO},
     *                 {@link LogLevel#WARN},{@link LogLevel#ERROR}, {@link LogLevel#NONE}
     */
    public static void setLogLevel(int logLevel) {
        DevLog.logLevel = logLevel;
    }

    /**
     * 设置保存值文件的日志的级别
     *
     * @param saveLevel 日志级别,可设置为:{@link LogLevel#VERBOSE}, {@link LogLevel#DEBUG}, {@link LogLevel#INFO},
     *                  {@link LogLevel#WARN},{@link LogLevel#ERROR}, {@link LogLevel#NONE}
     */
    public static void setSaveLevel(int saveLevel) {
        DevLog.saveLevel = saveLevel;
    }

    /**
     * 设置日志文件保存路径
     *
     * @param savePath 文件路径
     */
    public static void setSavePath(String savePath) {
        DevLog.savePath = savePath;
    }

    /**
     * 设置输出JSON时的日志级别
     *
     * @param level 日志级别，默认为Debug级别
     */
    public static void setJsonLevel(int level) {
        JsonLog.setLogLevel(level);
    }


    /**
     * 打印Verbose级别的空白日志
     */
    public static void v() {
        if (logLevel <= LogLevel.VERBOSE) {
            String[] logInfo = getLogInfo(null, null);
            printLog(LogType.BASE, LogLevel.VERBOSE, logInfo);
        }
    }

    /**
     * 打印Verbose级别的日志
     *
     * @param msg 日志内容
     */
    public static void v(String msg) {
        if (logLevel <= LogLevel.VERBOSE) {
            String[] logInfo = getLogInfo(null, msg);
            printLog(LogType.BASE, LogLevel.VERBOSE, logInfo);
        }
    }

    /**
     * 打印Verbose级别的日志
     *
     * @param tag 打印时的TAG
     * @param msg 日志内容
     */
    public static void v(String tag, String msg) {
        if (logLevel <= LogLevel.VERBOSE) {
            String[] logInfo = getLogInfo(tag, msg);
            printLog(LogType.BASE, LogLevel.VERBOSE, logInfo);
        }
    }

    /**
     * 打印debug级别的空白日志
     */
    public static void d() {
        if (logLevel <= LogLevel.DEBUG) {
            String[] logInfo = getLogInfo(null, null);
            printLog(LogType.BASE, LogLevel.DEBUG, logInfo);
        }
    }

    /**
     * 打印Debug级别的日志
     *
     * @param msg 日志内容
     */
    public static void d(String msg) {
        if (logLevel <= LogLevel.DEBUG) {
            String[] logInfo = getLogInfo(null, msg);
            printLog(LogType.BASE, LogLevel.DEBUG, logInfo);
        }
    }

    /**
     * 打印Debug级别的日志
     *
     * @param tag 打印时的TAG
     * @param msg 日志内容
     */
    public static void d(String tag, String msg) {
        if (logLevel <= LogLevel.DEBUG) {
            String[] logInfo = getLogInfo(tag, msg);
            printLog(LogType.BASE, LogLevel.DEBUG, logInfo);
        }
    }

    /**
     * 打印 Info 级别的空白日志
     */
    public static void i() {
        if (logLevel <= LogLevel.INFO) {
            String[] logInfo = getLogInfo(null, null);
            printLog(LogType.BASE, LogLevel.INFO, logInfo);
        }
    }

    /**
     * 打印Info级别的日志
     *
     * @param msg 日志内容
     */
    public static void i(String msg) {
        if (logLevel <= LogLevel.INFO) {
            String[] logInfo = getLogInfo(null, msg);
            printLog(LogType.BASE, LogLevel.INFO, logInfo);
        }
    }

    /**
     * 打印Info级别的日志
     *
     * @param tag 打印时的TAG
     * @param msg 日志内容
     */
    public static void i(String tag, String msg) {
        if (logLevel <= LogLevel.INFO) {
            String[] logInfo = getLogInfo(tag, msg);
            printLog(LogType.BASE, LogLevel.INFO, logInfo);
        }
    }

    /**
     * 打印warn级别的空白日志
     */
    public static void w() {
        if (logLevel <= LogLevel.WARN) {
            String[] logInfo = getLogInfo(null, null);
            printLog(LogType.BASE, LogLevel.WARN, logInfo);
        }
    }

    /**
     * 打印Warn级别的日志
     *
     * @param msg 日志内容
     */
    public static void w(String msg) {
        if (logLevel <= LogLevel.WARN) {
            String[] logInfo = getLogInfo(null, msg);
            printLog(LogType.BASE, LogLevel.WARN, logInfo);
        }
    }

    /**
     * 打印Warn级别的日志
     *
     * @param tag 打印时的TAG
     * @param msg 日志内容
     */
    public static void w(String tag, String msg) {
        if (logLevel <= LogLevel.WARN) {
            String[] logInfo = getLogInfo(tag, msg);
            printLog(LogType.BASE, LogLevel.WARN, logInfo);
        }
    }

    /**
     * 打印Error级别的空白日志
     */
    public static void e() {
        if (logLevel <= LogLevel.ERROR) {
            String[] logInfo = getLogInfo(null, null);
            printLog(LogType.BASE, LogLevel.ERROR, logInfo);
        }
    }

    /**
     * 打印Error级别的日志
     *
     * @param msg 日志内容
     */
    public static void e(String msg) {
        if (logLevel <= LogLevel.ERROR) {
            String[] logInfo = getLogInfo(null, msg);
            printLog(LogType.BASE, LogLevel.ERROR, logInfo);
        }
    }

    /**
     * 打印Error级别的日志
     *
     * @param tag 打印时的TAG
     * @param msg 日志内容
     */
    public static void e(String tag, String msg) {
        if (logLevel <= LogLevel.ERROR) {
            String[] logInfo = getLogInfo(tag, msg);
            printLog(LogType.BASE, LogLevel.ERROR, logInfo);
        }
    }

    /**
     * 打印Verbose级别的日志
     *
     * @param e 异常
     */
    public static void e(Exception e) {
        if (logLevel <= LogLevel.ERROR) {
            String[] logInfo = getLogInfo(null, e.getMessage());
            printLog(LogType.BASE, LogLevel.ERROR, logInfo);
        }
    }

    /**
     * 打印Verbose级别的日志
     *
     * @param tag 打印时的TAG
     * @param e   异常
     */
    public static void e(String tag, Exception e) {
        if (logLevel <= LogLevel.ERROR) {
            String[] logInfo = getLogInfo(tag, e.getMessage());
            printLog(LogType.BASE, LogLevel.ERROR, logInfo);
        }
    }

    /**
     * 打印JSON内容日志
     *
     * @param json 日志内容
     */
    public static void json(String json) {
        if (logLevel <= LogLevel.ERROR) {
            String[] logInfo = getLogInfo(null, json);
            printLog(LogType.JSON, LogLevel.DEBUG, logInfo);
        }
    }

    /**
     * 打印JSON内容日志
     *
     * @param tag  打印时的TAG
     * @param json 日志内容
     */
    public static void json(String tag, String json) {
        if (logLevel <= LogLevel.ERROR) {
            String[] logInfo = getLogInfo(tag, json);
            printLog(LogType.JSON, LogLevel.DEBUG, logInfo);
        }
    }

    private static void printLog(int logType, int logLevel, String[] logInfo) {
        String tag = logInfo[0];
        String location = logInfo[1];
        String content = logInfo[2];
        if (logType == LogType.JSON) {
            JsonLog.print(tag, location, content);
        }
        if (logType == LogType.BASE) {
            BaseLog.print(logLevel, tag, location + content);
        }
    }

    private static String[] getLogInfo(String tag, String msg) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = elements[4];
        String fileName = targetElement.getFileName();
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();
        String location = "[ (" + fileName + ":" + lineNumber + ")#" + methodName + " ]";
        String[] logInfo = new String[3];
        logInfo[0] = tag == null ? fileName : tag;
        logInfo[1] = location;
        logInfo[2] = msg == null ? "" : msg;
        return logInfo;
    }
}
