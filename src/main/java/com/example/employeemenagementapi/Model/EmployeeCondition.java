package com.example.employeemenagementapi.Model;;

public enum EmployeeCondition
{

        PRESENT("present"),
        SICK("sick"),
        DELEGATION("delegation"),
        VACATION("vacation");
        private final String value;
        EmployeeCondition(String value)
        {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

}
