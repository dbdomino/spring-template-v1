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
        UserEntity entity = UserEntity.builder()
                .id(dto.getId())
                .password(bcryptUtil.encode(dto.getPassword()))  // 비밀번호 암호화 필요 시 encode 처리
                .name(dto.getName())
                .email(dto.getEmail())
                .build();  // 나머지는 기본값이 적용됨

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