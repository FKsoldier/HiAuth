package com.lmq.hidemo.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.lmq.hidemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author 加藤惠
 */
@Controller
public class IndexController {

    @Value("${security.oauth2.hiAuth.resource.userInfoUri:}")
    private String userInfoUri;

    @Value("${security.oauth2.hiAuth.client.revokeTokenUri:}")
    private String revokeTokenUri;

    @Value("${security.oauth2.hiAuth.client.clientId:}")
    private String clientId;

    private static final String SESSION_KEY_ACCESS_TOKEN = "MY_ACCESS_TOKEN";

    @Autowired
    private OAuth20Service hiAuthApi;

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/signin")
    public void signin(HttpServletResponse response) throws IOException {
        String authorizationUrl = hiAuthApi.getAuthorizationUrl();
        System.err.println(authorizationUrl);
        response.sendRedirect(authorizationUrl);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws InterruptedException, ExecutionException, IOException {
        //注销access_token
        OAuth2AccessToken accessToken = (OAuth2AccessToken) request.getSession().getAttribute(SESSION_KEY_ACCESS_TOKEN);
        hiAuthApi.revokeToken(accessToken.getAccessToken());
        //清除session中保存的状态
        request.getSession().removeAttribute(SESSION_KEY_ACCESS_TOKEN);
        request.getSession().setAttribute("isAuth", false);
        return "/index";
    }


    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code", required = false) String code, HttpServletRequest request) throws Exception {

        OAuth2AccessToken accessToken = hiAuthApi.getAccessToken(code);


        OAuthRequest apiRequest = new OAuthRequest(Verb.GET, userInfoUri);
        hiAuthApi.signRequest(accessToken, apiRequest);
        Response resourceResponse = hiAuthApi.execute(apiRequest);
        System.err.println(resourceResponse.getBody());
        int indexBegin = resourceResponse.getBody().indexOf("userId")+8;
        int indexEnd = resourceResponse.getBody().indexOf(",",indexBegin);

        String userId = resourceResponse.getBody().substring(indexBegin,indexEnd);

        String s = testService.checkRoleByUserId(userId, clientId);
        if (s == null){
            hiAuthApi.revokeToken(accessToken.getAccessToken());
            request.getSession().removeAttribute(SESSION_KEY_ACCESS_TOKEN);
            request.getSession().setAttribute("isAuth", false);
            return "/404";
        }


        request.getSession().setAttribute(SESSION_KEY_ACCESS_TOKEN, accessToken);
        request.getSession().setAttribute("isAuth", true);

        return "/index";
    }
}
