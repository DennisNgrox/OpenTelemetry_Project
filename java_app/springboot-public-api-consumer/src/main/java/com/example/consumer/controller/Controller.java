package com.example.consumer.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.consumer.model.Response;
import com.example.consumer.model.UserData;
import com.example.consumer.model.UserDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/v1")
public class Controller {

	private static final String URL_1 = "https://jsonplaceholder.typicode.com/users";
	private static final String URL_2 = "https://jsonplaceholder.typicode.com/comments";

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Gson gson;

	// Configurar o logger
	private static final Logger logger = LogManager.getLogger(Controller.class);

	@GetMapping("/health")
	public String checkHealth() {
		logger.info("Health check endpoint accessed.");
		return "I am working fine";
	}

	/**
	 * Lê a resposta da URL_1 e executa algumas operações de filtro, retornando os dados.
	 * 
	 * @return
	 */
	@GetMapping("/details/url/1")
	public ResponseEntity<List<Response>> getDetails() {
		logger.info("Endpoint /details/url/1 accessed.");

		ResponseEntity<String> response = restTemplate.exchange(URL_1, HttpMethod.GET, HttpEntity.EMPTY, String.class);
		logger.info("Response from URL_1: " + response.getBody());

		Type type = new TypeToken<ArrayList<UserData>>() {}.getType();
		ArrayList<UserData> apiData = gson.fromJson(response.getBody(), type);

		List<Response> filteredResponse = new ArrayList<>();
		for (UserData user : apiData) {
			Response res = new Response(user.getAddress().getGeo().getLat(), user.getAddress().getGeo().getLng(),
					user.getCompany().getName());
			filteredResponse.add(res);
		}

		logger.info("Filtered response: " + filteredResponse);
		return ResponseEntity.ok().body(filteredResponse);
	}

	@GetMapping("/details/url/2")
	public ResponseEntity<UserDetail[]> getFilteredDetails() {
		logger.info("Endpoint /details/url/2 accessed.");

		ResponseEntity<String> response = restTemplate.exchange(URL_2, HttpMethod.GET, HttpEntity.EMPTY, String.class);
		logger.info("Response from URL_2: " + response.getBody());

		// Deserializa a resposta JSON para um array de UserDetail
		UserDetail[] details = gson.fromJson(response.getBody(), UserDetail[].class);

		// Criar uma lista para os detalhes filtrados
		List<UserDetail> filteredDetails = new ArrayList<>();

		// Filtrar manualmente os detalhes
		for (UserDetail detail : details) {
			if (detail.getPostId() == 1) {
				detail.setBody("Id " + detail.getId()); // Atualizar o corpo
				filteredDetails.add(detail); // Adicionar à lista filtrada
			}
		}

		// Pausa de 10 segundos
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			logger.error("Thread interrupted", e);
		}

		logger.info("Filtered details: " + Arrays.toString(filteredDetails.toArray()));
		return ResponseEntity.ok().body(filteredDetails.toArray(new UserDetail[0]));
	}
}
