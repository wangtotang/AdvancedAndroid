package com.tang.annotation_reflect;

import android.app.Activity;
import android.content.Intent;

import java.lang.reflect.Field;

/**
 * @author tanghongtu
 * @date 2021/5/22
 */
public class IntentInject {

    public static void inject(Activity activity) {
        Intent intent = activity.getIntent();

        Field[] declaredFields = activity.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if(annotation != null){
                field.setAccessible(true);
                try {
                    Class<?> type = field.getType();
                    if(type == int.class){
                        field.set(activity, intent.getIntExtra(field.getName(), 0));
                    }else if(type == String.class){
                        field.set(activity, intent.getStringExtra(field.getName()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
