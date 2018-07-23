package csvtest.service.impl;

import csvtest.model.Employee;
import csvtest.repository.EmployeeRepository;
import csvtest.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyServiceImpl implements MyService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Map<String, String> formStatus() {

        ArrayList<Employee> list = (ArrayList<Employee>) employeeRepository.findAll();

        Set<String> setUsers = new HashSet<>();
        Map<String, String> result = new HashMap<>();

        /**
         * Лист уникальных значений userov
         */
        list.forEach(e -> {
            if(!e.getSsoid().equals("")){
                setUsers.add(e.getSsoid());
            }
        });

        for (String user: setUsers
                ) {
            String formsStr = "";
            for (Employee empl: list
                    ) {
                if ((user.equals(empl.getSsoid()))&&(!empl.getSubtype().equals(""))){
                    if (!formsStr.contains(empl.getSubtype())){
                        formsStr+=(empl.getSubtype() + "; ");
                    }
                }
            }
            result.put(user, formsStr);
        }
        return result;

    }


    @Override
    public Map<String, String> userAndForm() {
        /**
         * Берем все записи с базы
         */
        ArrayList<Employee> list = (ArrayList<Employee>) employeeRepository.findAll();

        Set<String> setUsers = new HashSet<>();
        Map<String, String> result = new HashMap<>();

        /**
         * Лист уникальных значений userov
         */
        list.forEach(e -> {
            if(!e.getSsoid().equals("")){
                setUsers.add(e.getSsoid());
            }
        });

        for (String user: setUsers
                ) {
            String formsStr = "";
            for (Employee empl: list
                    ) {
                if ((user.equals(empl.getSsoid()))&&(!empl.getFormid().equals(""))){
                    if (!formsStr.contains(empl.getFormid())){
                        formsStr+=(empl.getFormid() + "; ");
                    }
                }
            }
            result.put(user, formsStr);
        }
        return result;
    }

    public Map<String, Integer> topFive(){

        /**
         * Берем все записи с базы
         */
        ArrayList<Employee> list = (ArrayList<Employee>) employeeRepository.findAll();

        Set<String> setFormID = new HashSet<>();
        Map<String, Integer> entryCount = new HashMap<>();

        /**
         * Лист уникальных значений форм
         */
        list.forEach(e -> {
            if(!e.getFormid().equals("")){
                setFormID.add(e.getFormid());
            }
        });

        /**
         * Подсчет по всем формам
         */
        for (String formIdName: setFormID
                ) {
            int sum = 0;
            for (Employee e: list
                    ) {
                if (e.getFormid().equals(formIdName)){
                    sum++;
                }
            }
            entryCount.put(formIdName, sum);
        }

        /**
         * Сортирую, добавляю в лист топ 5
         */
        Map<String,Integer> topFiveList =
                entryCount.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(5)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<String, Integer> e: topFiveList.entrySet()
                ) {
            System.out.println(e.getValue() + " -- " + e.getKey());
        }

        return topFiveList;

    }

}
