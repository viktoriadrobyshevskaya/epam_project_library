package com.epam.library.project.button;

public interface ButtonProvider {

    ButtonStrategy getButtonStrategy(String buttonName);

}
