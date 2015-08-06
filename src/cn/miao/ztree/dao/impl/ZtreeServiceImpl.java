package cn.miao.ztree.dao.impl;

import cn.miao.ztree.dao.ZtreeService;
import com.miao.util.base.dao.impl.DaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Created by Miaosk
 * @date Created on 2015/8/5.
 */
@Service
public class ZtreeServiceImpl extends DaoImpl implements ZtreeService{
    @Override
    public List<Map> listByPid(int pid){
        return this.sqlSession.selectList(this.getIface() + ".listByPid", pid);
    }
}
