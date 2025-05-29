Feature: SearchBusByLocationWithData
This features is to search bus for different location and with different dates

Scenario Outline: Search Room with dates
Given user opens "www.booking.com" page and navigate to stays page
  When user searches for "<destination>" with "<checkindate>" and "<checkoutdate>" and Passenger details and page loads with lists of stays available
    | 3 | 1 | 5 |
    | 1 | 1 | 5 |
    | 6 | 4 | 0 |
  When user searches for "Misty Meridian Serviced Apartments" from list and clicks reserve.
  When User landed in reserve page user selects 4 appartment under "Three-Bedroom Apartment" and clicks reserve
  Then user lands on Finish Booking page and verify the price displayed.


  Examples:
    | destination | checkindate | checkoutdate |
    | Bangalore   | 30-May-2025 | 3-June-2025  |






