package ru.mts.educationproject.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.annotations.Logging;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ENTRY_SYMBOL = ">>";
    private static final String EXIT_SYMBOL = "<<";

    @Around("execution(* *(..)) && @annotation(logging)")
    public Object logMethod(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        return log(joinPoint, logging);
    }

    private Object log(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Object[] args = joinPoint.getArgs();

        String message = logging.value().isEmpty() ? methodName : logging.value();

        if (logging.enter()) {
            logLevel(logging.level(), ENTRY_SYMBOL
                    + " Enter "
                    + methodName + ": "
                    + message);
        }

        if (logging.logParams()) {
            logLevel(logging.level(), ENTRY_SYMBOL
                    + " " + methodName
                    + " parameters: "
                    + objectMapper.writeValueAsString(args));
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            logLevel("ERROR", "Exception in : "
                    + methodName + ". "
                    + "Something went wrong"
                    + t.getMessage()
                    + t);
            throw t;
        }

        if (logging.logResult()) {
            logLevel(logging.level(), EXIT_SYMBOL
                    + " " + methodName
                    + " result: "
                    + objectMapper.writeValueAsString(result));
        }

        if (logging.exit()) {
            logLevel(logging.level(), EXIT_SYMBOL
                    + " Exit "
                    + methodName + ": "
                    + message);
        }

        return result;
    }

    private void logLevel(String level, String message) {
        switch (level.toUpperCase()) {
            case "DEBUG" -> log.debug(message);
            case "INFO" -> log.info(message);
            case "WARN" -> log.warn(message);
            case "ERROR" -> log.error(message);
            default -> log.info(message);
        }
    }
}

