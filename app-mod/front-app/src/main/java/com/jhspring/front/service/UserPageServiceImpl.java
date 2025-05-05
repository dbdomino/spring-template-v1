package com.jhspring.front.service;

import com.jhspring.common.constants.BackendUri;
import com.jhspring.common.dto.ApiResponse;
import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.req.RegistUserReqDto;
import com.jhspring.front.dto.res.FindmeResDto;
import com.jhspring.front.dto.res.LoginResDto;
import com.jhspring.front.dto.res.RegistUserResDto;
import com.jhspring.front.service.itf.UserPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public abstract class UserPageServiceImpl implements UserPageService {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8081")
            .build();

    @Override
    public LoginResDto login(LoginReqDto reqDto, HttpSession session) {
        BackendUri uri = BackendUri.LOGIN;

        /* 단순 body만 받는 통신용, 세션 ID까지는 받을 수 없음.
        ApiResponse<LoginResDto> response = restClient.post()
                .uri(uri.getUri())
                .body(reqDto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {}); // 제네릭 응답 파싱
*/
        ResponseEntity<ApiResponse<LoginResDto>> response = restClient.post()
                .uri(uri.getUri())
                .body(reqDto)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        // JSESSIONID 추출
        List<String> cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        String jsessionId = extractJsessionId(cookies);
        session.setAttribute("userAppSessionId", jsessionId);

//        return response.getData(); // ApiResponse<LoginResDto> 기준
        return response.getBody().getData();
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

    @Override
    public FindmeResDto me(HttpSession session){
        BackendUri uri = BackendUri.ME;

        String jsessionId = (String) session.getAttribute("userAppSessionId");
        if (jsessionId == null) return null;

        ApiResponse<FindmeResDto> response = restClient.get()
                .uri(uri.getUri())
                .header("Cookie", "JSESSIONID=" + jsessionId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return response.getData();
    }



    private String extractJsessionId(List<String> cookies) {
        if (cookies == null) return null;
        for (String cookie : cookies) {
            if (cookie.startsWith("JSESSIONID")) {
                return cookie.split(";")[0].split("=")[1];
            }
        }
        return null;
    }
}
