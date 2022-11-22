package com.kakavi.kafka.model

import groovy.transform.builder.Builder

@Builder
class Student {
    String firstName
    String lastName
    int age
    BigDecimal payedAmount


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", payedAmount=" + payedAmount +
                '}';
    }
}
