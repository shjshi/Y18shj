package com.shj.y18shj;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * Created by jash
 * Date: 15-7-17
 * Time: 下午4:22
 */
public class DbHelper {
    public static DbUtils utils;
    public static void init(Context context){
		//一旦被初始化，数据库中就添加下面的数据
        utils = DbUtils.create(context);
        utils.configAllowTransaction(true);
        utils.configDebug(true);
        try {
            if (!utils.tableIsExist(EntryType.class)){
                int i =1;
                utils.save(new EntryType(i++, "老人健康"));
                utils.save(new EntryType(i++, "孩子健康"));
                utils.save(new EntryType(i++, "健康饮食"));
                utils.save(new EntryType(i++, "男性健康"));
                utils.save(new EntryType(i++, "女性保养"));
                utils.save(new EntryType(i++, "孕婴手册"));
                utils.save(new EntryType(i++, "私密生活"));
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static DbUtils getUtils() {
        return utils;
    }
}
