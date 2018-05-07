package cn.nawa.restful.service;

import cn.nawa.restful.bean.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    User selectById(int id);

    List<User> selectAllUser();

    int updateUser(User user);

    int deleteUser(int id);

}
