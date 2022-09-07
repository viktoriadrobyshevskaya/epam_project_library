package com.epam.library.project.button.book;

import com.epam.library.project.button.ButtonProvider;
import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.button.author.AuthorAddButtonStrategy;
import com.epam.library.project.button.author.AuthorEditButtonStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BookButtonStrategyProvider implements ButtonProvider {


    private static final Map<String, ButtonStrategy> BUTTON_STRATEGY_MAP = new HashMap<>();

    static {
        BUTTON_STRATEGY_MAP.put("remove", new BookRemoveButtonStrategy());
        BUTTON_STRATEGY_MAP.put("edit", new BookEditButtonStrategy());
        BUTTON_STRATEGY_MAP.put("addBook", new BookAddButtonStrategy());
        BUTTON_STRATEGY_MAP.put("search", new BookSearchButtonStrategy());
        BUTTON_STRATEGY_MAP.put("request", new BookRequestButtonStrategy());
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
                return name;
            }
        }
        throw new RuntimeException("Can not find any pressed buttons from the request");
    }



}
