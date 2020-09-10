package com.github.aiosign.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * bean转map工具类
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2019/12/29
 */
public abstract class BeanMapUtils {
    /**
     * <p>convertToMap.</p>
     *
     * @param obj a {@link java.lang.Object} object.
     * @return a {@link java.util.Map} object.
     */
    public static Map<String, String> convertToMap(Object obj) {
        return convertToMap(obj, false);
    }

    /**
     * <p>convertToMap.</p>
     *
     * @param obj         a {@link java.lang.Object} object.
     * @param convertNull a boolean.
     * @return a {@link java.util.Map} object.
     */
    public static Map<String, String> convertToMap(Object obj, boolean convertNull) {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = getAllField(obj);
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object o = field.get(obj);
                if (Objects.nonNull(o)) {
                    if (o instanceof Collection) {
                        if (0 == ((Collection) o).size()) {
                            continue;
                        }
                    } else if (o instanceof Map) {
                        if (0 == ((Map) o).size()) {
                            continue;
                        }
                    } else if (o instanceof Enum) {
                        o = ((Enum) o).name();
                    }
                    if (null != o) {
                        map.put(field.getName(), o.toString());
                    }
                } else if (convertNull) {
                    map.put(field.getName(), null);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取该类所有的属性，包括父类的（Object除外）
     *
     * @param obj
     * @return
     */
    private static Field[] getAllField(Object obj) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        do {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class);
        return fieldList.toArray(new Field[]{});
    }

}
