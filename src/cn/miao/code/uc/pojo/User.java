package cn.miao.code.uc.pojo;

import com.miao.util.base.pojo.Basic;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
public class User extends Basic implements Serializable {

    private int id;

    private String name;

    private int sex;

    @JsonIgnore
    private String password;

    @Override
    protected String getClazzType() {
        return "user";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
