package sample;

import hr.algebra.dujmovic.confapp.model.Lecture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Controller implements Initializable {

    @FXML
    private TextArea outputArea;



    public void findLecture(ActionEvent actionEvent) {
        System.out.println("you clicked me");
//        System.out.println(inputId);


        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.ALL);
        converter.setSupportedMediaTypes(mediaTypes);
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);

        String restEndpointUrl = "http://localhost:8080/api/lecture";

        ResponseEntity<Lecture[]> speakerArrayResponse =
                restTemplate.getForEntity(restEndpointUrl, Lecture[].class);



        int shortest = 0;
        int i = 0;

        for(Lecture lecture : speakerArrayResponse.getBody()) {

            System.out.println("h "+lecture.getContents().toString()+ " " +i+" "+ lecture.getContents().toString().length());

            if(lecture.getContents().toString().length() < speakerArrayResponse.getBody()[shortest].toString().length());
            {
                shortest = i;
            }
                i++;
        }

        if(speakerArrayResponse.getBody().length == 0){

            outputArea.setText("Lectures are empty");
        }
        else{
            outputArea.setText(speakerArrayResponse.getBody()[shortest].toString());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
