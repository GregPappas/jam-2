package org.team8.jam.web.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.team8.jam.web.domain.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path("/customer")
public class CustomerService {

    private Gson gson = new Gson();


    @GET
    @Path("customers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomers() throws IOException {
        return retrieveJsonFromTestDataFile();
    }

    private List<Customer> generateDummyCustomers() {
        Customer customer1 = new Customer("Bob", "Somewhere", 77);
        Customer customer2 = new Customer("Doris", "Nowhere", 88);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        return customers;
    }

    private String retrieveJsonFromTestDataFile() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("customers.json");

        try {
            return IOUtils.toString(inputStream);
        }
        catch (IOException e) {}

        return null;
    }

    private List<Customer> deserialize(String json) {
        Type collectionType = new TypeToken<Collection<Customer>>(){}.getType();

        return gson.fromJson(json, collectionType);
    }

}
