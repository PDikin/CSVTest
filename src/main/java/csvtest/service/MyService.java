package csvtest.service;

import java.util.Map;

public interface MyService {
    Map<String, String> formStatus();
    Map<String, String> userAndForm();
    Map<String, Integer> topFive();
}
