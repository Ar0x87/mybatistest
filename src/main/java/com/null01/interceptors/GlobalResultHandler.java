package com.null01.interceptors;

import com.null01.annotations.DisableResponseWrapper;
import com.null01.annotations.EnableResponseWrapper;
import com.null01.models.IWrapperModel;
import com.null01.services.IWrapperService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ControllerAdvice(annotations = EnableResponseWrapper.class)
public class GlobalResultHandler implements ResponseBodyAdvice<Object> {

    private IWrapperService wrapperService;

    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class converterType) {
        for (Annotation a : returnType.getMethodAnnotations()) {
            if (a.annotationType() == DisableResponseWrapper.class) {
                return false;
            }
        }
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(
            @Nullable Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
            ) {
        if (body == null) {
            return null;
        }

        Class<? extends IWrapperModel> wrapperClass = null;
        for (Annotation annotation : returnType.getContainingClass().getAnnotations()) {
            if (annotation.annotationType() == EnableResponseWrapper.class) {
                wrapperClass = ((EnableResponseWrapper) annotation).wrapperClass();
                break;
            }
        }

        if (wrapperClass == null) {
            return body;
        }

        return generateResponseWrapper(body, wrapperClass);
    }

    @SneakyThrows
    private IWrapperModel generateResponseWrapper(Object body, Class<? extends IWrapperModel> wrapperClass) {
        IWrapperModel wrapper = wrapperClass.getDeclaredConstructor().newInstance();
        wrapper.setData(wrapperService.getData());
        wrapper.setBody(body);
        return wrapper;
    }
}
