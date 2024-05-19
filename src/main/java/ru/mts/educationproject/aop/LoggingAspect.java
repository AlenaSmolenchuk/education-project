package ru.mts.educationproject.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.annotations.Logging;
import ru.mts.educationproject.repository.AnimalsRepository;

import java.lang.reflect.Method;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(logging)")
    public Object logMethod(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        return log(joinPoint, logging);
    }

    private Object log(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Object[] args = joinPoint.getArgs();

        String entrySymbol = ">>";
        String exitSymbol = "<<";

        String message = logging.value().isEmpty() ? methodName : logging.value();

        if (logging.enter()) {
            log(logging.level(), entrySymbol + " Enter " + methodName + ": " + message);
        }

        if (logging.logParams()) {
            log(logging.level(), entrySymbol + methodName + " parameters: " + objectMapper.writeValueAsString(args));
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            log("ERROR", "Exception in : " + methodName + ". " + t.getMessage());
            throw t;
        }

        if (logging.logResult()) {
            log(logging.level(), exitSymbol + methodName + " result: " + objectMapper.writeValueAsString(result));
        }

        if (logging.exit()) {
            log(logging.level(), exitSymbol + " Exit " + methodName + ": " + message);
        }

        return result;
    }

    private void log(String level, String message) {
        switch (level.toUpperCase()) {
            case "DEBUG" -> log.debug(message);
            case "INFO" -> log.info(message);
            case "WARN" -> log.warn(message);
            case "ERROR" -> log.error(message);
            default -> log.info(message);
        }
    }
}

