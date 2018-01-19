package ua.training.model.service.strategy;

import ua.training.model.entity.Role;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {
    private static Map<Role, Strategy> strategyMap = initStrategies();

    public static Strategy getStrategy(Role role) {
        return strategyMap.get(role);
    }

    private static Map<Role, Strategy> initStrategies() {
        Map<Role, Strategy> result = new HashMap<>();
        result.put(Role.USER, new UserStrategy());
        result.put(Role.ADMIN, new AdminStrategy());
        return result;
    }
}
