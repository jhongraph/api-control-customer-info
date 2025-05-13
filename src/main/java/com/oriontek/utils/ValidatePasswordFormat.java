package com.oriontek.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePasswordFormat {


    public static boolean validPassword(String clave) {
        String patron = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";

        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(clave);

        return matcher.matches();
    }


}
