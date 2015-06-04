package cn.miao.code.uc.dao;

import cn.miao.code.uc.pojo.User;
import com.miao.util.base.dao.Dao;
import org.springframework.stereotype.Service;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
@Service
public interface UserService extends Dao<User> {

    public User findByUUID(String uuid);

    public User findById(int id);

    public int save(User user);
}
