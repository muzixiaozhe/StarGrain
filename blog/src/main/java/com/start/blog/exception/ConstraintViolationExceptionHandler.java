package com.start.blog.exception;



import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * ConstraintViolationException处理器
 */
public class ConstraintViolationExceptionHandler  {

    public static String getMessages(ConstraintViolationException exception){
        List<String> list=new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            list.add(constraintViolation.getMessage());
        }



        String message = StringUtils.join(list, ";");
        return message;
    }
}
