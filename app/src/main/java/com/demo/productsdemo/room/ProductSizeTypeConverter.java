package com.demo.productsdemo.room;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vishal on 13-05-2018.
 */

public class ProductSizeTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<ProductEntity.Size> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ProductEntity.Size>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<ProductEntity.Size> someObjects) {
        return gson.toJson(someObjects);
    }
}
