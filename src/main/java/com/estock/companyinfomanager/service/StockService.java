package com.estock.companyinfomanager.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("${kafka.stock.added.topic}")
    private String stockTopic;

    @Value("${Kafka.stock.delete.topic}")
    private String deleteTopic;

    public void sendStockDetails(String companyCode, Double stockPrice){
        String stock = Double.toString(stockPrice);
        ProducerRecord<String,String> stockRecord = new ProducerRecord<>(stockTopic,companyCode,stock);
        kafkaTemplate.send(stockRecord);
    }

    public void deleteAllByCompanyCode(String companyCode) {
        ProducerRecord<String,String> deleteRecord = new ProducerRecord<>(deleteTopic,companyCode);
        kafkaTemplate.send(deleteRecord);
    }
}
