package com.example.demotddexample;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoTddExampleApplication.class)
@ActiveProfiles("test")
public class IntegrationTest {

    @Autowired
    ResultService resultService;

    @Autowired
    ResultRepository resultRepository;

    @Test
    public void trafficLight_isGreen() {
        Assertions.assertThat(resultService.getTraffiLight(2017))
                .isEqualTo(TrafficLight.GREEN);
    }

    @Test
    public void findByYear_fromJdbcTemplate() {
        Assertions.assertThat(resultRepository.findByYearJDdbcTemplate(2017)).isEqualTo(1);
    }

    @Test
    public void findAll_returnsData() {
        List<Race> all = resultRepository.findAll();
        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }
}