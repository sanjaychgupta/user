package com.in.user.controller;

import com.in.user.domain.request.RequestData;
import com.in.user.domain.response.Message;
import com.in.user.domain.response.User;
import com.in.user.domain.response.XMLMessage;
import com.in.user.service.UserService;
import com.in.user.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SwaggerAPIController {

    public static final Logger logger = LoggerFactory.getLogger(SwaggerAPIController.class);

    @Autowired
    public Message message;
    @Autowired
    public XMLMessage xmlMessage;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Message getProducts() {
        List<String> productsList = new ArrayList<>();
        productsList.add("Honey");
        productsList.add("Almond");
        productsList.toString();
        message.setType("Sending list in form of string");
        message.setMsg(productsList.toString());
        return message ;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct() {
        return "Product is saved successfully";
    }

    @GetMapping("/api/foos")
    @ResponseBody
    public String getFoos(@RequestParam String id) {
        return "ID: " + id;
    }

    @PostMapping("/api/koos")
    @ResponseBody
    public String addKoos(@RequestParam(name = "id") String fooId, @RequestParam String name) {
        return "ID: " + fooId + " Name: " + name;
    }

    @GetMapping("/api/soos")
    @ResponseBody
    public String getSoos(@RequestParam(required = false) String id) {
        if(id == null)
            return "Request parmeter is not present..... but its okay.";
        return "ID: " + id;
    }

    @GetMapping("/api/doos")
    @ResponseBody
    public String getFogetDoosos(@RequestParam(defaultValue = "test") String id) {

        return "ID: " + id;
    }

    @GetMapping({"/myfoos/optional", "/myfoos/optional/{id}"})
    @ResponseBody
    public String getFooByOptionalId(@PathVariable(value="id", required = false) String id){
        return "ID: " + id;
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        User user = userService.findById(id);
        if (user == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers(@PathVariable("ID") long id) {
        logger.info("Deleting All Users");

        userService.deleteUser(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/products-xml", method = RequestMethod.GET, produces = "application/xml")
    public ResponseEntity<XMLMessage>  getProductsXML() {
        List<String> productsList = new ArrayList<>();
        productsList.add("Honey");
        productsList.add("Almond");
        productsList.toString();
        xmlMessage.setType("Sending list in form of string");
        xmlMessage.setMsg(productsList.toString());
        return new ResponseEntity<XMLMessage>(xmlMessage, HttpStatus.OK);
    }


    @RequestMapping (value = "/xooox", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE,
            produces=MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<XMLMessage> getProductXML2(@Valid @RequestBody RequestData requestData) {
        List<String> productsList = new ArrayList<>();
        productsList.add("Honey");
        productsList.add("Almond");
        productsList.toString();
        xmlMessage.setType("Sending list in form of string");
        xmlMessage.setMsg(productsList.toString());
        return new ResponseEntity<XMLMessage>(xmlMessage, HttpStatus.OK);
    }

}