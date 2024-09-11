package ru.dvornikov.MyFirstTestAppStringBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    private ArrayList<String> stringList = new ArrayList<>();
    private HashMap<Integer, String> stringMap = new HashMap<>();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "ivan", defaultValue = "Ivan") String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hello %s!\n", name));

        // Вызываем все методы и добавляем их результаты в строку
        sb.append(updateArrayList(""));
        sb.append(showArrayList());
        sb.append(updateHashMap(""));
        sb.append(showHashMap());
        sb.append(showAllLength());

        return sb.toString();
    }

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam(value = "s") String s) {
        if (stringList.isEmpty()) {
            stringList = new ArrayList<>();
        }
        stringList.add(s);
        return "Array updated: " + stringList + "\n";
    }

    @GetMapping("/show-array")
    public String showArrayList() {
        return "Elements in ArrayList: " + stringList + "\n";
    }

    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam(value = "s") String s) {
        // Если HashMap пустой, создаем новый
        if (stringMap.isEmpty()) {
            stringMap = new HashMap<>();
        }
        stringMap.put(stringMap.size() + 1, s);
        return "Map updated: " + stringMap + "\n";
    }

    @GetMapping("/show-map")
    public String showHashMap() {
        return "Elements in HashMap: " + stringMap + "\n";
    }

    @GetMapping("/show-all-length")
    public String showAllLength() {
        return "ArrayList size: " + stringList.size() + ", HashMap size: " + stringMap.size() + "\n";
    }
}