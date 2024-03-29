package com.nighthawk.spring_portfolio.mvc.calendar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;

import org.bson.codecs.pojo.annotations.BsonId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// public class specs {
//     public static void main(String[] args) {
//         try {

//             String makeid = "palceholder"; //makeid
//             // URL url = new URL("https://car-specs.p.rapidapi.com/v2/cars/makes/%7BmakeId%7D/models");
//             URL url = new URL ("https://car-specs.p.rapidapi.com/v2/cars/makes/71295/models"); //puts in makeid into the url
//             HttpURLConnection con = (HttpURLConnection) url.openConnection();

//             // Add Request Headers
//             con.setRequestMethod("GET");
//             con.setRequestProperty("X-RapidAPI-Host", "car-specs.p.rapidapi.com");
//             con.setRequestProperty("X-RapidAPI-Key", "032f716b5amsh1241419d17ff651p1d3c54jsna32e706ab7f6");

//             // Send the GET Request
//             int responseCode = con.getResponseCode();
//             System.out.println("Response Code : " + responseCode);

//             // Read the Response    
//             BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//             String inputLine;
//             StringBuilder response = new StringBuilder();

//             while ((inputLine = in.readLine()) != null) {
//                 response.append(inputLine);
//             }
//             in.close();

//             // Print the Response
//             System.out.println(response.toString());
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }




@RestController // annotation to create a RESTful web services
@RequestMapping("/api/specs")  //prefix of API
@ResponseBody
public class specs {
    private JSONArray body; //last run result
    private JSONObject specs;
    private HttpStatus status; //last run status
    String last_run = null; //last run day of month

