package com.jshinv.basic.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {
	@GetMapping("/getString")
	public Map getString() {
		RestTemplate rt = new RestTemplate();

		// 리스트 방식으로 그냥 보여줌
		// String result = rt.getForObject("http://ggoreb.com/http/json1.jsp",
		// String.class);

		// 제이슨 형태로 보여줌 (맵방식)
		Map result2 = rt.getForObject("http://ggoreb.com/http/json1.jsp", Map.class);

		return result2;
	}

	@GetMapping("/getListMap")
	public List<Map<String, Object>> getListMap() {
		RestTemplate rt = new RestTemplate();
		List<Map<String, Object>> list = rt.getForObject("http://ggoreb.com/http/json2.jsp", List.class);
		return list;
	}

	@GetMapping("/getMap")
	public Map<String, Object> getMap() {
		RestTemplate rt = new RestTemplate();
		Map<String, Object> map = rt.getForObject("http://ggoreb.com/http/json1.jsp", Map.class);
		return map;
	}

	@GetMapping("/getKakao")
	public ResponseEntity<Map> getKakao(
			@RequestParam("address") String address) {
		RestTemplate rt = new RestTemplate();
		RequestEntity requestEntity = null;
		try {
			requestEntity = RequestEntity
					.get(new URI("https://dapi.kakao.com/v2/local/search/address.json?query="
							+ URLEncoder.encode(address, "utf-8")))
					.header("Authorization", "KakaoAK d4be7b479f4b4cbd99bd19ae87f88b4b").build();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);

		return entity;
	}

	@GetMapping("/getNaver")
	public ResponseEntity<Map> getNaver(
			@RequestParam("text") String text) {
		RestTemplate rt = new RestTemplate();
		RequestEntity<Map<String, String>> requestEntity = null;
		try {
			Map<String, String> body = new HashMap<>();
			body.put("source", "ko");
			body.put("target", "en");
			body.put("text", text);
			requestEntity = RequestEntity.post(new URI("https://openapi.naver.com/v1/papago/n2mt"))
					.header("X-Naver-Client-Id", "OpcnSsAIn37qIu6Iyad6").header("X-Naver-Client-Secret", "p7qtbsYx8N")
					.body(body);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);
		return entity;
	}

}