package cn.itsite.abase.common;


/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    private static final String TAG = Params.class.getSimpleName();
    public int pageSize = 20;
    public int page = 1;

    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        return params;
    }

}