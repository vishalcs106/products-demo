package com.demo.productsdemo.room;

/**
 * Created by Vishal on 13-05-2018.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = ProductEntity.class,  version = 1)
@TypeConverters({ProductSizeTypeConverter.class})
public abstract class AppDb extends RoomDatabase{

    public abstract ProductDao productDao();
}


