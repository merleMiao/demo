package cn.miao.ztree.dao;

import com.miao.util.base.dao.Dao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Created by Miaosk
 * @date Created on 2015/8/5.
 */
@Service
public interface ZtreeService extends Dao {
    public List<Map> listByPid(int pid);
}
