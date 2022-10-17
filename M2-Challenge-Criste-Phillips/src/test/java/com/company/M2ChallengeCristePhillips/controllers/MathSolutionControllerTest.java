package com.company.M2ChallengeCristePhillips.controllers;

import com.company.M2ChallengeCristePhillips.models.MathSolution;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MathSolutionController.class)
public class MathSolutionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    private MathSolution inputMathSolution = new MathSolution();
    private MathSolution outputMathSolution = new MathSolution();


    @Test
    public void addMathSolution() throws Exception {
        inputMathSolution.setOperand1(10);
        inputMathSolution.setOperand2(20);


        String inputJson = mapper.writeValueAsString(inputMathSolution);

        outputMathSolution.setOperand1(10);
        outputMathSolution.setOperand2(20);
        outputMathSolution.setOperation("add");
        outputMathSolution.setAnswer(30);

        String outputJson = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void addMathSolutionReturnInvalidResponse() throws Exception {
        MathSolution inputOperands = new MathSolution();
        inputOperands.setOperand1(1);

        String inputJson = mapper.writeValueAsString(inputOperands);

        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

 @Test
    public void subtractMathSolution() throws Exception {
        inputMathSolution.setOperand1(10);
        inputMathSolution.setOperand2(5);

        String inputJson = mapper.writeValueAsString(inputMathSolution);

        outputMathSolution.setOperand1(10);
        outputMathSolution.setOperand2(5);
        outputMathSolution.setOperation("subtract");
        outputMathSolution.setAnswer(5);

        String outputJson = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void subtractMathSolutionReturnInvalidResponse() throws Exception {
        MathSolution inputOperands = new MathSolution();
        inputOperands.setOperand1(1);

        String inputJson = mapper.writeValueAsString(inputOperands);

        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void multiplyMathSolution() throws Exception {
        inputMathSolution.setOperand1(5);
        inputMathSolution.setOperand2(5);

        String inputJson = mapper.writeValueAsString(inputMathSolution);

        outputMathSolution.setOperand1(5);
        outputMathSolution.setOperand2(5);
        outputMathSolution.setOperation("multiply");
        outputMathSolution.setAnswer(25);

        String outputJson = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void multiplyMathSolutionReturnInvalidResponse() throws Exception {
        MathSolution inputOperands = new MathSolution();
        inputOperands.setOperand1(1);

        String inputJson = mapper.writeValueAsString(inputOperands);

        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void divideMathSolution() throws Exception {
        inputMathSolution.setOperand1(10);
        inputMathSolution.setOperand2(5);

        String inputJson = mapper.writeValueAsString(inputMathSolution);

        outputMathSolution.setOperand1(10);
        outputMathSolution.setOperand2(5);
        outputMathSolution.setOperation("divide");
        outputMathSolution.setAnswer(2);

        String outputJson = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void divideMathSolutionReturnInvalidResponse() throws Exception {
        MathSolution inputOperands = new MathSolution();
        inputOperands.setOperand1(1);

        String inputJson = mapper.writeValueAsString(inputOperands);

        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}