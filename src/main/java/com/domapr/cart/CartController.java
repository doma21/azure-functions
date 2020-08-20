package com.domapr.cart;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;

/**
 * Azure Functions with HTTP Trigger.
 */
public class CartController {
    /**
     * This function listens at endpoint "/api/CartController". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/CartController
     * 2. curl {your host}/api/CartController?name=HTTP%20Query
     */
    @FunctionName("CartController")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) 
            HttpRequestMessage<Optional<CartData>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Check request body
        if (!request.getBody().isPresent()) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                          .body("Cart JSON not found.")
                          .build();
        } 

        final CartData cartData = request.getBody().get();

        if (cartData == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass cart json in the request body").build();
        } else {
            CheckoutData checkOutData = new CheckoutData();
            checkOutData.setUrl("http://localhost/store/checkout.jsp");
            List<Cooky> cookies = new ArrayList<Cooky>();
            Cooky cooky = new Cooky();
            cooky.setDomain("com.chicos");
            cooky.setName("JSESSIONID");
            cooky.setIsHttpOnly(Boolean.TRUE);
            cookies.add(cooky);
            checkOutData.setCookies( cookies );
            Gson gson = new Gson();
            return request.createResponseBuilder(HttpStatus.OK).body(gson.toJson(checkOutData)).build();
            
        }
    }
}

class CheckoutData {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("cookies")
    @Expose
    private List<Cooky> cookies = null;
    @SerializedName("headers")
    @Expose
    private List<Header> headers = null;

    public String getUrl() {
    return url;
    }

    public void setUrl(String url) {
    this.url = url;
    }

    public String getMethod() {
    return method;
    }

    public void setMethod(String method) {
    this.method = method;
    }

    public List<Cooky> getCookies() {
    return cookies;
    }

    public void setCookies(List<Cooky> cookies) {
    this.cookies = cookies;
    }

    public List<Header> getHeaders() {
    return headers;
    }

    public void setHeaders(List<Header> headers) {
    this.headers = headers;
    }

}


class Cooky {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("isHttpOnly")
    @Expose
    private Boolean isHttpOnly;
    @SerializedName("isSecure")
    @Expose
    private Boolean isSecure;
    @SerializedName("path")
    @Expose
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getIsHttpOnly() {
        return isHttpOnly;
    }

    public void setIsHttpOnly(Boolean isHttpOnly) {
        this.isHttpOnly = isHttpOnly;
    }

    public Boolean getIsSecure() {
        return isSecure;
    }

    public void setIsSecure(Boolean isSecure) {
        this.isSecure = isSecure;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

class Header {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
