package ua.goodvice.easylib.easylib.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestAuthUtil {
    private final CookieParser cookieParser;

    public HttpHeaders createHeaders(HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String jwt = cookieParser.getCookie(request, "user-id").getValue();
        httpHeaders.add("Authorization", "Bearer " + jwt);
        return httpHeaders;
    }
}
