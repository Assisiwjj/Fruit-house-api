package guoshe.demo.service;

import guoshe.demo.domain.User;

import java.util.List;

public interface UserService {
    List<User> selectAllUser();

    int update(User user);
}
