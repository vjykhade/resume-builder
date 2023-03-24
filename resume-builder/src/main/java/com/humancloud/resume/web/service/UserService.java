package com.humancloud.resume.web.service;


import com.humancloud.resume.web.dto.UserDTO;
import com.humancloud.resume.web.utils.BaseResponseDTO;

public interface UserService {
    BaseResponseDTO registerAccount(UserDTO userDTO);


}
