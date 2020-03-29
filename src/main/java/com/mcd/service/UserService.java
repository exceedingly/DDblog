package com.mcd.service;

import com.mcd.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Service
public interface UserService {
    int selUserByUser(User user);
}
