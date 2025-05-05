package com.jhspring.front.service;

import com.jhspring.common.constants.BackendUri;
import com.jhspring.common.dto.ApiResponse;
import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.req.RegistUserReqDto;
import com.jhspring.front.dto.res.LoginResDto;
import com.jhspring.front.dto.res.RegistUserResDto;
import com.jhspring.front.service.itf.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
@RequiredArgsConstructor
public abstract class UserPageServiceImpl implements UserPageService {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8081")
            .build();

    @Override
    public LoginResDto login(LoginReqDto reqDto) {
        BackendUri uri = BackendUri.LOGIN;

        ApiResponse<LoginResDto> response = restClient.post()
                .uri(uri.getUri())
                .body(reqDto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {}); // 제네릭 응답 파싱

        return response.getData(); // ApiResponse<LoginResDto> 기준
    }

    @Override
    public RegistUserResDto register(RegistUserReqDto reqDto) {
        BackendUri uri = BackendUri.REGISTER;

        ApiResponse<RegistUserResDto> response = restClient.post()
                .uri(uri.getUri())
                .body(reqDto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return response.getData();
    }
}
