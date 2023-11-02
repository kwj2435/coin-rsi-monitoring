package com.uijin.community.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.uijin.community.entity.MemberInfoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MemberInfoRepositoryTest {

  @Autowired MemberInfoRepository memberInfoRepository;

  @Test
  void saveMemberTet() {
    MemberInfoEntity memberInfoEntity = MemberInfoEntity.builder()
        .memberId("test")
        .password("test")
        .nickName("test")
        .phoneNumber("01012341234")
        .email("dmlwls@naver.com")
        .grade("test")
        .status("123")
        .build();

    MemberInfoEntity save = memberInfoRepository.save(memberInfoEntity);

    Assertions.assertNotNull(save);
  }
}