Feature: SearchBusByLocationWithData
  This features is to search bus for different location and with different dates

  Scenario Outline: Search Room with dates
    Given user opens "www.booking.com" page and navigate to stays page
    When user searches for "<destination>" and "<destinationState>" with "<checkindate>" and "<checkoutdate>" and Passenger details and page loads with lists of stays available
    When user searches for "<HotelName>" from list and clicks reserve.
    When User landed in reserve page user selects <ApartmentNumber> appartment under "<ApartmentType>" and clicks reserve
    Then user lands on Finish Booking page and verify the price displayed.


    Examples:
      | destination | destinationState | checkindate | checkoutdate | HotelName                            | ApartmentType           | ApartmentNumber |
      | Bangalore   | Karnataka        | 31-May-2025 | 4-June-2025  | Misty Meridian Serviced Apartments   | Three-Bedroom Apartment | 1               |
      | Chennai     | Tamil Nadu       | 2-June-2025 | 5-June-2025  | ibis Chennai Sipcot - An Accor Brand | Standard Room Twin Bed  | 2               |







