package com.jhspring.service;

import com.jhspring.entity.User;
import com.jhspring.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserGeneratorService {
    private final UserInsertService userInsertService;
    private final UserRepository userRepository;
    private static final int TOTAL = 1_000_000;
    private static final int BATCH_SIZE = 10_000;
    private final Random random = new Random();

    @PostConstruct // 앱 시작 시 자동 실행
    public void init() {
        System.out.println("🚀 유저 데이터 100만 건 생성 시작");

        int batchSize = 10_000;
        int total = 1_000_000;

//        for (int i = 0; i < total; i += batchSize) {
//            List<User> userList  = new ArrayList<>(batchSize);
//            for (int j = 0; j < BATCH_SIZE; j++) {
//                userList.add(randomUser());
//            }
//            userRepository.saveAll(userList);
//        }

        // 훨씬 오래걸리겠지.
        for (int i = 0; i < total; i += 1) {
            User user = randomUser();
            userInsertService.insert(user);  // 👉 한 건마다 별도 트랜잭션
            log.info("{} ~ {} 저장 완료.", i+1, i + batchSize);
        }

        System.out.println("🎉 유저 생성 완료!");
    }

    private User randomUser() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String username = "user_" + uuid;
        String email = username + "@example.com";
        String password = UUID.randomUUID().toString().substring(0, 12);
        String phone = "010-" + (1000 + random.nextInt(9000)) + "-" + (1000 + random.nextInt(9000));
        String address = "서울시 강남구 " + (random.nextInt(100) + 1) + "번지";
        LocalDate birthday = LocalDate.of(1980 + random.nextInt(30), 1 + random.nextInt(12), 1 + random.nextInt(28));
        String gender = random.nextBoolean() ? "M" : "F";
        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setBirthday(birthday);
        user.setGender(gender);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        return user;
    }
}
