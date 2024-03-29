package com.nighthawk.spring_portfolio.database.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.view.RedirectView;

import com.nighthawk.spring_portfolio.database.ModelRepository;
import com.nighthawk.spring_portfolio.database.review.ReviewJpaRepository;

import java.util.*;

@RestController
@RequestMapping("/api/reviewInventory/")
public class ReviewApiController {

    @Autowired
    private ModelRepository repository;
    @Autowired 
    ReviewJpaRepository reviewJpaRepository;

    /*
    GET List of Cars
     */
    @GetMapping("all")
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(repository.listAllReviews(), HttpStatus.OK);
    }

        /*
    DELETE individual Car using ID
     */
    @GetMapping("delete/{id}")
    public RedirectView deleteReview(@PathVariable long id) {
        repository.deleteReview(id);
        return new RedirectView("https://ad1616.github.io/breadbops-frontend/show");
    }

    /*
    POST Aa record by Requesting Parameters from URI
     */
    @PostMapping( "/post/")
    public ResponseEntity<Object> postPerson(@RequestParam("name") String name, @RequestParam("email") String email,
                                             @RequestParam("phoneNumber") String phone, @RequestParam("stars") int stars,
                                             @RequestParam("comments") String comments) {

        repository.saveReview(new Review(null, name, email, phone, stars, comments));
        return new ResponseEntity<>(name +" is created successfully", HttpStatus.CREATED);
    }


}