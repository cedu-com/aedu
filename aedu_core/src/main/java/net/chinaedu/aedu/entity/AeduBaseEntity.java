package net.chinaedu.aedu.entity;

import java.io.Serializable;

/**
 * Created by qinyun on 2017/2/27.
 */

public class AeduBaseEntity<E> implements Serializable {
    private int code;
    private String msg;
    private E data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
