package com.goodfood.api;

import com.goodfood.api.dao.EmployeesDAO;
import com.goodfood.api.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class GoodFoodApiApplication implements CommandLineRunner
{
    @Autowired
    EmployeesDAO employeesDAO;

    public static void main(String[] args)
    {
        SpringApplication.run(GoodFoodApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        employeesDAO.save(new Employees((int) UUID.randomUUID().getMostSignificantBits(), (int) UUID.randomUUID().getMostSignificantBits(), (int) UUID.randomUUID().getMostSignificantBits(), (int) UUID.randomUUID().getMostSignificantBits(), (int) UUID.randomUUID().getMostSignificantBits(),
                "gaetan","taltavull","t","gaetan.taltavull@gmail.com","1",5));

        for (Employees employees: employeesDAO.findAll())
        {
            System.out.println(employees.getFirstname());
        }
    }
}
