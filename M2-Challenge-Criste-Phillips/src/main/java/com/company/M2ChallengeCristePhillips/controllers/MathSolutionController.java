package com.company.M2ChallengeCristePhillips.controllers;

import com.company.M2ChallengeCristePhillips.models.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController

public class MathSolutionController {
    // Add:
                // request param that generate MathSolution
            //    {
            //        "operand1": 25,
            //            "operand2": 5
            //    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution addMathSolution(@RequestBody @Valid MathSolution mathAdd) {
        try {
            mathAdd.setAnswer(mathAdd.getOperand1() + mathAdd.getOperand2());
            mathAdd.setOperation("add");

            // Error: 422, if missing operand or if operands are not both numbers
        } catch (NullPointerException e) {
            throw new NullPointerException("Must use two operands.");
        }
        return mathAdd;
    }

    // Subtract:
    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution subtractMathSolution(@RequestBody @Valid MathSolution mathSubtract) {
        try {
            mathSubtract.setAnswer(mathSubtract.getOperand1() - mathSubtract.getOperand2());
            mathSubtract.setOperation("subtract");

            // Error: 422, if missing operand or if operands are not both numbers
        } catch (NullPointerException e) {
            throw new NullPointerException("Must use two operands.");
        }
        return mathSubtract;
    }

    // Multiply:
    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution multiplyMathSolution(@RequestBody @Valid MathSolution mathMultiply) {
        try {
            mathMultiply.setAnswer(mathMultiply.getOperand1() * mathMultiply.getOperand2());
            mathMultiply.setOperation("multiply");

            // Error: 422, if missing operand or if operands are not both numbers
        } catch (NullPointerException e) {
            throw new NullPointerException("Must use two operands.");
        }
        return mathMultiply;
    }

    // Divide:
    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution divideMathSolution(@RequestBody @Valid MathSolution mathDivide) {

        try {
            mathDivide.setAnswer(mathDivide.getOperand1() / mathDivide.getOperand2());
            mathDivide.setOperation("divide");

            // Error: 422, if operand2 is zero
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Don't divide by zero, hoser!");

            // Error: 422, if missing operand or if operands are not both numbers
        } catch (NullPointerException e) {
            throw new NullPointerException("Must use two operands.");
        }
        return mathDivide;
    }
}
