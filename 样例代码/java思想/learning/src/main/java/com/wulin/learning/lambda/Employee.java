package com.wulin.learning.lambda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;
    private int salary;
    private String office;
}


class ExampleEmployee {

    private static List<Employee> employeeList = new ArrayList<>();

    static{
        employeeList.add(Employee.builder().name("Matt").salary(5000).office("New York").build());
        employeeList.add(Employee.builder().name("Steve").salary(6000).office("London").build());
        employeeList.add(Employee.builder().name("Carrie").salary(20000).office("New York").build());
        employeeList.add(Employee.builder().name("Peter").salary(7000).office("New York").build());
        employeeList.add(Employee.builder().name("Pat").salary(8000).office("London").build());
        employeeList.add(Employee.builder().name("Tammy").salary(29000).office("Shanghai").build());
    }

    public static void main(String[] args) {
        //anyMatch
        boolean isMatch = employeeList.stream().anyMatch(employee -> employee.getOffice().equals("London"));
        System.out.println(isMatch);

        //返回所有salary大于6000
        boolean matched = employeeList.stream().allMatch(employee -> employee.getSalary()>4000);
        System.out.println(matched);

        //找出工资最高
//        OptionalDemo<Employee> hightestSalary = employeeList.stream().max((e1, e2)->Integer.compare(e1.getSalary(),e2.getSalary()));
//        System.out.println(hightestSalary);

        //返回姓名列表
        List<String> names = employeeList.stream().map(employee -> employee.getName()).collect(Collectors.toList());
        System.out.println(names);

        //List转换成Map
        Map<String,Employee> employeeMap = employeeList.stream().collect(Collectors.toMap((key->key.getName()),(value->value)));
        employeeMap.forEach((key,value)-> System.out.println(key + "=" + value));

        //统计办公室是New York的个数
        long officeCount = employeeList.stream().filter(employee -> employee.getOffice().equals("Shanghai")).count();
        System.out.println(officeCount);

        //List转换为Set
        Set<String> officeSet = employeeList.stream().map(employee -> employee.getOffice()).distinct().collect(Collectors.toSet());
        System.out.println(officeSet);

        //查找办公室地点是New York的员工
        Optional<Employee> allMatchedEmployees = employeeList.stream().filter(employee -> employee.getOffice().equals("New York")).findAny();
        System.out.println(allMatchedEmployees);

        //按照工资的降序来列出员工信息
        List<Employee> sortEmployeeList =  employeeList.stream().sorted((e1,e2)->Integer.compare(e2.getSalary(),e1.getSalary())).collect(Collectors.toList());
        //按照名字的升序列出员工信息
        List<Employee> sortEmployeeByName = employeeList.stream().sorted((e1,e2)->e1.getName().compareTo(e2.getName())).collect(Collectors.toList());
        System.out.println(sortEmployeeList);
        System.out.println("按照名字的升序列出员工信息:" + sortEmployeeByName);

        //获取工资最高的前2条员工信息
        List<Employee> top2EmployeeList= employeeList.stream()
                .sorted((e1,e2)->Integer.compare(e2.getSalary(),e1.getSalary()))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(top2EmployeeList);

        //获取平均工资
        OptionalDouble averageSalary = employeeList.stream().mapToInt(employee->employee.getSalary()).average();
        System.out.println("平均工资:" + averageSalary);

        //查找New York
        OptionalDouble averageSalaryByOffice = employeeList.stream().filter(employee -> employee.getOffice()
                .equals("New York"))
                .mapToInt(employee->employee.getSalary())
                .average();
        System.out.println("New York办公室平均工资:" + averageSalaryByOffice);

    }
}