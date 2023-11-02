package com.uijin.community.controller;

import static org.mockito.BDDMockito.given;

import com.uijin.community.service.CoinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureRestDocs
@WebMvcTest(TestController.class)
public class TestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CoinService coinService;

  @Test
  void signUpTest() throws Exception {
    // given
    given(coinService.signUp()).willReturn(new String("test"));

    // when
    mockMvc.perform(MockMvcRequestBuilders.get("/test/member"))
        .andDo(MockMvcResultHandlers.print())
        .andDo(MockMvcRestDocumentation.document("members/signUp",
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    // then
  }

}
