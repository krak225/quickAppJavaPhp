package net.olea.santemaghreb.utils;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtils {
	public static final String SECRET = "MySecret321";
	public static final String AUTH_HEADER = "Authorization";
	public static final String PREFIX = "Bearer ";
	public static final long EXPIRE_ACCESS_TOKEN = 50*60*1000;
	public static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
	public static final long EXPIRE_REFRESH_TOKEN = 300*60*1000;
}
