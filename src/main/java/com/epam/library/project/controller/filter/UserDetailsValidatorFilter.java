package com.epam.library.project.controller.filter;

import com.epam.library.project.entity.UserDetails;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = {"/addUserDetails", "/editUserDetails"})
public class UserDetailsValidatorFilter implements Filter {

    private static final String PHONE_MASK_REGEX = "^(\\+375)\\(\\d[44|29|33]\\){1}(\\d{3})(-)?(\\d{2})(-)?(\\d{2})$";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String userDetailsPhoneInput = request.getParameter("user_phone");
        if (validatePhoneMask(userDetailsPhoneInput)) {
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute("modifiedUserDetails", fetchModifiedUserDetails(request));
            request.setAttribute("error", "Неправильно задан формат номера, пожалуйста, введите корректный номер!");
            request.getRequestDispatcher("/userDetailsOperation").forward(request, response);
        }
    }

    private static boolean validatePhoneMask(String phone) {
        Pattern pattern = Pattern.compile(PHONE_MASK_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private static UserDetails fetchModifiedUserDetails(ServletRequest request) {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String newName = request.getParameter("user_name");
        String newSurname = request.getParameter("user_surname");
        String newPhone = request.getParameter("user_phone");
        String newAddress = request.getParameter("user_address");
        return new UserDetails(user_id, newName, newSurname, newPhone, newAddress);
    }

}
