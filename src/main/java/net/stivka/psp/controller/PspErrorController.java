package net.stivka.psp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import net.stivka.psp.model.ApiError;

@Controller
public class PspErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public PspErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ResponseEntity<ApiError> handleError(HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);

        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(servletWebRequest, ErrorAttributeOptions.defaults());
        HttpStatus status = getStatus(request);

        List<String> errors = new ArrayList<>();
        errors.add((String) errorMap.get("error"));

        ApiError apiError = new ApiError(status, (String) errorMap.get("message"), errors);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }



    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            }
            catch (Exception ex) {
                // Ignore exception
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
