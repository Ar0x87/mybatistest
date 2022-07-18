package com.null01.services;

import com.null01.wrappers.Responser;
import org.springframework.stereotype.Service;

@Service
public class WrapperServiceImpl implements IWrapperService {
    @Override
    public Responser getData() {
        return new Responser(200, "OK", true);
    }
}
