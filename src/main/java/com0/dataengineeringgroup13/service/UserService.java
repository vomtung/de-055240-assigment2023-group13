package com0.dataengineeringgroup13.service;

import com0.dataengineeringgroup13.dto.PaperDetailDto;
import com0.dataengineeringgroup13.dto.UserDetailDto;

import java.sql.SQLException;

public interface UserService {

    void updateUser(UserDetailDto userDetailDto) throws SQLException;
}
