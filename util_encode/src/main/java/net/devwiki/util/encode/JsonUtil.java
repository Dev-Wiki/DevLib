package net.devwiki.util.encode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * JSON工具类
 * @author DevWiki
 */
public class JsonUtil {

    private static Gson gson;

    static {
        gson = new GsonBuilder().create();
    }

    /**
     * 对象转为json串
     * @param object 对象
     * @return json串
     */
    public static String toJSON(Object object) {
        return gson.toJson(object);
    }

    /**
     * json串转为对象
     * @param json json字符串
     * @param classType 对象类型
     * @return 转换后的对象
     */
    public static <T> T fromJSON(String json, Class<T> classType) {
        return gson.fromJson(json, classType);
    }

    /**
     * json字符串转带泛型的对象
     * @param json 字符串
     * @param outType 外部类类型
     * @param innerType 泛型类型
     * @return 转换后对象
     */
    public static <T> T fromJSON(String json, Class<?> outType, Class<?>... innerType ) {
        Type type = TypeToken.getParameterized(outType, innerType).getType();
        return gson.fromJson(json, type);
    }

    /**
     * JSON转成集合
     * @param json json字符串
     * @param collectionType 集合类型
     * @param elementType 元素类型
     * @return 集合
     */
    public static <T> T toCollection(String json, Class<?> collectionType, Class<?> elementType) {
        Type type = TypeToken.getParameterized(collectionType, elementType).getType();
        return gson.fromJson(json, type);
    }
}
