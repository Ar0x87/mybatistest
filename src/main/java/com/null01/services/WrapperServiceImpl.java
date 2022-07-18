package com.null01.services;

import com.null01.wrappers.Responser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WrapperServiceImpl implements IWrapperService {
    @Override
    public Responser getData(Object body) {
        Responser answer = new Responser(200, "OK", true);
        return answer;
    }
}
