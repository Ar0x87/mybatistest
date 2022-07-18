package com.null01.services;

import com.null01.wrappers.Responser;
import org.springframework.stereotype.Service;

@Service
public interface IWrapperService {
    Responser getData();
}
