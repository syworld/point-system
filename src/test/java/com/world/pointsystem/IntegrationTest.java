package com.world.pointsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


/**
 * SpringBootTest : 실제 구동되는 어플리케이션과 동일한 test를 위한 Application Context를 제공한다.
 * WebMvcTest : controller의 테스트에만 사용한다. MockMvc가 자동으로 설정되며 다른 계층의 빈들은 테스트 컨테이너에 올리지 않으므로 @MockBean과 함께 사용한다.
 * AutoConfigureMockMvc : @WebMvcTest가 아닌 @SpringBootTest 를 사용하는 경우, MockMvc 의존성을 제공한다.
 * Transactional: 클래스 내부의 테스트 메소드가 실행될때마다 데이터베이스를 롤백한다.
 * Disabled : 해당 어노테이션이 붙은 클래스 또는 테스트 메소드를 실행하지 않는다.
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public abstract class IntegrationTest {

  /**
   * MockMvc: 스프링 mvc의 통합테스트를 위한 라이브러리.
   * MockMvc.perform() : MockMvcRequestBuilders를 매개변수로 받아서 ResultActions를 반환
   * MockMvc.andDo() : MockMvc 요청 이후 행동을 지정
   * MockMvc.andExpect() : ResultActions 응답에 대한 테스트
   */
  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

}
