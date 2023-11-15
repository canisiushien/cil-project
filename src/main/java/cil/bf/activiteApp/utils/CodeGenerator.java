package cil.bf.activiteApp.utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CodeGenerator {
    public static String generateDemandeCode(String act) {
        String day = getDay();
        String code = act + "-" + day + "-";
        code = code + RandomStringUtils.randomAlphanumeric(25 - code.length());
        return code.toUpperCase();
    }

    public static String generateNumeroCODIR(String act) {
        String code = "RCODIR/" + String.valueOf(LocalDate.now().getYear())+"-" + act;
        return code.toUpperCase();
    }

    public static String generateNumeroCAB(String act) {
        String code = "RCAB/" + String.valueOf(LocalDate.now().getYear())+"-" + act;

        return code.toUpperCase();
    }
    public static String generateNumeroDIR(String act) {
        String code = "RDIR/" + String.valueOf(LocalDate.now().getYear())+"-" + act;
        return code.toUpperCase();
    }

    public static String generateNumeroCF(String act) {
        String code = "RCF/" + String.valueOf(LocalDate.now().getYear())+"-" + act;
        return code.toUpperCase();
    }

    public static String generateNumeroAG(String act) {
        String code = "AG/" + String.valueOf(LocalDate.now().getYear())+"-" + act;
        return code.toUpperCase();
    }

    public static String generateNumeroSO(String act) {
        String code = "SO/" + String.valueOf(LocalDate.now().getYear())+"-" + act;
        return code.toUpperCase();
    }
    public static String generateNumeroSE(String act) {
        String code = "SE/" + String.valueOf(LocalDate.now().getYear())+"-" + act;
        return code.toUpperCase();
    }


    public static String getDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(new Date());
    }
}
