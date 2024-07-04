package com.webservices.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface(SEI)
@WebService
@SOAPBinding(style = Style.RPC)//tipos primitivos in/out
public interface Calculator {
    @WebMethod
    public double add(double a, double b);
    @WebMethod
    public double sub(double a, double b);
    @WebMethod
    public double div(double a, double b);
    @WebMethod
    public double mult(double a, double b);

}
