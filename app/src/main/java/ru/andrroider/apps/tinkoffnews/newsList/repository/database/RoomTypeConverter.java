package ru.andrroider.apps.tinkoffnews.newsList.repository.database;

import android.arch.persistence.room.TypeConverter;

/**
 * Created by Jackson on 22/11/2017.
 */

public class RoomTypeConverter {

    //сохранять CharSequence не получается(потому что походу это SpannedString и все спаны теряются
    // при toString()), но зато я хотя бы попробовал конвертеры
    @TypeConverter
    public static String ToString(CharSequence value) {
        return value != null ? value.toString() : null;
    }

    @TypeConverter
    public static CharSequence ToCharSequence(String value) {
        return value;
    }
}
