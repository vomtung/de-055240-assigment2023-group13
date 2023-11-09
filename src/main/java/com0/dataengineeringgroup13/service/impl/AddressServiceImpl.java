package com0.dataengineeringgroup13.service.impl;

import com0.dataengineeringgroup13.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private Connection dataConnection;
}