    // GET Covid 19 Stats
    @GetMapping("/makes")   //added to end of prefix as endpoint
    public JSONArray getMakes() {

        //calls API once a day, sets body and status properties
        // String today = new Date().toString().substring(0,10); 
        // if (last_run == null || !today.equals(last_run)) // this thing gave me problems
        // {
            try {  //APIs can fail (ie Internet or Service down)
                
                //RapidAPI header
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://car-specs.p.rapidapi.com/v2/cars/makes"))
                    .header("x-rapidapi-key", "80ed0e7619msh300fdfb6098c24cp160b71jsn6b8d978c56ec") //032f716b5amsh1241419d17ff651p1d3c54jsna32e706ab7f6
                    .header("x-rapidapi-host", "car-specs.p.rapidapi.com")
                    .build();

                //RapidAPI request and response
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                //JSONParser extracts text body and parses to JSONObject
                System.out.println("hahahaha");
                String temp = response.body();
                System.out.println(temp);
                this.body = (JSONArray) new JSONParser().parse(response.body());
                

                //System.out.prinltn()

                this.status = HttpStatus.OK;  //200 success
                // this.last_run = today; //updates it so when it pulls each day
            }
            catch (Exception e) {  //capture failure info
                HashMap<String, String> status = new HashMap<>();

                // this.body = e.toString();

                System.out.println("error");
                System.out.println(e);

                status.put("status", "RapidApi failure: " + e);

                //Setup object for error
                // this.body = (JSONObject) status;
                // this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
                this.last_run = null;
            
            }
        // }

        //return JSONObject in RESTful style
        return this.body;  // JSON response, see ExceptionHandlerAdvice for throws
   //     return new ResponseEntity<>(body, status);

}
@GetMapping("/models/{modelid}")   //added to end of prefix as endpoint
public JSONArray getModels(@PathVariable String modelid) {

    //calls API once a day, sets body and status properties
    String today = new Date().toString().substring(0,10); 
    if (last_run == null || !today.equals(last_run)) // this thing gave me problems
    {
        try {  //APIs can fail (ie Internet or Service down)
            
            //RapidAPI header
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://car-specs.p.rapidapi.com/v2/cars/makes/" + modelid + "/models"))
                .header("x-rapidapi-key", "80ed0e7619msh300fdfb6098c24cp160b71jsn6b8d978c56ec") //032f716b5amsh1241419d17ff651p1d3c54jsna32e706ab7f6
                .header("x-rapidapi-host", "car-specs.p.rapidapi.com")
                .build();

            //RapidAPI request and response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            //JSONParser extracts text body and parses to JSONObject
            System.out.println("hahahaha");
            String temp = response.body();
            System.out.println(temp);
            this.body = (JSONArray) new JSONParser().parse(response.body());
            

            //System.out.prinltn()

            this.status = HttpStatus.OK;  //200 success
            // this.last_run = today; //updates it so when it pulls each day
        }
        catch (Exception e) {  //capture failure info
            HashMap<String, String> status = new HashMap<>();

            // this.body = e.toString();

            System.out.println("error");
            System.out.println(e);

            status.put("status", "RapidApi failure: " + e);

            //Setup object for error
            // this.body = (JSONObject) status;
            // this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
            this.last_run = null;
        
        }
    }

    //return JSONObject in RESTful style
    return this.body;  // JSON response, see ExceptionHandlerAdvice for throws
//     return new ResponseEntity<>(body, status);

}
@GetMapping("/generations/{genid}")   //added to end of prefix as endpoint
public JSONArray getGeneration(@PathVariable String genid) {

    //calls API once a day, sets body and status properties
    // String today = new Date().toString().substring(0,10); 
    // if (last_run == null || !today.equals(last_run)) // this thing gave me problems
    // {
        try {  //APIs can fail (ie Internet or Service down)
            
            //RapidAPI header
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://car-specs.p.rapidapi.com/v2/cars/models/" + genid + "/generations"))
                .header("x-rapidapi-key", "80ed0e7619msh300fdfb6098c24cp160b71jsn6b8d978c56ec") //032f716b5amsh1241419d17ff651p1d3c54jsna32e706ab7f6
                .header("x-rapidapi-host", "car-specs.p.rapidapi.com")
                .build();

            //RapidAPI request and response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            //JSONParser extracts text body and parses to JSONObject
            System.out.println("hahahaha");
            String temp = response.body();
            System.out.println(temp);
            this.body = (JSONArray) new JSONParser().parse(response.body());
            

            //System.out.prinltn()

            this.status = HttpStatus.OK;  //200 success
            // this.last_run = today; //updates it so when it pulls each day
        }
        catch (Exception e) {  //capture failure info
            HashMap<String, String> status = new HashMap<>();

            // this.body = e.toString();

            System.out.println("error");
            System.out.println(e);

            status.put("status", "RapidApi failure: " + e);

            //Setup object for error
            // this.body = (JSONObject) status;
            // this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
            this.last_run = null;
        
        }
    // }

    //return JSONObject in RESTful style
    return this.body;  // JSON response, see ExceptionHandlerAdvice for throws
//     return new ResponseEntity<>(body, status);

}
@GetMapping("/trims/{trimid}")   //added to end of prefix as endpoint
public JSONArray gettrims(@PathVariable String trimid) {

    //calls API once a day, sets body and status properties
    // String today = new Date().toString().substring(0,10); 
    // if (last_run == null || !today.equals(last_run)) // this thing gave me problems
    // {
        try {  //APIs can fail (ie Internet or Service down)
            
            //RapidAPI header
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://car-specs.p.rapidapi.com/v2/cars/generations/" + trimid + "/trims"))
                .header("x-rapidapi-key", "80ed0e7619msh300fdfb6098c24cp160b71jsn6b8d978c56ec") //032f716b5amsh1241419d17ff651p1d3c54jsna32e706ab7f6
                .header("x-rapidapi-host", "car-specs.p.rapidapi.com")
                .build();

            //RapidAPI request and response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            //JSONParser extracts text body and parses to JSONObject
            System.out.println("hahahaha");
            String temp = response.body();
            System.out.println(temp);
            this.body = (JSONArray) new JSONParser().parse(response.body());
            

            //System.out.prinltn()

            this.status = HttpStatus.OK;  //200 success
            // this.last_run = today; //updates it so when it pulls each day
        }
        catch (Exception e) {  //capture failure info
            HashMap<String, String> status = new HashMap<>();

            // this.body = e.toString();

            System.out.println("error");
            System.out.println(e);

            status.put("status", "RapidApi failure: " + e);

            //Setup object for error
            // this.body = (JSONObject) status;
            // this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
            this.last_run = null;
        
        }
    // }

    //return JSONObject in RESTful style
    return this.body;  // JSON response, see ExceptionHandlerAdvice for throws
//     return new ResponseEntity<>(body, status);

}
@GetMapping("/specs/{specid}")   //added to end of prefix as endpoint
public JSONObject getSpecs(@PathVariable String specid) {

    //calls API once a day, sets body and status properties
    // String today = new Date().toString().substring(0,10); 
    // if (last_run == null || !today.equals(last_run)) // this thing gave me problems
    // {
        try {  //APIs can fail (ie Internet or Service down)
            
            //RapidAPI header
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://car-specs.p.rapidapi.com/v2/cars/trims/" + specid))
                .header("x-rapidapi-key", "80ed0e7619msh300fdfb6098c24cp160b71jsn6b8d978c56ec") //032f716b5amsh1241419d17ff651p1d3c54jsna32e706ab7f6
                .header("x-rapidapi-host", "car-specs.p.rapidapi.com")
                .build();

            //RapidAPI request and response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            //JSONParser extracts text body and parses to JSONObject
            System.out.println("hahahaha");
            String temp = response.body();
            System.out.println(temp);
            this.specs = (JSONObject) new JSONParser().parse(response.body());
            

            //System.out.prinltn()

            this.status = HttpStatus.OK;  //200 success
            // this.last_run = today; //updates it so when it pulls each day
        }
        catch (Exception e) {  //capture failure info
            HashMap<String, String> status = new HashMap<>();

            // this.body = e.toString();

            System.out.println("error");
            System.out.println(e);

            status.put("status", "RapidApi failure: " + e);

            //Setup object for error
            // this.body = (JSONObject) status;
            // this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
            this.last_run = null;
        
        }
    // }

    //return JSONObject in RESTful style
    return this.specs;  // JSON response, see ExceptionHandlerAdvice for throws
//     return new ResponseEntity<>(body, status);

}
@GetMapping("/integra/{specid}")   //added to end of prefix as endpoint
public JSONObject getIntegra(@PathVariable String specid) {

    //calls API once a day, sets body and status properties
    String today = new Date().toString().substring(0,10); 
    if (last_run == null || !today.equals(last_run)) // this thing gave me problems
    {
        try {  //APIs can fail (ie Internet or Service down)
            
            //RapidAPI header
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://car-specs.p.rapidapi.com/v2/cars/trims/71325"))
                .header("x-rapidapi-key", "80ed0e7619msh300fdfb6098c24cp160b71jsn6b8d978c56ec") //032f716b5amsh1241419d17ff651p1d3c54jsna32e706ab7f6
                .header("x-rapidapi-host", "car-specs.p.rapidapi.com")
                .build();

            //RapidAPI request and response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            //JSONParser extracts text body and parses to JSONObject
            System.out.println("hahahaha");
            String temp = response.body();
            System.out.println(temp);
            this.specs = (JSONObject) new JSONParser().parse(response.body());
            

            //System.out.prinltn()

            this.status = HttpStatus.OK;  //200 success
            // this.last_run = today; //updates it so when it pulls each day
        }
        catch (Exception e) {  //capture failure info
            HashMap<String, String> status = new HashMap<>();

            // this.body = e.toString();

            System.out.println("error");
            System.out.println(e);

            status.put("status", "RapidApi failure: " + e);

            //Setup object for error
            // this.body = (JSONObject) status;
            // this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
            this.last_run = null;
        
        }
    }

    //return JSONObject in RESTful style
    return this.specs;  // JSON response, see ExceptionHandlerAdvice for throws
//     return new ResponseEntity<>(body, status);

}
}



