package cn.miao.code.uc.dao.impl;

import cn.miao.code.uc.dao.UserService;
import cn.miao.code.uc.pojo.User;
import com.miao.util.base.dao.impl.DaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
@Service
@Transactional
public class UserServiceImpl extends DaoImpl<User> implements UserService{

    @Autowired
    SqlSession sqlSession;

    public User findByUUID(String uuid){
        return sqlSession.selectOne(this.getIface() + ".findByUUID", uuid);
    }

    public int save(User user){
        return sqlSession.insert(this.getIface() + ".save", user);
    }

    public User findById(int id){
        return sqlSession.selectOne(this.getIface() + ".findById", id);
    }


}
