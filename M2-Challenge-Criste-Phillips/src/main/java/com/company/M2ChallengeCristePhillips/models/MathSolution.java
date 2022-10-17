package com.company.M2ChallengeCristePhillips.models;

import java.util.Objects;

public class MathSolution {
    private Integer operand1;
    private Integer operand2;
    private String operation;
    private Integer answer;

    public MathSolution() {
    }

    public MathSolution(Integer operand1, Integer operand2, String operation, Integer answer) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.answer = answer;
    }

    public Integer getOperand1() {
        return operand1;
    }

    public void setOperand1(Integer operand1) {
        this.operand1 = operand1;
    }

    public Integer getOperand2() {
        return operand2;
    }

    public void setOperand2(Integer operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathSolution)) return false;
        MathSolution that = (MathSolution) o;
        return Objects.equals(getOperand1(), that.getOperand1()) && Objects.equals(getOperand2(), that.getOperand2()) && Objects.equals(getOperation(), that.getOperation()) && Objects.equals(getAnswer(), that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperand1(), getOperand2(), getOperation(), getAnswer());
    }

    @Override
    public String toString() {
        return "MathSolution{" +
                "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", operation='" + operation + '\'' +
                ", answer=" + answer +
                '}';
    }
}
