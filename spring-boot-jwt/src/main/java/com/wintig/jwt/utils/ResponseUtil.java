package com.wintig.jwt.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 处理向前台发送响应工具类
 * @author Tig
 *
 */
public class ResponseUtil {
	
	public static void write(HttpServletResponse response, Object o) {
		try {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(o.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
