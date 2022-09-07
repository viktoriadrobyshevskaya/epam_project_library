package com.epam.library.project.button.author;

import com.epam.library.project.button.ButtonProvider;
import com.epam.library.project.button.ButtonStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AuthorButtonStrategyProvider implements ButtonProvider {

    private static final Map<String, ButtonStrategy> BUTTON_STRATEGY_MAP = new HashMap<>();

    static {
        BUTTON_STRATEGY_MAP.put("remove", new AuthorRemoveButtonStrategy());
        BUTTON_STRATEGY_MAP.put("edit", new AuthorEditButtonStrategy());
        BUTTON_STRATEGY_MAP.put("addAuthor", new AuthorAddButtonStrategy());
    }

    public ButtonStrategy getButtonStrategy(String buttonName) {
        ButtonStrategy buttonStrategy = BUTTON_STRATEGY_MAP.get(buttonName);
        if (buttonStrategy == null) {
            throw new RuntimeException(String.format("Can not recognize button [%s]", buttonName));
        }
        return BUTTON_STRATEGY_MAP.get(buttonName);
    }

    public static String getActiveButtonNameByRequest(HttpServletRequest request) {
        for (String name : BUTTON_STRATEGY_MAP.keySet()) {
            if (request.getParameterMap().containsKey(name)) {
                return request.getParameterMap().get(name)[0];
            }
        }
        throw new RuntimeException("Can not find any pressed buttons from the request");
    }


}
