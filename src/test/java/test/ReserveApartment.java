package test;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Container;

public class ReserveApartment {

    public Container container;

    public ReserveApartment(Container container) {
        this.container = container;
    }


    @When("User landed in reserve page user selects {int} appartment under {string} and clicks reserve")
    public void user_landed_in_reserve_page_user_selects_appartment_under_and_clicks_reserve(Integer noOfAppartment, String appartmentType) throws InterruptedException {
        container.reservePage.isAt();
        container.reservePage.selectNumberOfAppartment(noOfAppartment, appartmentType);
    }

    @Then("user lands on Finish Booking page and verify the price displayed.")
    public void user_lands_on_finish_booking_page_and_verify_the_price_displayed() throws InterruptedException {
        container.reservePage.finishBookingPage();
    }
}
