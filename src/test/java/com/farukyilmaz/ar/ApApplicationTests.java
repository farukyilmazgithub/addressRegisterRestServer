package com.farukyilmaz.ar;

import com.farukyilmaz.ar.controllers.RestController;
import com.farukyilmaz.ar.models.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApApplicationTests {
    @Autowired
    private RestController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getAllAddress() {
        assertThat(controller.getAddress()).isNotNull();
    }

}
