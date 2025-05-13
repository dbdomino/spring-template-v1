package com.jhspring.front.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserPageServiceImpl implements UserPageService {

    private RestClient userClient = RestClient.builder()
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
        ResponseEntity<ApiResponse<LoginResDto>> response = userClient.post()
                .uri(uri.getUri())
//                .bodyValue(reqDto) //
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(reqDto)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
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
        System.out.println("[DEBUG] 호출 전 URI = " + uri.getUri());

        try {
            ApiResponse<RegistUserResDto> response = userClient.post()
                    .uri(uri.getUri())
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(reqDto)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            System.out.println("[DEBUG] 호출 후 URI = " + uri.getUri());
            return response.getData();
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            System.err.println("[ERROR] 회원가입 실패 응답: " + responseBody);

            // 에러 메시지만 추출해서 던지기 (선택적으로 JSON 파싱 가능)
            String errorMessage;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = objectMapper.readValue(responseBody, new TypeReference<>() {});
                errorMessage = (String) map.get("message");
            } catch (Exception jsonParseException) {
                errorMessage = "알 수 없는 오류가 발생했습니다.";
            }

            throw new RuntimeException(errorMessage);  // 컨트롤러에서 잡아서 사용자에게 전달
        }
    }

    @Override
    public FindmeResDto me(HttpSession session) {
        BackendUri uri = BackendUri.ME;

        String jsessionId = (String) session.getAttribute("userAppSessionId");
        if (jsessionId == null) return null;

        ApiResponse<FindmeResDto> response = userClient.get()
                .uri(uri.getUri())
                .header(HttpHeaders.CONTENT_TYPE, "application/json") // 여기 추가
                .header("Cookie", "JSESSIONID=" + jsessionId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return response.getData();
    }

    @Override
    public void logout(HttpSession session) {
        String jsessionId = (String) session.getAttribute("userAppSessionId");
        if (jsessionId == null) return;

        userClient.post()
                .uri(BackendUri.LOGOUT.getUri())
                .header(HttpHeaders.CONTENT_TYPE, "application/json") // 여기 추가
                .header("Cookie", "JSESSIONID=" + jsessionId)
                .retrieve()
                .toBodilessEntity(); // 응답 내용 무시

        // 프론트 세션도 정리
        session.invalidate();
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