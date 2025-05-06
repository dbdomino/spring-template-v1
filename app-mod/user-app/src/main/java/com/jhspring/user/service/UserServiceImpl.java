package com.jhspring.user.service;

import com.jhspring.common.constants.ErrorCode;
import com.jhspring.common.dto.ApiResponse;
import com.jhspring.common.exception.CoException;
import com.jhspring.common.util.BcryptUtil;
import com.jhspring.data.entity.UserEntity;
import com.jhspring.data.mapper.UserEntityMapper;
import com.jhspring.data.repository.UserRepository;
import com.jhspring.user.dto.req.LoginReqDto;
import com.jhspring.user.dto.req.RegistUserReqDto;
import com.jhspring.user.dto.res.FindmeResDto;
import com.jhspring.user.dto.res.LoginResDto;
import com.jhspring.user.dto.res.RegistUserResDto;
import com.jhspring.user.mapper.UserDtoMapper;
import com.jhspring.user.service.inf.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BcryptUtil bcryptUtil;
    private final UserDtoMapper userDtoMapper;
    private final UserEntityMapper userEntityMapper;

    @Override
    public ApiResponse<RegistUserResDto> register(RegistUserReqDto dto){
        if (userRepository.existsById(dto.getId())) {
            throw new CoException(ErrorCode.DUPLICATE_ID_REGIST_FAIL);
        }
        UserEntity entity = userDtoMapper.toUserEntity(dto);
        entity.setPassword(bcryptUtil.encode(dto.getPassword()));
        entity.setCreateDate(LocalDateTime.now());
        entity.setUpdateDate(LocalDateTime.now());
        entity.setStatus(true);

        userRepository.save(entity);

        RegistUserResDto response = userDtoMapper.toRegisterDto(entity);
        response.setMessage("회원가입 완료");

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<LoginResDto> login(LoginReqDto dto, HttpSession session) {
        UserEntity entity = userRepository.findById(dto.getId())
                .orElseThrow(() -> new CoException(ErrorCode.USER_NOT_FOUND));

        if (!bcryptUtil.matches(dto.getPassword(), entity.getPassword())) {
            throw new CoException(ErrorCode.LOGIN_FAIL);
        }

        session.setAttribute("loginUser", entity.getId());

        LoginResDto response = userDtoMapper.toLoginDto(entity);
        response.setSuccess(true);

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<FindmeResDto> findMe(String userId) {
        UserEntity entity = userRepository.findById(userId)
                .orElseThrow(() -> new CoException(ErrorCode.USER_NOT_FOUND));

        FindmeResDto resDto = userDtoMapper.toFindmeDto(entity);

        return ApiResponse.success(resDto);
    }
}