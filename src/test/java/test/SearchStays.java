package test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.Container;

import java.time.LocalDate;
import java.time.Month;

public class SearchStays {

    public Container container;

    public SearchStays(Container container) {
        this.container = container;
    }


    @Given("^user opens (.+) page and navigate to stays page$")
    public void user_opens_www_booking_com_page_and_navigate_to_stays_page(String url) {
    }

    @When("user searches for {string} and {string} with {string} and {string} and Passenger details and page loads with lists of stays available")
    public void user_searches_for_and_with_and_and_passenger_details_and_page_loads_with_lists_of_stays_available(String location, String state, String checkinDate, String checkoutDate) throws InterruptedException {
        container.homepage.selectSearchType("accommodations").searchDestination(location + "|" + state);
        String[] checkinDateArr = checkinDate.split("-");
        String[] checkoutDateArr = checkoutDate.split("-");
        System.out.println(Integer.parseInt(checkinDateArr[2]));
        System.out.println(Month.valueOf(checkinDateArr[1].toUpperCase()));
        System.out.println(Integer.parseInt(checkinDateArr[0]));
        container.homepage.selectStartAndToDate(LocalDate.of(Integer.parseInt(checkinDateArr[2]), Month.valueOf(checkinDateArr[1].toUpperCase()), Integer.parseInt(checkinDateArr[0])), LocalDate.of(Integer.parseInt(checkoutDateArr[2]), Month.valueOf(checkoutDateArr[1].toUpperCase()), Integer.parseInt(checkoutDateArr[0])));

    }

    @When("user searches for {string} from list and clicks reserve.")
    public void user_searches_for_from_list_and_clicks_reserve(String hotelName) throws InterruptedException {
        container.searchResultsPage = container.homepage.searchSubmit();
        container.reservePage = container.searchResultsPage.searchForTheHotelAndSeeAvailability(hotelName);
    }


}
