package com.wulin.learning.自定义注解.分布式锁;

/**
 * @author 武林
 * @date 2021/1/5 17:04
 */
public enum RedisLockTypeEnum {

    /**
     * 自定义 key 前缀
     */
    ONE("Business1", "Test1"),

    TWO("Business2", "Test2");

    private String code;

    private String desc;

    RedisLockTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getUniqueKey(String key) {
        return String.format("%s:%s", this.getCode(), key);
    }
}
