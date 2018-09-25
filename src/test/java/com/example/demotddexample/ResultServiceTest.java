package com.example.demotddexample;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {

    private static final int YEAR = 2017;

    @Mock
    ResultRepository mockResultRepository;
    ResultService mockResultService;

    @Before
    public void init() {
        mockResultService = new ResultService(mockResultRepository);
    }

    @Test
    public void getTrafficLight_returnsGreen() {
        // stub:
        ResultService stubResultService = new ResultService(year -> Arrays.asList(1, 2, 3));
        TrafficLight tl = stubResultService.getTraffiLight(YEAR);
        assertThat(tl).isEqualTo(TrafficLight.GREEN);

        // mock
        when(mockResultRepository.findByYear(YEAR))
                .thenReturn(Arrays.asList(1, 2, 3));

        TrafficLight tl2 = mockResultService.getTraffiLight(YEAR);
        assertThat(tl).isEqualTo(TrafficLight.GREEN);
    }

    @Test
    public void getTrafficLight_doesNotReturnGreen() {
        when(mockResultRepository.findByYear(YEAR)).thenReturn(Arrays.asList(2, 4, 4));
        TrafficLight tl = mockResultService.getTraffiLight(YEAR);
        assertThat(tl).isNotEqualTo(TrafficLight.GREEN);
    }

    @Test
    public void getTrafficLight_returnsOrange() {
        // mock
        when(mockResultRepository.findByYear(YEAR))
                .thenReturn(Collections.singletonList(2));

        TrafficLight tl = mockResultService.getTraffiLight(YEAR);
        assertThat(tl).isEqualTo(TrafficLight.ORANGE);
    }
}