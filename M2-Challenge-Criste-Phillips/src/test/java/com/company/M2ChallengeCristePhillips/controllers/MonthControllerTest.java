package com.company.M2ChallengeCristePhillips.controllers;

import com.company.M2ChallengeCristePhillips.models.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)

public class MonthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    private Month outputMonth = new Month();

    // mock MVC test for successful response
    @Test
    public void getMonthByMonthNumber() throws Exception {

        Month outputMonth = new Month(1, "January");

        String outputJson = mapper.writeValueAsString(outputMonth);

        mockMvc.perform(
                        get("/month/1")

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // mock MVC test for input out of range
    @Test
    public void shouldReturnMonthInputIsOutOfRange() throws Exception {
        Month month = new Month(14, "February");

        mockMvc.perform(
                        get("/month/14"
                        )

                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }
}