package me.risky.base;

import me.risky.util.ResponseUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenupt@gmail.com on 14-2-24.
 * Description : TODO
 */
public class BaseController {

    private String responseMessage=null;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }


    private Object responseObject=null;

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

    private int responseCode = 200;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponse(int responseCode, Object responseObject,String responseMessage){
        setResponseCode(responseCode);
        setResponseObject(responseObject);
        setResponseMessage(responseMessage);
    }

    public void writeResponse(HttpServletResponse httpServletResponse){
        ResponseUtil.writeResponse(null, responseCode, responseObject, responseMessage,httpServletResponse);
        resetResponse();
    }

    public void writeResponse(String action, HttpServletResponse httpServletResponse){
        ResponseUtil.writeResponse(action, responseCode, responseObject, responseMessage,httpServletResponse);
        resetResponse();
    }

    public void resetResponse(){
        setResponseCode(200);
        setResponseMessage("");
        setResponseObject(null);
    }

}
