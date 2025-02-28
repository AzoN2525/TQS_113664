package com.example;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.ParseException;


public class TqsBasicHttpClient implements ISimpleHttpClient {
    @Override
    public String doHttpGet(String url) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            System.out.println("url: " + url);
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } 
        catch (ParseException e) {
            throw new IOException("Erro ao processar a resposta HTTP", e);
        }
    }
}


