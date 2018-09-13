package com.saman.oak.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public final class ServletUtils {

    private ServletUtils() {
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        request.getRequestDispatcher(url).forward(request, response);

    }

}
