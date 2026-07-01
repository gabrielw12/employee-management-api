package com.example.employeemenagementapi.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer
{
    Map<String,ClassEmployee> group = new HashMap<>();


    public void addClass(String className, double capacity)
    {
        ClassEmployee classToADD = new ClassEmployee(className, (int) capacity);
        group.put(className, classToADD);
    }
    public void removeClass(String className)
    {
        ClassEmployee classToREMOVE = group.get(className);
        if (classToREMOVE != null)
            group.remove(className);
        else
            System.out.println("Class " + className + " was not found");
    }

    public List<ClassEmployee> findEmpty()
    {
        List<ClassEmployee> emptyList = new ArrayList<>();
        for(ClassEmployee classEmployee : group.values())
        {
            List<Employee> cetf = classEmployee.getEmployees();
            if (cetf.isEmpty())
                emptyList.add(classEmployee);
        }
        return emptyList;
    }
    public void summary()
    {
        for(ClassEmployee classEmployee : group.values())
        {
            double procent= (double) classEmployee.getEmployees().size() / classEmployee.getRozmiar();
            procent = procent*100;

            System.out.println(classEmployee.getClassName()+ "   " + procent +"%" );


        }

    }


    //dodana metoda
    public void edytujGrupe(String staraNazwa, String nowaNazwa, int nowaPojemnosc) {

        if (!staraNazwa.equals(nowaNazwa) && group.containsKey(nowaNazwa)) {

            throw new IllegalArgumentException("Grupa o nazwie '" + nowaNazwa + "' już istnieje!");
        }


        ClassEmployee edytowanaGrupa = group.get(staraNazwa);

        if (edytowanaGrupa != null) {

            if (!staraNazwa.equals(nowaNazwa)) {
                removeClass(staraNazwa);
                edytowanaGrupa.setClassName(nowaNazwa);
                addClass(nowaNazwa, nowaPojemnosc);
            }

        }
    }


}
