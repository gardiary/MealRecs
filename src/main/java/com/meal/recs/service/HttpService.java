package com.meal.recs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: gardiary
 * Date: 28/12/21, 22.46
 */
@Service
public class HttpService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	/*public Object get(String url, Class clazz, Map<String, String> params) {
		return restTemplate.getForObject(url, clazz, params);
	}*/

	public <T> T get(String url, Class<T> clazz, Map<String, String> params) {
		return restTemplate.getForObject(url, clazz, params);
	}

	/*public WarehousesDto getWarehouse(String warehouseUuid) {
		WarehouseResponse warehouseResponse = (WarehouseResponse) httpService.get(
				ossWmsUrl + WMS_WAREHOUSE_URL + "/" + warehouseUuid,
				new WarehouseResponse(), new HashMap<>());

		return warehouseResponse.getData();
	}
	*/
	/*public Object get(String url, Class<?> clazz, Map<String, Object> uriVariables, Map<String, String> headerParams){
		final var headers = new HttpHeaders();
		if(Utilities.isNotBlank(headerParams)) {
			for(Map.Entry<String, String> entry : headerParams.entrySet()) {
				String key = entry.getKey();
				headers.set(key, headerParams.get(key));
			}
		}

		final HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<?> response = restTemplate
				.exchange(url, HttpMethod.GET, entity, clazz, uriVariables);
		return response.getBody();
	}
	
	public GenericResponse post(String url, Object request, Class<GenericResponse> responseType) {
		return restTemplate.postForObject(url, request, responseType);
	}

	public Object post(String url, Object request, Class<?> responseType, Map<String, Object> uriVariables) {
		return restTemplate.postForObject(url, request, responseType, uriVariables);
	}

	public Object postWithHeader(String url, Class<?> responseType, Map<String, String> headerParams, Map<String, Object> uriVariables){
		final var headers = new HttpHeaders();
		if(Utilities.isNotBlank(headerParams)) {
			for(Map.Entry<String, String> entry : headerParams.entrySet()) {
				String key = entry.getKey();
				headers.set(key, headerParams.get(key));
			}
		}

		final HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<?> response = restTemplate
				.exchange(url, HttpMethod.POST,entity,responseType,uriVariables);
		return response.getBody();
	}

	public Object postHeaders(String url, Class<?> clazz, Object objParam, Map<String, Object> uriVariables, Map<String, String> headerParams){
		final var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(Utilities.isNotBlank(headerParams)) {
			for(Map.Entry<String, String> entry : headerParams.entrySet()) {
				String key = entry.getKey();
				headers.set(key, headerParams.get(key));
			}
		}

		final HttpEntity<Object> entity = new HttpEntity<>(objParam, headers);

		ResponseEntity<?> response = restTemplate
				.postForEntity(url, entity, clazz, uriVariables);
		return response.getBody();
	}

	public Object putHeaders(String url, Class<?> responseObject, Object objParam, Map<String, String> uriVariables, Map<String, String> headerParams){
			logger.info("uriVariable {}",uriVariables);
			final var headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			if (Utilities.isNotBlank(headerParams)) {
				for (Map.Entry<String, String> entry : headerParams.entrySet()) {
					String key = entry.getKey();
					headers.set(key, headerParams.get(key));
				}
			}

			final HttpEntity<Object> entity = new HttpEntity<>(objParam, headers);

			ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.PUT, entity, responseObject);

			return response.getBody();

	}

	public Boolean option(String url, Class<?> responseObject, Map<String, String> uriVariables){
		ResponseEntity<?> response = restTemplate.exchange(url,HttpMethod.OPTIONS,null,responseObject,uriVariables);
		return response.getStatusCode().is2xxSuccessful();
	}

	public Object option(String url, Class<?> responseObject){
		ResponseEntity<?> response = restTemplate.exchange(url,HttpMethod.OPTIONS,null,responseObject,new HashMap<>());
		return response.getBody();
	}

	public Set<HttpMethod> optionForAllow(String url, Object uriVariables){
		return restTemplate.optionsForAllow(url, uriVariables);
	}*/
}
