package justme.projectAwesome.interceptors;

import justme.projectAwesome.utils.SuccessMessageMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

@Component
public class SuccessMessageInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String requestedWith = request.getHeader("X-Requested-With");
        Boolean isAjax = requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;


        String messageIdentifier = request.getParameter("success-message-identifier");

        if (messageIdentifier != null) {
            String successMessage = SuccessMessageMap.get(messageIdentifier);
            request.setAttribute("success", successMessage);
        } else if(request.getMethod().equals("POST") && isAjax) {
            BufferedReader r = request.getReader();
            String line;
            String content = "";
            while ((line = r.readLine()) != null) {
                content += line;
            }
            String[] tokens = content.split("success-message-identifier\"");
            messageIdentifier = tokens[1].substring(0, tokens[1].indexOf("--"));

            response.setHeader("success", SuccessMessageMap.get(messageIdentifier));
            response.setHeader("redirect-url", request.getRequestURI());

            response.getOutputStream().flush();
            response.getOutputStream().close();
        }


        super.postHandle(request, response, handler, modelAndView);
    }
}
