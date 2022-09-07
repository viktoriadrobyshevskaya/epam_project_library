package com.epam.library.project.button;

import com.epam.library.project.button.author.AuthorButtonStrategyProvider;
import com.epam.library.project.button.book.BookButtonStrategyProvider;

import java.util.EnumMap;

public class CommonButtonStrategyHandler {

    private static CommonButtonStrategyHandler commonButtonStrategyHandler;

    private CommonButtonStrategyHandler() {
    }

    public static CommonButtonStrategyHandler getInstance() {
        return (commonButtonStrategyHandler != null) ? commonButtonStrategyHandler : new CommonButtonStrategyHandler();
    }


    private final static EnumMap<ButtonType, ButtonProvider> BUTTON_PROVIDER_MAP = new EnumMap<>(ButtonType.class);

    static {
        BUTTON_PROVIDER_MAP.put(ButtonType.AUTHOR, new AuthorButtonStrategyProvider());
        BUTTON_PROVIDER_MAP.put(ButtonType.BOOK, new BookButtonStrategyProvider());
    }


    public ButtonProvider getButtonProvider(ButtonType buttonType) {
        return BUTTON_PROVIDER_MAP.get(buttonType);
    }


}
