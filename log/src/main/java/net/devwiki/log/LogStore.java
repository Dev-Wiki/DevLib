package net.devwiki.log;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 * Created by DevWiki on 016/8/7.
 */
class LogStore {

    /**
     * 日志保存的默认位置
     */
    private static final String DEFAULT_SAVE_DIR = "DevLog";

    private static String storePath = getSDRootPath() + DEFAULT_SAVE_DIR;

    static void init(Context context, String dirPath) {
        if (!TextUtils.isEmpty(dirPath)) {
            File file = new File(dirPath);
            if (file.isDirectory() && file.canWrite() && file.canRead()) {
                storePath = dirPath;
            }
        }
    }

    static String getStorePath() {
        return storePath;
    }

    /**
     * SD卡是否挂载
     *
     * @return true:挂载
     */
    static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD根目录
     *
     * @return SD根目录且尾部带有"/"
     */
    static String getSDRootPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 文件是否存在
     *
     * @param path 文件路径
     * @return true:存在,false:不存在
     */
     static boolean isExists(String path) {
        if (!isSDMounted()) {
            DevLog.w("sdcard is not mount!");
            return false;
        }
        if (TextUtils.isEmpty(path)) {
            DevLog.w("path is invalid!");
            return false;
        }
        return new File(path).exists();
    }

    /**
     * 创建文件或文件夹
     *
     * @param path 路径
     * @return true:创建成功
     */
     static boolean createFile(String path) {
        if (!isSDMounted()) {
            DevLog.w("sdcard is not mount!");
            return false;
        }
        if (TextUtils.isEmpty(path)) {
            DevLog.w("path is invalid!");
            return false;
        }
        File file = new File(path);
        if (file.exists()) {
            DevLog.w("file is exists!");
            return false;
        }
        if (file.isDirectory()) {
            return file.mkdirs();
        } else {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}