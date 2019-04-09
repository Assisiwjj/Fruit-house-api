package guoshe.demo.service.impl;

import guoshe.demo.dao.UserMapper;
import guoshe.demo.domain.User;
import guoshe.demo.domain.UserExample;
import guoshe.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllUser(){
        try{
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            List<User> list = userMapper.selectByExample(example);
            if (list.size()!=0){
                return list;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int update(User user){
        try {
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andPkIdEqualTo(user.getPkId());
            return userMapper.updateByExampleSelective(user,example);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
